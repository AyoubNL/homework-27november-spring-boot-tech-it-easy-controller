package nl.novi.techiteasycontroller.dtos;

import nl.novi.techiteasycontroller.models.Television;

public class RemoteControllerOutputDto {

    public Long id;
    public String compatibleWith;
    public String batteryType;
    public String name;
    public String brand;
    public Double price;
    public Integer originalStock;

    public RemoteControllerOutputDto(){}

    public RemoteControllerOutputDto(Long id, String compatibleWith, String batteryType, String name, String brand, Double price, Integer originalStock, Television television) {
        this.id = id;
        this.compatibleWith = compatibleWith;
        this.batteryType = batteryType;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.originalStock = originalStock;
    }

    public Long getId() {
        return id;
    }

    public String getCompatibleWith() {
        return compatibleWith;
    }

    public void setCompatibleWith(String compatibleWith) {
        this.compatibleWith = compatibleWith;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getOriginalStock() {
        return originalStock;
    }

    public void setOriginalStock(Integer originalStock) {
        this.originalStock = originalStock;
    }

}
