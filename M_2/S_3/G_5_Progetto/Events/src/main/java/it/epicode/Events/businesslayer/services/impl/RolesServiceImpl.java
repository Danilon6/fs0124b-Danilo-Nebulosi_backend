package it.epicode.Events.businesslayer.services.impl;

import it.epicode.Events.businesslayer.services.interfaces.RolesService;
import it.epicode.Events.datalayer.entities.Roles;
import it.epicode.Events.datalayer.entities.enums.RolesType;
import it.epicode.Events.datalayer.repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    RolesRepository roles;

    //SERVE DAVVERO? CHIEDERE A NELLO

    @Override
    public Roles save(String role) {
        var roleToEnumType = RolesType.valueOf(role);

        return roles.save(Roles.builder()
                .withRoleType(roleToEnumType).build());
    }
}
