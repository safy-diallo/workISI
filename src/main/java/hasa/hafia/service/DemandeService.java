package hasa.hafia.service;

import hasa.hafia.entites.Demande;
import hasa.hafia.repository.DemandeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DemandeService {
    private final DemandeRepository repository;

    public DemandeService(DemandeRepository repository) {
        this.repository = repository;
    }

    public Demande create(Demande demandeur) {
        return repository.save(demandeur);
    }

    public Demande findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Ce demandeur n'exsite pas"));
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public void delete(Long id) {
        if (!existsById(id))
            throw new RuntimeException("ce demandeur n'exsite pas");
        repository.deleteById(id);
    }

    public List<Demande> findAll() {
        return repository.findAll();
    }
}
