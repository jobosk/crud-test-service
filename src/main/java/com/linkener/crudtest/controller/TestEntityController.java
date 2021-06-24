package com.linkener.crudtest.controller;

import com.jobosk.crudifier.constant.CrudConstant;
import com.jobosk.crudifier.controller.CrudController;
import com.linkener.crudtest.constants.Constants;
import com.linkener.crudtest.dto.TestEntityDTO;
import com.linkener.crudtest.entity.TestEntity;
import com.linkener.crudtest.service.ITestEntityService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Log4j2
@RequestMapping(Constants.Http.Path.TESTENTITY)
public class TestEntityController extends CrudController<TestEntity, UUID> {

    /**
     * ADVERTENCIA:
     * Al extender del controlador generico, heredamos
     * los endpoints estandar del CRUD, lo que nos permite
     * sobreescribir y ampliar con cabeceras o validaciones
     * donde sea necesario, inhabilitar puntualmente alguno
     * de ellos, etc.; pero expone herramientas para interactuar
     * directamente con el modelo mediante un API REST (un gran
     * poder conlleva una gran responsabilidad).
     * Tambien podemos no extender la logica generica e implementar
     * nuestros propios controladores, pero el objetivo de esta
     * libreria es agilizar al maximo el levantamiento de un CRUD
     * (lo cual puede no ser recomendable en entornos productivos
     * y queda a discrecion del desarrollador) para centrarse en el
     * diseño del modelo desde etapas muy tempranas.
     */

    private final ITestEntityService service;

    @Autowired
    public TestEntityController(
            ITestEntityService service
    ) {
        this.service = service;
    }

    /**
     * Este metodo ilustra como podriamos (si quisieramos) emplear un DTO
     * especifico integrandonos con el servicio del CRUD generico.
     *
     * @param id
     * @return
     */
    @GetMapping(path = "{" + CrudConstant.Http.Param.ID + "}/dto")
    public @ResponseBody
    TestEntityDTO findOneAsDTO(@PathVariable(CrudConstant.Http.Param.ID) final UUID id) {
        return service.findOneAndApplyMapping(id);
    }
}
