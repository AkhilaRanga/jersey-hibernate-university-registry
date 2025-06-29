package com.learn.javaweb.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.NotFoundException;

import com.learn.javaweb.dao.DepartmentDao;
import com.learn.javaweb.dto.DepartmentDto;
import com.learn.javaweb.model.Department;
import com.learn.javaweb.util.ValidatorUtils;

public class DepartmentService {
    private final DepartmentDao departmentDao;
    private final Validator validator;

    public DepartmentService() {
        this.departmentDao = new DepartmentDao();
        this.validator = ValidatorUtils.getValidator();
    }

    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        validateDto(departmentDto);
        Department department = departmentDao.findByCode(departmentDto.getDepartmentCode());
        if (department != null) {
            throw new IllegalArgumentException("Department with code already exists");
        }
        Department createDepartment = convertToEntity(departmentDto);
        departmentDao.save(createDepartment);
        return departmentDto;
    }

    public List<DepartmentDto> getAllDepartments() {
        return departmentDao.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public DepartmentDto getDepartmentById(int departmentId) {
        Department department = departmentDao.findById(departmentId);
        if (department == null) {
        	throw new NotFoundException("Department not found with ID: " + departmentId);
        }
        return convertToDto(department);
    }

    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department department = departmentDao.findByCode(departmentCode);
        if (department == null) {
        	throw new NotFoundException("Department not found with code: " + departmentCode);
        }
        return convertToDto(department);
    }

    public void updateDepartment(int departmentId, DepartmentDto departmentDto) {
        validateDto(departmentDto);
        Department existing = departmentDao.findById(departmentId);
        if (existing == null) {
            throw new NotFoundException("Department not found with ID: " + departmentId);
        }
        Department updateDepartment = convertToEntity(departmentDto);
        updateDepartment.setDepartmentId(departmentId);
        departmentDao.update(updateDepartment);
    }

    public void deleteDepartmentById(int departmentId) {
        Department existing = departmentDao.findById(departmentId);
        if (existing == null) {
            throw new NotFoundException("Department not found with ID: " + departmentId);
        }
        departmentDao.deleteById(departmentId);
    }

    private DepartmentDto convertToDto(Department department) {
    	DepartmentDto departmentDto = new DepartmentDto();
    	departmentDto.setDepartmentId(department.getDepartmentId());
    	departmentDto.setDepartmentCode(department.getDepartmentCode());
    	departmentDto.setDepartmentName(department.getDepartmentName());
        return departmentDto;
    }
    
    private Department convertToEntity(DepartmentDto departmentDto) {
    	Department department = new Department();
    	department.setDepartmentCode(departmentDto.getDepartmentCode());
    	department.setDepartmentName(departmentDto.getDepartmentName());
        return department;
    }

    private void validateDto(DepartmentDto departmentDto) {
        Set<ConstraintViolation<DepartmentDto>> violations = validator.validate(departmentDto);
        if (!violations.isEmpty()) {
        	String validateResponse = new String();
            for (ConstraintViolation<DepartmentDto> violation : violations) {
            	validateResponse += violation.getMessage();
            	validateResponse += "\n";
            }
            throw new ConstraintViolationException("Validation failed: " + validateResponse, violations);
        }
    }
}
