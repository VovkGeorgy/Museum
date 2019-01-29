package by.home.museum.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "guide", schema = "public", catalog = "museum")
public class GuideEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "guide_id", unique = true, nullable = false)
    private Long guideId;

    @Basic
    @Column(name = "username", nullable = false)
    private String username;

    @Basic
    @Column(name = "password", nullable = false)
    private String password;

    @Basic
    @Column(name = "fio")
    public String fio;

    @Basic
    @Column(name = "age")
    private Short age;

    @Basic
    @Column(name = "experience")
    private Short experience;

    @Basic
    @Column(name = "languages")
    private String languages;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "guideEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<TourEntity> tourEntitySet = new HashSet<>();
}
