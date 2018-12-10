package by.home.museum.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tour", schema = "public", catalog = "museum")
public class TourEntity {
    private Long tourId;
    private String theme;
    private String typeOfExhibits;
    private Short duration;
    private Double cost;
    private String imageUrl;
    private Collection<GuideEntity> guidesByTourId;
    private Collection<TourExhibitEntity> tourExhibitsByTourId;
    private Collection<TourVisitorEntity> tourVisitorsByTourId;

    public TourEntity() {
    }

    public TourEntity(String theme, String typeOfExhibits, Short duration, Double cost, String imageUrl) {
        this.theme = theme;
        this.typeOfExhibits = typeOfExhibits;
        this.duration = duration;
        this.cost = cost;
        this.imageUrl = imageUrl;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tour_id", nullable = false)
    public Long getTourId() {
        return tourId;
    }

    public void setTourId(Long tourId) {
        this.tourId = tourId;
    }

    @Basic
    @Column(name = "theme", nullable = true, length = -1)
    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    @Basic
    @Column(name = "type_of_exhibits", nullable = true, length = -1)
    public String getTypeOfExhibits() {
        return typeOfExhibits;
    }

    public void setTypeOfExhibits(String typeOfExhibits) {
        this.typeOfExhibits = typeOfExhibits;
    }

    @Basic
    @Column(name = "duration", nullable = true)
    public Short getDuration() {
        return duration;
    }

    public void setDuration(Short duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "cost", nullable = true, precision = 0)
    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "image_url", nullable = true, length = -1)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "tourByTourId")
    public Collection<GuideEntity> getGuidesByTourId() {
        return guidesByTourId;
    }

    public void setGuidesByTourId(Collection<GuideEntity> guidesByTourId) {
        this.guidesByTourId = guidesByTourId;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "tourByTourId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Collection<TourExhibitEntity> getTourExhibitsByTourId() {
        return tourExhibitsByTourId;
    }

    public void setTourExhibitsByTourId(Collection<TourExhibitEntity> tourExhibitsByTourId) {
        this.tourExhibitsByTourId = tourExhibitsByTourId;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "tourByTourId")
    public Collection<TourVisitorEntity> getTourVisitorsByTourId() {
        return tourVisitorsByTourId;
    }

    public void setTourVisitorsByTourId(Collection<TourVisitorEntity> tourVisitorsByTourId) {
        this.tourVisitorsByTourId = tourVisitorsByTourId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TourEntity that = (TourEntity) o;

        if (!tourId.equals(that.tourId)) return false;
        if (theme != null ? !theme.equals(that.theme) : that.theme != null) return false;
        if (typeOfExhibits != null ? !typeOfExhibits.equals(that.typeOfExhibits) : that.typeOfExhibits != null) return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;
        if (cost != null ? !cost.equals(that.cost) : that.cost != null) return false;
        return imageUrl != null ? imageUrl.equals(that.imageUrl) : that.imageUrl == null;
    }

    @Override
    public int hashCode() {
        int result = tourId.hashCode();
        result = 31 * result + (theme != null ? theme.hashCode() : 0);
        result = 31 * result + (typeOfExhibits != null ? typeOfExhibits.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        return result;
    }
}
