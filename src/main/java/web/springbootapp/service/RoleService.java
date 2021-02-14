package web.springbootapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.springbootapp.model.Role;
import web.springbootapp.repositories.RoleRepository;

import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    public Role findByRoleName(String roleName) {
        return repository.findByRole(roleName);

    }



}
