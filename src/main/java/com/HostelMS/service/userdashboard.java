package com.HostelMS.service;

import com.HostelMS.exception.GlobalException;

//there's an  interface to provide abstraction 
//and to show the modules of user as candidate
public interface userdashboard {

	public void dashboard() throws GlobalException;

	public void viewRoom();

	public void viewDueAmmount();

	public void viewProfile();

	public void changePhonenumber();

	public void changePassword() throws GlobalException;

}