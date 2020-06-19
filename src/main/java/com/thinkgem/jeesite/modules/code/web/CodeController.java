/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.code.web;

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
import com.thinkgem.jeesite.modules.code.entity.Code;
import com.thinkgem.jeesite.modules.code.service.CodeService;

/**
 * 码Controller
 * @author 冯琪增
 * @version 2020-06-19
 */
@Controller
@RequestMapping(value = "${adminPath}/code/code")
public class CodeController extends BaseController {

	@Autowired
	private CodeService codeService;
	
	@ModelAttribute
	public Code get(@RequestParam(required=false) String id) {
		Code entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = codeService.get(id);
		}
		if (entity == null){
			entity = new Code();
		}
		return entity;
	}
	
	@RequiresPermissions("code:code:view")
	@RequestMapping(value = {"list", ""})
	public String list(Code code, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Code> page = codeService.findPage(new Page<Code>(request, response), code); 
		model.addAttribute("page", page);
		return "modules/code/codeList";
	}

	@RequiresPermissions("code:code:view")
	@RequestMapping(value = "form")
	public String form(Code code, Model model) {
		model.addAttribute("code", code);
		return "modules/code/codeForm";
	}

	@RequiresPermissions("code:code:edit")
	@RequestMapping(value = "save")
	public String save(Code code, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, code)){
			return form(code, model);
		}
		codeService.save(code);
		addMessage(redirectAttributes, "保存码成功");
		return "redirect:"+Global.getAdminPath()+"/code/code/?repage";
	}
	
	@RequiresPermissions("code:code:edit")
	@RequestMapping(value = "delete")
	public String delete(Code code, RedirectAttributes redirectAttributes) {
		codeService.delete(code);
		addMessage(redirectAttributes, "删除码成功");
		return "redirect:"+Global.getAdminPath()+"/code/code/?repage";
	}

}