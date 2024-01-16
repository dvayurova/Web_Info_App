package edu.school21.infowebjava.models;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="recommendations")
public class Recommendation implements EntityInterface{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String peer;
    private String recommendedPeer;

}
