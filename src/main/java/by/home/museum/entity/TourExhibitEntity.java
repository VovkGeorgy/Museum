package by.home.museum.entity;

import javax.persistence.*;

@Entity
@Table(name = "tour_exhibit", schema = "public", catalog = "museum")
@IdClass(TourExhibitEntityPK.class)
public class TourExhibitEntity {
    private Integer tourId;
    private Integer exhibitId;
    private TourEntity tourByTourId;
    private ExhibitEntity exhibitByExhibitId;

    @Id
    @Column(name = "tour_id", nullable = false)
    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    @Id
    @Column(name = "exhibit_id", nullable = false)
    public Integer getExhibitId() {
        return exhibitId;
    }

    public void setExhibitId(Integer exhibitId) {
        this.exhibitId = exhibitId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TourExhibitEntity that = (TourExhibitEntity) o;

        if (tourId != null ? !tourId.equals(that.tourId) : that.tourId != null) return false;
        if (exhibitId != null ? !exhibitId.equals(that.exhibitId) : that.exhibitId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tourId != null ? tourId.hashCode() : 0;
        result = 31 * result + (exhibitId != null ? exhibitId.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "tour_id", referencedColumnName = "tour_id", nullable = false, insertable = false, updatable = false)
    public TourEntity getTourByTourId() {
        return tourByTourId;
    }

    public void setTourByTourId(TourEntity tourByTourId) {
        this.tourByTourId = tourByTourId;
    }

    @ManyToOne
    @JoinColumn(name = "exhibit_id", referencedColumnName = "exhibit_id", nullable = false, insertable = false, updatable = false)
    public ExhibitEntity getExhibitByExhibitId() {
        return exhibitByExhibitId;
    }

    public void setExhibitByExhibitId(ExhibitEntity exhibitByExhibitId) {
        this.exhibitByExhibitId = exhibitByExhibitId;
    }
}
