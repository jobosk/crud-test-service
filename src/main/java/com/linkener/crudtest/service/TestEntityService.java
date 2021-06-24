package com.linkener.crudtest.service;

import com.jobosk.crudifier.service.CrudService;
import com.linkener.crudtest.dto.TestEntityDTO;
import com.linkener.crudtest.entity.TestEntity;
import com.linkener.crudtest.repository.TestEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TestEntityService extends CrudService<TestEntity, UUID> implements ITestEntityService {

    private final TestEntityRepository testEntityRepository;

    @Autowired
    public TestEntityService(
            final TestEntityRepository repository
    ) {
        super(repository);
        this.testEntityRepository = repository;
    }

    @Override
    public TestEntityDTO readEntity(UUID id, Class<TestEntityDTO> entityType) {
        TestEntity entity = testEntityRepository.findById(id).get();
        // TODO Aqui puedes meter tu 'mapper' para transformar la entidad en tu DTO especifico (si quieres)
        return new TestEntityDTO();
    }
}
