# teachme-kafka-config

`teachme-kafka-config` is a shared library designed to simplify Kafka configuration and message production for the `teachme` platform's microservices.
It provides standardized Kafka producer configurations, utilities for topic management, and a wrapper for producing messages to Kafka topics.

## Features

- Centralized Kafka Configuration

## Getting Started

### Prerequisites 

- Java 17
- Spring Boot 3.x
- Kafka setup (local or cloud)

### Installation

Add the `kafka-config` library as a dependency in your project's pom.xml:

```xml
<dependency>
    <groupId>us.cloud.teachme</groupId>
    <artifactId>kafka-config</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

### Configuration

In your Spring Boot application’s `application.properties` or application.yml, configure Kafka properties:

```properties
# Kafka server configuration
spring.kafka.bootstrap-servers=localhost:9092
# The service name, e.g student-service for convention
kafka.group-id=your-service-group
```

### Usage

1. Import Kafka Configuration

Import the KafkaConfiguration inside of your Configuration-file:

```java

package us.cloud.teachme.yourservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import us.cloud.teachme.kafkaconfig.KafkaConfig;

@Configuration
@Import(KafkaConfig.class)
public class AppKafkaConfig {
    // This imports the KafkaConfig from teachme-kafka-config
}
```

2. Register new Topics:

Inside of the Configuration you can create new Topics:

```java
@Configuration
@Import(KafkaConfig.class)
public class AppKafkaConfig {
    
    @Bean
    public NewTopic topic1() {
        return KafkaUtils.createTopic("topic1");
    }

    @Bean
    public NewTopic topic2() {
        return KafkaUtils.createTopic("topic2");
    }
}
```

3. Send messages to Topics:

`KafkaProducerService` provides a straightforward way to send messages to Kafka topics. Autowire and use it in your service classes:

```java
package us.cloud.teachme.yourservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.cloud.teachme.kafkaconfig.producer.KafkaProducerService;

@Service
public class MessageService {

    // Autowire KafkaProducerService or use the regular KafkaApi.
    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public MessageService(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    public void sendMessage(String topic, String key, String message) {
        kafkaProducerService.sendMessage(topic, key, message);
    }
}
```

4. Consume Messages:

Listen to Topics using the `KafkaListener`-Annotation providing the Topic:

```java
...

@KafkaListener(topics = "topic1")
public void consume(String message) {
    System.out.println(message);
}

...
```