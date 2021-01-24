package model;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

@Entity
@Table(name="t_category_elements")
public class CategoryElement extends model.Entity {
    @Column(name = "c_name")
    private String name;
    @Column(name = "c_code")
    private String code;
    @ManyToOne
    @JoinColumn(name = "c_category_id")
    private Category category;
    public CategoryElement() {
    }

    public CategoryElement(String name, String code, Category category) {
        this.name = name;
        this.code = code;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}