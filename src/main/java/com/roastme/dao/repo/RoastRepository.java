package com.roastme.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.roastme.dao.Roast;

public interface RoastRepository extends JpaRepository<Roast, Long> {

    @Query(value = "SELECT * FROM roasts WHERE type = :type ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Roast getRandomRoastByType(@Param("type") String type);

}
