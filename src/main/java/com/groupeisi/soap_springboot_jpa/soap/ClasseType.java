//
// Ce fichier a été généré par Eclipse Implementation of JAXB, v3.0.0 
// Voir https://eclipse-ee4j.github.io/jaxb-ri 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2025.08.20 à 12:12:34 PM CEST 
//


package com.groupeisi.soap_springboot_jpa.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour classeType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="classeType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="classeName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="sectorId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="sectorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "classeType", propOrder = {
    "id",
    "classeName",
    "description",
    "sectorId",
    "sectorName"
})
public class ClasseType {

    protected long id;
    @XmlElement(required = true)
    protected String classeName;
    @XmlElement(required = true)
    protected String description;
    protected Long sectorId;
    protected String sectorName;

    /**
     * Obtient la valeur de la propriété id.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Définit la valeur de la propriété id.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Obtient la valeur de la propriété classeName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClasseName() {
        return classeName;
    }

    /**
     * Définit la valeur de la propriété classeName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClasseName(String value) {
        this.classeName = value;
    }

    /**
     * Obtient la valeur de la propriété description.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Définit la valeur de la propriété description.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Obtient la valeur de la propriété sectorId.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSectorId() {
        return sectorId;
    }

    /**
     * Définit la valeur de la propriété sectorId.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSectorId(Long value) {
        this.sectorId = value;
    }

    /**
     * Obtient la valeur de la propriété sectorName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSectorName() {
        return sectorName;
    }

    /**
     * Définit la valeur de la propriété sectorName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSectorName(String value) {
        this.sectorName = value;
    }

}
