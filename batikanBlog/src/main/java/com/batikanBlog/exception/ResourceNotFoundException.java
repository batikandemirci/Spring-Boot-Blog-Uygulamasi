package com.batikanBlog.exception;

public class ResourceNotFoundException extends RuntimeException{

    /**
	 * @author BATIKAN
	 */
	private static final long serialVersionUID = 1L;
	private String field;
    String fieldId;
    int id;
    public ResourceNotFoundException(String field,String fieldId,int id){
        super(String.format("%s not found with %s : %d",field,fieldId,id));
        this.field = field;
        this.fieldId = fieldId;
        this.id = id;
    }

}
