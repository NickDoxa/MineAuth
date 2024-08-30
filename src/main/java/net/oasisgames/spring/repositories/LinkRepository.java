package net.oasisgames.spring.repositories;

import net.oasisgames.spring.entity.LinkGen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LinkRepository extends JpaRepository<LinkGen, UUID> {

    @Query("select lg.link from LinkGen lg")
    List<String> getAllLinks();

}
