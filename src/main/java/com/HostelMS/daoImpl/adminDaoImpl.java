package com.HostelMS.daoImpl;

import java.util.List;

import javax.persistence.Query;

import com.HostelMS.config.HibernateUtil;
import com.HostelMS.dao.adminDao;
import com.HostelMS.exception.GlobalException;
import com.HostelMS.model.*;

import org.hibernate.Session;

public class adminDaoImpl implements adminDao{
    
	
	
	@Override
	public int createRoom(room r1) throws GlobalException {
		// TODO Auto-generated method stub
		try(Session ses =HibernateUtil.getSession()){
			
			ses.beginTransaction();
			
			String roomName=r1.getRoomName();
			
			room r2 = null;
			
			r2= (room) ses.createQuery("from room where roomName =:roomName").setParameter("roomName",roomName).uniqueResult();
			
			
			if (r2==null) {
				
				ses.save(r1);
				ses.getTransaction().commit();
				return 1;
			}
			else
			{
				
				throw new GlobalException("room is already exist");
			}
			
			
			
		}
	
	}

	@Override
	public int allotRoom(int rId, int uId) {
		// TODO Auto-generated method stub
		try(Session ses =HibernateUtil.getSession()){
			
			ses.beginTransaction();
			
		    int st =ses.createQuery("update user set userRoom_roomId =:rId where uId=:uId ").setParameter("uId", uId).setParameter("rId", rId).executeUpdate();
			
		    ses.getTransaction().commit();
		    
		    return st;
		}
	}

	@Override
	public int deleteUser(int uId) {
		// TODO Auto-generated method stub
		try(Session ses = HibernateUtil.getSession()){
			
			ses.beginTransaction();
			
			int st = ses.createQuery("delete from user where uId =:uId").setParameter("uId",uId).executeUpdate();
			
			return st;
			
		
		}
	}

	@Override
	public int addDueAmount(int amount, int uId) {
		// TODO Auto-generated method stub
		try(Session ses = HibernateUtil.getSession()){
			
			ses.beginTransaction();
			
			int dueFee =ses.createQuery("select userFee from user where uId =:uId").setParameter("uId",uId).executeUpdate();
			
			dueFee +=amount;
			
			int st = ses.createQuery("update user set userFee =:dueFee where uId =:uId").setParameter("dueFee", dueFee).setParameter("uId",uId).executeUpdate();
			
			return st;
		}
	}

	@Override
	public int paidDueAmount(int amount, int uId) {
		// TODO Auto-generated method stub
		try(Session ses = HibernateUtil.getSession()){
			
			ses.beginTransaction();
			
            int dueFee =ses.createQuery("select userFee from user where uId =:uId").setParameter("uId",uId).executeUpdate();
			
			dueFee -=amount;
			
			int st = ses.createQuery("update user set userFee =:dueFee where uId =:uId").setParameter("dueFee", dueFee).setParameter("uId",uId).executeUpdate();
			
			return st; 
			
			
		}
	}

	@Override
	public user viewUserProfile(int uId) {
		// TODO Auto-generated method stub
		try(Session ses = HibernateUtil.getSession()){
			
			ses.beginTransaction();
			
			user u1 = ses.get(user.class, uId);
			
			return u1;
		}
	}
    @Override
	public List<room> viewRooms() {
		//autoclosable session object
		try(Session ses=HibernateUtil.getSession()){
			//getting rows of a room table
			Query q=ses.createQuery("from room");
			@SuppressWarnings("unchecked")
			List<room> rl=q.getResultList();
			return rl;
		}
	}

	@Override
	public List<user> viewUsers() {
		// TODO Auto-generated method stub
		try (Session ses =HibernateUtil.getSession()){
			
			Query q = ses.createQuery("from user");
			@SuppressWarnings("unchecked")
			List<user> ul = q.getResultList();
			return ul;
		}
	}

	@Override
	public List<user> userInARoom(int rId) {
		// TODO Auto-generated method stub
		try(Session ses =HibernateUtil.getSession()){
			
			Query q = ses.createQuery("select from user where roomId =:rId").setParameter("rId", rId);
			
		   @SuppressWarnings("unchecked")
		List <user> rl= q.getResultList();
		   return rl;
		}
	}


}
