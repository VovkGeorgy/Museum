package by.home.museum.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "visitor", schema = "public", catalog = "museum")
public class VisitorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visitor_id", nullable = false)
    private Long visitorId;

    @Basic
    @Column(name = "username", nullable = true, length = -1)
    private String username;

    @Basic
    @Column(name = "password", nullable = true, length = -1)
    private String password;

    @Basic
    @Column(name = "fio", nullable = true, length = -1)
    private String fio;

    @Basic
    @Column(name = "age", nullable = true)
    private Long age;

    @Basic
    @Column(name = "email", nullable = true, length = -1)
    private String email;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "tour_visitor",
            joinColumns = @JoinColumn(name = "visitor_id"),
            inverseJoinColumns = @JoinColumn(name = "tour_id"))
    private Set<TourEntity> tourEntitySet = new HashSet<>();
}
