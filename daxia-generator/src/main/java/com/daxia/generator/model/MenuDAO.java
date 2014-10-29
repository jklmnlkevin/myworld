package com.daxia.generator.model;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

public class MenuDAO extends JdbcBaseDAO<Menu> {
	public Menu findByName(String name) {
		String sql = "select * from menu where name = ? ";
		List<Menu> list = super.find(sql, new Object[] {name}, Menu.class);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		} else {
			return list.get(0);
		}
	}
}
