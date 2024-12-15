package org.example.configuration.bean;


public class KafkaConfigServer {

    private String host;

    private String port;


    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
