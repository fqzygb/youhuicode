package com.thinkgem.jeesite.modules.zhifubao.web;

import com.thinkgem.jeesite.modules.youhuicode.entity.Msg;
import com.thinkgem.jeesite.modules.zhifubao.alipayConfig.AlipayConfig;
import com.thinkgem.jeesite.modules.zhifubao.alipayConfig.AlipaySubmit;
import com.thinkgem.jeesite.modules.zhifubao.entity.NumberInfo;
import com.thinkgem.jeesite.modules.zhifubao.service.NumberInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller

public class AlipayController {
    @Autowired
    private NumberInfoService numberInfoService;

    private Integer flag = 0;

    /**
     *  跳转到授权界面
     */
    @RequestMapping(value = "zhifubao")
    public String save(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String,String> maps = new HashMap<String ,String>();
        //页面回调地址 必须与应用中的设置一样
        //System.out.println("开始");
        //String return_url = "http://10.116.204.80:8080/jeesite_war/imf";
        //String return_url = "http://10.116.204.80:8080/jeesite_war/touserInfo";
        String return_url = "http://129.204.135.23:8080/jeesite/touserInfo";

        //回调地址必须经encode
        return_url = java.net.URLEncoder.encode(return_url,"UTF-8");
      //  System.out.println("成功");
        //重定向到授权页面
//        https://openauth.alipay.com/oauth2/publicAppAuthorize.htm
       // &scope=auth_user
//        https://openauth.alipaydev.com/oauth2/publicAppAuthorize.htm?app_id=APPID&scope=SCOPE&redirect_uri=ENCODED_URL

        return "redirect:"+ AlipayConfig.ALIPAY_URL+"?app_id=" + AlipayConfig.APP_ID + "&scope=auth_user&redirect_uri=" + return_url;
    }


    /**
     * 获取用户信息
     * @param request
     * @param response
     */
    @RequestMapping(value = "imf")
    public void returnImf(HttpServletRequest request, HttpServletResponse response) {
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == (values.length-1)) ? valueStr + values[i]:valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        String accessToken= AlipaySubmit.buildRequest(params);
        if(accessToken!=null && accessToken!=""){
            String imf  =  AlipaySubmit.get(accessToken);
            String imf1 =imf.substring(200,227);
            String imf2 =imf.substring(210,226);
           // imf.alipay_user_info_share_response.user_id;
            System.out.println("------------------------------------------------");
            System.out.println("aaaaaaa:"+imf);
            System.out.println(imf1);
            System.out.println("user_id:"+imf2);
            System.out.println("------------------------------------------------");
        }
    }

/*
获取支付宝user_id并传到页面
 */
    @RequestMapping(value ="touserInfo" )
    public  String  touserInfo(HttpServletRequest request, HttpServletResponse response,Model model) {

        //获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == (values.length - 1)) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        String accessToken = AlipaySubmit.buildRequest(params);
        if (accessToken != null && accessToken != "") {
            String imf  =  AlipaySubmit.get(accessToken);
           // System.out.println(imf);//支付宝传过来的user_id
            model.addAttribute("userId", imf);
        }
       return "modules/zhifubao/numberInfo.html";
      //  return "modules/zhifubao/index.html";
    }


    @RequestMapping(value = "getNumberInfo")
    public  String getNumberInfo(){

        return "modules/zhifubao/numberInfo.html";
      // return "modules/zhifubao/index.html";
    }


    /*
    保存页面信息到数据库
     */
    @RequestMapping(value = "insertNumberInfo",method = RequestMethod.POST)
    @ResponseBody
    public Msg insertNumberInfo(@RequestParam(value = "userId") String userId, @RequestParam(value ="phoneNumber" ) String phoneNumber, @RequestParam(value = "psptId") String psptId, HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        //String submitTime = httpServletRequest.getParameter("userId");
        System.out.println("userId = " + userId);
        System.out.println("phoneNumber = " + phoneNumber);
        System.out.println("psptId = " + psptId);
       // System.out.println("submitTime = " + submitTime);
        Msg msg  = new Msg();

    if(userId != null && phoneNumber != null && psptId != null) {
        if (userId=="${userId}" ||userId.equals("${userId}" )||"".equals(userId)){
            msg.setFlg("4");
            msg.setMsgContent("请退出，重新授权");
            return msg;

        }

        if ("".equals(phoneNumber)||"".equals(psptId) ){
            msg.setFlg("2");
            msg.setMsgContent("请正确输入信息");
            return msg;
        }


        try {

            NumberInfo numberInfo = new NumberInfo();
            numberInfo.setUserId(userId);
            numberInfo.setPhoneNumber(phoneNumber);
            numberInfo.setPsptId(psptId);
            Date orderTime = new Date();
            String format = simpleDateFormat.format(orderTime);
            numberInfo.setOrderTime(format);
            if (numberInfoService.findByAll(numberInfo)==null ||numberInfoService.findByAll(numberInfo).size()<=0){
                numberInfoService.insert(numberInfo);
                msg.setFlg("1");
                msg.setMsgContent("您的信息我们已收到！我们会对您的信息进行审查，审查完成后我们将于次日早上十点后将江门28元公交乘车券发放至您的支付宝卡包，请及时领取");
                return msg;

            }else {
                msg.setFlg("3");
                msg.setMsgContent("不允许重复提交");
                return msg;
            }
        } catch (Exception e) {
            msg.setFlg("0");
            msg.setMsgContent("网络异常，请您稍后重试");
            return msg;
        }
    }else {
        msg.setFlg("2");
        msg.setMsgContent("请正确输入信息");
        return msg;

    }
    }





}
