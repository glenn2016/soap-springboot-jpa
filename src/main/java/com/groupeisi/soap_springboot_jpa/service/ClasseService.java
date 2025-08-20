package com.groupeisi.soap_springboot_jpa.service;


import com.groupeisi.soap_springboot_jpa.entity.Classe;
import com.groupeisi.soap_springboot_jpa.entity.Sector;
import com.groupeisi.soap_springboot_jpa.repository.ClasseRepository;
import com.groupeisi.soap_springboot_jpa.repository.SectorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClasseService {

    private final ClasseRepository classeRepository;
    private final SectorRepository sectorRepository;

    public ClasseService(ClasseRepository classeRepository, SectorRepository sectorRepository) {
        this.classeRepository = classeRepository;
        this.sectorRepository = sectorRepository;
    }

    @Transactional
    public Classe saveClasse(Classe classe) {
        // Validation du secteur associé
        if (classe.getSector() == null || classe.getSector().getId() == null) {
            throw new IllegalArgumentException("Une classe doit être associée à un secteur valide");
        }

        // Vérifier que le secteur existe
        Optional<Sector> sectorOpt = sectorRepository.findById(classe.getSector().getId());
        if (sectorOpt.isEmpty()) {
            throw new IllegalArgumentException("Le secteur avec l'ID " + classe.getSector().getId() + " n'existe pas");
        }

        // Assigner le secteur complet à la classe
        classe.setSector(sectorOpt.get());

        return classeRepository.save(classe);
    }

    @Transactional(readOnly = true)
    public List<Classe> getAllClasses() {
        return classeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Classe> getClasseById(Long id) {
        return classeRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Classe> getClassesBySectorId(Long sectorId) {
        // Vous devrez ajouter cette méthode au repository
        return classeRepository.findBySectorId(sectorId);
    }

    @Transactional
    public Classe updateClasse(Long id, Classe updatedClasse) {
        Optional<Classe> existingClasseOpt = classeRepository.findById(id);
        if (existingClasseOpt.isEmpty()) {
            throw new IllegalArgumentException("Classe avec l'ID " + id + " non trouvée");
        }

        Classe existingClasse = existingClasseOpt.get();

        // Validation du secteur si fourni
        if (updatedClasse.getSector() != null && updatedClasse.getSector().getId() != null) {
            Optional<Sector> sectorOpt = sectorRepository.findById(updatedClasse.getSector().getId());
            if (sectorOpt.isEmpty()) {
                throw new IllegalArgumentException("Le secteur avec l'ID " + updatedClasse.getSector().getId() + " n'existe pas");
            }
            existingClasse.setSector(sectorOpt.get());
        }

        // Mise à jour des champs
        if (updatedClasse.getClasseName() != null && !updatedClasse.getClasseName().trim().isEmpty()) {
            existingClasse.setClasseName(updatedClasse.getClasseName().trim());
        }

        if (updatedClasse.getDescription() != null) {
            existingClasse.setDescription(updatedClasse.getDescription().trim());
        }

        return classeRepository.save(existingClasse);
    }

    @Transactional
    public void deleteClasse(Long id) {
        if (!classeRepository.existsById(id)) {
            throw new IllegalArgumentException("Classe avec l'ID " + id + " non trouvée");
        }
        classeRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return classeRepository.existsById(id);
    }
}
