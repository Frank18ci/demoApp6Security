package org.carpio.com.demoapp6;

import lombok.RequiredArgsConstructor;
import org.carpio.com.demoapp6.entity.UserEntity;
import org.carpio.com.demoapp6.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class DemoApp6Application implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(DemoApp6Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.findByUsername("pepe").isEmpty()){
            userRepository.save(
                    UserEntity.builder()
                            .username("pepe")
                            .password(passwordEncoder.encode("123"))
                            .roles(Arrays.asList("ADMIN"))
                            .available(true)
                            .build()
            );
        }
        if(userRepository.findByUsername("carlos").isEmpty()){
            userRepository.save(
                    UserEntity.builder()
                            .username("carlos")
                            .password(passwordEncoder.encode("123"))
                            .roles(Arrays.asList("USER"))
                            .available(true)
                            .build()
            );
        }
        if(userRepository.findByUsername("maria").isEmpty()){
            userRepository.save(
                    UserEntity.builder()
                            .username("maria")
                            .password(passwordEncoder.encode("123"))
                            .roles(Arrays.asList("OTROS"))
                            .available(true)
                            .build()
            );
        }
    }
}
