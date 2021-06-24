package com.linkener.crudtest.service;

import com.jobosk.crudifier.service.ICrudService;
import com.linkener.crudtest.dto.TestEntityDTO;
import com.linkener.crudtest.entity.TestEntity;

import java.util.UUID;

public interface ITestEntityService extends ICrudService<TestEntity> {

    TestEntityDTO readEntity(UUID id, Class<TestEntityDTO> entityType);
}
