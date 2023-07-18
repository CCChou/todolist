package com.rh.api;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rh.model.Todo;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("todolist")
public class TodoListResource {
    private static final Logger LOG = LoggerFactory.getLogger(TodoListResource.class);

    @GET
    @Path("/{id}")
    public Todo get(@PathParam(value = "id") long id) {
        LOG.info("Get todolist item: " + id);
        return Todo.findById(id);
    }

    @POST
    @Transactional
    public Response create(Todo todo) {
        LOG.info("Create todolist " + todo.name);
        todo.persist();
    return Response.created(URI.create("/todolist/" + todo.id)).build();
    }
}
