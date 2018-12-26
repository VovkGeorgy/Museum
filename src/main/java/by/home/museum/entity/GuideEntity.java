package by.home.museum.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

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
    @Column(name = "username", nullable = true, length = 10)
    private String username;

    @Basic
    @Column(name = "password", nullable = true, length = 10)
    private String password;

    @Basic
    @Column(name = "fio", nullable = true, length = -1)
    private String fio;

    @Basic
    @Column(name = "age", nullable = true)
    private Short age;

    @Basic
    @Column(name = "experience", nullable = true)
    private Short experience;

    @Basic
    @Column(name = "languages", nullable = true, length = -1)
    private String languages;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "guideEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<TourEntity> tourEntitySet;
}
