package us.cloud.teachme.kafkaconfig.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("kafka-config")
public class KafkaConfigProperties {

    private String bootstrapServer;
    private String groupId;
}
