package by.home.museum.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "exhibit", schema = "public", catalog = "museum")
public class ExhibitEntity {
    private Long exhibitId;
    private String title;
    private Date dated;
    private String material;
    private String archiveNum;
    private String description;
    private Collection<TourExhibitEntity> tourExhibitsByExhibitId;

    @Id
    @Column(name = "exhibit_id", nullable = false)
    public Long getExhibitId() {
        return exhibitId;
    }

    public void setExhibitId(Long exhibitId) {
        this.exhibitId = exhibitId;
    }

    @Basic
    @Column(name = "title", nullable = true, length = -1)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "dated", nullable = true)
    public Date getDated() {
        return dated;
    }

    public void setDated(Date dated) {
        this.dated = dated;
    }

    @Basic
    @Column(name = "material", nullable = true, length = -1)
    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Basic
    @Column(name = "archive_num", nullable = true, length = -1)
    public String getArchiveNum() {
        return archiveNum;
    }

    public void setArchiveNum(String archiveNum) {
        this.archiveNum = archiveNum;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExhibitEntity that = (ExhibitEntity) o;

        if (exhibitId != null ? !exhibitId.equals(that.exhibitId) : that.exhibitId != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (dated != null ? !dated.equals(that.dated) : that.dated != null) return false;
        if (material != null ? !material.equals(that.material) : that.material != null) return false;
        if (archiveNum != null ? !archiveNum.equals(that.archiveNum) : that.archiveNum != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = exhibitId != null ? exhibitId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (dated != null ? dated.hashCode() : 0);
        result = 31 * result + (material != null ? material.hashCode() : 0);
        result = 31 * result + (archiveNum != null ? archiveNum.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "exhibitByExhibitId")
    public Collection<TourExhibitEntity> getTourExhibitsByExhibitId() {
        return tourExhibitsByExhibitId;
    }

    public void setTourExhibitsByExhibitId(Collection<TourExhibitEntity> tourExhibitsByExhibitId) {
        this.tourExhibitsByExhibitId = tourExhibitsByExhibitId;
    }
}
