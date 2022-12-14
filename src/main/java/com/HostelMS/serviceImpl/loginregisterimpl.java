package com.HostelMS.serviceImpl;

import java.util.Scanner;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;

import com.HostelMS.App;
import com.HostelMS.dao.hostelMSDao;
import com.HostelMS.daoImpl.hostelMSDaoImpl;
import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.user;
import com.HostelMS.service.admindashboard;
import com.HostelMS.service.loginregister;
import com.HostelMS.service.userdashboard;

//This class is to impplement functionalities of the user
public class loginregisterimpl implements loginregister {
	static Logger log = Logger.getLogger(App.class);
	static Scanner bs = new Scanner(System.in);
	static hostelMSDao dao = new hostelMSDaoImpl();

	// registration method
	public void register() throws GlobalException {
		log.info("welcome to registeration");
		log.info("Enter Username");
		String uname = bs.next();
		log.info("Create Password");
		String upwd = bs.next();
		log.info("Enter Phone number");
		String uphone = bs.next();
		log.info("Enter Address");
		String uaddress = bs.next();

		user u1 = new user();
		u1.setUserName(uname);
		u1.setUserPassword(upwd);
		u1.setUserPhone(uphone);
		u1.setUserAddress(uaddress);
		u1.setUserRole("student");
		u1.setUserRoom(null);
		u1.setUserFee(0);

		// we use validator instead of regullar expression
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		Validator val = vf.getValidator();

		Set<ConstraintViolation<user>> violation = val.validate(u1);
		int count = violation.size();
		if (count > 0) {

			for (ConstraintViolation<user> vl : violation) {

				log.info(vl.getMessage());
			}
		} else {
			// saving the user details
			int status = dao.registration(u1);
			// log.info(status);
			if (status == 1) {
				log.info("Registration success");
			} else {
				throw new GlobalException("Errorz....! ,Plz check details & put again ");
			}
		}

	}

//login method to logging in into the hostels
	public void login() throws GlobalException {
		log.info("---welcome ;) to Login---");

		log.info("Enter username");
		String username = bs.next();
		log.info("Enter password");
		String password = bs.next();
		// checking login
		user u1 = dao.login(username, password);
		// success message
		log.info("Hello " + u1.getUserName() + " Login Success");
		userdashboard udl = new userdashboardImpl();
		admindashboard adl = new admindashboardImpl();
		// if userrole is student userdashboard will open
		// if userrole is admin admindashboard will open
		if (u1.getUserRole().equals("student")) {
			udl.dashboard();
		} else if (u1.getUserRole().equals("admin")) {
			adl.dashboard();
		}
	}
  
  }