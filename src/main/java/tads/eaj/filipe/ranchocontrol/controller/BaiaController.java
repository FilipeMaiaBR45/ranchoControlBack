package tads.eaj.filipe.ranchocontrol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tads.eaj.filipe.ranchocontrol.model.Baia;
import tads.eaj.filipe.ranchocontrol.service.BaiaService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000/", exposedHeaders = "X-Total-Count")
@RequestMapping("/baia")
public class BaiaController {

    private BaiaService service;

    @Autowired
    public void setService(BaiaService service){
        this.service = service;
    }

    @GetMapping
    public List<Baia> getAllBaia() {
        return service.getAllBaia();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Baia> getBaiaUnique(@PathVariable Long id) {
        Optional<Baia> a = service.getBaiaById(id);
        if (a.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(a.get());
        }
    }

    @PostMapping
    public Baia newBaia(@RequestBody Baia baia) {
        return service.insert(baia);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateBaia(@PathVariable Long id, @RequestBody Baia b) {
        return service.getBaiaById(id).map(record -> {
            if (record.getId().equals(b.getId())) {
                service.update(b);
                return ResponseEntity.ok(b);
            } else {
                return ResponseEntity.notFound().build();
            }
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Baia b){
        return service.getBaiaById(id)
                .map( record -> {
                    service.delete(record.getId());
                    return ResponseEntity.ok(b);
                }).orElse(ResponseEntity.notFound().build());
    }
}
