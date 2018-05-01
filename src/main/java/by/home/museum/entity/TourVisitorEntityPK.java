package by.home.museum.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class TourVisitorEntityPK implements Serializable {
    private Long tourId;
    private Long visitorId;

    @Column(name = "tour_id", nullable = false)
    @Id
    public Long getTourId() {
        return tourId;
    }

    public void setTourId(Long tourId) {
        this.tourId = tourId;
    }

    @Column(name = "visitor_id", nullable = false)
    @Id
    public Long getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Long visitorId) {
        this.visitorId = visitorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TourVisitorEntityPK that = (TourVisitorEntityPK) o;

        if (tourId != null ? !tourId.equals(that.tourId) : that.tourId != null) return false;
        if (visitorId != null ? !visitorId.equals(that.visitorId) : that.visitorId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tourId != null ? tourId.hashCode() : 0;
        result = 31 * result + (visitorId != null ? visitorId.hashCode() : 0);
        return result;
    }
}
