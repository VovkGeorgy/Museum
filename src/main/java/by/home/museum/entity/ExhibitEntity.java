package by.home.museum.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "exhibit", schema = "public", catalog = "museum")
public class ExhibitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exhibit_id", nullable = false)
    private Long exhibitId;

    @Basic
    @Column(name = "title", nullable = true, length = -1)
    private String title;

    @Basic
    @Column(name = "dated", nullable = true)
    private String dated;

    @Basic
    @Column(name = "material", nullable = true, length = -1)
    private String material;

    @Basic
    @Column(name = "archive_num", nullable = true, length = -1)
    private String archiveNum;

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;

    @Basic
    @Column(name = "image_url", nullable = true, length = -1)
    private String imageUrl;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "tour_exhibit",
            joinColumns = @JoinColumn(name = "exhibit_id"),
            inverseJoinColumns = @JoinColumn(name = "tour_id"))
    private List<TourEntity> tourEntitySet = new ArrayList<>();
}
