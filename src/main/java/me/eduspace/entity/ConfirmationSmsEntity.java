package me.eduspace.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "confirmation_sms")
@Entity
public class ConfirmationSmsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String sms;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    private UserEntity userEntity;

    public ConfirmationSmsEntity(String sms,
                                 LocalDateTime createdAt,
                                 LocalDateTime expiresAt,
                                 UserEntity userEntity) {
        this.sms = sms;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.userEntity = userEntity;
    }
}
