package com.linkener.crudtest.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.linkener.crudtest.constants.Constants;
import com.linkener.crudtest.resolver.TestEntityIdResolver;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = Constants.Database.Table.TestEntity.NAME)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class
        , property = "id"
        , scope = TestEntity.class
        , resolver = TestEntityIdResolver.class
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TestEntity {

    @Id
    @Column(
            name = Constants.Database.Table.TestEntity.Column.ID
            , unique = true
            , nullable = false
    )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = Constants.Database.Table.TestEntity.Column.NAME)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = Constants.Database.Table.TestEntity.Column.PARENT)
    //@JsonIgnoreProperties("children")
    @JsonIdentityReference(alwaysAsId = true)
    private TestEntity parent;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", orphanRemoval = true)
    //@JsonIgnoreProperties("parent")
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<TestEntity> children = new ArrayList<>();
}
