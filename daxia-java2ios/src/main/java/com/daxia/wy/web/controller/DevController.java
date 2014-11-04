package com.daxia.wy.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.web.controller.BaseController;
import com.daxia.wy.dto.ApiModuleDTO;
import com.daxia.wy.model.ApiTest;
import com.daxia.wy.model.ApiTestParameter;
import com.daxia.wy.service.ApiModuleService;

@Controller
@RequestMapping(value = "/dev", produces="text/html;charset=UTF-8")
public class DevController extends BaseController {
    
    @Autowired
    private ApiModuleService apiModuleService;
    
    @RequestMapping(value = "api2service")
    public String api2service(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws Exception {
        List<ApiModuleDTO> apiModuleDTOs = apiModuleService.find(new ApiModuleDTO(), null);
        List<ApiTest> apiTests = new ArrayList<ApiTest>();
        for (ApiModuleDTO apiModuleDTO : apiModuleDTOs) {
            apiTests.addAll(apiModuleDTO.getApiTests());
        }
        
        for (ApiTest apiTest : apiTests) {
            String url = apiTest.getUrl();
            if (StringUtils.isBlank(url)) {
                continue;
            }
            if (!url.startsWith("m/")) {
                continue;
            }
            String[] arr = url.split("/");
            if (arr.length < 3) {
                continue;
            }
            String module = arr[1];
            String method = arr[2];
            System.out.println(module + "." + method);
            
            List<ApiTestParameter> parameters = apiTest.getApiTestParameters();
            for (ApiTestParameter parameter : parameters) {
                System.out.println(parameter.getName() + ": " + parameter.getDescription());
            }
        }
        
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
