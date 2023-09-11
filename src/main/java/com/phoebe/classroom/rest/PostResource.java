package com.phoebe.classroom.rest;

import com.phoebe.classroom.base.exception.ResourceNotFoundException;
import com.phoebe.classroom.entity.PostEntity;
import com.phoebe.classroom.service.PostService;
import com.phoebe.classroom.service.mapper.PostMapper;
import com.phoebe.classroom.service.model.Post;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/posts")
public class PostResource {
    @Inject
    private PostService postService;

    @Inject
    private PostMapper postMapper;


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

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(Post post) throws ResourceNotFoundException {
        PostEntity postEntity = postService.create(post);
        System.out.println("id:" + postEntity.getId());
        Post newPost = postMapper.toDto(postEntity);
        System.out.println("id:" + newPost.getId());
        return Response.created(URI.create("posts" + newPost.getId())).entity(newPost).build();
    }
}
