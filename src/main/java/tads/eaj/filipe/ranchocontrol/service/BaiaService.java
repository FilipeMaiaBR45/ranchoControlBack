package tads.eaj.filipe.ranchocontrol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tads.eaj.filipe.ranchocontrol.model.Animal;
import tads.eaj.filipe.ranchocontrol.model.Baia;
import tads.eaj.filipe.ranchocontrol.repository.AnimalRepository;
import tads.eaj.filipe.ranchocontrol.repository.BaiaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BaiaService {

    private BaiaRepository repository;

    @Autowired
    public void setRepository(BaiaRepository repository) {
        this.repository = repository;
    }

    public Optional<Baia> getBaiaById(Long id){
        return repository.findByDeletedIsNullAndId(id);
    }

    public List<Baia> getAllBaia(){
        return repository.findAllByDeletedIsNull();
    }

    public Baia insert(Baia b){
        return repository.save(b);
    }

    public Baia update(Baia b){
        return  repository.saveAndFlush(b);
    }
    public Baia delete(Long id){
        Baia b = repository.getById(id);
        b.setDeleted(new Date());
        return repository.saveAndFlush(b);
    }
}
