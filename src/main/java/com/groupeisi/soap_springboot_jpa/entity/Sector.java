package com.groupeisi.soap_springboot_jpa.entity;


import jakarta.persistence.*;
import java.util.List;

@Entity
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Un Sector peut avoir 0 ou plusieurs Classes
    @OneToMany(mappedBy = "sector", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Classe> classes;

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Classe> getClasses() { return classes; }
    public void setClasses(List<Classe> classes) { this.classes = classes; }
}
