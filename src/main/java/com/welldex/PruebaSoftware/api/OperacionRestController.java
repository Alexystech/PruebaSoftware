package com.welldex.PruebaSoftware.api;

import com.welldex.PruebaSoftware.entity.Operacion;
import com.welldex.PruebaSoftware.service.OperacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/operacion")
@RequiredArgsConstructor
public class OperacionRestController {

    private final OperacionService operacionService;

    @PostMapping("/create")
    public ResponseEntity<Operacion> createOperacion(@RequestBody Operacion operacion) {
        return new ResponseEntity<>(operacion, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteOperacionByFolio(@PathVariable String folio) {
        operacionService.deleteOperacionByFolio(folio);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Operacion> updateOperacion(@RequestBody Operacion operacion) {
        return new ResponseEntity<>(operacionService.updateOperacion(operacion),
                HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Operacion> getOperacionByFolio(@PathVariable String folio) {
        return new ResponseEntity<>(operacionService.getOperacionByFolio(folio),
                HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Operacion>> getAllOperaciones() {
        return new ResponseEntity<>(operacionService.getAllOperaciones(),
                HttpStatus.OK);
    }

}
