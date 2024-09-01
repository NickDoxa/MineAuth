package net.oasisgames.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Entity
@Data
@Table(name = "links")
@Component
@Scope("prototype")
public class Link {

    @Id
    private String uuid;
    @Column(unique = true)
    private String link;

}
