package nl.novi.techiteasycontroller.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TelevisionsController {

    @GetMapping("/televisions")
    public ResponseEntity<String> getTelevisions() {
        return ResponseEntity.ok("Televisions");
    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity<String> getTelevisionsById(@PathVariable int id){
        return ResponseEntity.ok("Televisions with id: " + id);
    }

    @PostMapping("/televisions")
    public ResponseEntity<String> createTelevision(){
        return ResponseEntity.created(null).body("Televisions created");
    }

    @PutMapping("/televisions/{id}")
    public ResponseEntity<String> updateTelevision(@PathVariable int id){
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<String> deleteTelevision(@PathVariable int id){
        return ResponseEntity.noContent().build();
    }




}


