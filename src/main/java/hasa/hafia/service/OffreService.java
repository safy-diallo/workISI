package hasa.hafia.service;

import hasa.hafia.entites.Offres;
import hasa.hafia.repository.OffreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OffreService {
    private final OffreRepository repository;

    public OffreService(OffreRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Offres create(Offres offres){
        return repository.save(offres);
    }

    public Offres findById(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Cette offre n'existe pas"));
    }

    @Transactional
    public Offres update(Long id, Offres offres){
        Offres existing = findById(id);
        /* todo */
        return existing;
    }

    @Transactional
    public void delete(Long id){
        if (!repository.existsById(id))
            throw new RuntimeException("Cette offre n'existe pas");
        repository.deleteById(id);
    }

    public List<Offres> findAll(){
        return repository.findAll();
    }
}
