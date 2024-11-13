package us.cloud.teachme.kafkaconfig.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import us.cloud.teachme.kafkaconfig.service.KafkaProducerService;

@EnableKafka
@Configuration
@Import({
        KafkaConsumerConfig.class,
        KafkaProducerConfig.class,
        KafkaTopicConfig.class,
})
public class KafkaConfig {

    @Bean
    public KafkaProducerService kafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        return new KafkaProducerService(kafkaTemplate);
    }
}
