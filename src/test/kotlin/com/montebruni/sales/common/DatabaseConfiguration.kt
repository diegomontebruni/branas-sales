package com.montebruni.sales.common

import com.montebruni.sales.configuration.jackson.JacksonObjectMapperConfiguration
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer

@DataJpaTest
@Suppress("UtilityClassWithPublicConstructor")
@Import(JacksonObjectMapperConfiguration::class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DatabaseConfiguration {

    companion object {
        @JvmStatic
        val postgresContainer = PostgreSQLContainer("postgres:12-alpine").apply {
            withUsername("app_sales")
            withPassword("app_sales")
            withDatabaseName("sales")
        }.also { it.start() }

        @JvmStatic
        @DynamicPropertySource
        fun registerDynamicProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", postgresContainer::getJdbcUrl)
            registry.add("spring.datasource.username", postgresContainer::getUsername)
            registry.add("spring.datasource.password", postgresContainer::getPassword)
        }
    }
}
