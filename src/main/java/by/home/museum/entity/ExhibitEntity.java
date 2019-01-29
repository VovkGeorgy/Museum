package by.home.museum.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "exhibit", schema = "public", catalog = "museum")
public class ExhibitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exhibit_id", nullable = false)
    private Long exhibitId;

    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "dated")
    private String dated;

    @Basic
    @Column(name = "material")
    private String material;

    @Basic
    @Column(name = "archive_num")
    private String archiveNum;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "image_url")
    private String imageUrl;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "tour_exhibit",
            joinColumns = @JoinColumn(name = "exhibit_id"),
            inverseJoinColumns = @JoinColumn(name = "tour_id"))
    private Set<TourEntity> tourEntitySet = new HashSet<>();
}
