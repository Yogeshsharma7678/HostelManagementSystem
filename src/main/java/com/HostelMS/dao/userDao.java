/* there's an  interface to provide abstraction 
 * and some abstract methods which are gonna 
 * be used in the program
 */

package com.HostelMS.dao;

import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.user;

public interface userDao{
	
	public user viewRoom(int uId);
		
	public int viewDueAmmount(int uId);
	
	public user viewProfile(int uId);
	
	public int changePhonenumber(int uId , String phone);
	
	public int changePassword(int uId , String oldPwd ,String newPwd) throws GlobalException;	
	
}