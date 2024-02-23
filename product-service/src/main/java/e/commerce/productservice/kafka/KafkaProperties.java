package e.commerce.productservice.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("spring.kafka")
public class KafkaProperties {
    private String bootstrapServers;
    private String paymentTopic;
}
