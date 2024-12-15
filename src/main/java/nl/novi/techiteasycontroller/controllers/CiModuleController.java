package nl.novi.techiteasycontroller.controllers;

import nl.novi.techiteasycontroller.dtos.CiModuleOutputDto;
import nl.novi.techiteasycontroller.models.CiModule;
import nl.novi.techiteasycontroller.service.CiModuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CiModuleController {

    private final CiModuleService ciModuleService;

    public CiModuleController(CiModuleService ciModuleService) {
        this.ciModuleService = ciModuleService;
    }

    @GetMapping("/ci-modules")
    public ResponseEntity <List<CiModule>> getAllCiModules() {


        List<CiModule> ciModules = this.ciModuleService.getAllCiModules();

        return ResponseEntity.ok(ciModules);
    }

    @GetMapping("/ci-modules/{id}")
    public ResponseEntity <CiModule> getCiModuleById(@PathVariable Long id) {

        CiModule ciModule = this.ciModuleService.getCiModuleById(id);

        return ResponseEntity.ok(ciModule);
    }

    @PostMapping("/ci-modules")
    public ResponseEntity <CiModuleOutputDto> addCiModule(@RequestBody CiModuleOutputDto ciModuleOutputDto) {
        CiModuleOutputDto ciModuleOutputDto1 = this.ciModuleService.addCiModule(ciModuleOutputDto);
        return ResponseEntity.ok(ciModuleOutputDto1);
    }

    @DeleteMapping("/ci-modules/{id}")
    public ResponseEntity<Object> deleteCiModuleById(@PathVariable Long id) {
        this.ciModuleService.deleteCiModuleById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/ci-modules/{id}")
    public ResponseEntity<CiModuleOutputDto> updateCiModule(@PathVariable Long id, @RequestBody CiModuleOutputDto updatedCiModuleOutputDto) {

        this.ciModuleService.updateCiModule(id, updatedCiModuleOutputDto);
        return ResponseEntity.ok(updatedCiModuleOutputDto);

    }


}
