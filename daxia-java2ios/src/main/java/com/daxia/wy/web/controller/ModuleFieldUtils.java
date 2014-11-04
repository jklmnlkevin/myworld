package com.daxia.wy.web.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class ModuleFieldUtils {
    
    private static Map<String, String> typeMap = new HashMap<String, String>();
    static {
        typeMap.put("String", "NSString");
        typeMap.put("Long", "NSNumber");
        typeMap.put("Integer", "NSNumber");
        typeMap.put("int", "NSNumber");
        typeMap.put("Double", "NSNumber");
        typeMap.put("Float", "NSNumber");
        typeMap.put("Date", "NSString");
        typeMap.put("boolean", "BOOL");
        typeMap.put("Boolean", "BOOL");
    }
    
    public static List<ModuleField> parseFields(File apiDTOFile) {
        try {
            return doParse(apiDTOFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<ModuleField>();
    }

    private static List<ModuleField> doParse(File apiDTOFile) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(apiDTOFile)));
        String line = null;
        List<ModuleField> list = new ArrayList<ModuleField>();
        while ((line = reader.readLine()) != null) {
            if (StringUtils.isBlank(line)) {
                continue;
            }
            
            line = line.trim();
            if (!line.startsWith("private")) {
                continue;
            }
            
            line = line.replace(";", "");
            String[] arr = line.split("\\ +");
            String type = arr[1];
            String name = arr[2];

            ModuleField mf = new ModuleField();
            if (type.startsWith("List<")) {
                mf.setArray(true);
                type = type.replace("List<", "").replace(">", "");
            }
            mf.setType(type);
            mf.setName(name);
            if (     Long.class.getSimpleName().equals(type)
                  || Integer.class.getSimpleName().equals(type)
                  || String.class.getSimpleName().equals(type)
                  || int.class.getSimpleName().equals(type)
                  || long.class.getSimpleName().equals(type)) {
            } else if (Date.class.getSimpleName().equals(type)) {
                mf.setDate(true);
            } else {
                mf.setObject(true);
            }
            String mappedType = typeMap.get(mf.getType());
            if (mappedType != null) {
                mf.setType(mappedType);
            }
            list.add(mf);
        }
        return list;
    }
    
    
}
