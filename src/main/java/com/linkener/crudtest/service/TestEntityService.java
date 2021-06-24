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

    @Autowired
    public TestEntityService(
            final TestEntityRepository repository
    ) {
        super(repository);
    }

    @Override
    public TestEntityDTO findOneAndApplyMapping(final UUID id) {
        // Aqui puedes meter tu 'mapper' para transformar la entidad en un
        // DTO especifico (si quieres), pero requiere implementar los DTOs
        // y gestionar el mapeo entre el DTO y la entidad
        return mapper.convertValue(find(id), TestEntityDTO.class);
    }
}
