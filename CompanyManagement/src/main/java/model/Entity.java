package model;

import javax.persistence.*;

@MappedSuperclass
public class Entity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="c_id")
    public Long id;
    @Column(name="c_version")
    public Integer version;
    @Column(name="c_modification_date")
    public String modificationDate;
    @Column(name="c_creation_date")
    public String creationDate;
    @Column(name="c_active")
    public boolean active;
    @Column(name="c_enable")
    public boolean enable;

    public Entity() {
    }

    public synchronized Integer versionIncrement() {
        version = version + 1;
        return version;
    }

    public boolean checkEquality(Entity entity) {
        return entity.equals(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
