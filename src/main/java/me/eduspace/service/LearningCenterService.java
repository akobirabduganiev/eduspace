package me.eduspace.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.eduspace.dto.learningcenter.LearningCenterDTO;
import me.eduspace.dto.learningcenter.LearningCenterRequestDTO;
import me.eduspace.entity.LearningCenterEntity;
import me.eduspace.repository.LearningCenterRepository;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LearningCenterService {
    private final LearningCenterRepository learningCenterRepository;

    public LearningCenterDTO create(LearningCenterRequestDTO dto) {
        var entity = new LearningCenterEntity();

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        learningCenterRepository.save(entity);

        return toDTO(entity);
    }

    public PageImpl<LearningCenterDTO> getPagination(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdDate");

        var pagination = learningCenterRepository.findAll(pageable);

        var list = pagination
                .stream()
                .map(this::toDTO)
                .toList();

        return new PageImpl<>(list, pageable, pagination.getTotalElements());
    }

    public LearningCenterDTO getById(Long id) {
        return null;
    }

    private LearningCenterDTO toDTO(LearningCenterEntity entity) {
        var dto = new LearningCenterDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setDescription(entity.getDescription());

        return dto;

    }
}
