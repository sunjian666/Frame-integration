package com.example.demo.util;

public class ConstantUtil {

    public static final String USE = "0";  //正在使用

    public static final String DELETED = "1";  //已删除

    /**
     * 当请求需要身份认证时，默认跳转的url
     *
     */
    public static final String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";
    /**
     * 默认的用户名密码登录请求处理url
     */
    public static final String DEFAULT_SIGN_IN_PROCESSING_URL_FORM = "/authentication/form";

    /**
     * 默认的手机验证码登录请求处理url
     */
    public static final String DEFAULT_SIGN_IN_PROCESSING_URL_MOBILE = "/authentication/mobile";

    /**
     * 默认的OPENID登录请求处理url
     */
    public static final String DEFAULT_SIGN_IN_PROCESSING_URL_OPENID = "/authentication/openid";

    /**
     * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";
    /**
     * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";

    /**
     * 发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";

    /**
     * 默认的处理验证码的url前缀
     */
    public static final String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";

    /**
     * openid参数名
     */
    public static final String DEFAULT_PARAMETER_NAME_OPENID = "openId";
    /**
     * providerId参数名
     */
    public static final String DEFAULT_PARAMETER_NAME_PROVIDERID = "providerId";

    /**
     * 获取第三方用户信息的url
     */
    public static final String DEFAULT_SOCIAL_USER_INFO_URL = "/social/signUp";


}
