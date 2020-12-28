package pers.dog.project.manager.entity;

import javax.persistence.*;

/**
 * 系统属性
 *
 * @author 废柴 2020/12/27 20:34
 */
@Entity
@Table(name = "PM_PROPERTY")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer propertyId;

    @Column(length = 32, updatable = false, unique = true, nullable = false)
    private String propertyCode;

    @Column(length = 32)
    private String propertyValue;

    public Integer getPropertyId() {
        return propertyId;
    }

    public Property setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
        return this;
    }

    public String getPropertyCode() {
        return propertyCode;
    }

    public Property setPropertyCode(String propertyCode) {
        this.propertyCode = propertyCode;
        return this;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public Property setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
        return this;
    }
}
