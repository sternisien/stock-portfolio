package org.example.infrastructure.configuration;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.example.infrastructure.configuration.bean.KafkaConfigServer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@EnableKafka
@Configuration
public class KafkaDefaultConfigurationConsumer {

  /**
   * Bean permattant de récupérer les informations concernant le host et le port du broker dans le
   * fichier application.yml
   *
   * @return {@link KafkaConfigServer} objet contenant les informations host et port du broker
   */
  @Bean
  @ConfigurationProperties(prefix = "kafka.bootstrap-server")
  public KafkaConfigServer getKafkaConfigServer() {
    return new KafkaConfigServer();
  }

  /**
   * Configuration par défaut d'un consumer
   *
   * @param kafkaConfigServer prend le bean en paramètre le bean instancier par la fonction {@link
   *     this#getKafkaConfigServer()}
   * @return une map contenant la configuration par défaut à appliquer à un consumer
   */
  @Bean(name = "kafka-settings")
  public Map<String, Object> kafkaSettings(KafkaConfigServer kafkaConfigServer) {
    Map<String, Object> settings = new HashMap<>();
    settings.put(
        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
        String.format("%s:%s", kafkaConfigServer.getHost(), kafkaConfigServer.getPort()));
    settings.put(ConsumerConfig.GROUP_ID_CONFIG, "monGroup");
    settings.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    settings.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    settings.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // Important

    return settings;
  }

  /**
   * Permet de créer une instance de consumer avec les settings fournis. Cette fonction initialisera
   * un consumer permettant de consommer un message en tant que {@link String} venant d'un topic
   *
   * @param settings la configuration à appliquer au consumer
   * @return
   */
  @Bean
  public DefaultKafkaConsumerFactory<String, String> getDefaultConsumerFactory(
      @Qualifier(value = "kafka-settings") Map<String, Object> settings) {
    return new DefaultKafkaConsumerFactory<>(settings);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> getDefaultConsumer(
      DefaultKafkaConsumerFactory<String, String> consumerFactory) {
    ConcurrentKafkaListenerContainerFactory<String, String> basicConsumer =
        new ConcurrentKafkaListenerContainerFactory<>();
    basicConsumer.setConsumerFactory(consumerFactory);
    return basicConsumer;
  }
}
