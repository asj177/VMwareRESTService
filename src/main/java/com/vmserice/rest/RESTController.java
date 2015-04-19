package com.vmserice.rest;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RESTController {

	
	
	@RequestMapping(value = URIConstansts.GETVM, produces = { "application/json" }, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<ArrayList<VMDetails>> getVM(HttpServletRequest request){
		
		ResponseEntity<ArrayList<VMDetails>> respEntity = null;
	    
		VMService vmService=new VMService();
		ArrayList<VMDetails> vmdetails=new ArrayList<VMDetails>();
		
		vmdetails=vmService.getVM();
		
		respEntity = new ResponseEntity<ArrayList<VMDetails>>(vmdetails, HttpStatus.OK);
		return respEntity;

	}
	
	
	@RequestMapping(value=URIConstansts.changePower,produces = { "application/json" }, method = RequestMethod.POST)
	public @ResponseBody ResponseEntity changePower(@PathVariable("vm_name")String vmName,@PathVariable("power_status")String powerState,HttpServletRequest request){
		ResponseEntity respEntity=null;
		
		VMService vmService=new VMService();
		boolean status=vmService.changePower(vmName, powerState);
		
		
		if(status){
			
			respEntity = new ResponseEntity(HttpStatus.NO_CONTENT);
			
		}
		
	return respEntity;
	}
}
