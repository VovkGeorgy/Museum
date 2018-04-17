package by.home.museum.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "roles", schema = "public", catalog = "museum")
public class RolesEntity {
    private Long id;
    private String name;
    private Collection<UsersRolesEntity> usersRolesById;

    public RolesEntity() {
    }

    public RolesEntity(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolesEntity that = (RolesEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "rolesByRoleId")
    public Collection<UsersRolesEntity> getUsersRolesById() {
        return usersRolesById;
    }

    public void setUsersRolesById(Collection<UsersRolesEntity> usersRolesById) {
        this.usersRolesById = usersRolesById;
    }
}
