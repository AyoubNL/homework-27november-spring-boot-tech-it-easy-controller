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





}
