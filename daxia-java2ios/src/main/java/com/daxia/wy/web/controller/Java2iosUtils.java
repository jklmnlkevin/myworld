package com.daxia.wy.web.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.daxia.core.util.dev.FreeMarkerUtils;

public class Java2iosUtils {
    private static String targetPath = "/Users/kevin/code/yc/trunk/ios/DaXiaProject/Classes/model";
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

    public static void java2ios() throws Exception {

        String path = "/Users/kevin/code/yc_java/trunk/src/main/java";
        String apiDTOPath = path + "/com/daxia/wy/dto/api";

        String templatePath = "src/main/resources/ios/";
        List<File> templateList = new ArrayList<File>();
        templateList.add(new File(templatePath + "Model.h.ftl"));
        templateList.add(new File(templatePath + "Model.m.ftl"));
        
        File[] dtos = new File(apiDTOPath).listFiles();
        
        for (File dto : dtos) {
            for (File template : templateList) {
                bean2ios(dto, template, new File(targetPath));
            }
        }

    }
    private static void bean2ios(File dto, File template, File targetDir) throws Exception {
        // get extract model and felds from file
        String moduleName = dto.getName();
        moduleName = moduleName.replace(".java", "");
        moduleName = moduleName.replace("APIDTO", "");
        if (invalidModules.contains(moduleName)) {
            return;
        }

        List<ModuleField> moduleFields = ModuleFieldUtils.parseFields(dto);
        
        boolean hasImageDTO = false;
        boolean hasUser = false;
        for (ModuleField moduleField : moduleFields) {
            if (moduleField.getType().equals("ImageDTO")) {
                hasImageDTO = true;
            } else if (moduleField.getType().equals("UserAPIDTO")) {
                hasUser = true;
                moduleField.setType("User");
            }
        }
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("Model", moduleName);
        paramMap.put("fields", moduleFields);
        paramMap.put("hasImageDTO", hasImageDTO);
        paramMap.put("hasUser", hasUser);
        // get tmeplate filef
        String fileName = template.getName().replace(".ftl", "").replace("Model", moduleName);
        File target = new File(targetDir, fileName);
        FreeMarkerUtils.generate(template, target, paramMap);
        // 
    }
    
    public static void main(String[] args) {
        
    }
}
