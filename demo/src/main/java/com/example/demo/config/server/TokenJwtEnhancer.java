package com.example.demo.config.server;

import com.example.demo.domain.SysUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TokenJwtEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {

        SysUser user = (SysUser)oAuth2Authentication.getPrincipal();
        String name = user.getName();
        String phone = user.getPhone();
        String email = user.getEmail();

        Map<String, Object> info = new HashMap<>();

        info.put("name", name);
        info.put("phone", phone);
        info.put("email", email);

        ((DefaultOAuth2AccessToken)oAuth2AccessToken).setAdditionalInformation(info);

        return oAuth2AccessToken;
    }
}
