package com.daxia.wy.dto;

public class ImageDTO {
    private String path;
    private String pathLarge;
    private String pathSmall;
    private String pathMedium;
    public ImageDTO() {
        
    }
    public ImageDTO(String path) {
        this.path = path;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getPathLarge() {
        if (path != null) {
            return path + "?width=" + 800;
        }
        return pathLarge;
    }
    public void setPathLarge(String pathLarge) {
        this.pathLarge = pathLarge;
    }
    public String getPathSmall() {
        if (path != null) {
            return path + "?width=" + 100;
        }
        return pathSmall ;
    }
    public void setPathSmall(String pathSmall) {
        this.pathSmall = pathSmall;
    }
    public String getPathMedium() {
        if (path != null) {
            return path + "?width=" + 400;
        }
        return pathMedium;
    }
    public void setPathMedium(String pathMedium) {
        this.pathMedium = pathMedium;
    }
    
}
