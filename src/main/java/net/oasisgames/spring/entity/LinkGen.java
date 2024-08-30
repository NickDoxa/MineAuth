package net.oasisgames.spring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Entity
@Data
@Table(name = "links")
@Component
@Scope("prototype")
public class LinkGen {

    @Id
    private UUID uuid;
    private String link;

}
