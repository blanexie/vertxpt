package com.github.blanexie.vxpt.sso;

import cn.dev33.satoken.config.SaSsoConfig;
import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.sso.SaSsoHandle;
import cn.dev33.satoken.sso.SaSsoUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.ejlchina.okhttps.OkHttps;
import com.github.blanexie.vxpt.auth.api.dto.RoleDTO;
import com.github.blanexie.vxpt.auth.domain.RoleService;
import com.github.blanexie.vxpt.auth.domain.entity.RoleEntity;
import com.github.blanexie.vxpt.auth.domain.factory.RoleFactory;
import com.github.blanexie.vxpt.user.api.dto.AccountDTO;
import com.github.blanexie.vxpt.user.api.dto.UserDTO;
import com.github.blanexie.vxpt.user.domain.entity.AccountEntity;
import com.github.blanexie.vxpt.user.domain.entity.UserEntity;
import com.github.blanexie.vxpt.user.domain.factory.AccountEntityFactory;
import com.github.blanexie.vxpt.user.domain.factory.UserEntityFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * Sa-Token-SSO Server端 Controller
 */
@Slf4j
@RestController
public class SsoServerController {
    @Resource
    private UserEntityFactory userEntityFactory;
    @Resource
    private AccountEntityFactory accountEntityFactory;
    @Resource
    private RoleService roleService;

    @RestControllerAdvice
    public class GlobalExceptionHandler {
        // 全局异常拦截
        @ExceptionHandler
        public SaResult handlerException(Exception e) {
            log.error("", e);
            return SaResult.error(e.getMessage());
        }
    }

    /*
     * SSO-Server端：处理所有SSO相关请求 (下面的章节我们会详细列出开放的接口)
     */
    @RequestMapping("/sso/*")
    public Object ssoRequest() {
        return SaSsoHandle.serverRequest();
    }

    // 自定义接口：获取userinfo
    @RequestMapping("/sso/userinfo")
    public Object userinfo(Integer loginId) {
        log.info("---------------- 获取userinfo --------");
        // 校验签名，防止敏感信息外泄
        SaSsoUtil.checkSign(SaHolder.getRequest());

        UserEntity userEntity = userEntityFactory.findByUserId(loginId);
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setId(userEntity.getId());
        userDTO.setSex(userEntity.getSex());
        userDTO.setNickName(userEntity.getNickName());

        AccountEntity accountEntity = accountEntityFactory.findByUserId(loginId);
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(accountEntity.getId());
        accountDTO.setUserId(accountEntity.getUserId());
        accountDTO.setLevel(accountEntity.getLevel());
        accountDTO.setPoints(accountEntity.getPoints());
        accountDTO.setInvitationCount(accountEntity.getInvitationCount());
        accountDTO.setCompleteCount(accountEntity.getCompleteCount());
        accountDTO.setActiveCount(accountEntity.getActiveCount());
        accountDTO.setDownload(accountEntity.getDownload());
        accountDTO.setUpload(accountEntity.getUpload());
        accountDTO.setCreateTime(accountEntity.getCreateTime());

        RoleDTO roleDTO = roleService.findByRoleCode(userEntity.getRole());

        // 自定义返回结果（模拟）
        return SaResult.ok()
                .set("id", loginId)
                .set("userDTO", userDTO)
                .set("accountDTO", accountDTO)
                .set("roleDTO", roleDTO);

    }

    /**
     * 配置SSO相关参数
     */
    @Autowired
    private void configSso(SaSsoConfig sso) {
        // 配置：未登录时返回的View 
        sso.setNotLoginView(() -> {
            String msg = "当前会话在SSO-Server端尚未登录，请先访问"
                    + "<a href='/sso/doLogin?name=sa&pwd=123456' target='_blank'> doLogin登录 </a>"
                    + "进行登录之后，刷新页面开始授权";
            return msg;
        });

        // 配置：登录处理函数 
        sso.setDoLoginHandle((name, pwd) -> {
            UserEntity userEntity = userEntityFactory.findByNickName(name);
            // 此处仅做模拟登录，真实环境应该查询数据进行登录
            if (userEntity.checkPwd(pwd)) {
                StpUtil.login(userEntity.getId());
                return SaResult.ok("登录成功！").setData(StpUtil.getTokenValue());
            }
            return SaResult.error("登录失败！");
        });

        // 配置 Http 请求处理器 （在模式三的单点注销功能下用到，如不需要可以注释掉） 
        sso.setSendHttp(url -> {
            try {
                // 发起 http 请求 
                System.out.println("发起请求：" + url);
                return OkHttps.sync(url).get().getBody().toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
    }

}
