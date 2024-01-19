package edu.school21.infowebjava.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="transferredPoints")
public class TransferredPoint implements EntityInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "checkingpeer")
    private String checkingPeer;
    @Column(name = "checkedpeer")
    private String checkedPeer;
    @Column(name = "pointsamount")
    private Integer pointsAmount;
}
