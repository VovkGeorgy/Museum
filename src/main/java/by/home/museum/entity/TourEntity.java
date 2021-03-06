package by.home.museum.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Column(name = "theme")
    private String theme;

    @Basic
    @Column(name = "type_of_exhibits")
    private String typeOfExhibits;

    @Basic
    @Column(name = "duration")
    private Short duration;

    @Basic
    @Column(name = "cost")
    private Double cost;

    @Basic
    @Column(name = "image_url")
    private String imageUrl;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne()
    @JoinColumn(name = "guide_id")
    private GuideEntity guideEntity;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "tour_exhibit",
            joinColumns = @JoinColumn(name = "tour_id"),
            inverseJoinColumns = @JoinColumn(name = "exhibit_id"))
    private List<ExhibitEntity> exhibitEntityList = new ArrayList<>();

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "tour_visitor",
            joinColumns = @JoinColumn(name = "tour_id"),
            inverseJoinColumns = @JoinColumn(name = "visitor_id"))
    private Set<VisitorEntity> visitorEntitySet = new HashSet<>();

}
