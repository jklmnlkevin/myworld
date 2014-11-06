package com.daxia.wy.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.web.controller.BaseController;
import com.daxia.wy.dto.ApiModuleDTO;
import com.daxia.wy.service.ApiModuleService;

@Controller
@RequestMapping(value = "/dev", produces="text/html;charset=UTF-8")
public class DevController extends BaseController {
    
    @Autowired
    private ApiModuleService apiModuleService;
    
    @RequestMapping(value = "api2service")
    public String api2service(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws Exception {
        List<ApiModuleDTO> apiModuleDTOs = apiModuleService.find(new ApiModuleDTO(), null);
        Api2ServiceUtils.api2service(apiModuleDTOs);
        return "";
    }
    
	@RequestMapping(value = "api")
	public String upload(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws Exception {
	    map.put("apiModules", apiModuleService.find(new ApiModuleDTO(), null));
	    return "dev/dev_api";
	}
	
	@ResponseBody
	@RequestMapping(value = "java2ios")
	public String java2ios() throws Exception {
	    Java2iosUtils.java2ios();
	    return "Done";
	}
	
	public static void main(String[] args) throws Exception {
        new DevController().java2ios();
    }
	
}
