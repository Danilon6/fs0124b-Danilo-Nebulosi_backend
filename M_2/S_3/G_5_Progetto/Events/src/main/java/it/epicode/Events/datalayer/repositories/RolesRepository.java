package it.epicode.Events.datalayer.repositories;

import it.epicode.Events.datalayer.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RolesRepository extends
        JpaRepository<Roles, Long>,
        PagingAndSortingRepository<Roles, Long> {
}
