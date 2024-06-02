package it.epicode.Events.datalayer.repositories;

import it.epicode.Events.datalayer.entities.Roles;
import it.epicode.Events.datalayer.entities.enums.RolesType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface RolesRepository extends
        JpaRepository<Roles, Long>,
        PagingAndSortingRepository<Roles, Long> {

    Optional<Roles> findOneByRoleType(RolesType roleName);
}
