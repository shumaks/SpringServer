package com.shumak.common.auto;

import com.shumak.common.mode.Mode;

public class Auto {
    private long id;
    private String model;
    private int sits;
    private int modelYear;
    private String image;
    private Mode mode;

    public Auto(long id, String model, int sits, int modelYear, String image, Mode mode) {
        this.id = id;
        this.model = model;
        this.sits = sits;
        this.modelYear = modelYear;
        this.image = image;
        this.mode = mode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSits() {
        return sits;
    }

    public void setSits(int sits) {
        this.sits = sits;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }
}
