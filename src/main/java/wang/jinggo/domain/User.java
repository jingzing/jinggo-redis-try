package wang.jinggo.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import wang.jinggo.base.XbootBaseEntity;
import wang.jinggo.common.constant.CommonConstant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author wangyj
 * @description
 * @create 2018-09-19 14:50
 **/
@Data
@Entity
@Table(name = "t_user")
@TableName("t_user")
public class User extends XbootBaseEntity {

    @ApiModelProperty(value = "用户名")
    @Column(unique = true, nullable = false)
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    @Column(name = "nick_name")
    private String nickName;

    @ApiModelProperty(value = "手机")
    private String mobile;

    @ApiModelProperty(value = "邮件")
    private String email;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "0女 1男 2保密")
    private Integer sex;

    @ApiModelProperty(value = "用户头像")
    @Column(length=1000)
    private String avatar = CommonConstant.USER_DEFAULT_AVATAR;

    @ApiModelProperty(value = "用户类型 0普通用户 1管理员")
    private Integer type = CommonConstant.USER_TYPE_NORMAL;

    @ApiModelProperty(value = "状态 默认0正常 -1拉黑")
    private Integer status = CommonConstant.USER_STATUS_NORMAL;

    @ApiModelProperty(value = "描述/详情/备注")
    private String description;

    @ApiModelProperty(value = "所属部门id")
    @Column(name = "department_id")
    private String departmentId;
}