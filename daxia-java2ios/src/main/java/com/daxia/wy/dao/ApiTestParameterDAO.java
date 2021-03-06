package com.daxia.wy.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.daxia.core.dao.GenericDAOHibernate;
import com.daxia.core.support.Page;
import com.daxia.wy.dto.ApiTestParameterDTO;
import com.daxia.wy.model.ApiTestParameter;

/**
 * dao的实现类必须加上@Repository
 * 继承自GenericDAOHibernate，以重用基本的增删改方法。

 *
 */
@Repository
public class ApiTestParameterDAO extends GenericDAOHibernate<ApiTestParameter> {

    /**
     * 查询方法，传入一个dto是为了灵活构建查询条件，避免创建多个查询方法。
     * page对象是用来分页的，一般可以不用管
     * @param dto
     * @param page
     * @return
     */
    public List<ApiTestParameter> find(ApiTestParameterDTO dto, Page page) {
        StringBuilder hql = new StringBuilder();
        List<Object> paras = new ArrayList<Object>();
        hql.append("From ApiTestParameter n where 1 = 1 ");
        if (dto.getId() != null) {
            hql.append(" and n.id = ? ");
            paras.add(dto.getId());
        }
        if (dto.getApiTest() != null) {
        	if (dto.getApiTest().getId() != null) {
	            hql.append(" and n.apiTest.id = ? ");
	            paras.add(dto.getApiTest().getId());
        	}
        }			
        return super.find(hql.toString(), paras.toArray(), page);
    }
    
    /**
     * 只找出一条符合条件的记录
     * @param dto
     * @return
     */
    public ApiTestParameter findOne(ApiTestParameterDTO dto) {
		Page page = new Page();
		page.setNumPerPage(1);
		page.setPageNum(1);
		
		List<ApiTestParameter> list = this.find(dto, page);
	    return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    public void deleteByApiTestId(Long id) {
        String hql = "delete from ApiTestParameter where apiTest.id = ?";
        super.executeUpdate(hql, new Object[] {id});
    }
}
