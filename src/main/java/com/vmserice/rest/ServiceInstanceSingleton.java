package com.vmserice.rest;

import java.net.URL;

import com.vmware.vim25.mo.ServiceInstance;

public class ServiceInstanceSingleton {
	
	public static ServiceInstanceSingleton serviceInstanceSingleton;
	public static ServiceInstance serviceInstance;
	
	private ServiceInstanceSingleton(){
		
		
	}

	
	public static ServiceInstanceSingleton getInstance(){
		
		serviceInstanceSingleton=new ServiceInstanceSingleton();
		serviceInstance=getServiceInstanceOnce();
		return serviceInstanceSingleton;
	}
	
	public  static ServiceInstance getServiceInstanceOnce(){
		
		
		
		try{
			URL url=new URL("https://130.65.132.110/sdk");
			serviceInstance = new ServiceInstance(url, "administrator", "12!@qwQW", true);
			
			if(serviceInstance!=null){
				
				return serviceInstance;
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
			return null;
		
		
	}
	
}
