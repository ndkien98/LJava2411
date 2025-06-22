
package vn.com.t3h.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "insurance_product")

public class InsuranceProductEntity extends BaseEntity {

    @Column(name = "name",unique = true)
    private String name;
    @Column(name = "description",columnDefinition = "text")
    private String description;
    private String coverage;
    // Getters and Setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverage() {
        return coverage;
    }

    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }
}
