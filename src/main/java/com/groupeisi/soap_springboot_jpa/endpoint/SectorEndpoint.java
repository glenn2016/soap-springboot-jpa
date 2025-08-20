package com.groupeisi.soap_springboot_jpa.endpoint;

import com.groupeisi.soap_springboot_jpa.entity.Classe;
import com.groupeisi.soap_springboot_jpa.entity.Sector;
import com.groupeisi.soap_springboot_jpa.service.SectorService;
import com.groupeisi.soap_springboot_jpa.soap.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import java.util.List;
import java.util.Optional;

@Endpoint


public class SectorEndpoint {

    private static final String NAMESPACE_URI = "http://groupeisi.com/soap";

    private final SectorService sectorService;

    public SectorEndpoint(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllSectorsRequest")
    @ResponsePayload
    public GetAllSectorsResponse getAllSectors(@RequestPayload GetAllSectorsRequest request) {
        GetAllSectorsResponse response = new GetAllSectorsResponse();
        List<Sector> sectors = sectorService.getAllSectors();

        for (Sector s : sectors) {
            SectorType sectorType = new SectorType();
            sectorType.setId(s.getId());
            sectorType.setName(s.getName());

            for (Classe c : s.getClasses()) {
                ClasseType classeType = new ClasseType();
                classeType.setId(c.getId());
                classeType.setClasseName(c.getClasseName());
                classeType.setDescription(c.getDescription());
                sectorType.getClasses().add(classeType);
            }

            response.getSector().add(sectorType);
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetSectorByIdRequest")
    @ResponsePayload
    public GetSectorByIdResponse getSectorById(@RequestPayload GetSectorByIdRequest request) {
        GetSectorByIdResponse response = new GetSectorByIdResponse();

        Optional<Sector> sectorOpt = sectorService.getSectorById(request.getId());
        if (sectorOpt.isPresent()) {
            Sector s = sectorOpt.get();
            SectorType sectorType = new SectorType();
            sectorType.setId(s.getId());
            sectorType.setName(s.getName());

            for (Classe c : s.getClasses()) {
                ClasseType classeType = new ClasseType();
                classeType.setId(c.getId());
                classeType.setClasseName(c.getClasseName());
                classeType.setDescription(c.getDescription());
                sectorType.getClasses().add(classeType);
            }

            response.setSector(sectorType);
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreateSectorRequest")
    @ResponsePayload
    public CreateSectorResponse createSector(@RequestPayload CreateSectorRequest request) {
        CreateSectorResponse response = new CreateSectorResponse();

        try {
            // Validation des données d'entrée
            if (request.getName() == null || request.getName().trim().isEmpty()) {
                // Créer un secteur avec ID = -1 pour indiquer l'erreur
                SectorType errorSector = new SectorType();
                errorSector.setId(-1L);
                errorSector.setName("Erreur: Le nom du secteur ne peut pas être vide");
                response.setSector(errorSector);
                return response;
            }

            // Vérifier si un secteur avec ce nom existe déjà
            // Note: Vous devrez ajouter cette méthode au service
            String trimmedName = request.getName().trim();

            // Créer et sauvegarder le nouveau secteur
            Sector sector = new Sector();
            sector.setName(trimmedName);

            Sector savedSector = sectorService.saveSector(sector);

            // Créer la réponse avec le secteur créé (selon votre XSD)
            SectorType sectorType = new SectorType();
            sectorType.setId(savedSector.getId());
            sectorType.setName(savedSector.getName());
            // Pas de classes lors de la création

            response.setSector(sectorType);

        } catch (Exception e) {
            // En cas d'erreur, créer un secteur avec ID = -1
            SectorType errorSector = new SectorType();
            errorSector.setId(-1L);
            errorSector.setName("Erreur: " + e.getMessage());
            response.setSector(errorSector);
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeleteSectorRequest")
    @ResponsePayload
    public DeleteSectorResponse deleteSector(@RequestPayload DeleteSectorRequest request) {
        DeleteSectorResponse response = new DeleteSectorResponse();

        try {
            // Validation de l'ID
            if (request.getId() <= 0) {
                response.setSuccess(false);
                response.setMessage("ID du secteur invalide");
                return response;
            }

            // Vérifier si le secteur existe
            Optional<Sector> sectorOptional = sectorService.getSectorById(request.getId());

            if (sectorOptional.isEmpty()) {
                response.setSuccess(false);
                response.setMessage("Secteur avec l'ID " + request.getId() + " non trouvé");
                return response;
            }

            Sector sector = sectorOptional.get();

            // Vérifier s'il y a des classes associées (contrainte métier)
            if (sector.getClasses() != null && !sector.getClasses().isEmpty()) {
                response.setSuccess(false);
                response.setMessage("Impossible de supprimer le secteur '" + sector.getName() +
                        "'. Il contient " + sector.getClasses().size() + " classe(s) associée(s)");
                return response;
            }

            // Supprimer le secteur
            sectorService.deleteSector(request.getId());

            // Réponse de succès
            response.setSuccess(true);
            response.setMessage("Secteur '" + sector.getName() + "' supprimé avec succès");

        } catch (Exception e) {
            // Gestion des erreurs
            response.setSuccess(false);
            response.setMessage("Erreur lors de la suppression du secteur: " + e.getMessage());
        }

        return response;
    }
}
