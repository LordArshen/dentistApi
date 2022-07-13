package com.dentist.controller;

import com.dentist.model.dto.TurnoDTO;
import com.dentist.service.impl.TurnoService;
import com.dentist.util.IValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/turnos")
@CrossOrigin(origins = "*")
public class TurnoController {

    @Autowired
    private TurnoService service;
    @Autowired
    private IValidation<TurnoDTO> validationBody;
    @Autowired
    private IValidation<Integer> validationId;
    /* ---------------------------------------------------------------------*/

    /* ──────────────────────────
         GUARDAR / POST
    ────────────────────────── */
    @PostMapping
    public ResponseEntity<String> postTurno(@RequestBody TurnoDTO turnoDTO) {
        if (validationBody.validate(turnoDTO)) {
            service.saveAndFlush(turnoDTO);
            return ResponseEntity.ok().body("El turno fue guardado con éxito");
        } else return ResponseEntity.badRequest().body("Los datos ingresados son incompletos o incorrectos");
    }
    /* ---------------------------------------------------------------------*/

    /* ──────────────────────────
       BUSCAR TODOS / GET ALL
    ────────────────────────── */
    @GetMapping
    public List<TurnoDTO> getAll() {
        return service.findAll();
    }
    /* ---------------------------------------------------------------------*/

    /* ──────────────────────────
     BUSCAR POR ID / GET BY ID
    ────────────────────────── */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        if (validationId.validate(id)) {
            TurnoDTO turnoResponse = service.findById(id);
            return ResponseEntity.ok(turnoResponse);
        } else return ResponseEntity.badRequest().body("El ID ingresado no es válido");
    }
    /* ---------------------------------------------------------------------*/

    /* ──────────────────────────
       ELIMINAR / DELETE BY ID
    ────────────────────────── */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id) {
        if (validationId.validate(id)) {
            service.deleteById(id);
            return ResponseEntity.ok().body("El turno fue eliminado con éxito");
        } else return ResponseEntity.badRequest().body("El ID ingresado no es válido");
    }
    /* ---------------------------------------------------------------------*/

    /* ──────────────────────────
       MODIFICAR POR ID / PUT
    ────────────────────────── */
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody TurnoDTO turnoDto) {
        if (validationBody.validate(turnoDto) && validationId.validate(id)) {
            turnoDto.setId(id);
            service.update(turnoDto);
            return ResponseEntity.ok().body("El turno se modificó con éxito");
        } else return ResponseEntity.badRequest().body("Los datos ingresados no son válidos");
    }

}
