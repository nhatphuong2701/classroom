package com.phoebe.classroom.rest;

import com.phoebe.classroom.base.exception.AuthorizationException;
import com.phoebe.classroom.base.exception.ResourceNotFoundException;
import com.phoebe.classroom.base.security.JwtUtils;
import com.phoebe.classroom.dao.ClassroomDAO;
import com.phoebe.classroom.service.ClassroomService;
import com.phoebe.classroom.service.mapper.ClassroomMapper;
import com.phoebe.classroom.service.model.Classroom;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/classrooms")
public class ClassroomResource {
    @Inject
    private ClassroomService classroomService;

    @Inject
    private ClassroomMapper classroomMapper;

    @Inject
    private JwtUtils jwtUtils;

    @GET
    @Path("/classroom-by-userId")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getByUserId(@HeaderParam("Authorization") String authorization) throws ResourceNotFoundException, AuthorizationException {
        Long userId = jwtUtils.getUserIdFromToken(authorization);
        List<Classroom> classroomList = classroomMapper.toDtoList(classroomService.getByUserId(userId));
        return Response.ok().entity(classroomList).build();
    }
}
