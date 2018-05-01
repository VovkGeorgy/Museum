package by.home.museum.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class TourExhibitEntityPK implements Serializable {
    private Long tourId;
    private Long exhibitId;

    @Column(name = "tour_id", nullable = false)
    @Id
    public Long getTourId() {
        return tourId;
    }

    public void setTourId(Long tourId) {
        this.tourId = tourId;
    }

    @Column(name = "exhibit_id", nullable = false)
    @Id
    public Long getExhibitId() {
        return exhibitId;
    }

    public void setExhibitId(Long exhibitId) {
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
