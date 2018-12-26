package by.home.museum.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "tour", schema = "public", catalog = "museum")
public class TourEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "tour_id", nullable = false)
    private Long tourId;

    @Basic
    @Column(name = "theme", nullable = true, length = -1)
    private String theme;

    @Basic
    @Column(name = "type_of_exhibits", nullable = true, length = -1)
    private String typeOfExhibits;

    @Basic
    @Column(name = "duration", nullable = true)
    private Short duration;

    @Basic
    @Column(name = "cost", nullable = true, precision = 0)
    private Double cost;

    @Basic
    @Column(name = "image_url", nullable = true, length = -1)
    private String imageUrl;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="guide_id", nullable=false)
    private GuideEntity guideEntity;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "tourByTourId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<TourExhibitEntity> tourExhibitsByTourId;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "tourByTourId", cascade = CascadeType.ALL)
    private Collection<TourVisitorEntity> tourVisitorsByTourId;
}
