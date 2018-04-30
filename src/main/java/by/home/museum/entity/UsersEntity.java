package by.home.museum.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UsersEntity {
    private Long id;
    private String username;
    private String password;
    private List<RolesEntity> roles = new ArrayList<>();

    public UsersEntity() {
    }

    public UsersEntity(String userName, String password) {
        this.username = userName;
        this.password = password;
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
    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
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