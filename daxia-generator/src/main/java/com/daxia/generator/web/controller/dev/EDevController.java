package com.daxia.generator.web.controller.dev;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.daxia.generator.config.AppProperties;
import com.daxia.generator.model.AuthorityDAO;
import com.daxia.generator.model.MenuDAO;
import com.daxia.generator.model.RoleAuthorityDAO;
import com.daxia.generator.util.dev.JdbcUtils;
import com.daxia.generator.util.dev.ModelInfo;
import com.daxia.generator.util.e.GenerateType_E;
import com.daxia.generator.util.e.TemplateUtils_E;
import com.google.common.collect.Lists;

@Controller
@RequestMapping(value = "/e/dev", produces="text/html;charset=UTF-8") // produces是为了解决@ResponseBody返回乱码
public class EDevController {
	private static AuthorityDAO authorityDAO = new AuthorityDAO();
	private static RoleAuthorityDAO roleAuthorityDAO = new RoleAuthorityDAO();
	private static MenuDAO menuDAO = new MenuDAO();
	
	// key: dbName
	private Map<String, BasicDataSource> dsMap = new HashMap<String, BasicDataSource>();
	
	@RequestMapping("index")
	public String index(HttpServletRequest request, Map<String, Object> map) {
		return "dev/e/e_dev_index";
	}
	
	@ResponseBody
    @RequestMapping(value = "/generate") 
	public String generate(
		String model,
	    String modelChineseName,                   
	    String parentMenu,                         
	    String menu,                               
	    String db,
	    String tableName,
	    @RequestParam("types[]") String[] types,          
	    @RequestParam("names[]") String[] names,
	    @RequestParam("dbNames[]") String[] dbNames,
	    @RequestParam("comments[]") String[] comments,
	    @RequestParam("shortComments[]") String[] shortComments,
	    @RequestParam("asQueries[]") boolean[] asQueries,    
	    @RequestParam("asLikeQueries[]") boolean[] asLikeQueries,
	    @RequestParam("dateformats[]") String[] dateformats,
	    @RequestParam("queryTypes[]") String[] queryTypes,
	    HttpServletRequest request,
	    HttpServletResponse response,
	    String projectPath, // 项目路径
	    String basePackage // 基本包名，如com.daxia.xxx
	    ) throws Exception {
		
		String _model = model.substring(0, 1).toLowerCase() + model.substring(1);
		String menuUrl = "admin/" + _model + "/list";
		String authorityCode = _model;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("names", names);
		map.put("shortComments", shortComments);
		ModelInfo mf = new ModelInfo();
		mf.setModel(model);
		mf.setModelChineseName(modelChineseName);
		mf.setParentMenu(parentMenu);
		mf.setMenu(menu);
		mf.setMenuUrl(menuUrl);
		mf.setAuthorityCode(authorityCode);
		mf.setTypes(types);
		mf.setNames(names);
		mf.setDbNames(dbNames);
		mf.setShortComments(shortComments);
		mf.setComments(comments);
		mf.setAsQueries(asQueries);
		mf.setAsLikeQueries(asLikeQueries);
		mf.setDateFormats(dateformats);
		mf.setQueryTypes(queryTypes);
		//projectPath, basePackage
		
		map.put("mf", mf);
		map.put("model", _model);
		map.put("Model", model);
		map.put("projectPath", projectPath);
		map.put("basePackage", basePackage);
		map.put("tableName", tableName);
		
		List<String> shortTypes = Lists.newArrayList();
		Set<String> set = new HashSet<String>();
		for (String t : types) {
			shortTypes.add(t.substring(t.lastIndexOf(".") + 1));
			if (!t.startsWith("java.lang.")) {
				set.add(t);
			}
		}
		map.put("shortTypes", shortTypes);
		map.put("imports", set);
		
		List<String> upperNames = Lists.newArrayList();
		for (String n : names) {
	        upperNames.add(n.substring(0, 1).toUpperCase() + n.substring(1));
        }
		map.put("upperNames", upperNames);

		/*Connection connection = dsMap.get(db).getConnection();
		JdbcUtils.connection = connection;*/
		TemplateUtils_E.generate(projectPath, GenerateType_E.values(), map);
        return null;
    }
	
