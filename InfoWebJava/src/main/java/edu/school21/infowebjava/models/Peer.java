package edu.school21.infowebjava.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name="peers")
public class Peer {
    @Id
    private String nickname;

    private Date birthday;
}
