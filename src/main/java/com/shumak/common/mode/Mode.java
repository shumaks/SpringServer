package com.shumak.common.mode;

public class Mode {
    private long id;
    private String name;
    private int maxSpeed;
    private double accelerationTime;
    private double engineVolume;
    private double gasMileage;
    private int price;

    public Mode(long id, String name, int maxSpeed, double accelerationTime, double engineVolume, double gasMileage, int price) {
        this.id = id;
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.accelerationTime = accelerationTime;
        this.engineVolume = engineVolume;
        this.gasMileage = gasMileage;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getAccelerationTime() {
        return accelerationTime;
    }

    public void setAccelerationTime(double accelerationTime) {
        this.accelerationTime = accelerationTime;
    }

    public double getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(double engineVolume) {
        this.engineVolume = engineVolume;
    }

    public double getGasMileage() {
        return gasMileage;
    }

    public void setGasMileage(double gasMileage) {
        this.gasMileage = gasMileage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
