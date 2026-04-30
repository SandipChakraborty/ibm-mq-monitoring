plugins {
	java
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.sandip.ibm.mq.monitor"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")

	implementation("org.springframework.retry:spring-retry:2.0.5")
	implementation("org.springframework:spring-aspects:6.1.6")

	implementation("org.springframework:spring-jms")
	implementation("jakarta.jms:jakarta.jms-api")
	implementation("com.ibm.mq:mq-jms-spring-boot-starter:3.1.1")

	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

	implementation("io.micrometer:micrometer-registry-prometheus")
}