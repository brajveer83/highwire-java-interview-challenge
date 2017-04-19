package com.highwire.interview.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Facts can have categories. One category can apply to many facts.
 */
@Entity
public class FactCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "factCategoryId")
    private Long id;
    
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Fact> facts;
    
    private String category;

    /**
     * Blank constructor for JPA.
     */
    public FactCategory() {
    }
    
    /**
     * Create a new fact category
     * @param category the descriptor of the category e.g. "animals"
     */
    public FactCategory(String category) {
        this.category = category;
    }
    
    /**
     * @return the descriptor of the category
     */
    public String getCategory() {
        return category;
    }
    
    /**
     * @return the category's database identifier. May be null if the category hasn't been persisted yet
     */
    public Long getId() {
        return this.id;
    }
}
