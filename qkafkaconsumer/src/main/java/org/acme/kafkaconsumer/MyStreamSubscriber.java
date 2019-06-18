package org.acme.kafkaconsumer;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

import io.reactivex.Flowable;

@ApplicationScoped
@Path("/")
public class MyStreamSubscriber {
    private static final Logger LOG = Logger.getLogger(MyStreamSubscriber.class);

    @GET
    @Path("reset")
    public String reset() {
      return "This is here to cause live reload";
    }
 
    // @Incoming("input")
    // public void process(String msg) {
    //     LOG.info("input1: " + msg);
    // }     

    // @Incoming("input")
    // public CompletionStage<Void> process(KafkaMessage<String,String> msg) {
    //     LOG.info("input2: " + msg.getPayload());
    //     return msg.ack();
    // }

    // you see input3 logged 1 time
    // @Incoming("input")
    // @Outgoing("output")
    // public Flowable<JsonObject> process(Flowable<JsonObject> input) {      
    //   System.out.println("\n\n\ninput3: " + input.toString());
    //   return input;
    // }

    @Incoming("input")
    @Outgoing("output")
    public Flowable<JsonObject> process(Flowable<JsonObject> input) {      
      // return input;
      return input
        .doOnError(e -> System.out.println("ERROR: " + e + "\n"))
        .doOnNext(json -> System.out.println("INPUT4: " + json + "\n"))
        ;
    }

    // @Incoming("input")
    // @Outgoing("output")
    // public JsonObject process(JsonObject input) {
    //   LOG.info("input3: " + input);
    //   return input;
    // }
    

}

