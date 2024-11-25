package nl.novi.techiteasycontroller.controllers;

import nl.novi.techiteasycontroller.models.Television;
import nl.novi.techiteasycontroller.repositories.TelevisionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TelevisionsController {

    private final TelevisionRepository televisionRepository;

    public TelevisionsController(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    @GetMapping("/televisions")
    public ResponseEntity<List<Television>> getTelevisions() {
        return ResponseEntity.ok(this.televisionRepository.findAll());
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity<Television> getTelevisionsById(@PathVariable Long id) {

        return ResponseEntity.ok(this.televisionRepository.findById(id).orElse(null));
    }

    @PostMapping("/televisions")
    public ResponseEntity<Television> createTelevision(@RequestBody Television television) {
        televisionRepository.save(television);
        return ResponseEntity.created(null).body(television);
    }

    @PutMapping("/televisions/{id}")
    public ResponseEntity<Television> updateTelevision(@RequestBody Television updateTelevision, @PathVariable Long id) {
        Television television = new Television();

        television.setId(id);
        television.setType(updateTelevision.getType());
        television.setName(updateTelevision.getName());
        television.setRefreshRate(updateTelevision.getRefreshRate());
        television.setAmbiLight(updateTelevision.getAmbiLight());
        television.setBluetooth(updateTelevision.getBluetooth());
        television.setHdr(updateTelevision.getHdr());
        television.setOriginalStock(updateTelevision.getOriginalStock());
        television.setPrice(updateTelevision.getPrice());
        television.setAvailableSize(updateTelevision.getAvailableSize());
        television.setBrand(updateTelevision.getBrand());
        television.setScreenQuality(updateTelevision.getScreenQuality());
        television.setScreenType(updateTelevision.getScreenType());
        television.setSold(updateTelevision.getSold());
        television.setWifi(updateTelevision.getWifi());
        television.setVoiceControl(updateTelevision.getVoiceControl());
        television.setSmartTv(updateTelevision.getSmartTv());

    this.televisionRepository.save(television);
            return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable Long id) {
        televisionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}


