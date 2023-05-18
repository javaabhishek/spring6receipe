package com.asoft.spring6receipe.repositories;

import com.asoft.spring6receipe.model.UnitOfMeasure;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/***
 * This is integration test class, the integration between database and repository class
 * For this kind of test you can either use //@SpringBootTest OR @DataJpaTest
 * If you configure both annotation then u will receive
 * java.lang.IllegalStateException: Configuration error: found multiple declarations of @BootstrapWith
 * for test class [com.asoft.spring6receipe.repositories.UnitOfMeasureRepositoryIntegTest]:
 * [@org.springframework.test.context.BootstrapWith(value=org.springframework.boot.test.context.
 * SpringBootTestContextBootstrapper.class), @org.springframework.test.context.
 * BootstrapWith(value=org.springframework.boot.test.autoconfigure.orm.jpa
 * .DataJpaTestContextBootstrapper.class)]
 *
 * Basically both annotation  will set up a Spring application context
 *
 * https://reflectoring.io/spring-boot-data-jpa-test/
 * */
@Slf4j
//@SpringBootTest
@DataJpaTest
class UnitOfMeasureRepositoryIntegTest {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByUom() {
        Optional<UnitOfMeasure> cupUom= unitOfMeasureRepository.findByUom("Cup");
        String cupUomStr=cupUom.get().getUom();
        log.info(">> cupUomStr:"+cupUomStr);
        assertEquals("Cup",cupUomStr);
    }
}