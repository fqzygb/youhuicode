/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.youhuicode.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.click.entity.ClickTime;
import com.thinkgem.jeesite.modules.click.service.ClickTimeService;
import com.thinkgem.jeesite.modules.code.entity.Code;
import com.thinkgem.jeesite.modules.code.service.CodeService;
import com.thinkgem.jeesite.modules.youhuicode.entity.Msg;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.youhuicode.entity.UserInfo;
import com.thinkgem.jeesite.modules.youhuicode.service.UserInfoService;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 优惠码Controller
 * @author 冯琪增
 * @version 2020-06-18
 */
@Controller
@RequestMapping(value = "${adminPath}/youhuicode/userInfo")
public class UserInfoController extends BaseController {

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private CodeService codeService;

	@Autowired
	private ClickTimeService clickTimeService;

	@ModelAttribute
	public UserInfo get(@RequestParam(required=false) String id) {
		UserInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = userInfoService.get(id);
		}
		if (entity == null){
			entity = new UserInfo();
		}
		return entity;
	}

	@RequiresPermissions("youhuicode:userIfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(UserInfo userInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<UserInfo> page = userInfoService.findPage(new Page<UserInfo>(request, response), userInfo);
		model.addAttribute("page", page);
		return "modules/youhuicode/userInfoList";
	}

	@RequiresPermissions("youhuicode:userInfo:view")
	@RequestMapping(value = "form")
	public String form(UserInfo userInfo, Model model) {
		model.addAttribute("userInfo", userInfo);
		return "modules/youhuicode/userInfoForm";
	}

	@RequiresPermissions("youhuicode:userInfo:edit")
	@RequestMapping(value = "save")
	public String save(UserInfo userInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, userInfo)){
			return form(userInfo, model);
		}
		userInfoService.save(userInfo);
		addMessage(redirectAttributes, "保存领取优惠码成功");
		return "redirect:"+Global.getAdminPath()+"/youhuicode/userInfo/?repage";
	}

	@RequiresPermissions("youhuicode:userInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(UserInfo userInfo, RedirectAttributes redirectAttributes) {
		userInfoService.delete(userInfo);
		addMessage(redirectAttributes, "删除领取优惠码成功");
		return "redirect:"+Global.getAdminPath()+"/youhuicode/userInfo/?repage";
	}



	@RequestMapping(value = "search")
	public String get(UserInfo userInfo,Model model) {
		model.addAttribute("serialName",userInfo.getSerialNumber());
		model.addAttribute("psptId",userInfo.getPsptId());

		//return "modules/youhuicode/code1";
		return "modules/youhuicode/youhuicode.html";
	}

	@RequestMapping(value = "code1")
	@ResponseBody     //加了这个注解，你返回的就不在是页面，而是一个对象？明白？嗯
	public Msg code(String serial_number,String pspt_id,Model model) {

		//1、现在的请求不是到这了吗，接下来是不是应该由controller来调用service来，
		//2、为什么要有service层，让controller简洁？controller层主要是核心控制层，用来控制请求的。
		// 那操作让其他来？   你的请求过来肯定是要处理业务逻辑的，是不是。是的
		//所以说要在service层进行处理业务逻辑 明白。
		//所以说接下来就要去调用service层，在service层中，你可以加入你的业务逻辑，判断。
		//最后是在dao层。dao层主要是用来操作数据库的，明白？明白
		//然后就是在controller调用service，是的。
		//那你刚刚和我说的找博客是这样的饿博客吗？
		//是的好的


		/**
		 * 1、先去数据库判断这个用户是否存在
		 *	这个明白吗OK
		 */
        Msg msg = null;
        try{
        	//这里很本来是拿着页面的那两个数据去查询数据库的，拿你这里new一个date，有什么意义
			//haode你改吧

            UserInfo userInfo = new UserInfo();
            userInfo.setSerialNumber(serial_number);
            userInfo.setPsptId(pspt_id);

            UserInfo info = userInfoService.getUserInfo(userInfo);
            msg = new Msg();
            Random random = new Random();
            //String string = random.toString();


			ClickTime clickTime = new ClickTime();
			String id = UUID.randomUUID().toString();
			clickTime.setId(id);
			clickTime.setSerialNumber(serial_number);
			clickTime.setPsptId(pspt_id);
			clickTime.setClicktime(new Date());
			//clickTime.setId(random.toString());
			if(clickTime != null){
				clickTimeService.insert(clickTime);
			}

			//现在开始写业务
            /**
             * 首先查询出结果了，为了安全起见，你不管他有没有查到结果，你都要去判断他是否为空
             */
            //flg :0 未领取，1 已领取 ，2 用户信息有误  ，3 活动未开始 ， 4活动已结束 ，5 码已领完
            if(info != null){

                //接下来你去判断他的状态，你来写
                //这样不是不为空了吗 然后你要判断什么就自己写
                if(info.getTag().equals("0")){
                    //这里你首先也要去数据库拿那个码，拿到之后判断是否为空，如果不为空，你才可以去修改那个状态。明白？OK
                    Code code = codeService.getEntity();
                    //Code code = codeService.get();
                    Code codeTag = codeService.getEntityByTag();
                    if(code != null){
                        if(new Date().before(code.getStartDate())){
                            //活动未开始
                            msg.setFlg("3");
                            msg.setMsgContent("活动未开始");
                        }else{
                            if((new Date()).after(code.getEndDate())){
                                //活动已结束
                                msg.setFlg("4");
                                msg.setMsgContent("活动已结束");
                            }else{
                                if(codeTag != null){
                                    //未领取
                                    info.setTag("1");
                                    info.setCode(codeTag.getCode());
									//
									info.setReceiveTime(new Date());
                                    codeTag.setTag("1");
                                    userInfoService.update(info);
									//userInfoService.save(info);
                                    codeService.update(codeTag);
                                    msg.setFlg("0");
                                    msg.setMsgContent(codeTag.getCode());   //这里你也同样吧那个码传过去 明白？是的，刚才那里就是ajax的作用？
									//model.addAttribute("msg",msg);
                                }else{
                                    //码已领完
                                    msg.setFlg("5");
                                    msg.setMsgContent("优惠码已领完");
                                }
                            }
                        }

                    }
                }else {
                    //这里，领取过了之后，不是这样输出一句话，而是要讲内容显示到前台页面上。
                    //所以我建议你再创建一个状态类，里边包含这么几个属性，1、状态值（0表示不成功，1表示成功）。2、内容（就是前台提示的内容）
                    //你比如说，号码不是江门移动的，内容已经领取过了这样之类的。明白？OK先去创建一个类Msg
                    msg.setFlg("1");
                    msg.setMsgContent("您已经领取过了："+info.getCode());     // 这里你可以把他的那个码拿过来

                }
            }else{
                msg.setFlg("2");
                msg.setMsgContent("请您正确输入江门联通号码及开户证件号码");
            }
            return msg;  //最终你请求成功了，要给ajax一个答复，你成功了，就进到ajax那个success里面了，如果失败了，进的就是err那个里面噢噢
        }catch (Exception e){
            msg.setFlg("-1");
            msg.setMsgContent("系统发生未知异常，请联系管理员");
            return msg;
        }

	}


}
