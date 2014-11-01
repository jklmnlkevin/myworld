package com.daxia.core.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.lang3.StringUtils;

import com.daxia.wy.dto.ImageDTO;

public class ImageUtils {
	
    public static String getImageFullPath(HttpServletRequest request, String imageName) {
        return request.getSession().getAttribute("ctx") + "/image/" + imageName;
    }
    public static String getOrCreateSmallImage(String path, int width) throws IOException {
        File existedSmallFile = new File(getSmallFileName(path, width));
        if (existedSmallFile.exists()) {
            return existedSmallFile.getPath();
        }
        
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        // 需要转换的文件为桌面上的1.png
        Thumbnails.of(file).
                width(width) // 生成图片的格式为png
                // .outputQuality(0.8f) //生成质量为80%
                // .scale(0.5f) //缩小50%
                // 输出到桌面5文件
                // .toFile("/Users/kevin/Documents/tmp_200");
                .toFile(existedSmallFile);
        return existedSmallFile.getPath();
    }
    
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        String path = getOrCreateSmallImage("d:/me.jpg", 150);
        System.out.println("took " + (System.currentTimeMillis() - start) + "ms");
        System.out.println(path);
    }
    
    public static String getSmallFileName(String path, int width) {
        String suffix = path.substring(path.lastIndexOf("."));
        path = path.substring(0, path.lastIndexOf(".")) + "_" + width + suffix;
        return path;
    }
    
    public static ImageDTO convertToImageDTO(String path) {
        if (StringUtils.isBlank(path)) {
            return null;
        }
        if (!path.startsWith("http")) {
            path = ImageUtils.getImageFullPath(MyWebUtils.getCurrentRequest(), path);
        }
        return new ImageDTO(path);
    }
    
    public static List<ImageDTO> convertToImageDTOs(String path) {
        List<ImageDTO> list = new ArrayList<ImageDTO>();
        if (StringUtils.isBlank(path)) {
            return list;
        }
        String[] arr = path.split("\\ *,\\ *");
        for (String string : arr) {
            ImageDTO dto = convertToImageDTO(string);
            if (dto != null) {
                list.add(dto);
            }
        }
        return list;
    }
}
