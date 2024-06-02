package it.epicode.Events.businesslayer.services.impl;

import it.epicode.Events.businesslayer.services.dto.LoginResponseDTO;
import it.epicode.Events.businesslayer.services.dto.RegisterUserDTO;
import it.epicode.Events.businesslayer.services.dto.RegisteredUserDTO;
import it.epicode.Events.businesslayer.services.interfaces.Mapper;
import it.epicode.Events.businesslayer.services.interfaces.UserService;
import it.epicode.Events.config.JwtUtils;
import it.epicode.Events.datalayer.entities.Roles;
import it.epicode.Events.datalayer.entities.User;
import it.epicode.Events.datalayer.entities.enums.RolesType;
import it.epicode.Events.datalayer.repositories.RolesRepository;
import it.epicode.Events.datalayer.repositories.UserRepository;
import it.epicode.Events.presentationlayer.controllers.api.exceptions.InvalidLoginException;
import it.epicode.Events.presentationlayer.controllers.api.exceptions.NotFoundException;
import it.epicode.Events.presentationlayer.controllers.api.exceptions.duplicated.DuplicateEmailException;
import it.epicode.Events.presentationlayer.controllers.api.exceptions.duplicated.DuplicateUsernameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserRepository user;


    @Autowired
    private RolesRepository roles;

    @Autowired
    private AuthenticationManager auth;

    @Autowired
    private JwtUtils jwt;

    @Autowired
    Mapper<RegisterUserDTO, User> mapEntity;

    @Autowired
    Mapper<User, RegisteredUserDTO> mapRegisteredUser;

    @Autowired
    Mapper<User, LoginResponseDTO> mapLogin;

    @Override
    public RegisteredUserDTO register(RegisterUserDTO newUser) {
        var emailDuplicated = user.findByEmail(newUser.getEmail());
        var usernameDuplicated = user.findOneByUsername(newUser.getUsername());

        if (emailDuplicated.isPresent()) {
            throw new DuplicateEmailException(newUser.getEmail());
        }else if (usernameDuplicated.isPresent()) {
            throw new DuplicateUsernameException(newUser.getUsername());
        } else {
            try {
                var userEntity = mapEntity.map(newUser);
                var p = encoder.encode(newUser.getPassword());
                log.info("Password encrypted: {}", p);
                userEntity.setPassword(p);
                        userEntity.getRoles().add(
                                roles.findOneByRoleType(RolesType.valueOf(newUser.getRoles()))
                                        .orElse(roles.save(
                                                Roles.builder()
                                                        .withRoleType(RolesType.valueOf(newUser.getRoles()))
                                                        .build())));
                        return mapRegisteredUser.map(user.save(userEntity));
            } catch (Exception e) {
                log.error(String.format("Exception saving user %s", user), e);
                throw new RuntimeException();
            }
        }
    }

    @Override
    public Optional<LoginResponseDTO> login(String username, String password) {
        try {
            var a = auth.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            a.getAuthorities();
            SecurityContextHolder.getContext().setAuthentication(a);

            var dto = mapLogin.map(user.findOneByUsername(username).orElseThrow());
            dto.setToken(jwt.generateToken(a));
            return Optional.of(dto);
        } catch (NoSuchElementException e) {
            log.error("User not found", e);
            throw new InvalidLoginException(username, password);
        } catch (AuthenticationException e) {
            log.error("Authentication failed", e);
            throw new InvalidLoginException(username, password);
        }
    }

    @Override
    public Optional<RegisteredUserDTO> getById(long id) {
        var u = user.findById(id).orElseThrow(()-> new NotFoundException(id));
        return Optional.ofNullable(mapRegisteredUser.map(u));
    }

    @Override
    public Page<User> getAll(Pageable p) {
        return user.findAll(p);
    }

    @Override
    public RegisteredUserDTO update(Long id, User userModified) {
        try {
            var u = user.findById(id).orElseThrow(()-> new NotFoundException(id));

            user.save(u);
            return mapRegisteredUser.map(u);
        } catch (NoSuchElementException e) {
            log.error(String.format("Cannot find user with id = %s", id), e);
            throw new RuntimeException("Cannot find user...");
        } catch (Exception e) {
            log.error(String.format("Error updating user with id = %s", id), e);
            throw new RuntimeException();
        }
    }

    @Override
    public RegisteredUserDTO delete(Long id) {
        try {
            var u = user.findById(id).orElseThrow();
            user.delete(u);
            return mapRegisteredUser.map(u);
        } catch (NoSuchElementException e) {
            log.error(String.format("Cannot find user with id = %s", id), e);
            throw new RuntimeException("Cannot find user...");
        } catch (Exception e) {
            log.error(String.format("Error deleting user with id = %s", id), e);
            throw new RuntimeException();
        }
    }
}
