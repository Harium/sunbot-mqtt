# sunbot-mqtt
Plugin to turn your Suneidesis Chatbot into a MQTT Subscriber

## Setup

### Fedora
```
sudo dnf install -y mosquitto screen
sudo mv /etc/mosquitto/mosquitto.conf.example /etc/mosquitto/mosquitto.conf
sudo firewall-cmd --permanent --add-port=1883/tcp
sudo firewall-cmd --permanent --add-port=1883/udp
sudo firewall-cmd --permanent --add-port=8883/tcp
sudo firewall-cmd --permanent --add-port=8883/udp
sudo systemctl enable mosquitto.service
sudo systemctl start mosquitto.service
```
