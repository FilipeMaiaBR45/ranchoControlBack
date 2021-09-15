package tads.eaj.filipe.ranchocontrol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tads.eaj.filipe.ranchocontrol.model.Endereco;
import tads.eaj.filipe.ranchocontrol.model.Funcionario;
import tads.eaj.filipe.ranchocontrol.service.EnderecoService;
import tads.eaj.filipe.ranchocontrol.service.FuncionarioService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000/", exposedHeaders = "X-Total-Count")
@RequestMapping("/funcionario")
public class FuncionarioController {

    private FuncionarioService service;

    @Autowired
    public void setService(FuncionarioService service){
        this.service = service;
    }

    @GetMapping
    public List<Funcionario> getAllFuncionario() {
        return service.getAllFuncionario();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Funcionario> getFuncionarioUnique(@PathVariable Long id) {
        Optional<Funcionario> a = service.getFuncionarioById(id);
        if (a.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(a.get());
        }
    }

    @PostMapping
    public Funcionario newFuncionario(@RequestBody Funcionario funcionario) {
        return service.insert(funcionario);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateFuncionario(@PathVariable Long id, @RequestBody Funcionario f) {
        return service.getFuncionarioById(id).map(record -> {
            if (record.getId().equals(f.getId())) {
                service.update(f);
                return ResponseEntity.ok(f);
            } else {
                return ResponseEntity.notFound().build();
            }
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Funcionario f){
        return service.getFuncionarioById(id)
                .map( record -> {
                    service.delete(record.getId());
                    return ResponseEntity.ok(f);
                }).orElse(ResponseEntity.notFound().build());
    }

}
