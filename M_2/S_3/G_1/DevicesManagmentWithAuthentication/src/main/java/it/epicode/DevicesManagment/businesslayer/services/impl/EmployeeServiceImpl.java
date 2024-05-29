package it.epicode.DevicesManagment.businesslayer.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import it.epicode.DevicesManagment.businesslayer.services.dto.RegisteredEmployeeDto;
import it.epicode.DevicesManagment.datalayer.entities.RoleEntity;
import it.epicode.DevicesManagment.datalayer.repositories.RoleRepository;
import it.epicode.DevicesManagment.presentationlayer.controllers.exceptions.InvalidLoginException;
import it.epicode.DevicesManagment.presentationlayer.controllers.exceptions.duplicated.DuplicateEmailException;
import it.epicode.DevicesManagment.presentationlayer.controllers.exceptions.duplicated.DuplicateUsernameException;
import it.epicode.DevicesManagment.presentationlayer.controllers.exceptions.NotFoundException;
import it.epicode.DevicesManagment.datalayer.entities.Employee;
import it.epicode.DevicesManagment.datalayer.repositories.EmployeeRepository;
import it.epicode.DevicesManagment.businesslayer.services.dto.EmployeeDTO;
import it.epicode.DevicesManagment.businesslayer.services.interfaces.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;


@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employee;

    @Autowired
    MapperImpl mapper;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roles;

    @Value("${CLOUDINARY_URL}")
    private String cloudinaryKey;

    @Override
    public Page<Employee> getAll(Pageable p) {
        return employee.findAll(p);
    }

    @Override
    public Employee getById(Long id) {
        return employee.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    @Override
    public RegisteredEmployeeDto save(EmployeeDTO e) {
        var emailDuplicated = employee.findByEmail(e.getEmail());
        var usernameDuplicated = employee.findByUsername(e.getUsername());

        if (emailDuplicated.isPresent()) {
            throw new DuplicateEmailException(e.getEmail());
        }else if (usernameDuplicated.isPresent()) {
            throw new DuplicateUsernameException(e.getUsername());
        } else {

                var emp = mapper.convertFrom(e);
                var p = encoder.encode(e.getPassword());
                emp.setPassword(p);
                if (e.getRoles() != null)
                    Stream.of(e.getRoles().split(",")).forEach(r ->
                            emp.getRoles().add(roles.findOneByName(r)
                                    .orElse(roles.save(RoleEntity.builder().withName(r).build()))));
                employee.save(emp);

                return RegisteredEmployeeDto.builder()
                        .withId(emp.getId())
                        .withCreatedAt(emp.getCreatedAt())
                        .withFirstName(emp.getFirstName())
                        .withLastName(emp.getLastName())
                        .withUsername(emp.getUsername())
                        .withEmail(emp.getEmail())
                        .build();
    }
    }

    @Override
    public Optional<RegisteredEmployeeDto> login(String username, String password) {

        var hashedPassword= encoder.encode(password);
        try {
            var a = encoder.matches(password, hashedPassword);
        var e = employee.findOneByUsernameAndPassword(username, hashedPassword).orElseThrow();
            return Optional.of(RegisteredEmployeeDto.builder()
                            .withId(e.getId())
                            .withCreatedAt(e.getCreatedAt())
                            .withFirstName(e.getFirstName())
                            .withLastName(e.getLastName())
                            .withEmail(e.getEmail())
                            .build());
        }catch (NoSuchElementException e) {
            log.error("User not found", e);
            throw new InvalidLoginException(username, password);
        }




    }

    @Override
    public RegisteredEmployeeDto update(Long id, Employee e) {
        var toModify = this.getById(id);
        toModify.setFirstName(e.getFirstName());
        toModify.setLastName(e.getLastName());
        toModify.setEmail(e.getEmail());
        toModify.setUsername(e.getUsername());
        toModify.setDevices(e.getDevices());
        var savedEmployee = employee.save(toModify);

        return RegisteredEmployeeDto.builder()
                .withId(toModify.getId())
                .withCreatedAt(toModify.getCreatedAt())
                .withFirstName(toModify.getFirstName())
                .withLastName(toModify.getLastName())
                .withUsername(toModify.getUsername())
                .withEmail(toModify.getEmail())
                .build();
    }

    @Override
    public Employee delete(Long id) {
        var e = this.getById(id);
        employee.delete(e);
        return e;
    }

    @Override
    public Employee saveProfileImage(Long id, MultipartFile file) throws IOException {
        Employee e = this.getById(id);

        Cloudinary cloudinary = new Cloudinary(cloudinaryKey);

            var image = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

            String imageUrl = (String) image.get("url");

            e.setProfileImagePath(imageUrl);
            return employee.save(e);
    }
}
