package com.thinkgem.jeesite.modules.youhuicode.interceptor;

import com.thinkgem.jeesite.modules.urlcount.entity.Count;
import com.thinkgem.jeesite.modules.urlcount.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class UserInterceptor  extends HandlerInterceptorAdapter {
    private String urlPC = "/a/youhuicode/userInfo/getpicturePC";
    private String url = "/a/youhuicode/userInfo/getpicture";
    private Integer countURL =0;
    private  Integer countPC =0;
    private String userIP;

    private Integer urlCount;
    @Autowired
    private CountService countService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("aaaa");
//        System.out.println(request.getRequestURI());
//        System.out.println("aaaa");
       System.out.println(request.getServletPath());
//        System.out.println("aaaa");
//       System.out.println(request.getSession());
//        System.out.println("aaaa");
//        System.out.println(request.getPathInfo());
//        System.out.println("aaaa");
//        System.out.println(request.getPathInfo());



       //  从 HTTP 头中取得 Referer 值
             //   String YuMing = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
             //   + request.getContextPath() +request.getServletPath();
       // System.out.println("getScheme:" + request.getScheme());
     //   System.out.println(YuMing);
//                System.out.println("getContextPath:"+request.getContextPath());
//               System.out.println("getServerPort:"+request.getServerPort());
//        System.out.println("getServerName:" + request.getServerName());
//        String referer = request.getHeader("Referer");
//        // 判断 Referer 是否以 bank.example 开头
//
//        //        startWith(referer, YuMing);
//        if (referer != null && !referer.trim().contains("login.action")) {
//            if (!(referer.trim().contains(request.getServerName()))) {
//                request.getRequestDispatcher("error.jsp").forward(request, response);
//                return false;
//            }
//        }
        Count count = new Count();
        UserInterceptor userInterceptor = new UserInterceptor();
        userIP=userInterceptor.getRemortIP(request);
        System.out.println(userIP);

        count.setIpAddress(userIP);
        count.setIpUrl(request.getServletPath());
        count.setInsertTime(new Date());
        count.setReMarks("0");
        countService.insert(count);


        System.out.println(countService.getCountByUrl(url));
        System.out.println(countService.getCountByUrl(urlPC));

        if(countService.getCountByUrl(url)!=null && countService.getCountByUrl(urlPC)!=null){
            countURL = countService.getCountByUrl(url);
            countPC = countService.getCountByUrl(urlPC);
            urlCount = countURL + countPC;
        }else if (countService.getCountByUrl(url)!=null && countService.getCountByUrl(urlPC)==null){
            countURL = countService.getCountByUrl(url);
            countPC = 0;
            urlCount = countURL + countPC;
        }else if (countService.getCountByUrl(url)==null && countService.getCountByUrl(urlPC)!=null){
            countURL = 0;
            countPC = countService.getCountByUrl(urlPC);
            urlCount = countURL + countPC;
        }else {
            urlCount = 0;
        }





       System.out.println("访问次数："+urlCount);


        return true;
    }

    /**
     * 获取登录用户的IP
     * @throws Exception
     */
    public String  getRemortIP(HttpServletRequest request) throws Exception {
        String ip = "";
        if (request.getHeader("x-forwarded-for") == null) {
            ip = request.getRemoteAddr();
        }else{
            ip = request.getHeader("x-forwarded-for");
        }
        return ip;
    }
}
