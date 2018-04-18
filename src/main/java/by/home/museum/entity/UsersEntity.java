package by.home.museum.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
//@Table(name = "users", schema = "public", catalog = "museum")
//public class UsersEntity {
//    private Long id;
//    private String name;
//    private String password;
////    private Collection<UsersRolesEntity> usersRolesById;
//
//    public UsersEntity() {
//    }
//
//    public UsersEntity(String username, String password, List<RolesEntity> roles) {
//        this.name = username;
//        this.password = password;
//        this.roles = roles;
//    }
//
//    @Id
//    @Column(name = "id", nullable = false)
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    @Basic
//    @Column(name = "name", nullable = false, length = -1)
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Basic
//    @Column(name = "password", nullable = false, length = -1)
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        UsersEntity that = (UsersEntity) o;
//
//        if (id != null ? !id.equals(that.id) : that.id != null) return false;
//        if (name != null ? !name.equals(that.name) : that.name != null) return false;
//        if (password != null ? !password.equals(that.password) : that.password != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id != null ? id.hashCode() : 0;
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + (password != null ? password.hashCode() : 0);
//        return result;
//    }
//
////    @OneToMany(mappedBy = "usersByUserId")
////    public Collection<UsersRolesEntity> getUsersRolesById() {
////        return usersRolesById;
////    }
////
////    public void setUsersRolesById(Collection<UsersRolesEntity> usersRolesById) {
////        this.usersRolesById = usersRolesById;
////    }
//
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "users_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
//    @JsonIgnore
//    private List<RolesEntity> roles = new ArrayList<>();
//
//    public List<RolesEntity> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(List<RolesEntity> roles) {
//        this.roles = roles;
//    }
//}

@Entity
@Table(name = "users")
public class UsersEntity {
    private Long id;
    private String username;
    private String password;
    private List<RolesEntity> roles = new ArrayList<>();

    public UsersEntity() {
    }

    public UsersEntity(String userName, String password, List<RolesEntity> roles) {
        this.username = userName;
        this.password = password;
        this.roles = roles;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "username")
    public String getName() {
        return username;
    }

    public void setName(String userName) {
        this.username = userName;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonIgnore
    public List<RolesEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RolesEntity> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", userName='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}