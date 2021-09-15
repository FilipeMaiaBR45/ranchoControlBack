package tads.eaj.filipe.ranchocontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tads.eaj.filipe.ranchocontrol.model.Funcionario;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    List<Funcionario> findAllByDeletedIsNull();
    Optional<Funcionario> findByDeletedIsNullAndId(Long id);
}
