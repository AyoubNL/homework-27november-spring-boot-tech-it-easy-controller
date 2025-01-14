package nl.novi.techiteasycontroller.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    private String role;

    private String apiKey;

    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "username",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private Set<Authority> authorities = new HashSet<Authority>();


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }

    public void deleteAuthority(Authority authority) {
        this.authorities.remove(authority);
    }


}
