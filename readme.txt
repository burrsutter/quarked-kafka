Prereqs:
Docker for Mac or Docker for Windows


1. In Terminal 1, "docker-compose up"
This leverages the docker-compose.yaml file to start a "local" zookeeper + Apache Kafka broker on ports 2181 and 9092

Kafkacat:

2. Use Kafkacat to test the broker, in Terminal 2, producer
brew install kafkacat
kafkacat -P -b localhost -t my-topic
and type out some messages into the terminal

3. In Terminal 3, consumer
kafkacat -C -b localhost -t my-topic

4. ctrl-z to stop the producer & consumer

Quarkus:

5. Terminal 2, 
cd qrestkafkaproducer
mvn quarkus:dev

6. Terminal 3,
cd qkafkaconsumersse
mvn quarkus:dev
open http://localhost:8082/stream

7. curl http://localhost:8080/produce

