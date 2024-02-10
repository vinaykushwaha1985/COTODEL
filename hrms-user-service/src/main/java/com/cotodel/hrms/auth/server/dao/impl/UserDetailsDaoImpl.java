package com.cotodel.hrms.auth.server.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.cotodel.hrms.auth.server.dao.UserDetailsDao;
import com.cotodel.hrms.auth.server.entity.UserEmpEntity;
import com.cotodel.hrms.auth.server.entity.UserEntity;
import com.cotodel.hrms.auth.server.repository.UserEmpRepository;
import com.cotodel.hrms.auth.server.repository.UserRepository;

@Repository
public class UserDetailsDaoImpl implements UserDetailsDao {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserEmpRepository userEmpRepository;
	
	@Override
	public UserEntity saveUserDetails(UserEntity user) {
		// TODO Auto-generated method stub
		return userRepository.saveAndFlush(user);
	}

	@Override
	public UserEmpEntity saveUserEmpEntity(UserEmpEntity userEmpEntity) {
		// TODO Auto-generated method stub
		return userEmpRepository.saveAndFlush(userEmpEntity);
	}

	@Override
	public UserEntity checkUserDetails(String userName) {
		// TODO Auto-generated method stub
		return userRepository.checkUserDetails(userName);
	}

	@Override
	public UserEntity checkUserMobile(String userMobile) {
		// TODO Auto-generated method stub
		return userRepository.checkUserMobile(userMobile);
	}

	@Override
	public UserEntity checkUserEmail(String userEmail) {
		// TODO Auto-generated method stub
		return userRepository.checkUserEmail(userEmail);
	}

	@Override
	public UserEntity getByUserName(String userName) {
		// TODO Auto-generated method stub
		return userRepository.getByUser(userName);
	}

}
