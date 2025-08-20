package com.groupeisi.soap_springboot_jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.groupeisi.soap_springboot_jpa.entity.Classe;
import com.groupeisi.soap_springboot_jpa.entity.Sector;
import com.groupeisi.soap_springboot_jpa.service.SectorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;


@SpringBootApplication
public class SoapSpringbootJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoapSpringbootJpaApplication.class, args);
	}

//	@Bean
//	CommandLineRunner runner(SectorService sectorService) {
//		return args -> {
//			// Création des Classes
//			Classe classe1 = new Classe();
//			classe1.setClasseName("Classe A");
//			classe1.setDescription("Description Classe A");
//
//			Classe classe2 = new Classe();
//			classe2.setClasseName("Classe B");
//			classe2.setDescription("Description Classe B");
//
//			// Création du Sector
//			Sector sector = new Sector();
//			sector.setName("Sector 1");
//			sector.setClasses(Arrays.asList(classe1, classe2));
//
//			// On lie les classes au sector (relation bidirectionnelle)
//			classe1.setSector(sector);
//			classe2.setSector(sector);
//
//			// Sauvegarde dans la base
//			sectorService.saveSector(sector);
//
//			System.out.println("Sector avec ses Classes insérés !");
//		};
//	}

}
