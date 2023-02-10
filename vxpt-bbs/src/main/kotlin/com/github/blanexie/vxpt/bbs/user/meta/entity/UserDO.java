package com.github.blanexie.vxpt.bbs.user.meta.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 2023/2/10 3:47 下午
 *
 * @author xiezhicheng
 */
@Data
@TableName("user")
public class UserDO {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String email;
    private String nickName;
    private String password;
    private String coverImg;
    private Integer sex;
    private String role;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
