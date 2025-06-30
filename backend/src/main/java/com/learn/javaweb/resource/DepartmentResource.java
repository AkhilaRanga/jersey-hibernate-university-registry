package com.learn.javaweb.resource;

import java.util.ArrayList;
import java.util.List;

import com.learn.javaweb.dto.DepartmentDto;
import com.learn.javaweb.mapper.DepartmentMapper;
import com.learn.javaweb.service.DepartmentService;
import com.learn.javaweb.util.ExceptionUtils;
import com.learn.webapp.invoker.ApiException;
import com.learn.webapp.resource.DepartmentApi;

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
import javax.persistence.EntityExistsException;
import javax.validation.ConstraintViolationException;

@Path("/department")
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class DepartmentResource extends DepartmentApi {
    private final DepartmentService departmentService;

    public DepartmentResource() {
        this.departmentService = new DepartmentService();
    }

	@POST
	@Override
	public com.learn.webapp.dto.DepartmentDto createDepartment(com.learn.webapp.dto.DepartmentDto departmentDto) throws ApiException {
        try {
			DepartmentDto createdDepartment = departmentService.createDepartment(DepartmentMapper.toInternalDepartmentDto(departmentDto));
	        return DepartmentMapper.toApiDepartmentDto(createdDepartment);
        } catch (ConstraintViolationException e) {
            throw ExceptionUtils.toApiException(e, Response.Status.BAD_REQUEST.getStatusCode());
        } catch (EntityExistsException e) {
        	throw ExceptionUtils.toApiException(e, Response.Status.CONFLICT.getStatusCode());
        } catch (Exception e) {
        	throw ExceptionUtils.toApiException(e, Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        }
	}

	@GET
	@Override
	public List<com.learn.webapp.dto.DepartmentDto> getAllDepartments() throws ApiException {
        try {
            List<DepartmentDto> departmentsList = departmentService.getAllDepartments();
            List<com.learn.webapp.dto.DepartmentDto> apiDepartmentDtos = new ArrayList<>();
            for (DepartmentDto internalDto : departmentsList) {
            	com.learn.webapp.dto.DepartmentDto apiDto = DepartmentMapper.toApiDepartmentDto(internalDto);
            	apiDepartmentDtos.add(apiDto);
            }
            return apiDepartmentDtos;
        } catch (Exception e) {
        	throw ExceptionUtils.toApiException(e, Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        }
	}
	
	@GET
	@Override
	@Path("/id/{id}")
	public com.learn.webapp.dto.DepartmentDto getDepartmentById(@PathParam("id") Integer id) throws ApiException {
        try {
			DepartmentDto departmentDto = departmentService.getDepartmentById(id);
	        return DepartmentMapper.toApiDepartmentDto(departmentDto);
        } catch (ConstraintViolationException e) {
            throw ExceptionUtils.toApiException(e, Response.Status.BAD_REQUEST.getStatusCode());
        } catch (NotFoundException e) {
        	throw ExceptionUtils.toApiException(e, Response.Status.NOT_FOUND.getStatusCode());
        } catch (Exception e) {
        	throw ExceptionUtils.toApiException(e, Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        }
	}
	
	@GET
	@Override
	@Path("/code/{code}")
	public com.learn.webapp.dto.DepartmentDto getDepartmentByCode(@PathParam("code") String code) throws ApiException {
        try {
			DepartmentDto departmentDto = departmentService.getDepartmentByCode(code);
	        return DepartmentMapper.toApiDepartmentDto(departmentDto);
        } catch (ConstraintViolationException e) {
            throw ExceptionUtils.toApiException(e, Response.Status.BAD_REQUEST.getStatusCode());
        } catch (NotFoundException e) {
        	throw ExceptionUtils.toApiException(e, Response.Status.NOT_FOUND.getStatusCode());
        } catch (Exception e) {
        	throw ExceptionUtils.toApiException(e, Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        }
	}
	
	@PUT
	@Override
	@Path("/{id}")
	public void updateDepartment(@PathParam("id") Integer id, com.learn.webapp.dto.DepartmentDto departmentDto) throws ApiException {
        try {
        	departmentService.updateDepartment(id, DepartmentMapper.toInternalDepartmentDto(departmentDto));
        } catch (ConstraintViolationException e) {
            throw ExceptionUtils.toApiException(e, Response.Status.BAD_REQUEST.getStatusCode());
        } catch (NotFoundException e) {
        	throw ExceptionUtils.toApiException(e, Response.Status.NOT_FOUND.getStatusCode());
        } catch (Exception e) {
        	throw ExceptionUtils.toApiException(e, Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        }
	}
	
	@DELETE
	@Override
	@Path("/{id}")
	public void deleteDepartment(@PathParam("id") Integer id) throws ApiException {
        try {
        	departmentService.deleteDepartmentById(id);
        } catch (ConstraintViolationException e) {
            throw ExceptionUtils.toApiException(e, Response.Status.BAD_REQUEST.getStatusCode());
        } catch (NotFoundException e) {
        	throw ExceptionUtils.toApiException(e, Response.Status.NOT_FOUND.getStatusCode());
        } catch (Exception e) {
        	throw ExceptionUtils.toApiException(e, Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        }
	}
}
