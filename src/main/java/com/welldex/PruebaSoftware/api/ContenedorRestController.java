package com.welldex.PruebaSoftware.api;

import com.welldex.PruebaSoftware.entity.Contenedor;
import com.welldex.PruebaSoftware.service.ContenedorService;
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
@RequestMapping("/contenedor")
@RequiredArgsConstructor
public class ContenedorRestController {

    private final ContenedorService contenedorService;

    @PostMapping("/create")
    public ResponseEntity<Contenedor> createContenedor(@RequestBody Contenedor contenedor) {
        return new ResponseEntity<>(contenedorService.createContenedor(contenedor),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{folio}")
    public ResponseEntity<Boolean> deleteContenedorByFolio(@PathVariable String folio) {
        contenedorService.deleteContenedorByFolio(folio);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Contenedor> updateContenedor(@RequestBody Contenedor contenedor) {
        return new ResponseEntity<>(contenedorService.updateContenedor(contenedor),
                HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/{folio}")
    public ResponseEntity<Contenedor> getContenedorByFolio(@PathVariable String folio) {
        return new ResponseEntity<>(contenedorService.getContenedorByFolio(folio),
                HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Contenedor>> getAllContenedores() {
        return new ResponseEntity<>(contenedorService.getAllContenedores(),
                HttpStatus.OK);
    }

    @PutMapping("/descargar/{folio}")
    public ResponseEntity<Boolean> descargaContenedor(@PathVariable String folio) {
        return new ResponseEntity<>(contenedorService.descargaContenedor(folio),
                HttpStatus.ACCEPTED);
    }

}
