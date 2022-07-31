package com.xinyet.mybatis.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author via
 * @since 2022-07-31
 */
@Getter
@Setter
@TableName("os_user")
@ApiModel(value = "OsUser对象", description = "")
public class OsUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("用户住址")
    private String address;

    @ApiModelProperty("用户生日")
    private LocalDate birth;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    //@DateTimeFormat(pattern = "yy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    //@DateTimeFormat(pattern = "yy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;


}
