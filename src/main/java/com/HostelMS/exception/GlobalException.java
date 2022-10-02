/*This is the class to create an exception 
 * which w will use in further programs
 */
package com.HostelMS.exception;

@SuppressWarnings("serial")
public class GlobalException extends Exception {

	// Creating GlobalException constructor
	// so that we can easily use it
	public GlobalException(String msg) {
		super(msg);
	}

}