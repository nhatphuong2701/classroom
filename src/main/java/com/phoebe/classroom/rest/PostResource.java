package com.phoebe.classroom.rest;

import com.phoebe.classroom.base.exception.ResourceNotFoundException;
import com.phoebe.classroom.service.PostService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/posts")
public class PostResource {
    @Inject
    private PostService postService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllPosts() {
        return Response.ok(postService.getAllPost()).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") Long id) throws ResourceNotFoundException {
        return Response.ok(postService.getById(id)).build();
    }
}
