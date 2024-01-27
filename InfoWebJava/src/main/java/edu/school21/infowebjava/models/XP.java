package edu.school21.infowebjava.models;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="xp")
public class XP implements EntityInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "check_id")
    private Integer check_id;
    @Column(name = "xpamount")
    private Integer xpAmount;
}
