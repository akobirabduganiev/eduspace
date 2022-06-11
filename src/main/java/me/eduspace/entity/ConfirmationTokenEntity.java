package me.eduspace.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.eduspace.enums.ConfirmationStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "confirmation_token")
@Entity
public class ConfirmationTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;
    @Column
    @Enumerated(EnumType.STRING)
    private ConfirmationStatus status = ConfirmationStatus.ACTIVE;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private UserEntity userEntity;

    public ConfirmationTokenEntity(String token,
                                   LocalDateTime createdAt,
                                   LocalDateTime expiresAt,
                                   ConfirmationStatus status,
                                   UserEntity userEntity) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.status = status;
        this.userEntity = userEntity;
    }
}
