package com.batikanBlog.exception;

public class UserExistsException extends RuntimeException{

    /**
	 * @author BATIKAN
	 */
	private static final long serialVersionUID = 1L;

	public UserExistsException(String msg){
        super(msg);
    }

}
