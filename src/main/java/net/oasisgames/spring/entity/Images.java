package net.oasisgames.spring.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "images")
public class Images {

    @Id
    private String id;
    private String url;

}
