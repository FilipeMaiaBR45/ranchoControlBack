package tads.eaj.filipe.ranchocontrol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tads.eaj.filipe.ranchocontrol.model.Endereco;
import tads.eaj.filipe.ranchocontrol.model.Funcionario;
import tads.eaj.filipe.ranchocontrol.repository.EnderecoRepository;
import tads.eaj.filipe.ranchocontrol.repository.FuncionarioRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    private FuncionarioRepository repository;

    @Autowired
    public void setRepository(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public Optional<Funcionario> getFuncionarioById(Long id){
        return repository.findByDeletedIsNullAndId(id);
    }

    public List<Funcionario> getAllFuncionario(){
        return repository.findAllByDeletedIsNull();
    }

    public Funcionario insert(Funcionario f){
        return repository.save(f);
    }

    public Funcionario update(Funcionario f){
        return  repository.saveAndFlush(f);
    }
    public Funcionario delete(Long id){
        Funcionario f = repository.getById(id);
        f.setDeleted(new Date());
        return repository.saveAndFlush(f);
    }
}
