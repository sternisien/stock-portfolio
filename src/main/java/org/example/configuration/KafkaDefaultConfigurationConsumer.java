package org.example.configuration;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.example.configuration.bean.KafkaConfigServer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaDefaultConfigurationConsumer {


    @Bean
    @ConfigurationProperties(prefix = "kafka.bootstrap-server")
    public KafkaConfigServer getKafkaConfigServer(){
        return new KafkaConfigServer();
    }


    @Bean(name = "kafka-settings")
    public Map<String, Object> kafkaSettings(KafkaConfigServer kafkaConfigServer){
        Map<String, Object> settings =  new HashMap<>();
        settings.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        settings.put(ConsumerConfig.GROUP_ID_CONFIG, "monGroup");
        settings.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        settings.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        settings.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // Important

        return settings;
    }

    @Bean
    public DefaultKafkaConsumerFactory<String, String> getDefaultConsumerFactory(@Qualifier(value = "kafka-settings") Map<String, Object> settings){
        return new DefaultKafkaConsumerFactory<>(settings);
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> getDefaultConsumer(DefaultKafkaConsumerFactory<String, String> consumerFactory){
        ConcurrentKafkaListenerContainerFactory<String, String> basicConsumer = new ConcurrentKafkaListenerContainerFactory<>();
        basicConsumer.setConsumerFactory(consumerFactory);
        return basicConsumer;
    }
}

