package tads.eaj.filipe.ranchocontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tads.eaj.filipe.ranchocontrol.model.Baia;

import java.util.List;
import java.util.Optional;

public interface BaiaRepository extends JpaRepository<Baia, Long> {
    List<Baia> findAllByDeletedIsNull();
    Optional<Baia> findByDeletedIsNullAndId(Long id);
}
