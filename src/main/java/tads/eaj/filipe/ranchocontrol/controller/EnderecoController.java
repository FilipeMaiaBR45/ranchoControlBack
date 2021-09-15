package tads.eaj.filipe.ranchocontrol.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tads.eaj.filipe.ranchocontrol.model.Endereco;
import tads.eaj.filipe.ranchocontrol.service.EnderecoService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000/", exposedHeaders = "X-Total-Count")
@RequestMapping("/endereco")
public class EnderecoController {

    private EnderecoService service;

    @Autowired
    public void setService(EnderecoService service){
        this.service = service;
    }

    @GetMapping
    public List<Endereco> getAllBaia() {
        return service.getAllEndereco();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Endereco> getEnderecoUnique(@PathVariable Long id) {
        Optional<Endereco> a = service.getEnderecoById(id);
        if (a.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(a.get());
        }
    }

    @PostMapping
    public Endereco newEndereco(@RequestBody Endereco endereco) {
        return service.insert(endereco);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateEndereco(@PathVariable Long id, @RequestBody Endereco e) {
        return service.getEnderecoById(id).map(record -> {
            if (record.getId().equals(e.getId())) {
                service.update(e);
                return ResponseEntity.ok(e);
            } else {
                return ResponseEntity.notFound().build();
            }
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Endereco e){
        return service.getEnderecoById(id)
                .map( record -> {
                    service.delete(record.getId());
                    return ResponseEntity.ok(e);
                }).orElse(ResponseEntity.notFound().build());
    }
}
