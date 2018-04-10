package by.home.museum.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class TourExhibitEntityPK implements Serializable {
    private Integer tourId;
    private Integer exhibitId;

    @Column(name = "tour_id", nullable = false)
    @Id
    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    @Column(name = "exhibit_id", nullable = false)
    @Id
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

        TourExhibitEntityPK that = (TourExhibitEntityPK) o;

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
}
