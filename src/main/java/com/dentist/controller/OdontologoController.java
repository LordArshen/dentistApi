package com.dentist.controller;

import com.dentist.model.dto.OdontologoDTO;
import com.dentist.service.impl.OdontologoService;
import com.dentist.util.IValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/odontologos")
@CrossOrigin(origins = "*")
public class OdontologoController {

    // ATRIBUTOS
    @Autowired
    private OdontologoService service;
    @Autowired
    private IValidation<OdontologoDTO> validateBody;
    @Autowired
    private IValidation<Integer> validateId;
    /* ---------------------------------------------------------------------*/

    /* ──────────────────────────
           GUARDAR / POST
    ────────────────────────── */
    @PostMapping
    public ResponseEntity<String> postOdontologo(@RequestBody OdontologoDTO odontologoDTO) {
        
        if (validateBody.validate(odontologoDTO)) {
            service.saveAndFlush(odontologoDTO);
            return ResponseEntity.ok().body("El odontólogo fue guardado con éxito");
        } else
            return ResponseEntity.badRequest().body("El odontólogo no pudo ser guardado porque los datos están incompletos");
    }
    /* ---------------------------------------------------------------------*/

    /* ──────────────────────────
        BUSCAR TODOS / GET ALL
     ────────────────────────── */
    @GetMapping
    public List<OdontologoDTO> getAll() {
        return service.findAll();
    }
    /* ---------------------------------------------------------------------*/

    /* ──────────────────────────
      BUSCAR POR ID / GET BY ID
    ────────────────────────── */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        if (validateId.validate(id)) {
            OdontologoDTO odontologoResponse = service.findById(id);
            return ResponseEntity.ok(odontologoResponse);
        } else return ResponseEntity.badRequest().body("El ID ingresado no es válido");
    }
    /* ---------------------------------------------------------------------*/

    /* ──────────────────────────
    ELIMINAR POR ID / DELETE BY ID
    ────────────────────────── */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {
        if (validateId.validate(id)) {
            service.deleteById(id);
            return ResponseEntity.ok().body("El odontólogo fue eliminado con éxito");
        } else return ResponseEntity.badRequest().body("El ID ingresado no es válido");
    }
    /* ---------------------------------------------------------------------*/

    /* ──────────────────────────
      MODIFICAR POR ID / PUT BY ID
    ────────────────────────── */
    @PutMapping("{id}")
    public ResponseEntity<?> putById(@PathVariable Integer id, @RequestBody OdontologoDTO odontologoDTO) {
        if (validateId.validate(id) && validateBody.validate(odontologoDTO)) {
            odontologoDTO.setId(id);
            service.update(odontologoDTO);
            return ResponseEntity.ok().body("El odontólogo fue modificado con éxito");
        } else
            return ResponseEntity.badRequest().body("El odontólogo no pudo ser modificado porque los datos ingresados son incorrectos");
    }
    /* ---------------------------------------------------------------------*/

}

