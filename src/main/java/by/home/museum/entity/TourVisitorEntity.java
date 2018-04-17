package by.home.museum.entity;

import javax.persistence.*;

@Entity
@Table(name = "tour_visitor", schema = "public", catalog = "museum")
@IdClass(TourVisitorEntityPK.class)
public class TourVisitorEntity {
    private Integer tourId;
    private Integer visitorId;
    private TourEntity tourByTourId;
    private VisitorEntity visitorByVisitorId;

    @Id
    @Column(name = "tour_id", nullable = false)
    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    @Id
    @Column(name = "visitor_id", nullable = false)
    public Integer getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Integer visitorId) {
        this.visitorId = visitorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TourVisitorEntity that = (TourVisitorEntity) o;

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

    @ManyToOne
    @JoinColumn(name = "tour_id", referencedColumnName = "tour_id", nullable = false, insertable = false, updatable = false)
    public TourEntity getTourByTourId() {
        return tourByTourId;
    }

    public void setTourByTourId(TourEntity tourByTourId) {
        this.tourByTourId = tourByTourId;
    }

    @ManyToOne
    @JoinColumn(name = "visitor_id", referencedColumnName = "visitor_id", nullable = false, insertable = false, updatable = false)
    public VisitorEntity getVisitorByVisitorId() {
        return visitorByVisitorId;
    }

    public void setVisitorByVisitorId(VisitorEntity visitorByVisitorId) {
        this.visitorByVisitorId = visitorByVisitorId;
    }
}
