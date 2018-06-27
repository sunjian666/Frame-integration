package com.example.demo.security;

import com.example.demo.social.support.MySpringSocialConfigurer;
import com.example.demo.util.ConstantUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;


/**
 * 所有的初始化之前和之后都要经过这个方法
 * @author zhailiang
 *
 */
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(StringUtils.equals(beanName, "SocialSecurityConfig")){
            MySpringSocialConfigurer config = (MySpringSocialConfigurer)bean;
            config.signupUrl(ConstantUtil.DEFAULT_SOCIAL_USER_INFO_URL);
            return config;
        }
        return bean;
    }
}
