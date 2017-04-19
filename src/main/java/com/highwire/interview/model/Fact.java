package com.highwire.interview.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Fact entity, representing a truthful statement. Facts have categories that allow them to be grouped.
 */
@Entity
public class Fact {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Date creationDate;
    
    private String factText;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private FactCategory factCategory;
    
    /**
     * Blank constructor for use by JPA.
     */
    public Fact() {
    }

    /**
     * Create a new Fact.
     * @param factText the fact itself, for example: "The sky is blue"
     * @param category the fact's category for example: "Geography"
     */
    public Fact(String factText, FactCategory category) {
        this.creationDate = new Date();
        this.factCategory = category;
    }
    
    /**
     * @return the fact's database id, may be null if the fact has not been persisted yet
     */
    public Long getId() {
        return this.id;
    }

    /**
     * @return the date that the fact was created
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @return the fact itself
     */
    public String getFactText() {
        return factText;
    }
    
    /**
     * @return the fact's category
     */
    public FactCategory getFactCategory() {
        return factCategory;
    }
}
