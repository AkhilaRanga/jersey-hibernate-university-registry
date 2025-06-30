package com.learn.javaweb.mapper;

import com.learn.javaweb.dto.DepartmentDto;

public class DepartmentMapper {
    public static com.learn.webapp.dto.DepartmentDto toApiDepartmentDto(DepartmentDto internalDto) {
    	com.learn.webapp.dto.DepartmentDto apiDto = new com.learn.webapp.dto.DepartmentDto();
        apiDto.setDepartmentId(internalDto.getDepartmentId());
        apiDto.setDepartmentCode(internalDto.getDepartmentCode());
        apiDto.setDepartmentName(internalDto.getDepartmentName());
        return apiDto;
    }

    public static DepartmentDto toInternalDepartmentDto(com.learn.webapp.dto.DepartmentDto apiDto) {
    	DepartmentDto internalDto = new DepartmentDto();
    	if (apiDto.getDepartmentId() != null) {
    	    internalDto.setDepartmentId(apiDto.getDepartmentId());
    	}
    	internalDto.setDepartmentCode(apiDto.getDepartmentCode());
    	internalDto.setDepartmentName(apiDto.getDepartmentName());
        return internalDto;
    }
}
