package com.thinkgem.jeesite.modules.zhifubao.web;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController

public class EncryptController {
    @Autowired
    private ToJsonController toJsonController;

    private String seed = "jmjf2020_alicamp";

    @RequestMapping(value = "toencrypt")
    public String toencrypt() throws Exception {
        //加密stg为需要加密的方法
        String strd = AesUtil.aesEncrypt(toJsonController.TOJSON(), "@jmltzfbxmfqzyxy");
        return strd;
    }


//    @RequestMapping(value = "toencryptByTime")
//    public String toencryptByTime(@RequestParam(value = "time") String time, @RequestParam(value = "flag") String flag) throws Exception {
//        //加密stg为需要加密的方法
//        String strd = AesUtil.aesEncrypt(toJsonController.tojsonByTime(time,flag), "@jmltzfbxmfqzyxy");
//        return strd;
//    }


    /*
    访问接口加限制：通过约定的token，
    访问接口方式：添加参数并且添加请求的头部（token）
     */
    @RequestMapping(value = "toencryptByTime")
    //AES/CBC/PKCS5PADDING  模式
    public String toencryptByTime(@RequestParam(value = "time") String time, @RequestParam(value = "flag") String flag, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {


        String token = httpServletRequest.getHeader("token");//获取请求的token
        System.out.println("token="+token);

        String mytoken = getToken(seed);//通过约定好的token生成方法生成token
        System.out.println("mytoken="+mytoken);

        //String md5Hex = DigestUtils.md5Hex(test);

        if (token!=null&&token.equals(mytoken)) {//token一致，允许访问接口
            httpServletResponse.setHeader("Content-type",  "text/html;charset=UTF-8");
            httpServletResponse.setCharacterEncoding("UTF-8");
            //httpServletResponse.setContentType("text/html;charset=UTF-8");
            String strd = AesCBC5.encrypt(toJsonController.tojsonByTime(time,flag));
            return strd;
        } else {
           return "非法用户";

        }

//        String strd = AesCBC5.encrypt(toJsonController.tojsonByTime(time,flag));
//        return strd;
    }




    /**
     token的算法：
     seed=jmjf2020_alicamp
     当前日期 20200817 共八个字符，有8个位置
     用当前时=  17%8  = 1 (第1个位置）
     token(md5前）='2' + seed +  '20200817' = 2jmjf2020_alicamp0200817
     token(md5后）=3f41ef8f3ec9a57a112e8cce8dd2707c
     */
    public static String getToken(String key){
//		DateFormat f = new SimpleDateFormat("yyyyMMdd");
//		Date now = new Date();
//		f.format(now);

        //java8 新API https://www.cnblogs.com/ark-blog/p/9694950.html
        LocalDateTime now = LocalDateTime.now();


        String dateStr = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int pos = now.getHour()% dateStr.length();
//		System.out.printf("pos=%d, hour=%d, data len=%d\n", pos, now.getHour(), dateStr.length());

        String str = dateStr.substring(0,pos) + key + dateStr.substring(pos);

//		System.out.println(str);
        return DigestUtils.md5Hex(str);

    }
}
