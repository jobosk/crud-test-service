package com.linkener.crudtest.repository;

import com.jobosk.crudifier.repository.GenericRepository;
import com.linkener.crudtest.entity.TestEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TestEntityRepository extends GenericRepository<TestEntity, UUID> {
}
