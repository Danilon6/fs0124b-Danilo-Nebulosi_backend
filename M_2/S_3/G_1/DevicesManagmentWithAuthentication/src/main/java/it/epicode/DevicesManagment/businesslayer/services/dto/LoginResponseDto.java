package it.epicode.DevicesManagment.businesslayer.services.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginResponseDto {
	Long id;
	LocalDate createdAt;
	String username;
	private final List<String> roles;
	private String token;

	@Builder(setterPrefix = "with")
	public LoginResponseDto(Long id, LocalDate createdAt, String username, List<String> roles, String token) {
		this.id = id;
		this.createdAt = createdAt;
		this.username = username;
		this.roles = roles;
		this.token = token;
	}
}
