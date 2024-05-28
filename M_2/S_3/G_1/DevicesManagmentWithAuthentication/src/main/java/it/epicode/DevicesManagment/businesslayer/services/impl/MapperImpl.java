package it.epicode.DevicesManagment.businesslayer.services.impl;

import it.epicode.DevicesManagment.businesslayer.services.dto.EmployeeDTO;
import it.epicode.DevicesManagment.businesslayer.services.interfaces.Mapper;
import it.epicode.DevicesManagment.datalayer.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MapperImpl implements Mapper<EmployeeDTO, Employee> {

    @Autowired
    PasswordEncoder encoder;

    @Override
    public EmployeeDTO convertTo(Employee destination) {
        return EmployeeDTO.builder()
                .withFirstName(destination.getFirstName())
                .withLastName(destination.getLastName())
                .withUsername(destination.getUsername())
                .withEmail(destination.getEmail())
                .withPassword(destination.getPassword())
                .build();
    }

    @Override
    public Employee convertFrom(EmployeeDTO source) {
        var hashedPassword = encoder.encode(source.getPassword());
        return Employee.builder()
                .withFirstName(source.getFirstName())
                .withLastName(source.getLastName())
                .withUsername(source.getUsername())
                .withEmail(source.getEmail())
                .withPassword(hashedPassword)
                .build();
    }
}
