package it.epicode.Events.businesslayer.services.interfaces;

import it.epicode.Events.businesslayer.services.dto.LoginResponseDTO;
import it.epicode.Events.businesslayer.services.dto.RegisterUserDTO;
import it.epicode.Events.businesslayer.services.dto.RegisteredUserDTO;
import it.epicode.Events.datalayer.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    RegisteredUserDTO register(RegisterUserDTO user);

    Optional<LoginResponseDTO> login(String username, String password);

    Optional<RegisteredUserDTO> get(long id);

    Page<User> getAll(Pageable p);

    RegisteredUserDTO update(Long id, User user);

    RegisteredUserDTO delete(Long id);
}
