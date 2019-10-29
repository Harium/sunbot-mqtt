package examples;

import com.harium.suneidesis.beign.Gender;
import com.harium.suneidesis.instance.Instance;
import com.harium.suneidesis.knowledge.linguistic.core.box.EchoBox;
import com.harium.suneidesis.mqtt.MQTTBox;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * Simulate client
 * mosquitto_pub -h localhost -t "voice" -m "Hello World!"
 */
public class BasicSubscriberExample {

  public static void main(String[] args) throws MqttException {
    Instance parrot = new Instance("Parrot", Gender.MALE);
    parrot.setLanguageBox(new EchoBox());

    MQTTBox mqtt = new MQTTBox()
        .clientId("input_example")
        .protocol("tcp").host("localhost").port(1883)
        .topic("voice")
        .instance(parrot);

    mqtt.init();
  }

}
