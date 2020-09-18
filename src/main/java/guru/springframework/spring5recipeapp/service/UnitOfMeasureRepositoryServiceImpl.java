package guru.springframework.spring5recipeapp.service;

import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import guru.springframework.spring5recipeapp.dto.UnitOfMeasureDTO;
import guru.springframework.spring5recipeapp.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UnitOfMeasureRepositoryServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    public UnitOfMeasureRepositoryServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public UnitOfMeasureDTO getUOMByName(String name) {
        UnitOfMeasure unitOfMeasure = unitOfMeasureRepository.findByName(name).orElseThrow(() -> new RuntimeException("UOM " + name + " not " +
                "found!"));

        UnitOfMeasureDTO unitOfMeasureDTO = new UnitOfMeasureDTO();
        BeanUtils.copyProperties(unitOfMeasure, unitOfMeasureDTO);

        return unitOfMeasureDTO;
    }
}
