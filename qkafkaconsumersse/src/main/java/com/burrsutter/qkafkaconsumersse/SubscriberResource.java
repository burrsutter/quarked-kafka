package com.burrsutter.qkafkaconsumersse;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.reactivestreams.Publisher;

import org.eclipse.microprofile.reactive.messaging.Channel;

@Path("/")
public class SubscriberResource {
    @Inject
    @Channel("mystream") Publisher<String> messages;

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Publisher<String> stream() {
        return messages;
    }

}