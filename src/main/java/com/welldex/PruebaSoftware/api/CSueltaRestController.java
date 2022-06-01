package com.welldex.PruebaSoftware.api;

import com.welldex.PruebaSoftware.entity.CSuelta;
import com.welldex.PruebaSoftware.service.CSueltaService;
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
@RequestMapping("/c_suelta")
@RequiredArgsConstructor
public class CSueltaRestController {

    private final CSueltaService cSueltaService;

    @PostMapping("/create")
    public ResponseEntity<CSuelta> createCSuelta(@RequestBody CSuelta cSuelta) {
        return new ResponseEntity<>(cSueltaService.createCSuelta(cSuelta),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteCSuelta(@PathVariable long id) {
        cSueltaService.deleteCSueltaById(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<CSuelta> updateCSuelta(@RequestBody CSuelta cSuelta) {
        return new ResponseEntity<>(cSueltaService.updateCSuelta(cSuelta),
                HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CSuelta> getCSueltaById(@PathVariable long id) {
        return new ResponseEntity<>(cSueltaService.getCSueltaById(id),
                HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<CSuelta>> getAllCSueltas() {
        return new ResponseEntity<>(cSueltaService.getAllCargasSuelta(),
                HttpStatus.OK);
    }

    @PutMapping("/descarga/{id}/{canDescargada}")
    public ResponseEntity<Boolean> descargaCargaSuelta(@PathVariable long id, @PathVariable double canDescargada) {
        return new ResponseEntity<>(cSueltaService.descargaCargaSuelta(id, canDescargada),
                HttpStatus.ACCEPTED);
    }

}
