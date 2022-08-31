package com.grupo3.confido.usercase.recomendations.info;

public class Info {

    private String nomInfo;
    private String description;
    private int imgInfo;

    public Info() { }

    public Info(String nomInfo, int imgInfo, String description) {
        this.nomInfo = nomInfo;
        this.description = description;
        this.imgInfo = imgInfo;
    }

    public String getNomInfo() {
        return nomInfo;
    }
    public void setNomInfo(String nomInfo) {
        this.nomInfo = nomInfo;
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


    public int getImgInfo() {
        return imgInfo;
    }
    public void setImgInfo(int imgInfo) {
        this.imgInfo = imgInfo;
    }
}
