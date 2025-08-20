package com.groupeisi.soap_springboot_jpa.entity;

import jakarta.persistence.*;

@Entity
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String classeName;
    private String description;

    // Une Classe appartient à un seul Sector
    @ManyToOne
    @JoinColumn(name = "sector_id", nullable = false)
    private Sector sector;

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getClasseName() { return classeName; }
    public void setClasseName(String classeName) { this.classeName = classeName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Sector getSector() { return sector; }
    public void setSector(Sector sector) { this.sector = sector; }
}
