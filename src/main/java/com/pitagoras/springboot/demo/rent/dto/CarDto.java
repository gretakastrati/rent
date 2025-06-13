package com.pitagoras.springboot.demo.rent.dto;

import java.math.BigDecimal;
import java.util.List;

public class CarDto {

    private int id;
    private String make;
    private String model;
    private int year;
    private String color;
    private String licensePlate;
    private boolean available;
    private Integer kmPassed;
    private String description;
    private BigDecimal litersPerKm;
    private String transmission;
    private Boolean hasNavigation;
    private BigDecimal pricePerDay;
    private List<CarImageDto> images;


    // Default constructor
    public CarDto() {}

    // Constructor with all fields
    public CarDto(int id, String make, String model, int year, String color, String licensePlate,
                  boolean available, Integer kmPassed, String description, BigDecimal litersPerKm,
                  String transmission, Boolean hasNavigation, BigDecimal pricePerDay, List<CarImageDto> images) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.licensePlate = licensePlate;
        this.available = available;
        this.kmPassed = kmPassed;
        this.description = description;
        this.litersPerKm = litersPerKm;
        this.transmission = transmission;
        this.hasNavigation = hasNavigation;
        this.pricePerDay = pricePerDay;
        this.images = images;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Integer getKmPassed() {
        return kmPassed;
    }

    public void setKmPassed(Integer kmPassed) {
        this.kmPassed = kmPassed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getLitersPerKm() {
        return litersPerKm;
    }

    public void setLitersPerKm(BigDecimal litersPerKm) {
        this.litersPerKm = litersPerKm;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public Boolean getHasNavigation() {
        return hasNavigation;
    }

    public void setHasNavigation(Boolean hasNavigation) {
        this.hasNavigation = hasNavigation;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public List<CarImageDto> getImages() {
        return images;
    }

    public void setImages(List<CarImageDto> images) {
        this.images = images;
    }
}

