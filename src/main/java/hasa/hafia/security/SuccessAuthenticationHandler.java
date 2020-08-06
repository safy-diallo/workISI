package hasa.hafia.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SuccessAuthenticationHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails details = (UserDetails) authentication.getPrincipal();
        GrantedAuthority authority = details.getAuthorities().stream().findFirst()
                .orElseThrow(() -> new RuntimeException("vous navez aucun role"));
        if (authority.getAuthority().equalsIgnoreCase("ROLE_DEMANDEUR")){
            response.sendRedirect("/demandes");
        }else if (authority.getAuthority().equalsIgnoreCase("ROLE_RECRUTEUR")){
            response.sendRedirect("offres");
        }
    }
}
