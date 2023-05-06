import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.10"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
}

group = "com.montebruni"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

val mockkVersion = "1.13.4"
val kotlinLoggingVersion = "3.0.5"
val springMockkVersion = "3.1.2"
val testContainerVersion = "1.18.0"
val testMongoDbVersion = "1.18.0"

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("io.github.microutils:kotlin-logging-jvm:$kotlinLoggingVersion")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")

	// Swagger
	implementation("org.springdoc:springdoc-openapi-data-rest:1.6.0")
	implementation("org.springdoc:springdoc-openapi-ui:1.6.0")
	implementation("org.springdoc:springdoc-openapi-kotlin:1.6.0")

	developmentOnly("org.springframework.boot:spring-boot-devtools")

	// Testcontainer
	testImplementation("org.testcontainers:junit-jupiter:1.18.0")
	testImplementation("org.testcontainers:mongodb:$testMongoDbVersion")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude("org.mockito")
	}
	testImplementation("io.mockk:mockk:${mockkVersion}")
	testImplementation("com.ninja-squad:springmockk:${springMockkVersion}")
}

dependencyManagement {
	imports {
		mavenBom("org.testcontainers:testcontainers-bom:$testMongoDbVersion")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
