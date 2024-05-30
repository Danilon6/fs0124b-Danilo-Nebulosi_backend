package it.epicode.DevicesManagment.config;

import it.epicode.DevicesManagment.businesslayer.services.dto.EmployeeDTO;
import it.epicode.DevicesManagment.businesslayer.services.dto.LoginResponseDto;
import it.epicode.DevicesManagment.businesslayer.services.dto.RegisteredEmployeeDto;
import it.epicode.DevicesManagment.businesslayer.services.interfaces.Mapper;
import it.epicode.DevicesManagment.datalayer.entities.Employee;
import it.epicode.DevicesManagment.datalayer.entities.RoleEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


@Configuration
public class BeansConfiguration {
    @Bean
    @Scope("singleton")
    Mapper<EmployeeDTO, Employee> mapEmployeeDTO2Employee() {
        return (input) -> Employee.builder() //
                .withEmail(input.getEmail())
                .withUsername(input.getUsername())
                .withPassword(input.getPassword())
                .withFirstName(input.getFirstName())
                .withLastName(input.getLastName())
                .build();
    }

    @Bean
    @Scope("singleton")
    Mapper<Employee, RegisteredEmployeeDto> mapEmployee2RegisteredEmployeeDto() {
        return (input) -> RegisteredEmployeeDto.builder() //
                .withId(input.getId()) //
                .withCreatedAt(input.getCreatedAt())
                .withFirstName(input.getFirstName())
                .withLastName(input.getLastName())
                .withEmail(input.getEmail())
                .withUsername(input.getUsername())
                .withRoles(input.getRoles().stream().map(RoleEntity::getName).toList()) //
                .build();
    }

    @Bean
    @Scope("singleton")
    Mapper<Employee, LoginResponseDto> mapUserEntity2LoginResponse() {
        return (input) -> LoginResponseDto.builder() //
                .withId(input.getId()) //
                .withUsername(input.getUsername()) //
                .withRoles(input.getRoles().stream().map(RoleEntity::getName).toList()) //
                .withCreatedAt(input.getCreatedAt())
                .build();
    }

}
