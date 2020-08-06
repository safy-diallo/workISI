package hasa.hafia.service;

import hasa.hafia.entites.Roles;
import hasa.hafia.entites.Users;
import hasa.hafia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Users create(Users user){
        String hashed = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashed);
        //todo...
        return repository.save(user);
    }

    @Transactional
    public Users update(Long id, Users user){
        Users existing = findById(id);
        //todo...
        return repository.save(existing);
    }

    public Users findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("ce utilisateur n'existe pas"));
    }

    @Transactional
    public void delete(Long id){
        if (!repository.existsById(id))
            throw new RuntimeException("ce utilisateur n'existe pas");
        repository.deleteById(id);
    }

    public List<Users> getAll(){
        return repository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String s){
        Users user = repository.getByUsername(s).orElseThrow(() -> new UsernameNotFoundException(s));
        List<GrantedAuthority> authorities = new ArrayList<>();
        Roles roles = user.getRoles().stream().findFirst().orElseThrow(() -> new RuntimeException("cet utilisateur n'a aucun role"));
        authorities.add(new SimpleGrantedAuthority(roles.getLibelle()));
        System.out.println("******************************************************");
        System.out.println(roles.getLibelle());
        System.out.println(user.getUsername());
        return new User(user.getUsername(), user.getPassword(), authorities);
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
