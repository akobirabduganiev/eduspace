package me.eduspace.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.eduspace.dto.learningcenter.LearningCenterDTO;
import me.eduspace.dto.learningcenter.LearningCenterRegistrationRequestDTO;
import me.eduspace.entity.LearningCenterEntity;
import me.eduspace.repository.LearningCenterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LearningCenterService {
    private final LearningCenterRepository learningCenterRepository;

    public LearningCenterDTO create(LearningCenterRegistrationRequestDTO dto){
        var entity = new LearningCenterEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPhone(dto.getPhone());
        learningCenterRepository.save(entity);

        return toDTO(entity);
    }



    private LearningCenterDTO toDTO(LearningCenterEntity entity){
        var dto = new LearningCenterDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPhone(entity.getPhone());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setDescription(entity.getDescription());

        return dto;

    }
}