    @RequestMapping("login")
    public String login() throws Exception {
	    return "dev/dev_login";
    }

	@ResponseBody
    @RequestMapping(value = "/getTableInfo")
    public String getTableInfo(String dbName, String dbUsername, String dbPassword, String table) throws Exception {
		Connection conn = getConnection(dbName, dbUsername, dbPassword, table);
		String sql = "select column_name, data_type, column_comment from `information_schema`.`COLUMNS` where TABLE_SCHEMA =  ? AND table_name = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, dbName);
		stmt.setString(2, table);
		ResultSet rs = stmt.executeQuery();
		List<ModelField> fieldList = new ArrayList<ModelField>();
		while (rs.next()) {
			ModelField mf = new ModelField();
			mf.name = rs.getString("column_name");
			mf.comment = rs.getString("column_comment");
			String dataType = rs.getString("data_type");
			mf.type = mysqlJavaDataTypeMapping.get(dataType);
			fieldList.add(mf);
		}
		if (CollectionUtils.isEmpty(fieldList)) {
			throw new RuntimeException("数据库表" + db + "." + table + "不存在");
		}
		
        return JSONArray.toJSONString(fieldList);
    }

    private Connection getConnection(String dbName, String dbUsername, String dbPassword, String table) {
    	DataSource ds = dsMap.get(dbName.toLowerCase());
    	if (ds == null) {
    		initDataSource(dbName, dbUsername, dbPassword);
    		ds = dsMap.get(dbName.toLowerCase());
    	}
    	try {
	        return ds.getConnection();
        } catch (SQLException e) {
        	throw new RuntimeException(e.getMessage(), e);
        }
    }

	private void initDataSource(String dbName, String dbUsername, String dbPassword) {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/" + dbName);
		ds.setUsername(dbUsername);
		ds.setPassword(dbPassword);
		dsMap.put(dbName.toLowerCase(), ds);
    }

	@RequestMapping(value = "query", method = RequestMethod.GET)
    public String query() throws Exception {
	    return "dev/dev_query";
    }
    
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public String querySubmit(String sql, Map<String, Object> map) throws Exception {
    	Connection connection = null; //dataSource.getConnection();
    	map.put("result", JdbcUtils.query(connection, sql));
    	map.put("sql", sql);
	    return "dev/dev_query";
    }
	
	private static Map<String, Class<? extends Object>> mysqlJavaDataTypeMapping = new HashMap<String, Class<? extends Object>>();
	static {
		mysqlJavaDataTypeMapping.put("bigint", Long.class);
		mysqlJavaDataTypeMapping.put("varchar", String.class);
		mysqlJavaDataTypeMapping.put("text", String.class);
		mysqlJavaDataTypeMapping.put("char", String.class);
		mysqlJavaDataTypeMapping.put("int", Integer.class);
		mysqlJavaDataTypeMapping.put("smallint", Integer.class);
		mysqlJavaDataTypeMapping.put("tinyint", Integer.class);
		mysqlJavaDataTypeMapping.put("float", Float.class);
		mysqlJavaDataTypeMapping.put("double", Double.class);
		mysqlJavaDataTypeMapping.put("blob", byte[].class);
		mysqlJavaDataTypeMapping.put("bool", boolean.class);
		mysqlJavaDataTypeMapping.put("boolean", boolean.class);
		mysqlJavaDataTypeMapping.put("datetime", Date.class);
		mysqlJavaDataTypeMapping.put("date", Date.class);
		mysqlJavaDataTypeMapping.put("timestamp", Date.class);
	}
	
	private static String db = AppProperties.get("project.name");
	
}
