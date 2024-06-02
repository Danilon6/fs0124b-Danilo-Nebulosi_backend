package it.epicode.Events.config;

import it.epicode.Events.businesslayer.services.dto.LoginResponseDTO;
import it.epicode.Events.businesslayer.services.dto.RegisterUserDTO;
import it.epicode.Events.businesslayer.services.dto.RegisteredUserDTO;
import it.epicode.Events.businesslayer.services.interfaces.Mapper;
import it.epicode.Events.datalayer.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


@Configuration
public class BeansConfiguration {
	@Bean
	@Scope("singleton")
	Mapper<RegisterUserDTO, User> mapRegisterUser2UserEntity() {
		return (input) -> User.builder()
				.withFirstName(input.getFirstName())
				.withLastName(input.getLastName())
				.withUsername(input.getUsername())
				.withEmail(input.getEmail())
				.withPassword(input.getPassword())
				.build();
	}

	@Bean
	@Scope("singleton")
	Mapper<User, RegisteredUserDTO> mapUserEntity2RegisteredUser() {
		return (input) -> RegisteredUserDTO.builder()
				.withId(input.getId())
				.withFirstName(input.getFirstName())
				.withLastName(input.getLastName())
				.withUsername(input.getUsername())
				.withEmail(input.getEmail())
				.withRoles(input.getRoles())
				.build();
	}
	
	@Bean
	@Scope("singleton")
	Mapper<User, LoginResponseDTO> mapUserEntity2LoginResponse() {
		return (input) -> LoginResponseDTO.builder()
				.withId(input.getId())
				.withFirstName(input.getFirstName())
				.withLastName(input.getLastName())
				.withUsername(input.getUsername())
				.withEmail(input.getEmail())
				.withUsername(input.getUsername())
				.withRoles(input.getRoles())
				.build();
	}
}
