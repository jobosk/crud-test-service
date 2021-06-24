package com.linkener.crudtest.repository;

import com.linkener.crudtest.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TestEntityRepository extends JpaRepository<TestEntity, UUID> {
}
