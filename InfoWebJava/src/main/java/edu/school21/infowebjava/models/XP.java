package edu.school21.infowebjava.models;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="xp")
public class XP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer check;
    private Integer xpAmount;
}
