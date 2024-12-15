package nl.novi.techiteasycontroller.service;

import nl.novi.techiteasycontroller.dtos.WallbracketOutputDto;
import nl.novi.techiteasycontroller.exceptions.RecordNotFoundException;
import nl.novi.techiteasycontroller.models.WallBracket;
import nl.novi.techiteasycontroller.repositories.WallBracketRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WallbracketService {

    private final WallBracketRepository wallBracketRepository;

    public WallbracketService(WallBracketRepository wallBracketRepository) {
        this.wallBracketRepository = wallBracketRepository;
    }

    public List<WallbracketOutputDto> getAllWallbrackets() {

        List<WallBracket> wallBrackets = wallBracketRepository.findAll();
        List<WallbracketOutputDto> wallbracketOutputDtoList = new ArrayList<>();

        for (WallBracket wallBracket : wallBrackets) {
            wallbracketOutputDtoList.add(transferToDto(wallBracket));
        }

        return wallbracketOutputDtoList;

    }

    public WallbracketOutputDto getWallbracketById(Long id) {
        Optional<WallBracket> wallBracket = wallBracketRepository.findById(id);

        if (wallBracket.isPresent()) {
            WallbracketOutputDto dto = transferToDto(wallBracket.get());
            return dto;
        } else {
            throw new RecordNotFoundException("Wallbracket not found");
        }
    }

    public WallbracketOutputDto addWallbracket(WallbracketOutputDto wallbracketOutputDto) {

        WallBracket wallBracket = transferToWallbracket(wallbracketOutputDto);
        this.wallBracketRepository.save(wallBracket);

        return transferToDto(wallBracket);

    }

    public void deleteWallbracket(Long id) {
        this.wallBracketRepository.deleteById(id);
    }

    public WallbracketOutputDto updateWallbracket(WallbracketOutputDto wallbracketOutputDto, Long id) {

        if (!wallBracketRepository.existsById(id)) {
            throw new RecordNotFoundException("Wallbracket not found");
        }

        WallBracket newWallBracket = wallBracketRepository.findById(id).orElse(null);
        newWallBracket.setName(wallbracketOutputDto.getName());
        newWallBracket.setAdjustable(wallbracketOutputDto.getAdjustable());
        newWallBracket.setPrice(wallbracketOutputDto.getPrice());
        newWallBracket.setSize(wallbracketOutputDto.getSize());


        return transferToDto(this.wallBracketRepository.save(newWallBracket));


    }

    public WallbracketOutputDto transferToDto(WallBracket wallBracket) {

        var dto = new WallbracketOutputDto();

        dto.setId(wallBracket.getId());
        dto.setName(wallBracket.getName());
        dto.setAdjustable(wallBracket.getAdjustable());
        dto.setPrice(wallBracket.getPrice());
        dto.setSize(wallBracket.getSize());

        return dto;

    }

    public WallBracket transferToWallbracket(WallbracketOutputDto wallbracketOutputDto) {
        var WallBracket = new WallBracket();

        WallBracket.setName(wallbracketOutputDto.getName());
        WallBracket.setAdjustable(wallbracketOutputDto.getAdjustable());
        WallBracket.setPrice(wallbracketOutputDto.getPrice());
        WallBracket.setSize(wallbracketOutputDto.getSize());

        return WallBracket;
    }


}
