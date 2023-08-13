package com.phoebe.classroom.rest;

import com.phoebe.classroom.base.exception.ResourceNotFoundException;
import com.phoebe.classroom.service.CommentService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/comments")
public class CommentResource {
    @Inject
    private CommentService commentService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllComments() {
        return Response.ok(commentService.getAllComments()).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") Long id) throws ResourceNotFoundException {
        return Response.ok(commentService.getByID(id)).build();
    }
}
