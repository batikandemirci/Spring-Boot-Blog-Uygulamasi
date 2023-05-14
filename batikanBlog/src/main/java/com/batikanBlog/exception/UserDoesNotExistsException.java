package com.batikanBlog.exception;

public class UserDoesNotExistsException extends RuntimeException{

    /**
	 * @author BATIKAN
	 */
	private static final long serialVersionUID = 1L;

	public UserDoesNotExistsException(String msg){
        super(msg);
    }

}
