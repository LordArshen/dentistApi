package com.dentist;

import com.dentist.exception.BadRequestExceptionService;
import com.dentist.exception.NotFoundIdException;
import com.dentist.model.dto.OdontologoDTO;
import com.dentist.model.dto.PacienteDTO;
import com.dentist.model.dto.TurnoDTO;
import com.dentist.model.entity.Domicilio;
import com.dentist.service.IOdontologoService;
import com.dentist.service.IPacienteService;
import com.dentist.service.ITurnoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;


@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
@Transactional
class TurnoServiceTest {

    @Autowired
    ITurnoService turnoService;
    @Autowired
    IOdontologoService odontologoService;
    @Autowired
    IPacienteService pacienteService;

    @Test
    void _1_testGuardarTurno() {
        // DADOS
        OdontologoDTO odontologoDTO1 = new OdontologoDTO();
        odontologoDTO1.setName("Pepito1");
        odontologoDTO1.setLastName("perez1");
        odontologoDTO1 = odontologoService.saveAndFlush(odontologoDTO1);

        PacienteDTO pacienteDTO1 = new PacienteDTO();
        pacienteDTO1.setName("Ichigo1");
        pacienteDTO1.setLastname("Kurosaki1");
        pacienteDTO1 = pacienteService.saveAndFlush(pacienteDTO1);

        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setOdontologo(odontologoDTO1);
        turnoDTO.setPaciente(pacienteDTO1);
        turnoDTO.setSchedule(LocalDateTime.of(2022, Month.JUNE, 8, 12, 30));

        // CUANDO
        turnoDTO = turnoService.saveAndFlush(turnoDTO);
        TurnoDTO resultadoEsperado = turnoService.findById(turnoDTO.getId());

        // ENTONCES
        Assertions.assertNotNull(resultadoEsperado);
        Assertions.assertEquals("Pepito1", resultadoEsperado.getOdontologo().getName());
        Assertions.assertEquals("Ichigo1", resultadoEsperado.getPaciente().getName());
    }

    @Test
    void _2_testBuscarTurnoPorId() throws BadRequestExceptionService {
        // DADOS
        OdontologoDTO odontologoDTO2 = new OdontologoDTO();
        odontologoDTO2.setName("Pepito2");
        odontologoDTO2.setLastName("perez2");
        odontologoDTO2 = odontologoService.saveAndFlush(odontologoDTO2);

        PacienteDTO pacienteDTO2 = new PacienteDTO();
        pacienteDTO2.setName("Ichigo2");
        pacienteDTO2.setLastname("Kurosaki2");
        pacienteDTO2.setDomicilio(new Domicilio());
        pacienteDTO2 = pacienteService.saveAndFlush(pacienteDTO2);

        TurnoDTO turnoDTO1 = new TurnoDTO();
        turnoDTO1.setOdontologo((odontologoDTO2));
        turnoDTO1.setPaciente(pacienteDTO2);
        turnoDTO1.setSchedule(LocalDateTime.of(2022, Month.JUNE, 9, 12, 30));
        turnoDTO1 = turnoService.saveAndFlush(turnoDTO1);

        // CUANDO
        TurnoDTO resultadoEsperado2 = turnoService.findById(turnoDTO1.getId());

        // ENTONCES
        Assertions.assertNotNull(resultadoEsperado2);
        Assertions.assertEquals("Pepito2", resultadoEsperado2.getOdontologo().getName());
        Assertions.assertEquals("Ichigo2", resultadoEsperado2.getPaciente().getName());
    }

    @Test
    void _3_BuscarTodosLosTurnos() throws BadRequestExceptionService {
        // DADOS
        OdontologoDTO odontologoDTO3 = new OdontologoDTO();
        odontologoDTO3.setName("Pepito3");
        odontologoDTO3.setLastName("perez3");
        odontologoDTO3 = odontologoService.saveAndFlush(odontologoDTO3);


        PacienteDTO pacienteDTO3 = new PacienteDTO();
        pacienteDTO3.setName("Ichigo3");
        pacienteDTO3.setLastname("Kurosaki3");
        pacienteDTO3 = pacienteService.saveAndFlush(pacienteDTO3);

        TurnoDTO turnoDTO2 = new TurnoDTO();
        turnoDTO2.setOdontologo((odontologoDTO3));
        turnoDTO2.setPaciente(pacienteDTO3);
        turnoDTO2.setSchedule(LocalDateTime.of(2022, Month.JUNE, 10, 12, 30));
        turnoService.saveAndFlush(turnoDTO2);

        // CUANDO
        List<TurnoDTO> listaTurnosEncontrados = turnoService.findAll();

        // ENTONCES
        Assertions.assertTrue(listaTurnosEncontrados.size() > 0);
    }

    @Test
    void _4_testEliminarTurno() throws BadRequestExceptionService {
        // DADOS
        OdontologoDTO odontologoDTO4 = new OdontologoDTO();
        odontologoDTO4.setName("Pepito4");
        odontologoDTO4.setLastName("perez4");
        odontologoDTO4 = odontologoService.saveAndFlush(odontologoDTO4);

        PacienteDTO pacienteDTO4 = new PacienteDTO();
        pacienteDTO4.setName("Ichigo4");
        pacienteDTO4.setLastname("Kurosaki4");
        pacienteDTO4 = pacienteService.saveAndFlush(pacienteDTO4);


        TurnoDTO turnoDTO3 = new TurnoDTO();
        turnoDTO3.setOdontologo((odontologoDTO4));
        turnoDTO3.setPaciente(pacienteDTO4);
        turnoDTO3.setSchedule(LocalDateTime.of(2022, Month.JUNE, 11, 12, 30));
        turnoDTO3 = turnoService.saveAndFlush(turnoDTO3);
        Integer idEliminado = turnoDTO3.getId();

        // CUANDO
        turnoService.deleteById(turnoDTO3.getId());

        // ENTONCES
        Assertions.assertThrows(NotFoundIdException.class, () -> turnoService.findById(idEliminado));
    }


    @Test
    void _5_testModificarTurno() {
        // DADOS
        OdontologoDTO odontologoDTO5 = new OdontologoDTO();
        odontologoDTO5.setName("Pepito5");
        odontologoDTO5.setLastName("perez5");
        odontologoDTO5 = odontologoService.saveAndFlush(odontologoDTO5);

        PacienteDTO pacienteDTO5 = new PacienteDTO();
        pacienteDTO5.setName("Ichigo5");
        pacienteDTO5.setLastname("Kurosaki5");
        pacienteDTO5.setDomicilio(new Domicilio());
        pacienteDTO5 = pacienteService.saveAndFlush(pacienteDTO5);

        TurnoDTO turnoDTO4 = new TurnoDTO();
        turnoDTO4.setOdontologo((odontologoDTO5));
        turnoDTO4.setPaciente(pacienteDTO5);
        turnoDTO4.setSchedule(LocalDateTime.of(2022, Month.JUNE, 19, 12, 30));
        turnoDTO4 = turnoService.saveAndFlush(turnoDTO4);
        turnoDTO4.setSchedule(LocalDateTime.of(2023, Month.JUNE, 19, 12, 30));

        // CUANDO
        turnoService.update(turnoDTO4);
        TurnoDTO resultadoEsperado3 = turnoService.findById(turnoDTO4.getId());

        // ENTONCES
        Assertions.assertEquals(resultadoEsperado3.getSchedule(), turnoDTO4.getSchedule());
    }
}