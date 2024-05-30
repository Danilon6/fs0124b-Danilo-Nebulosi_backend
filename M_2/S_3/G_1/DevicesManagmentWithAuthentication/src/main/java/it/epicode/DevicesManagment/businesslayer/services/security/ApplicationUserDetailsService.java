package it.epicode.DevicesManagment.businesslayer.services.security;

import it.epicode.DevicesManagment.datalayer.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    EmployeeRepository employee;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var e = employee.findOneByUsername(username).orElseThrow();
        return SecurityUserDetails.build(e);
    }
}
