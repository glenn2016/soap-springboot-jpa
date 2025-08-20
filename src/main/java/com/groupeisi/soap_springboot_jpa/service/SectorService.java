package com.groupeisi.soap_springboot_jpa.service;

import com.groupeisi.soap_springboot_jpa.entity.Sector;
import com.groupeisi.soap_springboot_jpa.repository.SectorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SectorService {

    private final SectorRepository sectorRepository;

    public SectorService(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    @Transactional
    public Sector saveSector(Sector sector) {
        // Validation des doublons avant sauvegarde
        if (sector.getName() != null && sectorRepository.existsByName(sector.getName().trim())) {
            throw new IllegalArgumentException("Un secteur avec le nom '" + sector.getName() + "' existe déjà");
        }
        return sectorRepository.save(sector);
    }

    @Transactional(readOnly = true)
    public List<Sector> getAllSectors() {
        return sectorRepository.findAllWithClasses();
    }

    @Transactional(readOnly = true)
    public Optional<Sector> getSectorById(Long id) {
        return sectorRepository.findByIdWithClasses(id);
    }

    @Transactional
    public void deleteSector(Long id) {
        sectorRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return sectorRepository.existsById(id);
    }

    // Nouvelle méthode pour vérifier les doublons de noms
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return sectorRepository.existsByName(name);
    }
}
