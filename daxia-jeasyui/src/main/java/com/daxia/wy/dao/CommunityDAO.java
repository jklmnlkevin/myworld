package com.daxia.wy.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.daxia.core.dao.GenericDAOHibernate;
import com.daxia.core.support.Page;
import com.daxia.wy.dto.CommunityDTO;
import com.daxia.wy.model.Community;

/**
 * dao的实现类必须加上@Repository
 * 继承自GenericDAOHibernate，以重用基本的增删改方法。
 * @author Kewen.Zhu
 *
 */
@Repository
public class CommunityDAO extends GenericDAOHibernate<Community> {

    /**
     * 查询方法，传入一个dto是为了灵活构建查询条件，避免创建多个查询方法。
     * page对象是用来分页的，一般可以不用管
     * @param dto
     * @param page
     * @return
     */
    public List<Community> find(CommunityDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From Community n where 1 = 1 ");
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }
        if (StringUtils.isNotBlank(dto.getName())) {
            hql.append(" and n.name like ? ");
            paras.add("%" + dto.getName() + "%");
        }					
        if (StringUtils.isNotBlank(dto.getAddress())) {
            hql.append(" and n.address like ? ");
            paras.add("%" + dto.getAddress() + "%");
        }					
        if (dto.getProvince() != null) {
            if (dto.getProvince().getId() != null) {
                hql.append(" and n.province.id = ? ");
                paras.add(dto.getProvince().getId());
            }
            if (StringUtils.isNotBlank(dto.getProvince().getName())) {
                hql.append(" and n.province.name = ? ");
                paras.add(dto.getProvince().getName());
            }
        }
        if (dto.getCity() != null) {
            if (dto.getCity().getId() != null) {
                hql.append(" and n.city.id = ? ");
                paras.add(dto.getCity().getId());
            }
            if (StringUtils.isNotBlank(dto.getCity().getName())) {
                hql.append(" and n.city.name = ? ");
                paras.add(dto.getCity().getName());
            }
        }
        if (dto.getDistrict() != null) {
            if (dto.getDistrict().getId() != null) {
                hql.append(" and n.district.id = ? ");
                paras.add(dto.getDistrict().getId());
            }
            if (StringUtils.isNotBlank(dto.getDistrict().getName())) {
                hql.append(" and n.district.name = ? ");
                paras.add(dto.getDistrict().getName());
            }
        }
        return super.find(hql.toString(), paras.toArray(), page);
    }
    
    /**
     * 只找出一条符合条件的记录
     * @param dto
     * @return
     */
    public Community findOne(CommunityDTO dto) {
		Page page = new Page();
		page.setNumPerPage(1);
		page.setPageNum(1);
		
		List<Community> list = this.find(dto, page);
	    return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
}
