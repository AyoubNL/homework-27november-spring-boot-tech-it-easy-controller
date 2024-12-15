package nl.novi.techiteasycontroller.service;

import nl.novi.techiteasycontroller.dtos.RemoteControllerOutputDto;
import nl.novi.techiteasycontroller.exceptions.RecordNotFoundException;
import nl.novi.techiteasycontroller.models.RemoteController;
import nl.novi.techiteasycontroller.repositories.RemoteControllerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RemoteControllerService {

    final private RemoteControllerRepository remoteControllerRepository;


    public RemoteControllerService(RemoteControllerRepository remoteControllerRepository) {
        this.remoteControllerRepository = remoteControllerRepository;

    }

    public List<RemoteControllerOutputDto> getAllRemoteControllers() {
        List<RemoteControllerOutputDto> remoteControllers = new ArrayList<>();
        List<RemoteController> remoteControllerList = remoteControllerRepository.findAll();

        for (RemoteController remoteController : remoteControllerList) {
            remoteControllers.add(transferToDto(remoteController));
        }

        return remoteControllers;

    }

    public RemoteControllerOutputDto getRemoteControllerById(Long id) {

        Optional<RemoteController> remoteController = this.remoteControllerRepository.findById(id);
        if (remoteController.isPresent()) {
            return transferToDto(remoteController.get());
        } else {
            throw new RecordNotFoundException("No remotecontroller found");
        }

    }

    public RemoteControllerOutputDto addRemoteController(RemoteControllerOutputDto remoteControllerOutputDto) {

        RemoteController remoteController1 = transferToRemoteController(remoteControllerOutputDto);
        this.remoteControllerRepository.save(remoteController1);

        return remoteControllerOutputDto;
    }

    public void deleteRemoteControllerById(Long id) {

     this.remoteControllerRepository.deleteById(id);

    }

    public void updateRemoteControllerById(Long id, RemoteControllerOutputDto remoteControllerOutputDto) {

        if (!this.remoteControllerRepository.existsById(id)){
            throw new RuntimeException("No remotecontroller found");
        }

        RemoteController remoteController1 = this.remoteControllerRepository.findById(id).orElse(null);

        remoteController1.setId(remoteControllerOutputDto.getId());
        remoteController1.setCompatibleWith(remoteControllerOutputDto.getCompatibleWith());
        remoteController1.setBatteryType(remoteControllerOutputDto.getBatteryType());
        remoteController1.setName(remoteControllerOutputDto.getName());
        remoteController1.setPrice(remoteControllerOutputDto.getPrice());
        remoteController1.setBrand(remoteControllerOutputDto.getBrand());
        remoteController1.setOriginalStock(remoteControllerOutputDto.getOriginalStock());

        this.remoteControllerRepository.save(remoteController1);

    }

    public RemoteControllerOutputDto transferToDto(RemoteController remoteController) {

        var remoteControllersDto = new RemoteControllerOutputDto();

        remoteControllersDto.id = remoteController.getId();
        remoteControllersDto.name = remoteController.getName();
        remoteControllersDto.price = remoteController.getPrice();
        remoteControllersDto.batteryType = remoteController.getBatteryType();
        remoteControllersDto.compatibleWith = remoteController.getCompatibleWith();
        remoteControllersDto.brand = remoteController.getBrand();
        remoteControllersDto.originalStock = remoteController.getOriginalStock();

        return remoteControllersDto;

    }

    public RemoteController transferToRemoteController(RemoteControllerOutputDto remoteControllerOutputDto) {

        var remoteController = new RemoteController();

        remoteController.setId(remoteControllerOutputDto.getId());
        remoteController.setName(remoteControllerOutputDto.getName());
        remoteController.setPrice(remoteControllerOutputDto.getPrice());
        remoteController.setBatteryType(remoteControllerOutputDto.getBatteryType());
        remoteController.setCompatibleWith(remoteControllerOutputDto.getCompatibleWith());
        remoteController.setBrand(remoteControllerOutputDto.getBrand());
        remoteController.setOriginalStock(remoteControllerOutputDto.getOriginalStock());

        return remoteController;

    }


}


