package com.dentist;

import com.dentist.exception.NotFoundIdException;
import com.dentist.model.dto.OdontologoDTO;
import com.dentist.service.IOdontologoService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
@Transactional
class OdontologoServiceTest {

    @Autowired
    IOdontologoService odontologoService;

    @Test
    public void _1_testCrearOdontologo() {
        // DADOS
        OdontologoDTO odontologoDTO1 = new OdontologoDTO();
        odontologoDTO1.setName("Pepito1");
        odontologoDTO1.setLastName("perez1");

        // CUANDO
        odontologoDTO1 = odontologoService.saveAndFlush(odontologoDTO1);
        OdontologoDTO resultadoEsperado1 = odontologoService.findById(odontologoDTO1.getId());

        // ENTONCES
        Assertions.assertNotNull(resultadoEsperado1);
        Assertions.assertEquals("Pepito1", resultadoEsperado1.getName());
        Assertions.assertEquals("perez1", resultadoEsperado1.getLastName());
    }

    @Test
    public void _2_testBuscarOdontologoPorId() {
        // DADOS
        OdontologoDTO odontologoDTO2 = new OdontologoDTO();
        odontologoDTO2.setName("Pepito2");
        odontologoDTO2.setLastName("perez2");
        odontologoDTO2 = odontologoService.saveAndFlush(odontologoDTO2);


        // CUANDO
        OdontologoDTO resultadoEsperado2 = odontologoService.findById(odontologoDTO2.getId());

        // ENTONCES
        Assertions.assertTrue(resultadoEsperado2 != null);

    }

    @Test
    public void _3_testBuscarTodosLosOdontologos() {
        // DADOS
        OdontologoDTO odontologoDTO3 = new OdontologoDTO();
        odontologoDTO3.setName("Pepito3");
        odontologoDTO3.setLastName("perez3");
        odontologoService.saveAndFlush(odontologoDTO3);

        OdontologoDTO odontologoDTO4 = new OdontologoDTO();
        odontologoDTO4.setName("Pepito4");
        odontologoDTO4.setLastName("perez4");
        odontologoService.saveAndFlush(odontologoDTO4);

        // CUANDO
        List<OdontologoDTO> listaOdontologosEncontrados = odontologoService.findAll();

        // ENTONCES
        Assertions.assertTrue(listaOdontologosEncontrados.size() > 0);

    }

    @Test
    public void _4_testEliminarOdontologo() {
        // DADOS
        OdontologoDTO odontologoDTO5 = new OdontologoDTO();
        odontologoDTO5.setName("Pepito5");
        odontologoDTO5.setLastName("perez5");
        odontologoDTO5 = odontologoService.saveAndFlush(odontologoDTO5);
        Integer idEliminado = odontologoDTO5.getId();

        // CUANDO
        odontologoService.deleteById(odontologoDTO5.getId());

        // ENTONCES
        Assertions.assertThrows(NotFoundIdException.class, ()-> odontologoService.findById(idEliminado));
    }

    @Test
    public void _5_testModificarOdontologo() {
        // DADOS
        OdontologoDTO odontologoDTO6 = new OdontologoDTO();
        odontologoDTO6.setName("Pepito6");
        odontologoDTO6.setLastName("perez6");
        odontologoDTO6 = odontologoService.saveAndFlush(odontologoDTO6);
        odontologoDTO6.setLastName("OtroApellido");


        // CUANDO
        odontologoService.update(odontologoDTO6);
        OdontologoDTO resultadoEsperado4 = odontologoService.findById(odontologoDTO6.getId());

        // ENTONCES
        Assertions.assertEquals("OtroApellido", resultadoEsperado4.getLastName());
    }

}