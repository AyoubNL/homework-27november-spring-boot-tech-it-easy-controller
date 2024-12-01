package nl.novi.techiteasycontroller.dtos;


import jakarta.validation.constraints.*;

public class TelevisionInputDto {

    @NotNull
    public String type;
    @NotNull
    public String brand;
    @Size(min = 2, max = 59)
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





}
