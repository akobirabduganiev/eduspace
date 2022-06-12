package me.eduspace.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.eduspace.dto.payment.PaymentRequestDTO;
import me.eduspace.dto.payment.PaymentResponseDTO;
import me.eduspace.dto.user.UserResponseDTO;
import me.eduspace.entity.PaymentEntity;
import me.eduspace.exceptions.ItemNotFoundException;
import me.eduspace.repository.PaymentRepository;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserService userService;

    public PaymentResponseDTO create(Long userId, PaymentRequestDTO dto){
        userService.checkOrGet(userId);
        PaymentEntity entity=new PaymentEntity();
        entity.setAmount(dto.getAmount());
        entity.setUserId(userId);
        entity.setType(dto.getType());

        paymentRepository.save(entity);

        return toDTO(entity);
    }

    public PaymentResponseDTO getById(Long id){
        return toDTO(checkOrGet(id));
    }

    public PageImpl<PaymentResponseDTO> getPaginationByUserId(Long userId, Integer page, Integer size){
        userService.checkOrGet(userId);
        Pageable pageable= PageRequest.of(page, size, Sort.Direction.DESC, "createdDate");

        var pagination=paymentRepository.findAllByUserId(userId, pageable);

        var list=pagination
                .stream()
                .map(this::toDTO)
                .toList();
        return new PageImpl<>(list, pageable, pagination.getTotalElements());
    }


    /**
     * OTHER METHODS*/

    public PaymentEntity checkOrGet(Long id){
        return paymentRepository.findById(id)
                .orElseThrow(()->{
                    log.info("payment not found {}", id);
                    throw new ItemNotFoundException("payment not found");
                });
    }

    private PaymentResponseDTO toDTO(PaymentEntity entity) {
        var user=entity.getUser();
        return PaymentResponseDTO.builder()
                .id(entity.getId())
                .type(entity.getType())
                .amount(entity.getAmount())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .user(UserResponseDTO.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .surname(user.getSurname())
                        .email(user.getEmail())
                        .build())
                .build();
    }
}
