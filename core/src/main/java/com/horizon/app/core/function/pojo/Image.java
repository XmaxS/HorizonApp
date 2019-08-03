package com.horizon.app.core.function.pojo;

public class Image {
    private String name;
    private int imgId;

    public Image(String name, int id) {
        this.name = name;
        this.imgId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
