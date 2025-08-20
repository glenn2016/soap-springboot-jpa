package com.groupeisi.soap_springboot_jpa.endpoint;

import com.groupeisi.soap_springboot_jpa.entity.Classe;
import com.groupeisi.soap_springboot_jpa.entity.Sector;
import com.groupeisi.soap_springboot_jpa.service.ClasseService;
import com.groupeisi.soap_springboot_jpa.soap.*;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import java.util.List;
import java.util.Optional;

@Endpoint
public class ClasseEndpoint {

    private static final String NAMESPACE_URI = "http://groupeisi.com/soap";

    private final ClasseService classeService;

    public ClasseEndpoint(ClasseService classeService) {
        this.classeService = classeService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllClassesRequest")
    @ResponsePayload
    public GetAllClassesResponse getAllClasses(@RequestPayload GetAllClassesRequest request) {
        GetAllClassesResponse response = new GetAllClassesResponse();
        List<Classe> classes = classeService.getAllClasses();

        for (Classe c : classes) {
            ClasseType classeType = new ClasseType();
            classeType.setId(c.getId());
            classeType.setClasseName(c.getClasseName());
            classeType.setDescription(c.getDescription());

            // Ajouter les informations du secteur
            if (c.getSector() != null) {
                classeType.setSectorId(c.getSector().getId());
                classeType.setSectorName(c.getSector().getName());
            }

            response.getClasse().add(classeType);
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetClasseByIdRequest")
    @ResponsePayload
    public GetClasseByIdResponse getClasseById(@RequestPayload GetClasseByIdRequest request) {
        GetClasseByIdResponse response = new GetClasseByIdResponse();

        Optional<Classe> classeOpt = classeService.getClasseById(request.getId());
        if (classeOpt.isPresent()) {
            Classe c = classeOpt.get();
            ClasseType classeType = new ClasseType();
            classeType.setId(c.getId());
            classeType.setClasseName(c.getClasseName());
            classeType.setDescription(c.getDescription());

            // Ajouter les informations du secteur
            if (c.getSector() != null) {
                classeType.setSectorId(c.getSector().getId());
                classeType.setSectorName(c.getSector().getName());
            }

            response.setClasse(classeType);
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetClassesBySectorIdRequest")
    @ResponsePayload
    public GetClassesBySectorIdResponse getClassesBySectorId(@RequestPayload GetClassesBySectorIdRequest request) {
        GetClassesBySectorIdResponse response = new GetClassesBySectorIdResponse();
        List<Classe> classes = classeService.getClassesBySectorId(request.getSectorId());

        for (Classe c : classes) {
            ClasseType classeType = new ClasseType();
            classeType.setId(c.getId());
            classeType.setClasseName(c.getClasseName());
            classeType.setDescription(c.getDescription());

            if (c.getSector() != null) {
                classeType.setSectorId(c.getSector().getId());
                classeType.setSectorName(c.getSector().getName());
            }

            response.getClasse().add(classeType);
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreateClasseRequest")
    @ResponsePayload
    public CreateClasseResponse createClasse(@RequestPayload CreateClasseRequest request) {
        CreateClasseResponse response = new CreateClasseResponse();

        try {
            // Validation des données d'entrée
            if (request.getClasseName() == null || request.getClasseName().trim().isEmpty()) {
                ClasseType errorClasse = new ClasseType();
                errorClasse.setId(-1L);
                errorClasse.setClasseName("Erreur: Le nom de la classe ne peut pas être vide");
                errorClasse.setDescription("");
                response.setClasse(errorClasse);
                return response;
            }

            if (request.getSectorId() <= 0) {
                ClasseType errorClasse = new ClasseType();
                errorClasse.setId(-1L);
                errorClasse.setClasseName("Erreur: Un secteur valide doit être spécifié");
                errorClasse.setDescription("");
                response.setClasse(errorClasse);
                return response;
            }

            // Créer la nouvelle classe
            Classe classe = new Classe();
            classe.setClasseName(request.getClasseName().trim());
            classe.setDescription(request.getDescription() != null ? request.getDescription().trim() : "");

            // Créer un secteur temporaire avec l'ID pour la validation
            Sector sector = new Sector();
            sector.setId(request.getSectorId());
            classe.setSector(sector);

            // Sauvegarder
            Classe savedClasse = classeService.saveClasse(classe);

            // Créer la réponse
            ClasseType classeType = new ClasseType();
            classeType.setId(savedClasse.getId());
            classeType.setClasseName(savedClasse.getClasseName());
            classeType.setDescription(savedClasse.getDescription());
            classeType.setSectorId(savedClasse.getSector().getId());
            classeType.setSectorName(savedClasse.getSector().getName());

            response.setClasse(classeType);

        } catch (Exception e) {
            ClasseType errorClasse = new ClasseType();
            errorClasse.setId(-1L);
            errorClasse.setClasseName("Erreur: " + e.getMessage());
            errorClasse.setDescription("");
            response.setClasse(errorClasse);
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "UpdateClasseRequest")
    @ResponsePayload
    public UpdateClasseResponse updateClasse(@RequestPayload UpdateClasseRequest request) {
        UpdateClasseResponse response = new UpdateClasseResponse();

        try {
            // Validation des données d'entrée
            if (request.getId() <= 0) {
                ClasseType errorClasse = new ClasseType();
                errorClasse.setId(-1L);
                errorClasse.setClasseName("Erreur: ID de classe invalide");
                errorClasse.setDescription("");
                response.setClasse(errorClasse);
                return response;
            }

            if (request.getClasseName() == null || request.getClasseName().trim().isEmpty()) {
                ClasseType errorClasse = new ClasseType();
                errorClasse.setId(-1L);
                errorClasse.setClasseName("Erreur: Le nom de la classe ne peut pas être vide");
                errorClasse.setDescription("");
                response.setClasse(errorClasse);
                return response;
            }

            // Créer l'objet classe pour la mise à jour
            Classe updatedClasse = new Classe();
            updatedClasse.setClasseName(request.getClasseName().trim());
            updatedClasse.setDescription(request.getDescription() != null ? request.getDescription().trim() : "");

            if (request.getSectorId() > 0) {
                Sector sector = new Sector();
                sector.setId(request.getSectorId());
                updatedClasse.setSector(sector);
            }

            // Mettre à jour
            Classe savedClasse = classeService.updateClasse(request.getId(), updatedClasse);

            // Créer la réponse
            ClasseType classeType = new ClasseType();
            classeType.setId(savedClasse.getId());
            classeType.setClasseName(savedClasse.getClasseName());
            classeType.setDescription(savedClasse.getDescription());
            classeType.setSectorId(savedClasse.getSector().getId());
            classeType.setSectorName(savedClasse.getSector().getName());

            response.setClasse(classeType);

        } catch (Exception e) {
            ClasseType errorClasse = new ClasseType();
            errorClasse.setId(-1L);
            errorClasse.setClasseName("Erreur: " + e.getMessage());
            errorClasse.setDescription("");
            response.setClasse(errorClasse);
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "DeleteClasseRequest")
    @ResponsePayload
    public DeleteClasseResponse deleteClasse(@RequestPayload DeleteClasseRequest request) {
        DeleteClasseResponse response = new DeleteClasseResponse();

        try {
            // Validation de l'ID
            if (request.getId() <= 0) {
                response.setSuccess(false);
                response.setMessage("ID de classe invalide");
                return response;
            }

            // Vérifier si la classe existe
            Optional<Classe> classeOptional = classeService.getClasseById(request.getId());

            if (classeOptional.isEmpty()) {
                response.setSuccess(false);
                response.setMessage("Classe avec l'ID " + request.getId() + " non trouvée");
                return response;
            }

            Classe classe = classeOptional.get();

            // Supprimer la classe
            classeService.deleteClasse(request.getId());

            // Réponse de succès
            response.setSuccess(true);
            response.setMessage("Classe '" + classe.getClasseName() + "' supprimée avec succès");

        } catch (Exception e) {
            // Gestion des erreurs
            response.setSuccess(false);
            response.setMessage("Erreur lors de la suppression de la classe: " + e.getMessage());
        }

        return response;
    }
}
