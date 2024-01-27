package edu.school21.infowebjava.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Data
@Entity
@Table(name = "p2p")
public class P2P implements EntityInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "check_id")
    private Integer check_id;
    @Column(name = "checkingpeer")
    private String checkingPeer;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private CheckStatus state;

    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime time;

}
