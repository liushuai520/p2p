package com.zking.p2p.ls.shiro;

import com.zking.ssm.model.SysUser;
import com.zking.ssm.service.ISysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @company Admin
 * @create 2019-11-3 09:07
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username=principalCollection.getPrimaryPrincipal().toString();
        //根据username获取角色
        Set<String> roles = sysUserService.findRoles(username);
        //根据username获取权限
        Set<String> permissions = sysUserService.findPermissions(username);

        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取登录身份凭证信息
        String username = authenticationToken.getPrincipal().toString();
        String password = authenticationToken.getCredentials().toString();

        //调用service层方法进行登录身份验证
        SysUser sysUser = sysUserService.userLogin(username);
        if (null == sysUser)
            throw new UnknownAccountException();

        SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo(
                sysUser.getUsername(),
                sysUser.getPassword(),
                ByteSource.Util.bytes(sysUser.getSalt()),
                this.getName()
        );
        return simpleAuthenticationInfo;
    }
}
