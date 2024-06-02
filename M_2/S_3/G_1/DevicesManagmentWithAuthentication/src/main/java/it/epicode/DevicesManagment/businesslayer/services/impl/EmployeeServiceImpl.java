package it.epicode.DevicesManagment.businesslayer.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import it.epicode.DevicesManagment.businesslayer.services.dto.LoginResponseDto;
import it.epicode.DevicesManagment.businesslayer.services.dto.RegisteredEmployeeDto;
import it.epicode.DevicesManagment.businesslayer.services.interfaces.Mapper;
import it.epicode.DevicesManagment.config.JwtUtils;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;


@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    PasswordEncoder encoder;


    @Autowired
    EmployeeRepository employee;

    @Autowired
    RoleRepository roles;


    @Autowired
    private AuthenticationManager auth;

    @Autowired
    JwtUtils jwt;


    @Autowired
    Mapper<EmployeeDTO, Employee> mapEntity;
    @Autowired
    Mapper<Employee, RegisteredEmployeeDto> mapRegisteredEmployee;
    @Autowired
    Mapper<Employee, LoginResponseDto> mapLogin;

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
        var usernameDuplicated = employee.findOneByUsername(e.getUsername());

        if (emailDuplicated.isPresent()) {
            throw new DuplicateEmailException(e.getEmail());
        }else if (usernameDuplicated.isPresent()) {
            throw new DuplicateUsernameException(e.getUsername());
        } else {

                var emp = mapEntity.map(e);
                var p = encoder.encode(e.getPassword());
                emp.setPassword(p);
                if (e.getRoles() != null)
                {
                    Stream.of(e.getRoles().split(",")).forEach(r ->

                            emp.getRoles().add(roles.findOneByName(r)
                                    .orElse(roles.save(RoleEntity.builder().withName(r).build()))));
                }
                employee.save(emp);

                return mapRegisteredEmployee.map(emp);
    }
    }

    @Override
    public Optional<LoginResponseDto> login(String username, String password) {
        try {
            var a = auth.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            a.getAuthorities();
            SecurityContextHolder.getContext().setAuthentication(a);

            var dto = mapLogin.map(employee.findOneByUsername(username).orElseThrow());
            dto.setToken(jwt.generateToken(a));
            return Optional.of(dto);
        } catch (NoSuchElementException e) {
            log.error("User not found", e);
            throw new InvalidLoginException(username, password);
        }
//        } catch (AuthenticationException e) {
//            log.error("Authentication failed", e);
//            throw new InvalidLoginException(username, password);
//        }
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
