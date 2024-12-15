package nl.novi.techiteasycontroller.controllers;

import jakarta.validation.Valid;
import nl.novi.techiteasycontroller.dtos.TelevisionInputDto;
import nl.novi.techiteasycontroller.dtos.TelevisionOutputDto;
import nl.novi.techiteasycontroller.models.WallBracket;
import nl.novi.techiteasycontroller.repositories.TelevisionRepository;
import nl.novi.techiteasycontroller.service.TelevisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class TelevisionsController {


    private final TelevisionService televisionService;

    public TelevisionsController(TelevisionRepository televisionRepository, TelevisionService televisionService) {
        this.televisionService = televisionService;
    }

    @GetMapping("/televisions")
    public ResponseEntity <List<TelevisionOutputDto>> getAllTelevisions(@RequestParam(value = "brand", required = false) Optional<String> brand) {

        List<TelevisionOutputDto> dtos;

        if (brand.isEmpty()){
            dtos = televisionService.getAllTelevisions();
        }

        else {
            dtos = televisionService.getAllTelevisionsByBrand(brand.get());
        }

        return ResponseEntity.ok().body(dtos);

//        return ResponseEntity.ok(this.televisionService.getAllTelevisions());
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity <TelevisionOutputDto> getTelevisionsById(@PathVariable Long id) {

        TelevisionOutputDto television = this.televisionService.getTelevisionById(id);

        return ResponseEntity.ok(television);
    }

    @PostMapping("/televisions")
    public ResponseEntity <TelevisionOutputDto> createTelevision(@Valid @RequestBody TelevisionInputDto televisionInputDto) {

        TelevisionOutputDto televisionOutputDto = this.televisionService.createTelevision(televisionInputDto);

        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/" + televisionOutputDto.id).toUriString());

        return ResponseEntity.created(uri).body(televisionOutputDto);
    }


    @PutMapping("/televisions/{id}")
    public ResponseEntity<TelevisionOutputDto> updateTelevision(@Valid @RequestBody TelevisionInputDto televisionInputDto, @PathVariable Long id) {

        TelevisionOutputDto televisionOutputDto = this.televisionService.updateTelevision(id, televisionInputDto);

        return ResponseEntity.ok().body(televisionOutputDto);
    }


    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable Long id) {
        this.televisionService.deleteTelevision(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/televisions/{id}/remotecontroller/{remoteId}")
    public void assignRemoteControllerToTelevision(@PathVariable Long id, @PathVariable Long remoteId) {

        this.televisionService.assignRemoteControllerToTelevision(id, remoteId);

    }

    @PutMapping("/televisions/{id}/wallbracket")
    public void assignWallBracketToTelevision(@PathVariable Long id, @RequestBody WallBracket wallBracket) {
        this.televisionService.assignWallBracketToTelevision(id, wallBracket);
    }


    @PutMapping("/televisions/{id}/cimodule/{ciModuleId}")
    public void assignCIModuleToTelevision(@PathVariable Long id, @PathVariable Long ciModuleId) {
        this.televisionService.assignCIModuleToTelevision(id, ciModuleId);
    }


}


