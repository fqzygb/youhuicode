/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.click.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.click.entity.ClickTime;
import com.thinkgem.jeesite.modules.click.service.ClickTimeService;

/**
 * 点击时间Controller
 * @author 冯琪增
 * @version 2020-06-20
 */
@Controller
@RequestMapping(value = "${adminPath}/click/clickTime")
public class ClickTimeController extends BaseController {

	@Autowired
	private ClickTimeService clickTimeService;
	
	@ModelAttribute
	public ClickTime get(@RequestParam(required=false) String id) {
		ClickTime entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = clickTimeService.get(id);
		}
		if (entity == null){
			entity = new ClickTime();
		}
		return entity;
	}
	
	@RequiresPermissions("click:clickTime:view")
	@RequestMapping(value = {"list", ""})
	public String list(ClickTime clickTime, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ClickTime> page = clickTimeService.findPage(new Page<ClickTime>(request, response), clickTime); 
		model.addAttribute("page", page);
		return "modules/click/clickTimeList";
	}

	@RequiresPermissions("click:clickTime:view")
	@RequestMapping(value = "form")
	public String form(ClickTime clickTime, Model model) {
		model.addAttribute("clickTime", clickTime);
		return "modules/click/clickTimeForm";
	}

	@RequiresPermissions("click:clickTime:edit")
	@RequestMapping(value = "save")
	public String save(ClickTime clickTime, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, clickTime)){
			return form(clickTime, model);
		}
		clickTimeService.save(clickTime);
		addMessage(redirectAttributes, "保存纪录成功成功");
		return "redirect:"+Global.getAdminPath()+"/click/clickTime/?repage";
	}
	
	@RequiresPermissions("click:clickTime:edit")
	@RequestMapping(value = "delete")
	public String delete(ClickTime clickTime, RedirectAttributes redirectAttributes) {
		clickTimeService.delete(clickTime);
		addMessage(redirectAttributes, "删除纪录成功成功");
		return "redirect:"+Global.getAdminPath()+"/click/clickTime/?repage";
	}

}