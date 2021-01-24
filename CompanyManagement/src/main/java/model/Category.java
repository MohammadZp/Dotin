package model;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

@Entity
@Table(name="t_categories")
public class Category extends model.Entity{
    @Column(name="c_name")
    private String name;
    @OneToMany(mappedBy = "category")
    @Column(name="c_category_elements")
    private List<CategoryElement> categoryElements;

    public Category() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryElement> getCategoryElements() {
        return categoryElements;
    }

    public void setCategoryElements(List<CategoryElement> categoryElements) {
        this.categoryElements = categoryElements;
    }

    public Category(String name) {
        this.name = name;
    }
}
