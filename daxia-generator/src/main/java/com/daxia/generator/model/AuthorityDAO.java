package com.daxia.generator.model;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

public class AuthorityDAO extends JdbcBaseDAO<Authority> {
	public Authority findByName(String name) {
		String sql = "select * from authority where name = ? ";
		List<Authority> list = super.find(sql, new Object[] {name}, Authority.class);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		} else {
			return list.get(0);
		}
	}
}
