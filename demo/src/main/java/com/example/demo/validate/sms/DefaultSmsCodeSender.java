package com.example.demo.validate.sms;

import com.example.demo.validate.sms.SmsCodeSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 默认的短信验证码发送器
 *
 * @author zhailiang
 *
 */
@Component
public class DefaultSmsCodeSender implements SmsCodeSender {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void send(String mobile, String code) {
        logger.info("向手机"+mobile+"发送短信验证码"+code);
    }
}
