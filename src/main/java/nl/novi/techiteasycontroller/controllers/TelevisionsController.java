package nl.novi.techiteasycontroller.controllers;

import nl.novi.techiteasycontroller.dtos.TelevisionInputDto;
import nl.novi.techiteasycontroller.dtos.TelevisionOutputDto;
import nl.novi.techiteasycontroller.models.Television;
import nl.novi.techiteasycontroller.repositories.TelevisionRepository;
import nl.novi.techiteasycontroller.service.TelevisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.List;


@RestController
public class TelevisionsController {

    private final TelevisionRepository televisionRepository;
    private final TelevisionService televisionService;

    public TelevisionsController(TelevisionRepository televisionRepository, TelevisionService televisionService) {
        this.televisionRepository = televisionRepository;
        this.televisionService = televisionService;
    }

//    @GetMapping("/televisions")
//    public ResponseEntity TelevisionOutputDto getTelevisions() {
//        return this.televisionService.getAllTelevisions();
//    }

    @GetMapping("/televisions")
    public ResponseEntity <List<TelevisionOutputDto>> getAllTelevisions() {
        return ResponseEntity.ok(this.televisionService.getAllTelevisions());
    }

//    @GetMapping("/televisions/{id}")
//    public ResponseEntity<Television> getTelevisionsById(@PathVariable Long id) {
//
//        return ResponseEntity.ok(this.televisionRepository.findById(id).orElse(null));
//    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity <TelevisionOutputDto> getTelevisionsById(@PathVariable Long id) {

        return ResponseEntity.ok(this.televisionService.getTelevisionById(id));
    }

//    @PostMapping("/televisions")
//    public ResponseEntity<Television> createTelevision(@RequestBody Television television) {
//        televisionRepository.save(television);
//        return ResponseEntity.created(null).body(television);
//    }

    @PostMapping("/televisions")
    public ResponseEntity <TelevisionOutputDto> createTelevision(@RequestBody TelevisionInputDto televisionInputDto) {

        TelevisionOutputDto televisionOutputDto = this.televisionService.createTelevision(televisionInputDto);

        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + televisionOutputDto.id).toUriString());

        return ResponseEntity.created(uri).body(televisionOutputDto);
    }


//    @PutMapping("/televisions/{id}")
//    public ResponseEntity<Television> updateTelevision(@RequestBody Television updateTelevision, @PathVariable Long id) {
//        Television television = new Television();
//
//        television.setId(id);
//        television.setType(updateTelevision.getType());
//        television.setName(updateTelevision.getName());
//        television.setRefreshRate(updateTelevision.getRefreshRate());
//        television.setAmbiLight(updateTelevision.getAmbiLight());
//        television.setBluetooth(updateTelevision.getBluetooth());
//        television.setHdr(updateTelevision.getHdr());
//        television.setOriginalStock(updateTelevision.getOriginalStock());
//        television.setPrice(updateTelevision.getPrice());
//        television.setAvailableSize(updateTelevision.getAvailableSize());
//        television.setBrand(updateTelevision.getBrand());
//        television.setScreenQuality(updateTelevision.getScreenQuality());
//        television.setScreenType(updateTelevision.getScreenType());
//        television.setSold(updateTelevision.getSold());
//        television.setWifi(updateTelevision.getWifi());
//        television.setVoiceControl(updateTelevision.getVoiceControl());
//        television.setSmartTv(updateTelevision.getSmartTv());
//
//    this.televisionRepository.save(television);
//            return ResponseEntity.noContent().build();
//    }

    @PutMapping("/televisions/{id}")
    public ResponseEntity<TelevisionInputDto> updateTelevision(@RequestBody TelevisionInputDto televisionInputDto, @PathVariable Long id) {

        this.televisionService.updateTelevision(id, televisionInputDto);

        return ResponseEntity.noContent().build();
    }

//    @DeleteMapping("/televisions/{id}")
//    public ResponseEntity<Object> deleteTelevision(@PathVariable Long id) {
//        televisionRepository.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }


    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable Long id) {
        this.televisionService.deleteTelevision(id);
        return ResponseEntity.noContent().build();
    }


}


