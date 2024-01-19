package edu.school21.infowebjava.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;

@Data
@Entity
@Table(name = "p2p")
public class P2P implements EntityInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "check_")
    private Integer check_;
    @Column(name = "checkingpeer")
    private String checkingPeer;

    @Enumerated(EnumType.STRING)
    private CheckStatus state;

    private Time time;

}
