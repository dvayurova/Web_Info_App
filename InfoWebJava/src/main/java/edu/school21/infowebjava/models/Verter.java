package edu.school21.infowebjava.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;

@Data
@Entity
@Table(name="verter")
public class Verter implements EntityInterface{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer check;
    @Enumerated(EnumType.STRING)
    private CheckStatus state;
    private Time time;
}
