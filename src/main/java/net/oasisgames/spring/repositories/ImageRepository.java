package net.oasisgames.spring.repositories;

import net.oasisgames.spring.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Images, String> {}
