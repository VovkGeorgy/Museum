package by.home.museum.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "guide", schema = "public", catalog = "museum")
public class GuideEntity {
    private Long guideId;
    private String username;
    private String password;
    private String fio;
    private Short age;
    private Short experience;
    private String languages;
    private Long tourId;
    private TourEntity tourByTourId;

    public GuideEntity() {
    }

    public GuideEntity(String username, String password, String fio, Short age, Short experience, String languages, Long tourId) {
        this.username = username;
        this.password = password;
        this.fio = fio;
        this.age = age;
        this.experience = experience;
        this.languages = languages;
        this.tourId = tourId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guide_id", nullable = false)
    public Long getGuideId() {
        return guideId;
    }

    public void setGuideId(Long guideId) {
        this.guideId = guideId;
    }

    @Basic
    @Column(name = "username", nullable = true, length = -1)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = true, length = -1)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Basic
    @Column(name = "tour_id", nullable = true)
    public Long getTourId() {
        return tourId;
    }

    public void setTourId(Long tourId) {
        this.tourId = tourId;
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
        if (tourId != null ? !tourId.equals(that.tourId) : that.tourId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = guideId != null ? guideId.hashCode() : 0;
        result = 31 * result + (fio != null ? fio.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (experience != null ? experience.hashCode() : 0);
        result = 31 * result + (languages != null ? languages.hashCode() : 0);
        result = 31 * result + (tourId != null ? tourId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "tour_id", referencedColumnName = "tour_id", insertable = false, updatable = false)
    public TourEntity getTourByTourId() {
        return tourByTourId;
    }

    public void setTourByTourId(TourEntity tourByTourId) {
        this.tourByTourId = tourByTourId;
    }


}
