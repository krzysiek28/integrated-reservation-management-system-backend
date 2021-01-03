package com.uliasz.irms.backend.rest.controllers;

import com.uliasz.irms.backend.rest.objects.request.LoginRequest;
import com.uliasz.irms.backend.rest.objects.request.RegisterRequest;
import com.uliasz.irms.backend.rest.objects.response.JwtResponse;
import com.uliasz.irms.backend.rest.objects.response.MessageResponse;
import com.uliasz.irms.internal.database.entities.AppUserEntity;
import com.uliasz.irms.internal.database.repositories.AppUserRepository;
import com.uliasz.irms.internal.emailService.EmailService;
import com.uliasz.irms.security.jwt.JWTService;
import com.uliasz.irms.security.services.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.uliasz.irms.internal.common.enums.UserRoles.USER;

@RestController
@RequestMapping("/authApi")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final EmailService emailService;

    @PostMapping("/signIn")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles.get(0)));
    }

    /** Allows only register USER_ROLE accounts */
    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        if (appUserRepository.existsByLogin(registerRequest.getUserName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Błąd: Nazwa użytkownika jest już używana!"));
        }

        if (appUserRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Błąd: Email jest już używany!"));
        }

        AppUserEntity user = AppUserEntity.builder()
                .login(registerRequest.getUserName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .role(USER.getValue())
                .enabled(true)
                .build();

        appUserRepository.save(user);
        emailService.sendSuccessfullyRegistrationMail(registerRequest.getEmail(), registerRequest.getUserName());
        return ResponseEntity.ok(new MessageResponse("Rejestracja zakończyła się pomyślnie!"));
    }
}
