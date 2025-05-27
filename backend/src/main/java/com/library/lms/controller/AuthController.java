package com.library.lms.controller;

import com.library.lms.payload.request.LoginRequest;
import com.library.lms.payload.request.SignupRequest;
import com.library.lms.payload.response.JwtResponse;
import com.library.lms.payload.response.MessageResponse;
import com.library.lms.security.jwt.JwtUtils;
import com.library.lms.security.service.UserDetailsImpl;

import jakarta.validation.Valid;

import com.library.lms.model.ERole;
import com.library.lms.model.Role;
import com.library.lms.model.User;
import com.library.lms.repository.RoleRepository;
import com.library.lms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
            jwt,
            userDetails.getId(),
            userDetails.getUsername(),
            userDetails.getEmail(),
            roles
        ));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user account
        User user = new User(
            signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            passwordEncoder.encode(signUpRequest.getPassword()),
            signUpRequest.getFirstName(),
            signUpRequest.getLastName(),
            signUpRequest.getPhoneNumber(),
            signUpRequest.getAddress()
        );

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role memberRole = roleRepository.findByName(ERole.ROLE_MEMBER)
                .orElseThrow(() -> new RuntimeException("Error: Role not found."));
            roles.add(memberRole);
        } else {
            strRoles.forEach(role -> {
                switch (role.toLowerCase()) {
                    case "librarian":
                        Role librarianRole = roleRepository.findByName(ERole.ROLE_LIBRARIAN)
                            .orElseThrow(() -> new RuntimeException("Error: Role not found."));
                        roles.add(librarianRole);
                        break;
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role not found."));
                        roles.add(adminRole);
                        break;
                    default:
                        Role memberRole = roleRepository.findByName(ERole.ROLE_MEMBER)
                            .orElseThrow(() -> new RuntimeException("Error: Role not found."));
                        roles.add(memberRole);
                }
            });
        }

        user.setRoles(roles);
        user.setMembershipDate(LocalDate.now());
        user.setMembershipExpiryDate(LocalDate.now().plusYears(1));
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}