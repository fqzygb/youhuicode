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


	@RequestMapping(value = "getshow")
	public String getshow() {
		//return "modules/youhuicode/code1";
		return "modules/youhuicode/show.html";
	}



	@RequestMapping(value = "getpicture")
	public String getpicture() {
		//return "modules/youhuicode/code1";
		return "modules/youhuicode/picture.html";
	}


	@RequestMapping(value = "getpicturePC")
	public String getpicturePC() {
		//return "modules/youhuicode/code1";
		return "modules/youhuicode/picturePC.html";
	}



	@RequestMapping(value = "getduihuan")
	public String getduihuan() {
		//return "modules/youhuicode/code1";
		return "modules/youhuicode/duihuanliucheng.html";
	}


	@RequestMapping(value = "search")
	public String get(UserInfo userInfo,Model model) {

		//return "modules/youhuicode/code1";
		return "modules/youhuicode/youhuicode.html";
	}

	@RequestMapping(value = "code1")
	@ResponseBody     //返回的不是页面，而是一个对象
	public Msg code(String serial_number,String pspt_id,Model model) {

		//由controller来调用service
		//controller层主要是核心控制层，用来控制请求的。

		//service层进行处理业务逻辑
		//在service层中，加入业务逻辑，判断。
		//dao层主要是用来操作数据库的
		//然在controller调用service。




		/**
		 * 1、先去数据库判断这个用户是否存在
		 *
		 */
        Msg msg = null;
        try{

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

			//写业务
            /**
             * 为了安全起见，不管它有没有查到结果，判断它是否为空
             */
            //flg :0 未领取，1 已领取 ，2 用户信息有误  ，3 活动未开始 ， 4活动已结束 ，5 码已领完,6 其他情况
            if(info != null){

                //判断他的状态

                if(info.getTag().equals("0") || info.getTag().equals("")||info.getTag()==null){
                    //去数据库拿那个码，拿到之后判断是否为空，如果不为空，才可以去修改那个状态
                    //Code code = codeService.getEntity();
                    //Code code = codeService.get();
                    Code codeTag = codeService.getEntityByTag();
                    if(codeTag != null){
                        //if(new Date().before(code.getStartDate()))
                        if (codeTag.getStartDate().after(new Date()))

                        {
                            //活动未开始
                            msg.setFlg("3");
                            msg.setMsgContent("活动还未开始,敬请期待");
                        }else{
                           // if((new Date()).after(code.getEndDate()))
                        if (codeTag.getEndDate().before(new Date()))
                            {
                                //活动已结束
                                msg.setFlg("4");
                                msg.setMsgContent("活动已经结束，感谢支持");
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
                                    msg.setMsgContent("您的优惠码是："+codeTag.getCode());   //码传过去 是ajax的作用
									//model.addAttribute("msg",msg);
                                }else{
                                    //码已领完
                                    msg.setFlg("5");
                                    msg.setMsgContent("您来晚啦，优惠码已领完");
                                }
                            }
                        }

                    }else {
						//其他情况
						msg.setFlg("6");
						msg.setMsgContent("活动在筹备中，敬请期待");
					}
                }else {
                    //领取过了输出一句话，要讲内容显示到前台页面上。
                    //创建一个状态类，里边包含这么几个属性，1、状态值（0表示不成功，1表示成功）。2、内容（就是前台提示的内容）

                    msg.setFlg("1");
                    msg.setMsgContent("您已经领取过了："+info.getCode());     // 把码拿过来

                }
            }else{
                msg.setFlg("2");
                msg.setMsgContent("手机号码或证件号码错误");
            }
            return msg;  //请求成功了，要给ajax一个答复，成功了，就进到ajax那个success里面了，如果失败了，进的就是err那个里面
        }catch (Exception e){
            msg.setFlg("-1");
            msg.setMsgContent("网络异常，请您稍后重试");
            return msg;
        }

	}


}
