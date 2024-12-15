package nl.novi.techiteasycontroller.controllers;

import nl.novi.techiteasycontroller.dtos.WallbracketOutputDto;
import nl.novi.techiteasycontroller.models.WallBracket;
import nl.novi.techiteasycontroller.service.WallbracketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WallbracketController {

    private final WallbracketService wallbracketService;

    public WallbracketController(WallbracketService wallbracketService) {
        this.wallbracketService = wallbracketService;
    }

    @GetMapping("/wallbrackets")
    public ResponseEntity<List<WallbracketOutputDto>> getAllWallbrackets() {
        List<WallbracketOutputDto> wallbracketOutputDtos = this.wallbracketService.getAllWallbrackets();

        return ResponseEntity.ok(wallbracketOutputDtos);


    }

    @GetMapping("/wallbrackets/{id}")
    public ResponseEntity<WallbracketOutputDto> getWallbracketById(@PathVariable long id) {

        WallbracketOutputDto wallbracketOutputDto = wallbracketService.getWallbracketById(id);

        return ResponseEntity.ok(wallbracketOutputDto);

    }

    @PostMapping("/wallbrackets")
    public ResponseEntity<WallbracketOutputDto> addWallbracket(@RequestBody WallbracketOutputDto wallbracketOutputDto) {
        WallbracketOutputDto wallbracketOutputDto1 = this.wallbracketService.addWallbracket(wallbracketOutputDto);
        return ResponseEntity.created(null).body(wallbracketOutputDto1);
    }

    @DeleteMapping("/wallbrackets/{id}")
    public ResponseEntity<Object> deleteWallbracket(@PathVariable Long id) {
        this.wallbracketService.deleteWallbracket(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/wallbrackets/{id}")
    public ResponseEntity<WallbracketOutputDto> updateWallbracket(@RequestBody WallbracketOutputDto wallbracketOutputDto, @PathVariable Long id) {

        this.wallbracketService.updateWallbracket(wallbracketOutputDto, id);

        return ResponseEntity.noContent().build();

    }





}
