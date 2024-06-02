package it.epicode.Events.businesslayer.services.security;

import java.io.Serial;
import java.util.Collection;

import it.epicode.Events.datalayer.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class SecurityUserDetails implements UserDetails {
	@Serial
	private static final long serialVersionUID = 1L;

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

	public static SecurityUserDetails build(User user) {
		var authorities = user.getRoles().stream()
				.map(r -> new SimpleGrantedAuthority(String.valueOf(r.getRoleType()))).toList();
		return SecurityUserDetails.builder()
				.withUsername(user.getUsername())
				.withPassword(user.getPassword())
				.withAuthorities(authorities)
				.build();
	}
}
