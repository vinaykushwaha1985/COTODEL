package com.cotodel.hrms.auth.server.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.transaction.Transactional;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cotodel.hrms.auth.server.controller.MobileEmailVerifyController;
import com.cotodel.hrms.auth.server.dao.UserDetailsDao;
import com.cotodel.hrms.auth.server.dto.UserRequest;
import com.cotodel.hrms.auth.server.entity.UserEmpEntity;
import com.cotodel.hrms.auth.server.entity.UserEntity;
import com.cotodel.hrms.auth.server.properties.ApplicationConstantConfig;
import com.cotodel.hrms.auth.server.service.UserService;
import com.cotodel.hrms.auth.server.util.CommonUtility;
import com.cotodel.hrms.auth.server.util.CopyUtility;
import com.cotodel.hrms.auth.server.util.MessageConstant;
import com.cotodel.hrms.auth.server.util.TransactionManager;

import java.nio.charset.StandardCharsets;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.xml.bind.DatatypeConverter;

@Repository
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	UserDetailsDao userDetailsDao;
	
	@Autowired
	ApplicationConstantConfig applicationConstantConfig;
	
	@Override
	@Transactional
	public UserEntity saveUserDetails(UserRequest user) {
		// TODO Auto-generated method stub
		UserEntity userDetails= new UserEntity();
		UserEmpEntity userEmpEntity= new UserEmpEntity();
		//CopyUtility.copyProperties(userDetails, user);
		CopyUtility.copyProperties(user,userDetails);
		Date date = new Date();
		LocalDate localDate =date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		userDetails.setCreated_date(localDate);
		UserEntity UserEntity1=userDetailsDao.saveUserDetails(userDetails);
		userEmpEntity.setUser_id(UserEntity1.getId());
		userEmpEntity.setStatus(UserEntity1.getStatus());
		
		userEmpEntity.setCreated_date(localDate);
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

	
	@Override
	public void sendEmailToEmployee(UserRequest req) {
		
		 // Set up mail server properties
	        Properties properties = new Properties();
	        properties.put("mail.smtp.host", "smtp.gmail.com");
	        properties.put("mail.smtp.port", "587");
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
		  Session session = Session.getInstance(properties, new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("dkawal73@gmail.com", "jaygeajbqvinwacz");
	            }
	        });
			
			Message msg = new MimeMessage(session);
				try {
					msg.setHeader("Content-Type", "text/plain; charset=UTF-8");
					msg.setFrom(new InternetAddress(req.getEmail(), false));
					msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(req.getEmail()));
					//msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("fakhrudeen.mca@gmail.com"));
					msg.setSubject("User Registration Request Verification");
					msg.setContent("Verify Sigin", "text/html");
					msg.setSentDate(new Date());
					byte[] bytes = req.getMobile().getBytes(StandardCharsets.UTF_8);
				    String encoded = DatatypeConverter.printBase64Binary(bytes);
				    byte[] byt = req.getEmail().getBytes(StandardCharsets.UTF_8);
				    String emailbyt = DatatypeConverter.printBase64Binary(byt);
				    
				    String confirmationUrl = applicationConstantConfig.emailVerifyUrl+"?token="+encoded.replaceAll("==","")+"/"+emailbyt;
				    String emailBody = "Click the link to verify your email: " + confirmationUrl;
				    
					String link="<p></p><a href=" +applicationConstantConfig.emailVerifyUrl+"/"+emailbyt.replaceAll("==","")+"/"+encoded.replaceAll("==","") +"><h3>Please click here to verify....<h3></a>";
					MimeBodyPart messageBodyPart = new MimeBodyPart();
					//String password =generatePassword(8);
					messageBodyPart.setContent(link, "text/html");
					Multipart multipart = new MimeMultipart();
					multipart.addBodyPart(messageBodyPart);
//					MimeBodyPart attachPart = new MimeBodyPart();
//					attachPart.attachFile(attachement);
//					multipart.addBodyPart(attachPart);
					msg.setContent(multipart);
					Transport.send(msg);
					System.out.println("verification mail sended successfully to :"+req.getEmail());

				} catch (MessagingException e) {
					e.printStackTrace();
				}
			
			}

	@Override
	public String getToken(String comId) {
		return CommonUtility.getTokenRequest(null,"",comId,applicationConstantConfig.getTokenUrl);
		
	}

	
	
	@Override
	public String verifyEmailUpdate(String email) {
		UserEntity userDetails= null;
		try {
			//check user exist
			userDetails = userDetailsDao.checkUserEmail(email);
			if(userDetails!=null) {
				
						
			userDetails.setStatus(1);
			userDetails.setEmail_verify_status(1);
			userDetails.setEmail_verify_date(LocalDate.now());
			//logger.info("updateUserStatus object going to save------"+userDetails.toString());
			
			userDetails = userDetailsDao.saveUserDetails(userDetails);
			
			if(userDetails!=null && userDetails.getStatus()==1) {
				return MessageConstant.RESPONSE_SUCCESS;
			}else {
				return MessageConstant.RESPONSE_FAILED;	
			}		
			}
		} catch (Exception e) {
			e.printStackTrace();
			//logger.error("error in updateUserStatus----"+e);
			return MessageConstant.RESPONSE_FAILED;
		}finally {
			userDetails= null;
		}		
		return MessageConstant.RESPONSE_FAILED;
	}

	

	@Override
	public String sendSmsOtp(String authToken,String mobile) {
		return CommonUtility.userRequest(authToken,sendSmsOtpRequest(mobile),applicationConstantConfig.otpSenderUrl);
		
	}

	public  String sendSmsOtpRequest(String mobile){
		JSONObject data= new JSONObject();
		data.put("mobile", mobile);
		data.put("templateid", applicationConstantConfig.templateId);
		logger.info("send SMS OTP Request"+data);
		return data.toString();
	}



}
