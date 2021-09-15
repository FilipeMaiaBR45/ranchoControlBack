package tads.eaj.filipe.ranchocontrol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tads.eaj.filipe.ranchocontrol.model.Animal;
import tads.eaj.filipe.ranchocontrol.service.AnimalService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000/", exposedHeaders = "X-Total-Count")
@RequestMapping("/animal")
public class AnimalController {

    private AnimalService service;

    @Autowired
    public void setService(AnimalService service){
        this.service = service;
    }

    @GetMapping
    public List<Animal> getAllAnimal() {
        return service.getAllAnimal();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Animal> getAnimalUnique(@PathVariable Long id) {
        Optional<Animal> a = service.getAnimalById(id);
        if (a.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(a.get());
        }
    }

    @PostMapping
    public Animal newAnimal(@RequestBody Animal animal) {
        return service.insert(animal);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateAnimal(@PathVariable Long id, @RequestBody Animal a) {
        return service.getAnimalById(id).map(record -> {
            if (record.getId().equals(a.getId())) {
                service.update(a);
                return ResponseEntity.ok(a);
            } else {
                return ResponseEntity.notFound().build();
            }
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Animal a){
        return service.getAnimalById(id)
                .map( record -> {
                    service.delete(record.getId());
                    return ResponseEntity.ok(a);
                }).orElse(ResponseEntity.notFound().build());
    }






}
