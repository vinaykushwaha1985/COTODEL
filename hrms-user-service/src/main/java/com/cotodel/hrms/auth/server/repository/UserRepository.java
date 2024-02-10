package com.cotodel.hrms.auth.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cotodel.hrms.auth.server.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	

	  @Query("select s  from UserEntity s where s.username = ?1 and s.status=1")
	  public UserEntity getByUser(String userName);
	  
	  @Query("select s  from UserEntity s where s.username = ?1")
	  public UserEntity checkUserDetails(String userName);
	  
	  @Query("select s  from UserEntity s where s.mobile = ?1")
		public UserEntity checkUserMobile(String userMobile);
	  
	  @Query("select s  from UserEntity s where s.email = ?1")
		public UserEntity checkUserEmail(String userEmail);
	 
	  
} 

