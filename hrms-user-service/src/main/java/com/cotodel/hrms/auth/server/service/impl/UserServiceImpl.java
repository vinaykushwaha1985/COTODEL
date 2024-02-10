package com.cotodel.hrms.auth.server.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cotodel.hrms.auth.server.dao.UserDetailsDao;
import com.cotodel.hrms.auth.server.dto.UserRequest;
import com.cotodel.hrms.auth.server.entity.UserEmpEntity;
import com.cotodel.hrms.auth.server.entity.UserEntity;
import com.cotodel.hrms.auth.server.service.UserService;
import com.cotodel.hrms.auth.server.util.CopyUtility;

@Repository
public class UserServiceImpl implements UserService {

	
	@Autowired
	UserDetailsDao userDetailsDao;
	
	
	@Override
	@Transactional
	public UserEntity saveUserDetails(UserRequest user) {
		// TODO Auto-generated method stub
		UserEntity userDetails= new UserEntity();
		UserEmpEntity userEmpEntity= new UserEmpEntity();
		CopyUtility.copyProperties(userDetails, user);
		
		UserEntity UserEntity1=userDetailsDao.saveUserDetails(userDetails);
		
		userDetailsDao.saveUserEmpEntity(userEmpEntity);
		
		
		return UserEntity1;
	}

	@Override
	public UserEmpEntity saveUserEmpEntity(UserEmpEntity userEmpEntity) {
		// TODO Auto-generated method stub
		return userDetailsDao.saveUserEmpEntity(userEmpEntity);
	}

	@Override
	public UserEntity checkUserDetails(String userName) {
		// TODO Auto-generated method stub
		return userDetailsDao.checkUserDetails(userName);
	}

	@Override
	public UserEntity checkUserMobile(String userMobile) {
		// TODO Auto-generated method stub
		return userDetailsDao.checkUserMobile(userMobile);
	}

	@Override
	public UserEntity checkUserEmail(String userEmail) {
		// TODO Auto-generated method stub
		return userDetailsDao.checkUserEmail(userEmail);
	}
	
	
	@Override
	public UserEntity getByUserName(String userName) {
		// TODO Auto-generated method stub
		return userDetailsDao.getByUserName(userName);
	}


}
