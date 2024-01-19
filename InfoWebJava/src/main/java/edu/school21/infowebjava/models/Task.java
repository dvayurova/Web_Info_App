package edu.school21.infowebjava.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="tasks")
public class Task implements EntityInterface {
    @Id
    private String title;
    @Column(name = "parenttask")
    private String parentTask;
    @Column(name = "maxxp")
    private Integer maxXp;
}
