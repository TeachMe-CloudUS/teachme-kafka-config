package us.cloud.teachme.kafkaconfig.service;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.stereotype.Component;

@Component
public class KafkaUtils {

    public static NewTopic createTopic(String topicName) {
        return new NewTopic(topicName, 1, (short) 1);
    }

    public static NewTopic createTopic(String topicName, int partitions, short replicationFactor) {
        return new NewTopic(topicName, partitions, replicationFactor);
    }
}
