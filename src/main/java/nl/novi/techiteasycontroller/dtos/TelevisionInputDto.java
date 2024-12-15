package nl.novi.techiteasycontroller.dtos;

import jakarta.validation.constraints.*;

public class TelevisionInputDto {

    @NotBlank(message = "Please provide a type")
    public String type;
    @NotBlank(message = "Please provide a brand")
    public String brand;
    @Size(min = 2, max = 59, message = "name must be at least 2 characters long")
    public String name;
    @Positive
    @Max(value = 10000)
    public Double price;
    @Max(value = 170)
    public Double availableSize;
    public Integer refreshRate;
    public String screenType;
    public String screenQuality;
    public Boolean smartTv;
    public Boolean wifi;
    public Boolean voiceControl;
    public Boolean hdr;
    public Boolean bluetooth;
    public Boolean ambiLight;
    @Min(value = 1)
    public Integer originalStock;
    public Integer sold;

    public @NotBlank(message = "Please provide a type") String getType() {
        return type;
    }

    public @NotBlank(message = "Please provide a brand") String getBrand() {
        return brand;
    }

    public @Size(min = 2, max = 59, message = "name must be at least 2 characters long") String getName() {
        return name;
    }

    public @Positive @Max(value = 10000) Double getPrice() {
        return price;
    }

    public @Max(value = 170) Double getAvailableSize() {
        return availableSize;
    }

    public Integer getRefreshRate() {
        return refreshRate;
    }

    public String getScreenType() {
        return screenType;
    }

    public String getScreenQuality() {
        return screenQuality;
    }

    public Boolean getSmartTv() {
        return smartTv;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public Boolean getVoiceControl() {
        return voiceControl;
    }

    public Boolean getHdr() {
        return hdr;
    }

    public Boolean getBluetooth() {
        return bluetooth;
    }

    public Boolean getAmbiLight() {
        return ambiLight;
    }

    public @Min(value = 1) Integer getOriginalStock() {
        return originalStock;
    }

    public Integer getSold() {
        return sold;
    }
}
