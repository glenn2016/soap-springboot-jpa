package com.groupeisi.soap_springboot_jpa.repository;

import com.groupeisi.soap_springboot_jpa.entity.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long>{
    @Query("SELECT DISTINCT s FROM Sector s LEFT JOIN FETCH s.classes")
    List<Sector> findAllWithClasses();

    @Query("SELECT s FROM Sector s LEFT JOIN FETCH s.classes WHERE s.id = :id")
    Optional<Sector> findByIdWithClasses(@Param("id") Long id);

    boolean existsByName(String name);


}
