package tads.eaj.filipe.ranchocontrol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tads.eaj.filipe.ranchocontrol.model.Animal;
import tads.eaj.filipe.ranchocontrol.repository.AnimalRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    private AnimalRepository repository;

    @Autowired
    public void setRepository(AnimalRepository repository) {
        this.repository = repository;
    }

    public Optional<Animal> getAnimalById(Long id){
        return repository.findByDeletedIsNullAndId(id);
    }

    public List<Animal> getAllAnimal(){
        return repository.findAllByDeletedIsNull();
    }

    public Animal insert(Animal a){
        return repository.save(a);
    }

    public Animal update(Animal a){
        return  repository.saveAndFlush(a);
    }
    public Animal delete(Long id){
        Animal a = repository.getById(id);
        a.setDeleted(new Date());
        return repository.saveAndFlush(a);
    }



}
