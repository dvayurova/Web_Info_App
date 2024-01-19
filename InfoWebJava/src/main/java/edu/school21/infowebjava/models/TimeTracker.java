package edu.school21.infowebjava.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
@Entity
@Table(name="timetracking")
public class TimeTracker implements EntityInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String peer;
    private Date date;
    private Time time;
    private Integer state;
}
