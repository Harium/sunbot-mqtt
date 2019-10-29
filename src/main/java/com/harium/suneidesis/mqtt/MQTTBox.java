package com.harium.suneidesis.mqtt;

import com.harium.suneidesis.instance.Instance;
import com.harium.suneidesis.knowledge.linguistic.core.box.Chatbox;
import com.harium.suneidesis.output.Output;
import com.harium.suneidesis.output.TextOutput;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Examples:
 *
 * https://github.com/CloudMQTT/java-mqtt-example/blob/master/src/main/java/com/cloudmqtt/example/Subscriber.java
 * https://riptutorial.com/mqtt/example/28353/example-of-publish-subscriber-in-java
 */
public class MQTTBox implements Chatbox, MqttCallback {

  private Instance instance;
  private Output output = new TextOutput();

  private String protocol = "tcp";
  private String host = "localhost";
  private int port = 1883;

  private String clientId = "mqttbox";
  private String topic = "voice";

  private MqttClient client;

  public void init() throws MqttException {
    String fullHost = buildHost();

    client = new MqttClient(fullHost, clientId);
    client.setCallback(this);
    MqttConnectOptions mqOptions = new MqttConnectOptions();
    mqOptions.setCleanSession(true);
    client.connect(mqOptions);      //connecting to broker
    client.subscribe(topic);
  }

  public MQTTBox host(String host) {
    this.host = host;
    return this;
  }

  public MQTTBox topic(String topic) {
    this.topic = topic;
    return this;
  }

  public MQTTBox clientId(String clientId) {
    this.clientId = clientId;
    return this;
  }

  public MQTTBox instance(Instance instance) {
    this.addInstance(instance);
    return this;
  }

  public MQTTBox output(Output output) {
    this.output = output;
    return this;
  }

  public MQTTBox port(int port) {
    this.port = port;
    return this;
  }

  public MQTTBox protocol(String protocol) {
    this.protocol = protocol;
    return this;
  }

  private String buildHost() {
    return protocol + "://" + host + ":" + port;
  }

  @Override
  public void addInstance(Instance instance) {
    this.instance = instance;
  }

  @Override
  public void sendMessage(String channel, String message) {
    // Method to send message spontaneously
    output.print(message);
  }

  @Override
  public void connectionLost(Throwable throwable) {

  }

  @Override
  public void messageArrived(String channel, MqttMessage mqttMessage) {
    instance.input(mqttMessage.toString(), output);
  }

  @Override
  public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

  }
}
