package com.daxia.wy.web.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.select.Evaluator.IsEmpty;

import com.daxia.core.util.dev.FreeMarkerUtils;
import com.daxia.wy.dto.ApiModuleDTO;
import com.daxia.wy.model.ApiTest;
import com.daxia.wy.model.ApiTestParameter;

public class Api2ServiceUtils {
    private static String targetPath = "/Users/kevin/code/yc/trunk/ios/DaXiaProject/Classes/service";

    public static void api2service(List<ApiModuleDTO> apiModuleDTOs) throws Exception {
        List<ApiTest> apiTests = new ArrayList<ApiTest>();
        for (ApiModuleDTO apiModuleDTO : apiModuleDTOs) {
            apiTests.addAll(apiModuleDTO.getApiTests());
        }

        Map<String, List<ApiTest>> moduleMap = new HashMap<String, List<ApiTest>>();

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
            module = StringUtils.upperCase(module.substring(0, 1)) + module.substring(1);
            String method = arr[2];
            System.out.println(module + "." + method);

            apiTest.setModule(module);
            apiTest.setMethod(method);

            List<ApiTest> list = moduleMap.get(module);
            if (list == null) {
                list = new ArrayList<ApiTest>();
                moduleMap.put(module, list);
            }
            Collections.sort(apiTest.getApiTestParameters(), new Comparator<ApiTestParameter>() {

                @Override
                public int compare(ApiTestParameter o1, ApiTestParameter o2) {
                    return Boolean.valueOf(o2.getRequired()).compareTo(o1.getRequired());
                }
            });
            list.add(apiTest);
            
            apiTest.setArgs(processArgs(apiTest));
        }

        
        String templatePath = "src/main/resources/ios/";
        List<File> templateList = new ArrayList<File>();
        templateList.add(new File(templatePath + "ModelService.h.ftl"));
        templateList.add(new File(templatePath + "ModelService.m.ftl"));

        for (String module : moduleMap.keySet()) {
            System.out.println("begin: " + module);
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("Model", module);
            paramMap.put("apiTests", moduleMap.get(module));
            
            for (File template : templateList) {
                // get tmeplate filef
                String fileName = template.getName().replace(".ftl", "").replace("Model", module);
                File target = new File(targetPath, fileName);
                FreeMarkerUtils.generate(template, target, paramMap);
            }
        }
    }

    private static String processArgs(ApiTest apiTest) {
        List<ApiTestParameter> parameters = apiTest.getApiTestParameters();
        String args = "";
        if (CollectionUtils.isEmpty(apiTest.getApiTestParameters())) {
            return "";
        }
        
        args = ":";
        boolean isListMethod = apiTest.getMethod().equals("list");
        int index = 0;
        for (ApiTestParameter p : parameters) {
            String type = "";
            String name = p.getName();
            if (name.contains(".")) {
                name = name.replace(".", "__");
            }
            if (name.toLowerCase().endsWith("id")) {
                type = "(NSNumber *)";
            } else {
                type = "(NSString *)";
            }
            if (index == 0) {
                args += type + name + " ";
            } else {
                args += name + ":" + type + name + " ";
            }
            
            index ++;
        }
        if (isListMethod) {
            args += " pageNum:(NSNumber *)pageNum numPerPage:(NSNumber *)numPerPage";
        }
        if (args.endsWith(" ")) {
            args = args.substring(0, args.length() - 1);
        }
        return args;
    }
}
