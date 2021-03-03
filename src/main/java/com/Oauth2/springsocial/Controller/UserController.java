package com.Oauth2.springsocial.Controller;

import com.Oauth2.springsocial.exception.ResourceNotFoundException;
import com.Oauth2.springsocial.model.User;
import com.Oauth2.springsocial.repository.UserRepository;
import com.Oauth2.springsocial.security.CurrentUser;
import com.Oauth2.springsocial.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
