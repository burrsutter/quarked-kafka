package com.burrsutter.qrestkafkaproducer;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.smallrye.reactive.messaging.annotations.Emitter;
import io.smallrye.reactive.messaging.annotations.Stream;


@Path("/produce")
public class ProducerResource {
    @Inject @Stream("mystream")
    Emitter<String> emitter;
    private int cnt;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject produce() {    
                
        JsonArray orders = Json.createArrayBuilder()
        .add(Json.createObjectBuilder()
        .add("id",cnt++)
        .add("itemid","I100")
        .add("description","waffle maker")
        .add("qty",10)
        .add("price",10.0))
        .add(Json.createObjectBuilder()
        .add("id",cnt++)
        .add("itemid","I200")
        .add("description","waffle batter")
        .add("qty",11)
        .add("price",1.0))
        .build();

        JsonObject customer = Json.createObjectBuilder()
        .add("id","CUST" + cnt)
        .add("orders",orders)
        .build();

        emitter.send(customer.toString());
        return customer;
    }
}