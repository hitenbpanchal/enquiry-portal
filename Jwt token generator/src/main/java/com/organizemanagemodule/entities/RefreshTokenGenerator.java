package com.organizemanagemodule.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshTokenGenerator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String token;
    private Instant expiry;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
