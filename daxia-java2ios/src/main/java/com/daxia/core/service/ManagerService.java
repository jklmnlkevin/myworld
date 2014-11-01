package com.daxia.core.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daxia.core.common.UserType;
import com.daxia.core.dao.ManagerDAO;
import com.daxia.core.dao.RoleDAO;
import com.daxia.core.dto.ManagerDTO;
import com.daxia.core.model.Manager;
import com.daxia.core.model.Role;
import com.daxia.core.security.PasswordEncoder;
import com.daxia.core.support.Page;
import com.daxia.core.util.BeanMapper;
import com.daxia.core.util.ValidationUtils;

/**
 * Service层，类要加@Service标识 一般都是由service层操作数据库， 并且，只有save, update,
 * delete等开头的方法，才有新增修改删除数据库表的权限，
 * 其它的方法名只有查询的权限，这是事务管理，具体哪些方法名可以指定这种权限，是在applicationContext
 * .xml文件的txAdvice定义处配置。
 * 

 * 
 */
@Service
public class ManagerService {

	@Autowired
	private ManagerDAO managerDAO;

	/**
	 * 根据dto的查询条件和分页条件查找记录
	 * 
	 * @param dto
	 * @param page
	 * @return
	 */
	public List<ManagerDTO> find(ManagerDTO dto, Page page) {
		List<Manager> models = managerDAO.find(dto, page);
		List<ManagerDTO> dtos = toDTOs(models);
		return dtos;
	}

	/**
	 * 将model集合转为dto集合
	 * 
	 * @param models
	 * @return
	 */
	private List<ManagerDTO> toDTOs(List<Manager> models) {
		if (CollectionUtils.isEmpty(models)) {
			return new ArrayList<ManagerDTO>(0);
		}
		List<ManagerDTO> dtos = new ArrayList<ManagerDTO>(models.size());
		for (Manager model : models) {
			ManagerDTO dto = toDTO(model);
			dtos.add(dto);
		}
		return dtos;
	}

	/**
	 * 将model转为dto。属性名和类型相同的部分调用BeanMapper.map()方法，不同的单独转换。
	 * 
	 * @param model
	 * @return
	 */
	private ManagerDTO toDTO(Manager model) {
		if (model == null) {
			return null;
		}
		ManagerDTO dto = BeanMapper.map(model, ManagerDTO.class);

		return dto;
	}

	public void create(ManagerDTO dto) {
		ValidationUtils.assertTrue(StringUtils.isNotBlank(dto.getPassword()), "密码不能为空");
		ValidationUtils.assertTrue(dto.getPassword().trim().length() >= 6, "密码不能少于6位数");
		String reg = "^[a-z0-9]{3,15}$";
		ValidationUtils.assertTrue(Pattern.matches(reg, dto.getUsername()), "用户名不合法，用户名只能是用字母和数字组成，最少3位，最多15位");
		Manager model = new Manager();
		toModel(model, dto);
		model.setPassword(passwordEncoder.encodePassword(model.getPassword(), model.getUsername()));
		managerDAO.create(model);
	}

	private void toModel(Manager model, ManagerDTO dto) {
		BeanMapper.copy(dto, model);
	}

	@SuppressWarnings("unused")
	private List<Manager> toModels(List<ManagerDTO> dtos) {
		if (CollectionUtils.isEmpty(dtos)) {
			return new ArrayList<Manager>(0);
		}
		List<Manager> models = new ArrayList<Manager>(dtos.size());
		for (ManagerDTO dto : dtos) {
			Manager model = new Manager();
			toModel(model, dto);
			models.add(model);
		}
		return models;
	}

	public ManagerDTO load(Long id) {
		Manager model = managerDAO.load(id);
		return toDTO(model);
	}

	/**
	 * 这个方法会把dto所有的字段都赋给model，然后更新model。
	 * 
	 * @param dto
	 */
	public void updateAllFields(ManagerDTO dto) {
		Manager model = managerDAO.load(dto.getId());
		toModel(model, dto);
		managerDAO.update(model);
	}

	public void deleteByIds(Long[] ids) {
		if (ids != null) {
			for (Long id : ids) {
				managerDAO.deleteById(id);
			}
		}
	}

	public void deleteById(Long id) {
		this.deleteByIds(new Long[] { id });
	}

	public ManagerDTO findOne(ManagerDTO dto) {
		Manager model = managerDAO.findOne(dto);
		return toDTO(model);
	}

	public void saveRole(Long id, Long[] roleIds) {
		Manager user = managerDAO.load(id);
		Set<Role> roleSet = new HashSet<Role>();
		for (Long roleId : roleIds) {
			roleSet.add(roleDAO.load(roleId));
		}
		user.setRoles(roleSet);
		managerDAO.update(user);
	}

	@Autowired
	private RoleDAO roleDAO;

	public void updateDisabled(Long[] ids) {
		for (Long id : ids) {
			Manager user = managerDAO.load(id);
			user.setType(UserType.Disabled.value());
			managerDAO.update(user);
		}
	}

	public void updateActive(Long[] ids) {
		for (Long id : ids) {
			Manager user = managerDAO.load(id);
			user.setType(UserType.Normal.value());
			managerDAO.update(user);
		}
	}

		public void updatePassword(Long id, String password) {
			Manager user = managerDAO.load(id);
			password = passwordEncoder.encodePassword(password, user.getUsername());
			
			user.setPassword(password);
			managerDAO.update(user);
	    }
		@Autowired
		private PasswordEncoder passwordEncoder;
}
