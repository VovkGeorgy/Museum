package by.home.museum.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "visitor", schema = "public", catalog = "museum")
public class VisitorEntity {
    private Long visitorId;
    private String username;
    private String password;
    private String fio;
    private Long age;
    private String email;
    private Collection<TourVisitorEntity> tourVisitorsByVisitorId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visitor_id", nullable = false)
    public Long getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Long visitorId) {
        this.visitorId = visitorId;
    }

    @Basic
    @Column(name = "username", nullable = true, length = -1)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = true, length = -1)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "fio", nullable = true, length = -1)
    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Basic
    @Column(name = "age", nullable = true)
    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    @Basic
    @Column(name = "email", nullable = true, length = -1)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VisitorEntity that = (VisitorEntity) o;

        if (visitorId != null ? !visitorId.equals(that.visitorId) : that.visitorId != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (fio != null ? !fio.equals(that.fio) : that.fio != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = visitorId != null ? visitorId.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (fio != null ? fio.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "visitorByVisitorId")
    @JsonIgnore
    public Collection<TourVisitorEntity> getTourVisitorsByVisitorId() {
        return tourVisitorsByVisitorId;
    }

    public void setTourVisitorsByVisitorId(Collection<TourVisitorEntity> tourVisitorsByVisitorId) {
        this.tourVisitorsByVisitorId = tourVisitorsByVisitorId;
    }

}
