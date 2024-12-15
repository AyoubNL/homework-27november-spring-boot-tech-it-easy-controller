package nl.novi.techiteasycontroller.service;

import nl.novi.techiteasycontroller.dtos.CiModuleOutputDto;
import nl.novi.techiteasycontroller.exceptions.RecordNotFoundException;
import nl.novi.techiteasycontroller.models.CiModule;
import nl.novi.techiteasycontroller.repositories.CiModuleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CiModuleService {

    private final CiModuleRepository ciModuleRepository;

    public CiModuleService(CiModuleRepository ciModuleRepository) {
        this.ciModuleRepository = ciModuleRepository;
    }

    public List<CiModule> getAllCiModules() {

        List<CiModule> ciModules = ciModuleRepository.findAll();
        List<CiModuleOutputDto> ciModuleOutputDtos = new ArrayList<>();

        for(CiModule ciModule : ciModules) {
            ciModuleOutputDtos.add(transferToDto(ciModule));
        }
        return ciModules;
    }

    public CiModule getCiModuleById(Long id){

        Optional <CiModule> optionalCiModule = ciModuleRepository.findById(id);

        if(optionalCiModule.isPresent()) {
            return optionalCiModule.get();
        }
        else{
            throw new RecordNotFoundException("CiModule with id " + id + " not found");
        }

    }

    public CiModuleOutputDto addCiModule(CiModuleOutputDto ciModuleOutputDto) {

        this.ciModuleRepository.save(transferToCiModule(ciModuleOutputDto));

        return ciModuleOutputDto;

    }

    public void deleteCiModuleById(Long id){

        ciModuleRepository.deleteById(id);

    }

    public void updateCiModule(Long id, CiModuleOutputDto ciModuleOutputDto) {

        if (!ciModuleRepository.existsById(id)){
            throw new RecordNotFoundException("CiModule with id " + id + " not found");
        }
        CiModule ciModule = ciModuleRepository.findById(id).orElse(null);

        ciModule.setId(id);
        ciModule.setName(ciModuleOutputDto.getName());
        ciModule.setPrice(ciModuleOutputDto.getPrice());
        ciModule.setType(ciModuleOutputDto.getType());
        this.ciModuleRepository.save(ciModule);
    }

    public CiModuleOutputDto transferToDto(CiModule ciModule) {

        var ciModuleOutputDto = new CiModuleOutputDto();

        ciModuleOutputDto.setId(ciModule.getId());
        ciModuleOutputDto.setName(ciModule.getName());
        ciModuleOutputDto.setPrice(ciModule.getPrice());
        ciModuleOutputDto.setType(ciModule.getType());

        return ciModuleOutputDto;


    }

    public CiModule transferToCiModule(CiModuleOutputDto ciModuleOutputDto) {
        CiModule ciModule = new CiModule();

        ciModule.setId(ciModuleOutputDto.getId());
        ciModule.setName(ciModuleOutputDto.getName());
        ciModule.setPrice(ciModuleOutputDto.getPrice());
        ciModule.setType(ciModuleOutputDto.getType());

        return ciModule;

    }



}
