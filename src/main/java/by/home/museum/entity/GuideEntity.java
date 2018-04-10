package by.home.museum.entity;

import javax.persistence.*;

@Entity
@Table(name = "guide", schema = "public", catalog = "museum")
public class GuideEntity {
    private Long guideId;
    private String fio;
    private Short age;
    private Short experience;
    private String languages;

    @Id
    @Column(name = "guide_id", nullable = false)
    public Long getGuideId() {
        return guideId;
    }

    public void setGuideId(Long guideId) {
        this.guideId = guideId;
    }

    @Basic
    @Column(name = "fio", nullable = true, length = -1)
    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Basic
    @Column(name = "age", nullable = true)
    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    @Basic
    @Column(name = "experience", nullable = true)
    public Short getExperience() {
        return experience;
    }

    public void setExperience(Short experience) {
        this.experience = experience;
    }

    @Basic
    @Column(name = "languages", nullable = true, length = -1)
    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GuideEntity that = (GuideEntity) o;

        if (guideId != null ? !guideId.equals(that.guideId) : that.guideId != null) return false;
        if (fio != null ? !fio.equals(that.fio) : that.fio != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (experience != null ? !experience.equals(that.experience) : that.experience != null) return false;
        if (languages != null ? !languages.equals(that.languages) : that.languages != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = guideId != null ? guideId.hashCode() : 0;
        result = 31 * result + (fio != null ? fio.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (experience != null ? experience.hashCode() : 0);
        result = 31 * result + (languages != null ? languages.hashCode() : 0);
        return result;
    }
}
