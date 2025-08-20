//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v3.0.0 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2025.08.20 à 12:12:34 PM CEST 
//


package com.groupeisi.soap_springboot_jpa.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="sector" type="{http://groupeisi.com/soap}sectorType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "sector"
})
@XmlRootElement(name = "GetSectorByIdResponse")
public class GetSectorByIdResponse {

    protected SectorType sector;

    /**
     * Obtient la valeur de la propriété sector.
     * 
     * @return
     *     possible object is
     *     {@link SectorType }
     *     
     */
    public SectorType getSector() {
        return sector;
    }

    /**
     * Définit la valeur de la propriété sector.
     * 
     * @param value
     *     allowed object is
     *     {@link SectorType }
     *     
     */
    public void setSector(SectorType value) {
        this.sector = value;
    }

}
