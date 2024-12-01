package nl.novi.techiteasycontroller.service;

import nl.novi.techiteasycontroller.dtos.TelevisionInputDto;
import nl.novi.techiteasycontroller.dtos.TelevisionOutputDto;
import nl.novi.techiteasycontroller.models.Television;
import nl.novi.techiteasycontroller.repositories.TelevisionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelevisionService {

    final private TelevisionRepository televisionRepository;

    public TelevisionService(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    public List<TelevisionOutputDto> getAllTelevisions() {
        List<Television> televisions = televisionRepository.findAll();
        List<TelevisionOutputDto> televisionOutputDtos = new ArrayList<>();

        for (Television television : televisions) {
            TelevisionOutputDto televisionOutputDto = new TelevisionOutputDto();

            televisionOutputDto.id = television.getId();
            televisionOutputDto.type = television.getType();
            televisionOutputDto.name = television.getName();
            televisionOutputDto.brand = television.getBrand();
            televisionOutputDto.price = television.getPrice();
            televisionOutputDto.availableSize = television.getAvailableSize();
            televisionOutputDto.refreshRate = television.getRefreshRate();
            televisionOutputDto.screenType = television.getScreenType();
            televisionOutputDto.screenQuality = television.getScreenQuality();
            televisionOutputDto.smartTv = television.getSmartTv();
            televisionOutputDto.wifi = television.getWifi();
            televisionOutputDto.voiceControl = television.getVoiceControl();
            televisionOutputDto.hdr = television.getHdr();
            televisionOutputDto.bluetooth = television.getBluetooth();
            televisionOutputDto.ambiLight = television.getAmbiLight();
            televisionOutputDto.originalStock = television.getOriginalStock();
            televisionOutputDto.sold = television.getSold();

            televisionOutputDtos.add(televisionOutputDto);

        }

        return televisionOutputDtos;
    }

    public TelevisionOutputDto getTelevisionById(Long id) {

        Television television = this.televisionRepository.findById(id).orElse(null);

        TelevisionOutputDto televisionOutputDto = new TelevisionOutputDto();
        televisionOutputDto.id = id;
        televisionOutputDto.type = television.getType();
        televisionOutputDto.name = television.getName();
        televisionOutputDto.brand = television.getBrand();
        televisionOutputDto.price = television.getPrice();
        televisionOutputDto.availableSize = television.getAvailableSize();
        televisionOutputDto.refreshRate = television.getRefreshRate();
        televisionOutputDto.screenType = television.getScreenType();
        televisionOutputDto.screenQuality = television.getScreenQuality();
        televisionOutputDto.smartTv = television.getSmartTv();
        televisionOutputDto.wifi = television.getWifi();
        televisionOutputDto.voiceControl = television.getVoiceControl();
        televisionOutputDto.hdr = television.getHdr();
        televisionOutputDto.bluetooth = television.getBluetooth();
        televisionOutputDto.ambiLight = television.getAmbiLight();
        televisionOutputDto.originalStock = television.getOriginalStock();
        televisionOutputDto.sold = television.getSold();

        return televisionOutputDto;


    }

    public TelevisionOutputDto createTelevision(TelevisionInputDto televisionInputDto) {
        Television television = new Television();

        television.setType(televisionInputDto.type);
        television.setName(televisionInputDto.name);
        television.setBrand(televisionInputDto.brand);
        television.setPrice(televisionInputDto.price);
        television.setAvailableSize(televisionInputDto.availableSize);
        television.setRefreshRate(televisionInputDto.refreshRate);
        television.setScreenType(televisionInputDto.screenType);
        television.setScreenQuality(televisionInputDto.screenQuality);
        television.setSmartTv(televisionInputDto.smartTv);
        television.setWifi(televisionInputDto.wifi);
        television.setVoiceControl(televisionInputDto.voiceControl);
        television.setHdr(televisionInputDto.hdr);
        television.setBluetooth(televisionInputDto.bluetooth);
        television.setAmbiLight(televisionInputDto.ambiLight);
        television.setOriginalStock(televisionInputDto.originalStock);
        television.setSold(televisionInputDto.sold);


        this.televisionRepository.save(television);

        TelevisionOutputDto televisionOutputDto = new TelevisionOutputDto();
        televisionOutputDto.id = television.getId();
        televisionOutputDto.type = television.getType();
        televisionOutputDto.name = television.getName();
        televisionOutputDto.brand = television.getBrand();
        televisionOutputDto.price = television.getPrice();
        televisionOutputDto.availableSize = television.getAvailableSize();
        televisionOutputDto.refreshRate = television.getRefreshRate();
        televisionOutputDto.screenType = television.getScreenType();
        televisionOutputDto.screenQuality = television.getScreenQuality();
        televisionOutputDto.smartTv = television.getSmartTv();
        televisionOutputDto.wifi = television.getWifi();
        televisionOutputDto.voiceControl = television.getVoiceControl();
        televisionOutputDto.hdr = television.getHdr();
        televisionOutputDto.bluetooth = television.getBluetooth();
        televisionOutputDto.ambiLight = television.getAmbiLight();
        televisionOutputDto.originalStock = television.getOriginalStock();
        televisionOutputDto.sold = television.getSold();

        return televisionOutputDto;
    }

    public Television deleteTelevision(Long id) {
        Television television = this.televisionRepository.findById(id).orElse(null);
        this.televisionRepository.delete(television);

        return television;
    }

    public void updateTelevision(Long id, TelevisionInputDto televisionInputDto) {
        Television television = this.televisionRepository.findById(id).orElse(null);

        television.setType(televisionInputDto.type);
        television.setName(televisionInputDto.name);
        television.setBrand(televisionInputDto.brand);
        television.setPrice(televisionInputDto.price);
        television.setAvailableSize(televisionInputDto.availableSize);
        television.setRefreshRate(televisionInputDto.refreshRate);
        television.setScreenType(televisionInputDto.screenType);
        television.setScreenQuality(televisionInputDto.screenQuality);
        television.setSmartTv(televisionInputDto.smartTv);
        television.setWifi(televisionInputDto.wifi);
        television.setVoiceControl(televisionInputDto.voiceControl);
        television.setHdr(televisionInputDto.hdr);
        television.setBluetooth(televisionInputDto.bluetooth);
        television.setAmbiLight(televisionInputDto.ambiLight);
        television.setOriginalStock(televisionInputDto.originalStock);
        television.setSold(televisionInputDto.sold);

        this.televisionRepository.save(television);

    }


}
