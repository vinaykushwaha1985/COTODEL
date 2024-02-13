package com.cotodel.hrms.auth.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cotodel.hrms.auth.server.dto.UserRequest;
import com.cotodel.hrms.auth.server.dto.UserSignUpResponse;
import com.cotodel.hrms.auth.server.entity.UserEntity;
import com.cotodel.hrms.auth.server.exception.ApiError;
import com.cotodel.hrms.auth.server.multi.datasource.SetDatabaseTenent;
import com.cotodel.hrms.auth.server.service.UserService;
import com.cotodel.hrms.auth.server.util.TransactionManager;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

/**
 * @author vinay
 */
@RestController
@RequestMapping("/Api")
public class UserSignUpController {
	

	
	private static final Logger logger = LoggerFactory.getLogger(UserSignUpController.class);
    
	@Autowired
	UserService userService;
	
	 @Operation(summary = "This API will provide the Save User Details ", security = {
	    		@SecurityRequirement(name = "task_auth")}, tags = {"Authentication Token APIs"})
	    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200",description = "ok", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ResponseEntity.class))),		
	    @ApiResponse(responseCode = "400",description = "Request Parameter's Validation Failed", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ApiError.class))),
	    @ApiResponse(responseCode = "404",description = "Request Resource was not found", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ApiError.class))),
	    @ApiResponse(responseCode = "500",description = "System down/Unhandled Exceptions", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ApiError.class)))})
	    @RequestMapping(value = "/get/saveUserDetails",produces = {"application/json"}, 
	    consumes = {"application/json","application/text"},method = RequestMethod.POST)
	    public ResponseEntity<Object> saveUserDetails(HttpServletRequest request,@Valid @RequestBody UserRequest userReq) {
	    	logger.info("inside token generation");
	    	UserEntity userEntity=null;
	    	String responseToken="";
	    	String authToken = "";
	    	try {	    		
	    		String companyId = request.getHeader("companyId");
				SetDatabaseTenent.setDataSource(companyId);
	    	 userEntity=	userService.saveUserDetails(userReq);
	    		
	    	 if(userEntity!=null) {	    
	    		 userService.sendEmailToEmployee(userReq);
	    		 responseToken = userService.getToken(companyId);
					//String authToken = "";
					if (!ObjectUtils.isEmpty(responseToken)) {
						JSONObject getTokenRes = new JSONObject(responseToken);
						authToken = getTokenRes.getString("access_token");
					}
	    		 return ResponseEntity.ok(new UserSignUpResponse(true,userEntity,TransactionManager.getTransactionId(),TransactionManager.getCurrentTimeStamp(),authToken));
	    	 }
	    	 
	    	}catch (Exception e) {
				
	    		e.printStackTrace();
			}
	        
	        return ResponseEntity.ok(new UserSignUpResponse(false,userEntity,TransactionManager.getTransactionId(),TransactionManager.getCurrentTimeStamp(),authToken));
	          
	        
	    }

	 
	 
	 @Operation(summary = "This API will provide the Save User Details ", security = {
	    		@SecurityRequirement(name = "task_auth")}, tags = {"Authentication Token APIs"})
	    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200",description = "ok", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ResponseEntity.class))),		
	    @ApiResponse(responseCode = "400",description = "Request Parameter's Validation Failed", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ApiError.class))),
	    @ApiResponse(responseCode = "404",description = "Request Resource was not found", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ApiError.class))),
	    @ApiResponse(responseCode = "500",description = "System down/Unhandled Exceptions", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ApiError.class)))})
	    @RequestMapping(value = "/get/checkUserDetails",produces = {"application/json"}, 
	    consumes = {"application/json","application/text"},method = RequestMethod.POST)
	    public ResponseEntity<Object> checkEligibility(@Valid @RequestBody UserRequest userReq) {
	    	logger.info("inside token generation");
	    	UserEntity userEntity=null;
	    	try {
	    		
	    		
	    		
	    	 userEntity=	userService.checkUserDetails(userReq.getUsername());
	    		
	    	 if(userEntity!=null)
	    		 return ResponseEntity
	 	                .ok(userEntity);
	    	 
	    	 
	    	}catch (Exception e) {
				
	    		// TODO: handle exception
			}
	        
	        return ResponseEntity
	                .ok(null);
	          
	        
	    }


	 
	
	

}
