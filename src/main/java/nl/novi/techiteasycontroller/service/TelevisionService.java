package nl.novi.techiteasycontroller.service;

import nl.novi.techiteasycontroller.models.Television;
import nl.novi.techiteasycontroller.repositories.TelevisionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelevisionService {

    final private TelevisionRepository televisionRepository;

    public TelevisionService(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    public List<Television> getAllTelevisions() {
        return televisionRepository.findAll();
    }




}
