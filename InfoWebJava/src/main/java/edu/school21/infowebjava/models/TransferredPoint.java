package edu.school21.infowebjava.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="transferredPoints")
public class TransferredPoint implements EntityInterface{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String checkingPeer;
    private String checkedPeer;
    private Integer pointsAmount;
}
