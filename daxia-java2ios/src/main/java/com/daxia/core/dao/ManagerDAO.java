package com.daxia.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.daxia.core.dto.ManagerDTO;
import com.daxia.core.model.Manager;
import com.daxia.core.support.Page;

/**
 * dao的实现类必须加上@Repository
 * 继承自GenericDAOHibernate，以重用基本的增删改方法。

 *
 */
@Repository
public class ManagerDAO extends GenericDAOHibernate<Manager> {

    /**
     * 查询方法，传入一个dto是为了灵活构建查询条件，避免创建多个查询方法。
     * page对象是用来分页的，一般可以不用管
     * @param dto
     * @param page
     * @return
     */
    public List<Manager> find(ManagerDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From Manager n where 1 = 1 ");
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }
        if (StringUtils.isNotBlank(dto.getUsername())) {
            hql.append(" and n.username = ? ");
            paras.add(dto.getUsername());
        }
        if (dto.getType() != null) {
            hql.append(" and n.type = ? ");
            paras.add(dto.getType());
        }
        if (StringUtils.isNotBlank(dto.getTelephone())) {
            hql.append(" and n.telephone like ? ");
            paras.add("%" + dto.getTelephone() + "%");
        }
        if (StringUtils.isNotBlank(dto.getEmail())) {
            hql.append(" and n.email like ? ");
            paras.add("%" + dto.getEmail() + "%");
        }
        if (StringUtils.isNotBlank(dto.getStoreName())) {
            hql.append(" and n.storeName like ? ");
            paras.add("%" + dto.getStoreName() + "%");
        }

        return super.find(hql.toString(), paras.toArray(), page);
    }
    
    /**
     * 只找出一条符合条件的记录
     * @param dto
     * @return
     */
    public Manager findOne(ManagerDTO dto) {
		Page page = new Page();
		page.setNumPerPage(1);
		page.setPageNum(1);
		
		List<Manager> list = this.find(dto, page);
	    return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
}
