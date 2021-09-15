package tads.eaj.filipe.ranchocontrol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tads.eaj.filipe.ranchocontrol.model.Baia;
import tads.eaj.filipe.ranchocontrol.model.Endereco;
import tads.eaj.filipe.ranchocontrol.repository.BaiaRepository;
import tads.eaj.filipe.ranchocontrol.repository.EnderecoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private EnderecoRepository repository;

    @Autowired
    public void setRepository(EnderecoRepository repository) {
        this.repository = repository;
    }

    public Optional<Endereco> getEnderecoById(Long id){
        return repository.findByDeletedIsNullAndId(id);
    }

    public List<Endereco> getAllEndereco(){
        return repository.findAllByDeletedIsNull();
    }

    public Endereco insert(Endereco e){
        return repository.save(e);
    }

    public Endereco update(Endereco e){
        return  repository.saveAndFlush(e);
    }
    public Endereco delete(Long id){
        Endereco e = repository.getById(id);
        e.setDeleted(new Date());
        return repository.saveAndFlush(e);
    }
}
