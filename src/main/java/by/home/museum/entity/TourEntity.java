package by.home.museum.entity;

import javax.persistence.*;

@Entity
@Table(name = "tour", schema = "public", catalog = "museum")
public class TourEntity {
    private Long tourId;
    private String theme;
    private String typeOfExhibits;
    private Short duration;
    private Double cost;

    @Id
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TourEntity that = (TourEntity) o;

        if (tourId != null ? !tourId.equals(that.tourId) : that.tourId != null) return false;
        if (theme != null ? !theme.equals(that.theme) : that.theme != null) return false;
        if (typeOfExhibits != null ? !typeOfExhibits.equals(that.typeOfExhibits) : that.typeOfExhibits != null)
            return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;
        if (cost != null ? !cost.equals(that.cost) : that.cost != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tourId != null ? tourId.hashCode() : 0;
        result = 31 * result + (theme != null ? theme.hashCode() : 0);
        result = 31 * result + (typeOfExhibits != null ? typeOfExhibits.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }
}
