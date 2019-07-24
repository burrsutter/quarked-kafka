Prereqs:
Docker for Mac or Docker for Windows


1. Terminal 1
docker-compose up
This leverages the docker-compose.yaml file to start a "local" zookeeper + Apache Kafka broker on ports 2181 and 9092

2. Terminal 2 - producer to atopic
cd qrestkafkaproducer
mvn quarkus:dev

3. Terminal 3 - consumer of atopic, websocket to browser
cd qkafkaconsumerws
mvn compile quarkus:dev
open http://localhost:8081/index.html

4. Terminal 4 - consumer of btopic, SSE to browser
cd qkafkaconsumersse
mvn quarkus:dev
open http://localhost:8082/stream

5. Terminal 5 - atopic to btopic transformer/filter
cd qkafkaconsumer
mvn compile quarkus:dev

curl http://localhost:8083/hello to cause reload

6. Terminal 6 - send in a message
curl http://localhost:8080/produce

