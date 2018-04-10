package by.home.museum.entity;

import javax.persistence.*;

@Entity
@Table(name = "visitor", schema = "public", catalog = "museum")
public class VisitorEntity {
    private Long visitorId;
    private String login;
    private String fio;
    private Long age;
    private String email;

    @Id
    @Column(name = "visitor_id", nullable = false)
    public Long getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Long visitorId) {
        this.visitorId = visitorId;
    }

    @Basic
    @Column(name = "login", nullable = true, length = -1)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (fio != null ? !fio.equals(that.fio) : that.fio != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = visitorId != null ? visitorId.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (fio != null ? fio.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
