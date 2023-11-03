package com.rh;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @GET
    @Path("fill/{index}")
    @Produces(MediaType.TEXT_PLAIN)
    public String fill(@PathParam("index") String index) {
        Map<String, String> mem = new HashMap<>();
        char[] chars = new char[2 * 1024 * 1024];
        String log = "Added " + index + "MB";
        Arrays.fill(chars, 'f');
        mem.put(Math.random() + "", new String(chars));
        System.out.println(log);
        return log + "\n";
    }
}
