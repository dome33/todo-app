package com.nefos.spring.jpa.h2.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nefos.spring.jpa.h2.model.ERole;
import com.nefos.spring.jpa.h2.model.Role;
import com.nefos.spring.jpa.h2.model.Task;
import com.nefos.spring.jpa.h2.model.User;
import com.nefos.spring.jpa.h2.repository.TaskRepository;
import com.nefos.spring.jpa.h2.repository.UserRepository;
import com.nefos.spring.jpa.h2.repository.RoleRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(TaskRepository taskRepo, RoleRepository roleRepo, UserRepository userRepo) {
        return args -> {
            
            
            roleRepo.deleteAll();

            roleRepo.save(new Role(ERole.ROLE_ADMIN));
            roleRepo.save(new Role(ERole.ROLE_MODERATOR));
            roleRepo.save(new Role(ERole.ROLE_USER));

            taskRepo.save(new Task("Write email", "write email to finance", false));
            taskRepo.save(new Task("Meeting", "meeting with hr", false));

            User u = new User("frank", "frank@gmail.com", "frank");

            Set<Role> roles = new HashSet<>();

            Optional<Role> defaultUserRole = roleRepo.findByName(ERole.ROLE_USER);

            if(defaultUserRole.isPresent()) {
                roles.add(defaultUserRole.get());
            }

            u.setRoles(roles);

            userRepo.save(u);

            // Optional: log results to console
            System.out.println("✅ Sample roles inserted into H2 database:");
           
        };
    }
}
