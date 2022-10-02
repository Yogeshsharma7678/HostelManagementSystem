package com.HostelMS.daoImpl;

import org.hibernate.Session;

import com.HostelMS.config.HibernateUtil;
import com.HostelMS.dao.hostelMSDao;
import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.user;

//hostelMSDaoImpl class which is used to implement all the methods of hostelMSDao interface 
public class hostelMSDaoImpl implements hostelMSDao {

	@Override
	// implementation of registration method which we have declared in hostelMSDao
	public int registration(user u1) throws GlobalException {

		// establish connection to store data into DB
		try (Session ses = HibernateUtil.getSession()) {

			String username = u1.getUserName();
			user u2 = null;
			u2 = (user) ses.createQuery("from user where userName=:username").setParameter("username", username)
					.uniqueResult();
			if (u2 == null) {
				ses.beginTransaction();
				ses.save(u1);
				ses.getTransaction().commit();
				return 1;
			}
			// This else block is throwing user defined global exception if the user is
			// already existed
			else {
				throw new GlobalException("user already existed");
			}

		}

	}

	@Override
	// implementing the login method
	public user login(String username, String password) throws GlobalException {
		// establish connection
		try (Session ses = HibernateUtil.getSession()) {
			ses.beginTransaction();

			user u2 = null;
			u2 = (user) ses.createQuery("from user where userName=:username").setParameter("username", username)
					.uniqueResult();
			if (u2 != null) {
				if (u2.getUserPassword().equals(password)) {
					return u2;
				}
				// this else block throws GlobalException if any detail is wrong about user
				else {
					throw new GlobalException("Wrong Username or Password");
				}
			} else {
				throw new GlobalException("Wrong Username or Password");
			}

		}

	}

}