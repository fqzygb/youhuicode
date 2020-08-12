package com.thinkgem.jeesite.modules.zhifubao.alipayConfig;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;


import java.util.Map;

public class AlipaySubmit {
    /**
     * 获取AccessToken
     * @param sParaTemp
     * @return
     * * 测试地址   https://openapi.alipaydev.com/gateway.do
     * * 正式地址   https://openapi.alipay.com/gateway.do
     */
    public static  String buildRequest(Map sParaTemp){
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, "RSA2");
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setCode((String)sParaTemp.get("auth_code"));
        request.setGrantType("authorization_code");
        try {
            AlipaySystemOauthTokenResponse oauthTokenResponse = alipayClient.execute(request);
            System.out.println(oauthTokenResponse.getAccessToken());
            return oauthTokenResponse.getAccessToken();
        } catch (AlipayApiException e) {
            //处理异常
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取用户信息
     * @param accessToken
     * @return
     */
    public static String get(String accessToken){
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, "RSA2");
        AlipayUserInfoShareRequest request = new AlipayUserInfoShareRequest();

        try {
            AlipayUserInfoShareResponse userinfoShareResponse = alipayClient.execute(request, accessToken);


            System.out.println("------------------------------------------------");
            System.out.print("UserId:" + userinfoShareResponse.getUserId() + "\n");//用户支付宝ID
            System.out.print("NickName:" + userinfoShareResponse.getNickName() + "\n");//用户支付宝昵称
            System.out.print("UserType:" + userinfoShareResponse.getUserType() + "\n");//用户类型
            System.out.print("UserStatus:" + userinfoShareResponse.getUserStatus() + "\n");//用户账户动态
            System.out.print("Email:" + userinfoShareResponse.getEmail() + "\n");//用户Email地址
            System.out.print("IsCertified:" + userinfoShareResponse.getIsCertified() + "\n");//用户是否进行身份认证
            System.out.print("IsStudentCertified:" + userinfoShareResponse.getIsStudentCertified() + "\n");//用户是否进行学生认证

            System.out.println("------------------------------------------------");



           // return  userinfoShareResponse.getBody();
            return userinfoShareResponse.getUserId();

        } catch (AlipayApiException e) {
            //处理异常
            e.printStackTrace();
        }
        return null;
    }
}
