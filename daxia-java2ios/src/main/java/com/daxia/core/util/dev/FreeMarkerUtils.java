package com.daxia.core.util.dev;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class FreeMarkerUtils {
	private static final String CHARSET = "UTF-8";
	
	public static void generate(File templateFile, File targetFile, Map<String, Object> paramMap) throws Exception {
		
        Configuration configuration = new Configuration();  
        configuration.setDirectoryForTemplateLoading(templateFile.getParentFile());  
        configuration.setObjectWrapper(new DefaultObjectWrapper());  
        configuration.setDefaultEncoding(CHARSET); 
        
        Template template = configuration.getTemplate(templateFile.getName());  
        Writer writer  = new OutputStreamWriter(new FileOutputStream(targetFile), CHARSET);  
        template.process(paramMap, writer);  
        System.out.println("Done!");  
    }
	
}
