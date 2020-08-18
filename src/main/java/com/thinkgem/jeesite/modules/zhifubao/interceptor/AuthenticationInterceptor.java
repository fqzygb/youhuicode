//package com.thinkgem.jeesite.modules.zhifubao.interceptor;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTDecodeException;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.thinkgem.jeesite.modules.zhifubao.web.Annotations.PassToken;
//import com.thinkgem.jeesite.modules.zhifubao.web.Annotations.UserLoginToken;
//import org.apache.commons.codec.digest.DigestUtils;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.lang.reflect.Method;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//public class AuthenticationInterceptor implements HandlerInterceptor {
//
//    private String seed = "jmjf2020_alicamp";
//
//    @Override
//    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
//       // String token = httpServletRequest.getParameter("token");// 从 http 请求头中取出 token
//        String token = httpServletRequest.getHeader("token");
//        System.out.println("token="+token);
//
//        String mytoken = getToken(seed);
//        System.out.println("mytoken="+mytoken);
//
//        //String md5Hex = DigestUtils.md5Hex(test);
//
//    if (token!=null&&token.equals(mytoken)) {
//        return true;
//    } else {
//        throw new RuntimeException("非法用户");
//
//    }
//
//
//
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest httpServletRequest,
//                           HttpServletResponse httpServletResponse,
//                           Object o, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest httpServletRequest,
//                                HttpServletResponse httpServletResponse,
//                                Object o, Exception e) throws Exception {
//    }
//
//
//
//
//    /**
//     token的算法：
//     seed=jmjf2020_alicamp
//     当前日期 20200817 共八个字符，有8个位置
//     用当前时=  17%8  = 1 (第1个位置）
//
//     token(md5前）='2' + seed +  '20200817' = 2jmjf2020_alicamp0200817
//     token(md5后）=3f41ef8f3ec9a57a112e8cce8dd2707c
//     * @param seed
//     * @author yexy6
//     * @return
//     */
//
//
//    public static String getToken(String key){
////		DateFormat f = new SimpleDateFormat("yyyyMMdd");
////		Date now = new Date();
////		f.format(now);
//
//        //java8 新API https://www.cnblogs.com/ark-blog/p/9694950.html
//        LocalDateTime now = LocalDateTime.now();
//
//
//        String dateStr = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
//        int pos = now.getHour()% dateStr.length();
////		System.out.printf("pos=%d, hour=%d, data len=%d\n", pos, now.getHour(), dateStr.length());
//
//        String str = dateStr.substring(0,pos) + key + dateStr.substring(pos);
//
////		System.out.println(str);
//        return DigestUtils.md5Hex(str);
//
//    }
//
//}
