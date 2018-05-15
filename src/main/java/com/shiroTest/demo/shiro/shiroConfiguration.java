package com.shiroTest.demo.shiro;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class shiroConfiguration {
    private SecurityManager manager;

    @Bean("credentitalMatcher")
    public CredentitalMatcher credentitalMatcher(){
        return new CredentitalMatcher();
    }

    @Bean("authRealm")
    public AuthRealm authRealm(@Qualifier("credentitalMatcher")CredentitalMatcher credentitalMatcher){
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCredentialsMatcher(credentitalMatcher);
        return authRealm;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm")AuthRealm authRealm){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(authRealm);
        return manager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager")SecurityManager manager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/index");
        bean.setUnauthorizedUrl("/unauthorized");
        LinkedHashMap<String,String> fifterChainDefinitionMap = new LinkedHashMap<>();
        fifterChainDefinitionMap.put("index","authc");
        fifterChainDefinitionMap.put("/login","anon");
        bean.setFilterChainDefinitionMap(fifterChainDefinitionMap);
        return bean;
    }

    //Spring和Shiro之间的配置
    @Bean("advisor")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager")SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }
}
