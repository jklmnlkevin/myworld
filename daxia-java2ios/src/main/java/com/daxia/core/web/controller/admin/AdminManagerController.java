package com.daxia.core.web.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.common.Log;
import com.daxia.core.common.ManagerType;
import com.daxia.core.dto.JsonResultDTO;
import com.daxia.core.dto.ManagerDTO;
import com.daxia.core.dto.RoleDTO;
import com.daxia.core.service.ManagerService;
import com.daxia.core.service.RoleService;
import com.daxia.core.support.Page;
import com.daxia.core.util.ValidationUtils;
import com.daxia.core.web.controller.BaseController;

/**
 * 以Admin开头的Controller，都是只为管理后台提供服务的
 */
// @Module(name = "商家管理员模块")
@Controller
@RequestMapping(value = "/admin/manager", produces="text/html;charset=UTF-8") // produces是为了解决@ResponseBody返回乱码
public class AdminManagerController extends BaseController {


	/**
	 * @Autowired 表示自动注入spring管理的bean，不需要写getter和setter就能注入
	 */
	@Autowired
	private ManagerService managerService;
	@Autowired
	private RoleService roleService;
	
	/**
	 * 详情页面。用来获得单个对象的具体信息的
	 * map是用来传值 ，在页面通过${key}的方式就能得到值 。比如map里put("user", dto)，在页面取就是${user.id}这种。
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public String detail(Map<String, Object> map, Long id, Integer type) {
		if (id != null) {
			ManagerDTO dto = managerService.load(id);
			map.put("manager", dto);
		} else {
			map.put("type", type);
		}
		return "admin/core/manager/manager_detail";
	}
	
	@RequestMapping(value = "/detailRole")
	public String detailRole(Map<String, Object> map, Long id) {
		if (id != null) {
			ManagerDTO dto = managerService.load(id);
			map.put("manager", dto);
		}
		
		map.put("roles", roleService.list(new RoleDTO(), null));
		return "admin/core/manager/manager_detailRole";
	}
	
	/**
	 * 如果dto.getId不为空，则为更新，如果为空，则是保存.
	 * @ResponseBody 表示这是一个接收json请求的方式，返回值 不会跳转到哪个页面，返回值为void。
	 * 要传给页面的json值，请调用父类BaseController提供的三个方法。
	 * @param dto
	 */
	@Log(operation = "保存用户") 
	@ResponseBody
	@RequestMapping(value = "/save")
	public String save(ManagerDTO dto, JsonResultDTO resultDTO) throws Exception {
		if (dto.getId() == null) {
			managerService.create(dto);
		} else {
			ManagerDTO model = managerService.load(dto.getId());
			model.setUsername(dto.getUsername());
			model.setType(dto.getType());
			model.setTelephone(dto.getTelephone());
			model.setEmail(dto.getEmail());
			model.setStoreName(dto.getStoreName());
			managerService.updateAllFields(model);
		}
		return okAndClose(resultDTO);
	}
	
	@Log(operation = "保存用户角色") 
	@ResponseBody
	@RequestMapping(value = "/saveRole")
	public String saveRole(Long id, Long[] roleIds, JsonResultDTO resultDTO) throws Exception {
		managerService.saveRole(id, roleIds);
		return okAndClose(resultDTO);
	}

    @Log(operation = "禁用用户")
	@ResponseBody
	@RequestMapping(value = "/disable") 
	public String disable(Long[] ids, JsonResultDTO resultDTO) throws IOException {
		managerService.updateDisabled(ids);
		return okAndRefresh(resultDTO);
	}
    
    @Log(operation = "解禁用户")
	@ResponseBody
	@RequestMapping(value = "/active") 
	public String active(Long[] ids, JsonResultDTO resultDTO) throws IOException {
		managerService.updateActive(ids);
		return okAndRefresh(resultDTO);
	}
	
	@RequestMapping(value = "/list")
	public String list(ManagerDTO dto, Map<String, Object> map, Page page) {
		List<ManagerDTO> dtos = managerService.find(dto, page);
		// 这个数据是用来展示的
		map.put("managers", dtos);
		// 这个数据是保存查询条件的
		map.put("manager", dto);
		// 保存类型
		map.put("typeList", ManagerType.values());
		return "admin/core/manager/manager_list";
	}
	
	@ResponseBody
	@RequestMapping("autocomplete")
	public String autocomplete(String term) {
		/*ManagerDTO dto = new ManagerDTO();
		dto.setTerm(term);
		Page page = new Page();
		page.setNumPerPage(50);
		List<ManagerDTO> users = userService.list(dto, page);
		return toJson(users);*/
		return null;
	}
	
    @RequestMapping("resetPassword")
    public String resetPassword(Map<String, Object> map, Long id) throws Exception {
		if (id != null) {
			ManagerDTO dto = managerService.load(id);
			map.put("manager", dto);
		}
		return "admin/core/manager/manager_resetPassword";
	}
	
	@Log(operation = "重置密码")
	@ResponseBody
    @RequestMapping("doResetPassword")
    public String doResetPassword(Long id, String password, String repassword, JsonResultDTO resultDTO) throws Exception {
		ValidationUtils.assertTrue(StringUtils.isNotBlank(password), "密码不能为空");
		password = password.trim();
		ValidationUtils.assertTrue(password.length() >= 6, "密码不能少于6位数");
		ValidationUtils.assertTrue(password.equals(repassword), "两次密码输入不一致");

		managerService.updatePassword(id, password);
		
		return okAndClose(resultDTO);
	}
	

}
