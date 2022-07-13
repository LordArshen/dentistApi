package com.dentist;

import com.dentist.exception.NotFoundIdException;
import com.dentist.model.dto.PacienteDTO;
import com.dentist.service.IPacienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
@Transactional
class PacienteServiceTest {

    @Autowired
    IPacienteService pacienteService;

    @Test
    void _1_testCrearPaciente() {
        // DADOS
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setName("Ichigo1");
        pacienteDTO.setLastname("Kurosaki1");

        // CUANDO
       pacienteDTO =  pacienteService.saveAndFlush(pacienteDTO);
        PacienteDTO resultadoEsperado1 = pacienteService.findById(pacienteDTO.getId());

        // ENTONCES
        Assertions.assertNotNull(resultadoEsperado1);
        Assertions.assertEquals("Ichigo1", resultadoEsperado1.getName());
        Assertions.assertEquals("Kurosaki1", resultadoEsperado1.getLastname());
    }

    @Test
    void _2_testBuscarPacientePorId() {
        // DADOS
        PacienteDTO pacienteDTO2 = new PacienteDTO();
        pacienteDTO2.setName("Ichigo2");
        pacienteDTO2.setLastname("Kurosaki2");
       pacienteDTO2 = pacienteService.saveAndFlush(pacienteDTO2);

        // CUANDO
        PacienteDTO resultadoEsperado2 = pacienteService.findById(pacienteDTO2.getId());
        // ENTONCES
        Assertions.assertNotNull(resultadoEsperado2);
        Assertions.assertEquals("Ichigo2", resultadoEsperado2.getName());
        Assertions.assertEquals("Kurosaki2", resultadoEsperado2.getLastname());
    }

    @Test
    public void _3_testBuscarTodosLosPacientes() {
        // DADOS
        PacienteDTO pacienteDTO3 = new PacienteDTO();
        pacienteDTO3.setName("Ichigo3");
        pacienteDTO3.setLastname("Kurosaki3");
        pacienteService.saveAndFlush(pacienteDTO3);

        PacienteDTO pacienteDTO4 = new PacienteDTO();
        pacienteDTO4.setName("Ichigo4");
        pacienteDTO4.setLastname("Kurosaki4");
        pacienteService.saveAndFlush(pacienteDTO4);

        // CUANDO
        List<PacienteDTO> listaPacientesEncontrados = pacienteService.findAll();

        // ENTONCES
        Assertions.assertTrue(listaPacientesEncontrados.size() > 0);

    }

    @Test
    public void _4_testEliminarPaciente() {
        // DADOS
        PacienteDTO pacienteDTO5 = new PacienteDTO();
        pacienteDTO5.setName("Ichigo5");
        pacienteDTO5.setLastname("Kurosaki5");
        pacienteDTO5 = pacienteService.saveAndFlush(pacienteDTO5);
        Integer idEliminado = pacienteDTO5.getId();

        // CUANDO
        pacienteService.deleteById(pacienteDTO5.getId());

        // ENTONCES
        Assertions.assertThrows(NotFoundIdException.class, ()-> pacienteService.findById(idEliminado));
    }

    @Test
    public void _5_testModificarPaciente() {
        // DADOS
        PacienteDTO pacienteDTO6 = new PacienteDTO();
        pacienteDTO6.setName("Ichigo6");
        pacienteDTO6.setLastname("Kurosaki6");
        pacienteDTO6 = pacienteService.saveAndFlush(pacienteDTO6);
        pacienteDTO6.setLastname("OtroApellido");

        // CUANDO
        pacienteService.update(pacienteDTO6);
        PacienteDTO resultadoEsperado4 = pacienteService.findById(pacienteDTO6.getId());

        // ENTONCES
        Assertions.assertEquals(resultadoEsperado4.getLastname(), pacienteDTO6.getLastname());
        Assertions.assertEquals("Ichigo6", resultadoEsperado4.getName());
        Assertions.assertEquals("OtroApellido", resultadoEsperado4.getLastname());
    }

}