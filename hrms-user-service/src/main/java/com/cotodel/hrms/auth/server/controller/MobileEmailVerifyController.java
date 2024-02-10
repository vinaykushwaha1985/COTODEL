package com.cotodel.hrms.auth.server.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cotodel.hrms.auth.server.dto.UserRequest;
import com.cotodel.hrms.auth.server.entity.RoleMaster;
import com.cotodel.hrms.auth.server.exception.ApiError;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/Api")
public class MobileEmailVerifyController {
		

	
	private static final Logger logger = LoggerFactory.getLogger(RolesController.class);
    
	
	 @Operation(summary = "This API will provide the User Mobile Verify Details ", security = {
	    		@SecurityRequirement(name = "task_auth")}, tags = {"Authentication Token APIs"})
	    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200",description = "ok", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ResponseEntity.class))),		
	    @ApiResponse(responseCode = "400",description = "Request Parameter's Validation Failed", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ApiError.class))),
	    @ApiResponse(responseCode = "404",description = "Request Resource was not found", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ApiError.class))),
	    @ApiResponse(responseCode = "500",description = "System down/Unhandled Exceptions", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ApiError.class)))})
	    @RequestMapping(value = "/getOtp",produces = {"application/json"}, consumes = {"application/json","application/text"},
	    method = RequestMethod.POST)
	    public ResponseEntity<Object> sendOtp(@Valid @RequestBody UserRequest userReq) {
	    	logger.info("inside token generation");
	    	List<RoleMaster> roleMaster=null;
	    	try {
	    		
	    	
	    		// write code here
	    		
	    	 if(roleMaster!=null)
	    		 return ResponseEntity
	 	                .ok(roleMaster);
	    	 
	    	 
	    	}catch (Exception e) {
				
	    		// TODO: handle exception
			}
	        
	        return ResponseEntity
	                .ok(null);
	          
	        
	    }

	

	 
	 @Operation(summary = "This API will provide the User Mobile Verify Details ", security = {
	    		@SecurityRequirement(name = "task_auth")}, tags = {"Authentication Token APIs"})
	    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200",description = "ok", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ResponseEntity.class))),		
	    @ApiResponse(responseCode = "400",description = "Request Parameter's Validation Failed", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ApiError.class))),
	    @ApiResponse(responseCode = "404",description = "Request Resource was not found", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ApiError.class))),
	    @ApiResponse(responseCode = "500",description = "System down/Unhandled Exceptions", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ApiError.class)))})
	    @RequestMapping(value = "/verifyOtp",produces = {"application/json"}, consumes = {"application/json","application/text"},
	    method = RequestMethod.POST)
	    public ResponseEntity<Object> verifyOtp(@Valid @RequestBody UserRequest userReq) {
	    	logger.info("inside token generation");
	    	List<RoleMaster> roleMaster=null;
	    	try {
	    		
	    		// write code here
	    		
	    	 if(roleMaster!=null)
	    		 return ResponseEntity
	 	                .ok(roleMaster);
	    	 
	    	 
	    	}catch (Exception e) {
				
	    		// TODO: handle exception
			}
	        
	        return ResponseEntity
	                .ok(null);
	          
	        
	    }



	 @Operation(summary = "This API will provide the User Emial Address Verify Details ", security = {
	    		@SecurityRequirement(name = "task_auth")}, tags = {"Authentication Token APIs"})
	    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200",description = "ok", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ResponseEntity.class))),		
	    @ApiResponse(responseCode = "400",description = "Request Parameter's Validation Failed", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ApiError.class))),
	    @ApiResponse(responseCode = "404",description = "Request Resource was not found", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ApiError.class))),
	    @ApiResponse(responseCode = "500",description = "System down/Unhandled Exceptions", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ApiError.class)))})
	    @RequestMapping(value = "/sendEmailVerifyLink",produces = {"application/json"}, consumes = {"application/json","application/text"},
	    method = RequestMethod.POST)
	    public ResponseEntity<Object> sendLinkToEmail(@Valid @RequestBody UserRequest userReq) {
	    	logger.info("inside token generation");
	    	List<RoleMaster> roleMaster=null;
	    	try {
	    		
	    	
	    		// write code here
	    		
	    	 if(roleMaster!=null)
	    		 return ResponseEntity
	 	                .ok(roleMaster);
	    	 
	    	 
	    	}catch (Exception e) {
				
	    		// TODO: handle exception
			}
	        
	        return ResponseEntity
	                .ok(null);
	          
	        
	    }

	



	 
	 @Operation(summary = "This API will provide the User Emial Verify Details ", security = {
	    		@SecurityRequirement(name = "task_auth")}, tags = {"Authentication Token APIs"})
	    @ApiResponses(value = {
	    @ApiResponse(responseCode = "200",description = "ok", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ResponseEntity.class))),		
	    @ApiResponse(responseCode = "400",description = "Request Parameter's Validation Failed", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ApiError.class))),
	    @ApiResponse(responseCode = "404",description = "Request Resource was not found", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ApiError.class))),
	    @ApiResponse(responseCode = "500",description = "System down/Unhandled Exceptions", content = @Content(mediaType = "application/json",schema = @Schema(implementation = ApiError.class)))})
	    @RequestMapping(value = "/verifyLink",produces = {"application/json"}, consumes = {"application/json","application/text"},
	    method = RequestMethod.POST)
	    public ResponseEntity<Object> verifyLinkFromEmail(@Valid @RequestBody UserRequest userReq) {
	    	logger.info("inside token generation");
	    	List<RoleMaster> roleMaster=null;
	    	try {
	    		
	    	
	    		// write code here
	    		
	    	 if(roleMaster!=null)
	    		 return ResponseEntity
	 	                .ok(roleMaster);
	    	 
	    	 
	    	}catch (Exception e) {
				
	    		// TODO: handle exception
			}
	        
	        return ResponseEntity
	                .ok(null);
	          
	        
	    }

	


	

}
