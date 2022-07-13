package com.dentist.controller;

import com.dentist.model.dto.PacienteDTO;
import com.dentist.service.impl.PacienteService;
import com.dentist.util.IValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/pacientes")
@CrossOrigin(origins = "*")
public class PacienteController {

    // ATRIBUTOS
    @Autowired
    private PacienteService service;
    @Autowired
    private IValidation<PacienteDTO> validationBody;
    @Autowired
    private IValidation<Integer> validationId;
    /* ---------------------------------------------------------------------*/

    /* ──────────────────────────
           GUARDAR / POST
    ────────────────────────── */
    @PostMapping
    public ResponseEntity<String> postPaciente(@RequestBody PacienteDTO pacienteDTO) {
        if (validationBody.validate(pacienteDTO)) {
            service.saveAndFlush(pacienteDTO);
            return ResponseEntity.ok("El paciente se guardó con éxito");
        } else return ResponseEntity.badRequest().body("El paciente debe tener todos los campos completos");
    }
    /* ---------------------------------------------------------------------*/

    /* ──────────────────────────
      BUSCAR TODOS / GET ALL
    ────────────────────────── */
    @GetMapping
    public List<PacienteDTO> getAll() {
        return service.findAll();
    }
    /* ---------------------------------------------------------------------*/

    /* ──────────────────────────
      BUSCAR POR ID / GET BY ID
    ────────────────────────── */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        if (validationId.validate(id)) {
            PacienteDTO pacienteResponse = service.findById(id);
            return ResponseEntity.ok(pacienteResponse);
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
            return ResponseEntity.ok().body("El paciente fue eliminado con éxito");
        } else return ResponseEntity.badRequest().body("El ID ingresado no es válido");
    }
    /* ---------------------------------------------------------------------*/

    /* ──────────────────────────
        MODIFICAR / PUT BY ID
     ────────────────────────── */
    @PutMapping("/{id}")
    public ResponseEntity<String> putById(@PathVariable Integer id, @RequestBody PacienteDTO pacienteDTO) {
        if (validationId.validate(id) && validationBody.validate(pacienteDTO)) {
            pacienteDTO.setId(id);
            service.update(pacienteDTO);
            return ResponseEntity.ok().body("El paciente fue modificado con éxito");
        } else return ResponseEntity.badRequest().body("Los datos ingresados no son válidos");
    }
    /* ---------------------------------------------------------------------*/

}
