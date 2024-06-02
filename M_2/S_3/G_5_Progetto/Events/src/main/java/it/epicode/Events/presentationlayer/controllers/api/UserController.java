package it.epicode.Events.presentationlayer.controllers.api;

import it.epicode.Events.businesslayer.services.dto.LoginResponseDTO;
import it.epicode.Events.businesslayer.services.dto.RegisterUserDTO;
import it.epicode.Events.businesslayer.services.dto.RegisteredUserDTO;
import it.epicode.Events.businesslayer.services.interfaces.UserService;
import it.epicode.Events.datalayer.entities.User;
import it.epicode.Events.presentationlayer.controllers.api.exceptions.ApiValidationException;
import it.epicode.Events.presentationlayer.controllers.api.models.LoginModel;
import it.epicode.Events.presentationlayer.controllers.api.models.RegisterUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService user;

    @GetMapping
    public ResponseEntity<Page<User>> getEvents (Pageable p) {
        var allUsers = user.getAll(p);
        var headers = new HttpHeaders();
        headers.add("Totale", String.valueOf(allUsers.getTotalElements()));
        return new ResponseEntity<>(allUsers, headers, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<RegisteredUserDTO> getEvent (@PathVariable Long id) {
        var u = user.getById(id);
        return new ResponseEntity<>(u, HttpStatus.FOUND);
    }
    @PostMapping
    public ResponseEntity<RegisteredUserDTO> register(@RequestBody @Validated RegisterUserModel model, BindingResult validator){
        if (validator.hasErrors()) {
            throw new ApiValidationException(validator.getAllErrors());
        }

        var registeredUser = user.register(
                RegisterUserDTO.builder()
                        .withFirstName(model.firstName())
                        .withLastName(model.lastName())
                        .withUsername(model.username())
                        .withEmail(model.email())
                        .withPassword(model.password())
                        .withRoles(model.roles())
                .build());

        return  new ResponseEntity<> (registeredUser, HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Validated LoginModel model, BindingResult validator) {
        if (validator.hasErrors()) {
            throw  new ApiValidationException(validator.getAllErrors());
        }
        return new ResponseEntity<>(user.login(model.username(), model.password()).orElseThrow(), HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<RegisteredUserDTO> deleteEvent (
            @PathVariable Long id
    ) {
        var e = user.delete(id);
        return new ResponseEntity<>(e, HttpStatus.OK);
    }

}
