package nl.novi.techiteasycontroller.service;

import nl.novi.techiteasycontroller.dtos.TelevisionInputDto;
import nl.novi.techiteasycontroller.dtos.TelevisionOutputDto;
import nl.novi.techiteasycontroller.exceptions.RecordNotFoundException;
import nl.novi.techiteasycontroller.models.Television;
import nl.novi.techiteasycontroller.models.WallBracket;
import nl.novi.techiteasycontroller.repositories.CiModuleRepository;
import nl.novi.techiteasycontroller.repositories.RemoteControllerRepository;
import nl.novi.techiteasycontroller.repositories.TelevisionRepository;
import nl.novi.techiteasycontroller.repositories.WallBracketRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TelevisionService {

    final private TelevisionRepository televisionRepository;
    final private CiModuleRepository ciModuleRepository;
    final private CiModuleService ciModuleService;
    final private WallBracketRepository wallBracketRepository;
    final private WallbracketService wallbracketService;
    final private RemoteControllerRepository remoteControllerRepository;
    final private RemoteControllerService remoteControllerService;

    public TelevisionService(TelevisionRepository televisionRepository, CiModuleRepository ciModuleRepository, WallBracketRepository wallBracketRepository, CiModuleService ciModuleService, WallbracketService wallbracketService, RemoteControllerRepository remoteControllerRepository, RemoteControllerService remoteControllerService) {
        this.televisionRepository = televisionRepository;
        this.ciModuleRepository = ciModuleRepository;
        this.wallBracketRepository = wallBracketRepository;
        this.ciModuleService = ciModuleService;
        this.wallbracketService = wallbracketService;
        this.remoteControllerRepository = remoteControllerRepository;
        this.remoteControllerService = remoteControllerService;
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

        Television tv = transferToTelevision(televisionInputDto);
        this.televisionRepository.save(tv);

        return transferToDto(tv);

    }

    public void deleteTelevision(Long id) {

        this.televisionRepository.deleteById(id);
    }

    public TelevisionOutputDto updateTelevision(Long id, TelevisionInputDto televisionInputDto) {
        Optional <Television> televisionOptional = this.televisionRepository.findById(id);

        if(televisionOptional.isPresent()) {
            Television television = televisionOptional.get();

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

            Television returnTelevision = this.televisionRepository.save(television);

            return transferToDto(returnTelevision);
        }

        else{

            throw new RecordNotFoundException();
        }

    }

    public List<TelevisionOutputDto> getAllTelevisionsByBrand(String brand) {
        List<Television> tvList = televisionRepository.findAllTelevisionsByBrandEqualsIgnoreCase(brand);
        List <TelevisionOutputDto> televisionOutputDtoList = new ArrayList<>();

        for (Television television : tvList) {
            TelevisionOutputDto televisionOutputDto = transferToDto(television);
            televisionOutputDtoList.add(televisionOutputDto);
        }

        return televisionOutputDtoList;

    }

    public Television transferToTelevision(TelevisionInputDto televisionInputDto) {
        var television = new Television();

        television.setType(televisionInputDto.getType());
        television.setBrand(televisionInputDto.getBrand());
        television.setName(televisionInputDto.getName());
        television.setPrice(televisionInputDto.getPrice());
        television.setAvailableSize(televisionInputDto.getAvailableSize());
        television.setRefreshRate(televisionInputDto.getRefreshRate());
        television.setScreenType(televisionInputDto.getScreenType());
        television.setScreenQuality(televisionInputDto.getScreenQuality());
        television.setSmartTv(televisionInputDto.getSmartTv());
        television.setWifi(televisionInputDto.getWifi());
        television.setVoiceControl(televisionInputDto.getVoiceControl());
        television.setHdr(televisionInputDto.getHdr());
        television.setBluetooth(televisionInputDto.getBluetooth());
        television.setAmbiLight(televisionInputDto.getAmbiLight());
        television.setOriginalStock(televisionInputDto.getOriginalStock());
        television.setSold(televisionInputDto.getSold());

        return television;

    }

    public TelevisionOutputDto transferToDto(Television television) {
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

    public void assignRemoteControllerToTelevision(Long id, Long remoteId) {
    var optionalTelevision = this.televisionRepository.findById(id);
    var optionalRemoteController = this.remoteControllerRepository.findById(remoteId);

    if(optionalTelevision.isPresent() && optionalRemoteController.isPresent()){
        var television = optionalTelevision.get();
        var remoteController = optionalRemoteController.get();

        television.setRemoteController(remoteController);
        this.televisionRepository.save(television);
    }
    else{
        throw new RecordNotFoundException();
    }
    }

    public void assignCIModuleToTelevision(Long id, Long ciModuleId){
        var optionalTelevision = this.televisionRepository.findById(id);
        var optionalCiModule = this.ciModuleRepository.findById(ciModuleId);

        if(optionalTelevision.isPresent() && optionalCiModule.isPresent()){
            var television = optionalTelevision.get();
            var ciModule = optionalCiModule.get();

            television.setCiModule(ciModule);
            this.televisionRepository.save(television);
        }
        else{
            throw new RecordNotFoundException();
        }
    }

    public void assignWallBracketToTelevision(Long id, WallBracket wallBracket){
        var OptionalTelevision = this.televisionRepository.findById(id);

        if(OptionalTelevision.isPresent()){
            var television = OptionalTelevision.get();

            television.getWallBrackets().add(wallBracket);

            this.televisionRepository.save(television);

        }
        else{
            throw new RecordNotFoundException();
        }
    }




}
