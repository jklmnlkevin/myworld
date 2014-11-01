package com.daxia.wy.web.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.daxia.core.web.controller.BaseController;
import com.daxia.wy.dto.ApiModuleDTO;
import com.daxia.wy.model.ApiTest;
import com.daxia.wy.model.ApiTestParameter;
import com.daxia.wy.service.ApiModuleService;

@Controller
@RequestMapping(value = "/dev", produces="text/html;charset=UTF-8")
public class DevController extends BaseController {
    private static Map<String, List<ModuleField>> moduleFieldMap = new HashMap<String, List<ModuleField>>();
    private static Set<String> invalidModules = new HashSet<String>();
    static {
        invalidModules.add("Base");
        invalidModules.add("BaseAPIOutput");
        invalidModules.add("City");
        invalidModules.add("District");
        
        invalidModules.add("MobileApiOutput");
        invalidModules.add("info");
        invalidModules.add("Order2");
        invalidModules.add("Province");
        invalidModules.add("SystemConfig");
        invalidModules.add("UserSimple");
        invalidModules.add("UserSimple2");
    }
    
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
	
	@RequestMapping(value = "java2ios")
	public String java2ios() {
	    String path = "/Users/kevin/code/yc_java/trunk/src/main/java";
	    String apiDTOPath = path + "/com/daxia/wy/dto/api";
	    
	    File[] dtos = new File(apiDTOPath).listFiles();
	    for (File dto : dtos) {
            String moduleName = dto.getName();
            moduleName = moduleName.replace(".java", "");
            moduleName = moduleName.replace("APIDTO", "");
            if (invalidModules.contains(moduleName)) {
                continue;
            }
            
            List<ModuleField> moduleFields = ModuleFieldUtils.parseFields(dto);
            moduleFieldMap.put(moduleName, moduleFields);
            System.out.println("models.add(\"" + moduleName + "\");");
        }
	             
	    return null;
	}
	
	public static void main(String[] args) {
        new DevController().java2ios();
    }
	
}
