package com.linkener.crudtest.service;

import com.belike.core.mapper.dozer.services.DozerService;
import com.jobosk.crudifier.service.CrudService;
import com.linkener.crudtest.dto.TestEntityDTO;
import com.linkener.crudtest.entity.TestEntity;
import com.linkener.crudtest.repository.TestEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TestEntityService extends CrudService<TestEntity> implements ITestEntityService {

    private TestEntityRepository testEntityRepository;

    private DozerService dozerService;

    @Autowired
    public TestEntityService(
            final TestEntityRepository repository
            , final DozerService dozerService
    ) {
        super(repository);
        this.testEntityRepository = repository;
        this.dozerService = dozerService;
    }

    @Override
    public TestEntityDTO readEntity(UUID id, Class<TestEntityDTO> entityType) {
        TestEntity entity = testEntityRepository.findById(id).get();
        return dozerService.map(entity, entityType);
    }
}
