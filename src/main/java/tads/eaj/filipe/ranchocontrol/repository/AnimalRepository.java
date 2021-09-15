package tads.eaj.filipe.ranchocontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tads.eaj.filipe.ranchocontrol.model.Animal;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findAllByDeletedIsNull();
    Optional<Animal> findByDeletedIsNullAndId(Long id);
}
