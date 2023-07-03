package com.example.studentservice.bootstrap;

import com.example.studentservice.model.Role;
import com.example.studentservice.repositories.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoadData implements CommandLineRunner {
    private final RoleRepo roleRepositories;

    @Override
    public void run(String... args) throws Exception {
        saveUsers();

    }

    private void saveUsers() {
        if (roleRepositories.count() == 0) {

            Role savedStudent = roleRepositories.save(new Role("STUDENT"));

            Role savedAdminRole = roleRepositories.save(new Role("ADMIN"));
        }
    }
}


