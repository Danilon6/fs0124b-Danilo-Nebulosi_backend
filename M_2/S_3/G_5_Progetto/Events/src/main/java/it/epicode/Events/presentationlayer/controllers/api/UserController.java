package it.epicode.Events.presentationlayer.controllers.api;

import it.epicode.Events.businesslayer.services.dto.LoginResponseDTO;
import it.epicode.Events.businesslayer.services.dto.RegisterUserDTO;
import it.epicode.Events.businesslayer.services.dto.RegisteredUserDTO;
import it.epicode.Events.businesslayer.services.impl.UserServiceImpl;
import it.epicode.Events.businesslayer.services.interfaces.UserService;
import it.epicode.Events.presentationlayer.controllers.api.exceptions.ApiValidationException;
import it.epicode.Events.presentationlayer.controllers.api.models.LoginModel;
import it.epicode.Events.presentationlayer.controllers.api.models.RegisterUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService user;

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

}
