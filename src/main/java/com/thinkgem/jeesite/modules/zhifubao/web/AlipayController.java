package com.thinkgem.jeesite.modules.zhifubao.web;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.modules.youhuicode.entity.Msg;
import com.thinkgem.jeesite.modules.zhifubao.alipayConfig.AlipayConfig;
import com.thinkgem.jeesite.modules.zhifubao.alipayConfig.AlipaySubmit;
import com.thinkgem.jeesite.modules.zhifubao.entity.NumberInfo;
import com.thinkgem.jeesite.modules.zhifubao.service.NumberInfoService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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




    /**
     *  跳转到授权界面
     */
    @RequestMapping(value = "zhifubao")
    public String save(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Map<String,String> maps = new HashMap<String ,String>();
        //页面回调地址 必须与应用中的设置一样
        System.out.println("开始");
        //String return_url = "http://10.116.204.80:8080/jeesite_war/imf";
        String return_url = "http://10.116.204.80:8080/jeesite_war/touserInfo";

        //回调地址必须经encode
        return_url = java.net.URLEncoder.encode(return_url,"UTF-8");
        System.out.println("成功");
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
            System.out.println(imf);
            model.addAttribute("userId", imf);
//            String imf  =  AlipaySubmit.get(accessToken);
//            String imf1 =imf.substring(200,227);
//            String imf2 =imf.substring(210,226);
//            // imf.alipay_user_info_share_response.user_id;
//            System.out.println("------------------------------------------------");
//            System.out.println("aaaaaaa:"+imf);
//            System.out.println(imf1);
//            System.out.println("user_id:"+imf2);
//            System.out.println("------------------------------------------------");
        }
        return "modules/zhifubao/numberInfo.html";
    }


    @RequestMapping(value = "getNumberInfo")
    public  String getNumberInfo(){
       return "modules/zhifubao/numberInfo.html";
    }


    /*
    保存页面信息到数据库
     */
    @RequestMapping(value = "insertNumberInfo")
    @ResponseBody
    public Msg insertNumberInfo(String userId,String phoneNumber,String psptId){
        Msg msg = null;
        try {
            msg = new Msg();
            NumberInfo numberInfo = new NumberInfo();
            numberInfo.setUserId(userId);
            numberInfo.setPhoneNumber(phoneNumber);
            numberInfo.setPsptId(psptId);
            numberInfo.setOrderTime(new Date());
            numberInfoService.insert(numberInfo);
            msg.setFlg("1");
            msg.setMsgContent("提交成功："+numberInfo.getPhoneNumber());
            return msg;
        }catch(Exception e){
            msg.setFlg("-1");
            msg.setMsgContent("网络异常，请您稍后重试");
            return msg;
        }
    }

    /*
    获取数据库数据（list）转换成json格式
     */
    @RequestMapping(value = "toJSONArray")
    public JSONArray toJSONArray() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMDDhhmmss");

        try {
            List<NumberInfo> numberInfoLists= numberInfoService.getEntity();//获取List
            if(null == numberInfoLists){
                return null;
            }
            JSONArray jsonArray = new JSONArray();
            for(NumberInfo numberInfoList : numberInfoLists){
                JSONObject jo = new JSONObject();
                jo.put("id", numberInfoList.getId());
                jo.put("userId", numberInfoList.getUserId());
                jo.put("psptId", numberInfoList.getPsptId());
                jo.put("orderTime", simpleDateFormat.format(numberInfoList.getOrderTime()));
                jsonArray.add(jo);

            }
            System.out.println(jsonArray.toString());
            return jsonArray;
        } catch (Exception e) {

            e.printStackTrace();// TODO: handle exception
            return null;
        }


    }



}
