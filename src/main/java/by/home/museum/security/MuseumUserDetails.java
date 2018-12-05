package by.home.museum.security;

import by.home.museum.entity.RolesEntity;
import by.home.museum.entity.UsersEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MuseumUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;
    private Collection<? extends GrantedAuthority> authorities;
    private String password;
    private String username;

    public MuseumUserDetails(UsersEntity user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = translate(user.getRoles());
    }

    /**
     * Method translate name of role which get in base, to role name which use of authorization
     *
     * @param roles - list of roles
     * @return authorities
     */
    private Collection<? extends GrantedAuthority> translate(List<RolesEntity> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();

         roles.stream().map(role -> role.getName().toUpperCase())
                .forEach(name -> authorities.add(getRole(name)));
        return authorities;
    }

    private SimpleGrantedAuthority getRole(String name) {
        if (!name.startsWith("ROLE_")) {
            name = "ROLE_" + name;
        }
        return new SimpleGrantedAuthority(name);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
