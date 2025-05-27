package com.library.lms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.library.lms.model.ERole;
import com.library.lms.model.Role;
import com.library.lms.repository.RoleRepository;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    boolean alreadySetup = false;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) return;

        createRoleIfNotFound(ERole.ROLE_MEMBER);
        createRoleIfNotFound(ERole.ROLE_LIBRARIAN);
        createRoleIfNotFound(ERole.ROLE_ADMIN);

        alreadySetup = true;
    }

    @Transactional
    void createRoleIfNotFound(ERole name) {
        Role role = roleRepository.findByName(name)
            .orElse(null);
        if (role == null) {
            role = new Role();
            role.setName(name);
            roleRepository.save(role);
        }
    }
}