package com.daxia.wy.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class UploadFileUtils {

	/**
	 * 保存上传文件
	 * 
	 * @param path   保存的上传文件的路径
	 * @param request
	 * @return       上传文件的名字
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public static List<String> saveFiles(String path, HttpServletRequest request) throws Exception {
		List<String> list = new ArrayList<String>();
		if (!(request instanceof MultipartHttpServletRequest)) {
		    return list;
		}
		MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = mhs.getFileMap();
		for (String key : fileMap.keySet()) {
			MultipartFile multipartFile = fileMap.get(key);
			String fileName = multipartFile.getOriginalFilename();
			if (StringUtils.isEmpty(fileName)) {
				continue;
			}
			fileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
			list.add(fileName);
			File file = new File(path + "/" + fileName);
			multipartFile.transferTo(file);
		}
		return list;
	}
	
	/**
	 * 保存单个文件
	 * @param path
	 * @param request
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public static String saveFile(String path, HttpServletRequest request, String name) throws Exception {
	    if (!(request instanceof MultipartHttpServletRequest)) {
            return null;
        }
		   MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;  
           MultipartFile mf = mhs.getFile(name);  
           String fileName = mf.getOriginalFilename();
           if (StringUtils.isBlank(fileName)) {
        	   return null;
           }
           fileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
           File file = new File(path + "/" + fileName);
           mf.transferTo(file);
           return fileName;
	}
	
	/**
	 * 根据后台保存的文件名，保存完成的路径
	 * @param request
	 * @param list   
	 * @return
	 * 
	 * <pre>
	 *   比如: 文件    hello.jpg  ,返回的全路径是 http://www.**.com/img/hello.jpg
	 * </pre>
	 */
	public static List<String> getFullPath(HttpServletRequest request, List<String> list) {
		List<String> path = new ArrayList<String>();
		for (String file : list) {
            String fullPath = request.getSession().getAttribute("ctx").toString() + "/image/" + file;
            path.add(fullPath);
		}
		return path;
	}
	
	public static String getFullPath(HttpServletRequest request, String file) {
        return request.getSession().getAttribute("ctx").toString() + "/image/" + file;
	}
}
