package me.eduspace.service;

import lombok.RequiredArgsConstructor;
import me.eduspace.dto.time.TimeRequestDTO;
import me.eduspace.dto.time.TimeResponseDTO;
import me.eduspace.entity.TimeEntity;
import me.eduspace.repository.TimeRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeService {
    private final TimeRepository timeRepository;

    public TimeResponseDTO create(TimeRequestDTO dto){

       /* TimeEntity entity=new TimeEntity();
        entity.setStartedTime(dto.getStartedTime());
        entity.setEndTime(dto.getEndTime());*/
        return null;

    }
}
