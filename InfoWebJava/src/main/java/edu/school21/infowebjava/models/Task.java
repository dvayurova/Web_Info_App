package edu.school21.infowebjava.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="tasks")
public class Task implements EntityInterface{
    @Id
    private String title;
    private String parentTask;
    private Integer maxXp;
}