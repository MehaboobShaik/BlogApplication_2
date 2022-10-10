package com.blogApp.customexceptions;

public class ResourceNotFoundException extends RuntimeException{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String resourceName; 
	private String feildName; 
	private Integer feildValue;
	
	
	public ResourceNotFoundException(String resourceName, String feildName, Integer feildValue) {
		super(String.format("%s is not found with %s : %s",resourceName,feildName,feildValue));
		this.resourceName = resourceName;
		this.feildName = feildName;
		this.feildValue = feildValue;
	} 
	
	
	
}
