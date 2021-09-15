package tads.eaj.filipe.ranchocontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tads.eaj.filipe.ranchocontrol.model.Endereco;

import java.util.List;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findAllByDeletedIsNull();
    Optional<Endereco> findByDeletedIsNullAndId(Long id);
}
