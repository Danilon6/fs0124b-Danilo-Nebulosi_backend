package it.epicode.DevicesManagment.businesslayer.services.security;

import it.epicode.DevicesManagment.datalayer.entities.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class SecurityUserDetails implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities;
    private String password;
    private String username;
    @Builder.Default
    private boolean accountNonExpired = true;
    @Builder.Default
    private boolean accountNonLocked = true;
    @Builder.Default
    private boolean credentialsNonExpired = true;
    @Builder.Default
    private boolean enabled = true;


    public static SecurityUserDetails build(Employee employee) {
        var authorities = employee.getRoles().stream().map(
                r-> new SimpleGrantedAuthority(r.getName())).toList();

        return SecurityUserDetails.builder()
                .withAuthorities(authorities)
                .withPassword(employee.getPassword())
                .withUsername(employee.getUsername())
                .build();
    }

}
