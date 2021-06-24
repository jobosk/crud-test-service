package com.linkener.crudtest;

import com.belike.core.mapper.dozer.services.DozerService;
import com.linkener.crudtest.entity.TestEntity;
import com.linkener.crudtest.repository.TestEntityRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public abstract class BaseTest {

    private static final Map<String, TestEntity> entities = new HashMap<>();

    @Autowired
    public MockMvc mvc;

    @Autowired
    protected DozerService dozerService;

    @Autowired
    protected JacksonTester<List> jsonList;

    @Autowired
    protected JacksonTester<TestEntity> jsonTestEntity;

    @Autowired
    private TestEntityRepository testEntityRepository;

    protected <Entity> List<Entity> toList(Entity... args) {
        return Arrays.stream(args).collect(Collectors.toList());
    }

    @Before
    public void setUp() {
        entities.put("test", testEntityRepository.save(buildTestEntity("test_entity")));
    }

    protected TestEntity buildTestEntity(final String name) {
        final TestEntity entity = new TestEntity();
        entity.setName(name);
        return entity;
    }

    protected TestEntity getTestEntity(final String key) {
        return entities.get(key);
    }

    protected List<TestEntity> getTestEntities(final String name) {
        return entities.values()
                .stream()
                .filter(te -> te.getName() != null && te.getName().contains(name))
                .collect(Collectors.toList());
    }
}
