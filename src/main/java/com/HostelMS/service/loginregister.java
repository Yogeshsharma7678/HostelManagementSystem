package com.HostelMS.service;

import com.HostelMS.exception.GlobalException;

//This interface is show functionalities of the user
public interface loginregister {
	public void register() throws GlobalException;

	public void login() throws GlobalException;
}