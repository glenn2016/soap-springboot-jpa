package com.groupeisi.soap_springboot_jpa.repository;

import com.groupeisi.soap_springboot_jpa.entity.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClasseRepository  extends JpaRepository<Classe, Long>{

    /**
     * Trouve toutes les classes appartenant à un secteur spécifique
     */
    @Query("SELECT c FROM Classe c WHERE c.sector.id = :sectorId")
    List<Classe> findBySectorId(@Param("sectorId") Long sectorId);

    /**
     * Trouve toutes les classes avec leur secteur associé (évite le lazy loading)
     */
    @Query("SELECT c FROM Classe c JOIN FETCH c.sector")
    List<Classe> findAllWithSector();

    /**
     * Vérifie si une classe avec ce nom existe déjà dans le même secteur
     */
    @Query("SELECT COUNT(c) > 0 FROM Classe c WHERE c.classeName = :classeName AND c.sector.id = :sectorId")
    boolean existsByClasseNameAndSectorId(@Param("classeName") String classeName, @Param("sectorId") Long sectorId);

    /**
     * Compte le nombre de classes dans un secteur
     */
    @Query("SELECT COUNT(c) FROM Classe c WHERE c.sector.id = :sectorId")
    long countBySectorId(@Param("sectorId") Long sectorId);
}
