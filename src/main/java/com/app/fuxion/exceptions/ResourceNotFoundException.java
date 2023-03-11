package com.app.fuxion.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private String resourceName;
    private String fieldName;
    private Long id;

    public ResourceNotFoundException(String resourceName) {
        super(String.format("%s not found!" , resourceName));
    }

    public ResourceNotFoundException(String resourceName, String fieldName) {
        super(String.format("%s not found with %s ", resourceName, fieldName));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
    }
    
    public ResourceNotFoundException(String resourceName, Long id) {
        super(String.format("%s not found with id %d ", resourceName, id));
        this.resourceName = resourceName;
        this.id = id;
    }

    public ResourceNotFoundException(String resourceName, String field, String objId) {
        super(String.format("%s not found with %s %s ", resourceName, field, objId));
        this.resourceName = resourceName;
        this.id = id;
    }

    public ResourceNotFoundException() {super("Data not found");}
    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

	public Long getId() {
		return id;
	}
}
