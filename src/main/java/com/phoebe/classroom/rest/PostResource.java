package com.phoebe.classroom.rest;

import com.phoebe.classroom.base.exception.AuthorizationException;
import com.phoebe.classroom.base.exception.ResourceNotFoundException;
import com.phoebe.classroom.base.security.JwtUtils;
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

    @Inject
    private JwtUtils jwtUtils;

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
    public Response create(@HeaderParam("Authorization") String authorization, Post post) throws ResourceNotFoundException, AuthorizationException {
        jwtUtils.verifyUserIsTeacher(authorization);
        PostEntity postEntity = postService.create(post);
        Post newPost = postMapper.toDto(postEntity);
        return Response.created(URI.create("posts" + newPost.getId())).entity(newPost).build();
    }
}
