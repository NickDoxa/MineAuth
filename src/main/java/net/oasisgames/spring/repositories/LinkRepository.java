package net.oasisgames.spring.repositories;

import net.oasisgames.spring.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkRepository extends JpaRepository<Link, String> {

    @Query("select lg.link from Link lg")
    List<String> getAllLinks();

    @Query(value = "select * from links where link = :code",
            nativeQuery = true)
    Link getUUIDByLink(String code);

    boolean existsByLink(String link);

}
