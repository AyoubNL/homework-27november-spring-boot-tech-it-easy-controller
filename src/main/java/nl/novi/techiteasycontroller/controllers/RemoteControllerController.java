package nl.novi.techiteasycontroller.controllers;

import nl.novi.techiteasycontroller.dtos.RemoteControllerOutputDto;
import nl.novi.techiteasycontroller.repositories.TelevisionRepository;
import nl.novi.techiteasycontroller.service.RemoteControllerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RemoteControllerController {

    private final RemoteControllerService remoteControllerService;

    public RemoteControllerController(RemoteControllerService remoteControllerService) {
        this.remoteControllerService = remoteControllerService;
    }

    @GetMapping("/remotecontrollers")
    public ResponseEntity <List<RemoteControllerOutputDto>> getAllRemoteControllers() {
        return ResponseEntity.ok(this.remoteControllerService.getAllRemoteControllers());
    }

    @GetMapping("/remotecontrollers/{id}")
    public ResponseEntity <RemoteControllerOutputDto> getRemoteControllerById(@PathVariable Long id) {

        RemoteControllerOutputDto remoteControllerOutputDto = this.remoteControllerService.getRemoteControllerById(id);

        return ResponseEntity.ok(remoteControllerOutputDto);


    }

    @PostMapping("/remotecontrollers")
    public ResponseEntity<RemoteControllerOutputDto> addRemoteController(@RequestBody RemoteControllerOutputDto remoteControllerOutputDto) {
        RemoteControllerOutputDto remoteControllerOutputDto1 = this.remoteControllerService.addRemoteController(remoteControllerOutputDto);

        return ResponseEntity.ok(remoteControllerOutputDto1);
    }

    @DeleteMapping ("/remotecontrollers/{id}")
    public ResponseEntity <RemoteControllerOutputDto> deleteRemoteControllerById(@PathVariable Long id) {

        this.remoteControllerService.deleteRemoteControllerById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/remotecontrollers/{id}")
    public ResponseEntity<RemoteControllerOutputDto> updateRemoteControllerById(@PathVariable Long id, @RequestBody RemoteControllerOutputDto remoteControllerOutputDto) {

        this.remoteControllerService.updateRemoteControllerById(id, remoteControllerOutputDto);
        return ResponseEntity.ok(remoteControllerOutputDto);

    }




}
