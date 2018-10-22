package wang.jinggo.web.common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wang.jinggo.common.vo.Result;
import wang.jinggo.util.IpInfoUtil;
import wang.jinggo.util.ResultUtil;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@Api(description = "IP接口")
@RequestMapping("/xboot/common/ip")
@Transactional
public class IpInfoController {

    @Autowired
    private IpInfoUtil ipInfoUtil;

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ApiOperation(value = "IP及天气相关信息")
    public Result<Object> upload(HttpServletRequest request) {

    //    String result= ipInfoUtil.getIpWeatherInfo(ipInfoUtil.getIpAddr(request));
        String result= ipInfoUtil.getIpWeatherInfo("183.232.231.173");
        return new ResultUtil<Object>().setData(result);
    }
}
