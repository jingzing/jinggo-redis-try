package wang.jinggo.web.mine;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import wang.jinggo.common.constant.CommonConstant;
import wang.jinggo.common.vo.PageVo;
import wang.jinggo.common.vo.Result;
import wang.jinggo.domain.MusicLrc;
import wang.jinggo.domain.TimeAxis;
import wang.jinggo.pojo.SearchVo;
import wang.jinggo.service.MusicService;
import wang.jinggo.util.PageUtil;
import wang.jinggo.util.ResultUtil;

import java.util.Date;

/**
 * 时间轴管理
 * @author wangyj
 * @description
 * @create 2018-10-23 17:39
 **/

@Slf4j
@RestController
@Api(description = "时间轴管理接口")
@RequestMapping("/xboot/music")
@Transactional
public class MusicController {

    private final static Logger LOG =  LoggerFactory.getLogger(MusicController.class);

    @Autowired
    MusicService musicService;

    @RequestMapping(value = "/getByCondition",method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取内容列表")
    public Result<Page<MusicLrc>> getByCondition(SearchVo searchVo, @ModelAttribute PageVo pageVo){

        LOG.info("====>>> " + searchVo.toString());
        Page<MusicLrc> page = musicService.findByCondition(searchVo, PageUtil.initPage(pageVo));
        return new ResultUtil<Page<MusicLrc>>().setData(page);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "添加歌曲")
    public Result<Object> addMusic(@ModelAttribute MusicLrc musicLrc){
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        musicLrc.setCreateBy(user.getUsername());
        musicLrc.setCreateTime(new Date());
        musicLrc.setUpdateBy("");
        musicLrc.setStatus(CommonConstant.STATUS_NORMAL);
        musicService.save(musicLrc);
        return new ResultUtil<Object>().setSuccessMsg("添加歌曲成功");
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ApiOperation(value = "修改歌曲")
    public Result<Object> editTime(@ModelAttribute MusicLrc musicLrc){
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        musicLrc.setUpdateBy(user.getUsername());
        musicLrc.setUpdateTime(new Date());
        musicService.update(musicLrc);
        return new ResultUtil<Object>().setSuccessMsg("修改歌曲成功");
    }

    @RequestMapping(value = "/disable/{id}",method = RequestMethod.POST)
    @ApiOperation(value = "后台禁用歌曲")
    public Result<Object> disable(@ApiParam("歌曲唯一id标识") @PathVariable String id){

        MusicLrc musicLrc = musicService.get(id);
        if(musicLrc==null){
            return new ResultUtil<Object>().setErrorMsg("通过id获取歌曲失败");
        }
        musicLrc.setStatus(CommonConstant.USER_STATUS_LOCK);
        musicService.update(musicLrc);
        return new ResultUtil<Object>().setData("禁用歌曲成功");
    }
}
