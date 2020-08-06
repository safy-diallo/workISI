package hasa.hafia.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;

public class SecurityService {
    private final UserService service;

    public SecurityService(UserService service) {
        this.service = service;
    }

    public boolean isJobSeeker(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return false;
    }

    public boolean isJobOffer(){
        return false;
    }
}
