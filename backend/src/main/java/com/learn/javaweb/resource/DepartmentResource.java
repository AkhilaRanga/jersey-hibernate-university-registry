package com.learn.javaweb.resource;

import java.util.List;

import com.learn.javaweb.dto.DepartmentDto;
import com.learn.javaweb.service.DepartmentService;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.validation.ConstraintViolationException;

@Path("/department")
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class DepartmentResource {
    private final DepartmentService departmentService;

    public DepartmentResource() {
        this.departmentService = new DepartmentService();
    }

	@POST
	public Response createDepartment(DepartmentDto departmentDto) {
        try {
			DepartmentDto createdDepartment = departmentService.createDepartment(departmentDto);
	        return Response.status(Response.Status.CREATED)
	                       .entity(createdDepartment)
	                       .build();
        } catch (ConstraintViolationException | IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Failed to create department").build();
        }
	}

	@GET
	public Response getAllDepartments() {
        try {
            List<DepartmentDto> departmentsList = departmentService.getAllDepartments();
            return Response.ok(departmentsList).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Failed to fetch departments").build();
        }
	}
	
	@GET
	@Path("/id/{id}")
	public Response getDepartmentById(@PathParam("id") int id) {
        try {
			DepartmentDto departmentDto = departmentService.getDepartmentById(id);
	        return Response.ok(departmentDto).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Failed to get department").build();
        }
	}
	
	@GET
	@Path("/code/{code}")
	public Response getDepartmentById(@PathParam("code") String code) {
        try {
			DepartmentDto departmentDto = departmentService.getDepartmentByCode(code);
	        return Response.ok(departmentDto).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Failed to get department").build();
        }
	}
	
	@PUT
	@Path("/{id}")
	public Response updateDepartment(@PathParam("id") int id, DepartmentDto departmentDto) {
        try {
        	departmentService.updateDepartment(id, departmentDto);
            return Response.ok("Department updated successfully").build();
        } catch (ConstraintViolationException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity(e.getMessage())
                           .build();
        }catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Failed to update department").build();
        }
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteDepartment(@PathParam("id") int id) {
        try {
        	departmentService.deleteDepartmentById(id);
            return Response.noContent().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity(e.getMessage())
                           .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            				.entity("Failed to delete department").build();
        }
	}
}
