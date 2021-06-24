package com.linkener.crudtest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    private @Autowired
    ITestEntityService service;

    private @Autowired
    ObjectMapper mapper;

    @GetMapping(path = "{" + CrudConstant.Http.Param.ID + "}/dto")
    public @ResponseBody
    TestEntityDTO findOneAsDTO(@PathVariable(CrudConstant.Http.Param.ID) final UUID id) {
        return mapper.convertValue(service.find(id), TestEntityDTO.class);
    }

    @GetMapping(path = "{" + CrudConstant.Http.Param.ID + "}/dozer")
    public @ResponseBody
    TestEntityDTO findOneWithDozer(@PathVariable(CrudConstant.Http.Param.ID) final UUID id) {
        return service.readEntity(id, TestEntityDTO.class);
    }
}
