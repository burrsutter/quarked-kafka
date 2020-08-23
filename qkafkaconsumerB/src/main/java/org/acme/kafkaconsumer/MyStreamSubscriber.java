package org.acme.kafkaconsumer;

import javax.enterprise.context.ApplicationScoped;
// import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

// import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.reactive.messaging.Incoming;
// import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

// import io.reactivex.Flowable;

@ApplicationScoped
@Path("/")
public class MyStreamSubscriber {
    private static final Logger LOG = Logger.getLogger(MyStreamSubscriber.class);

    @GET
    @Path("reset")
    public String reset() {
      return "This is here to cause live reload";
    }
 
    @Incoming("input")
    public void process(String msg) {
        LOG.info("input1: " + msg);
    }     


}

