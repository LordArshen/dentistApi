package com.dentist.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private IUserRepository userRepository;

    @Autowired
    public DataLoader(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("admin1");
        String password2 = passwordEncoder.encode("user1");

        userRepository.save(new AppUser("admin", "admin", "admin", password, AppUsuarioRoles.ROLE_ADMIN));
        userRepository.save(new AppUser("user", "user", "user", password2, AppUsuarioRoles.ROLE_USER));

    }
}
