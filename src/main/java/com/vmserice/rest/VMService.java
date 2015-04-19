package com.vmserice.rest;

import java.rmi.RemoteException;
import java.util.ArrayList;

import com.vmware.vim25.InvalidProperty;
import com.vmware.vim25.RuntimeFault;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.VirtualMachine;

public class VMService {
	
	private   ArrayList<VirtualMachine>virtualMachines=new ArrayList<VirtualMachine>();
	
	public ArrayList<VMDetails>getVM(){
		getVirtualMachineList();
		ArrayList<VMDetails>vmdetails=new ArrayList<VMDetails>();
		for(VirtualMachine vm:virtualMachines){
			
			VMDetails vminfo=new VMDetails();
			vminfo.setVmName(vm.getName());
			vminfo.setPowerStatus(vm.getRuntime().getPowerState().toString());
			vmdetails.add(vminfo);
		}
		
		return vmdetails;
		
		
		
	}
	
	public boolean changePower(String vmName,String powerState){
		
		
		
		
		ServiceInstance serviceInstance;
		ServiceInstanceSingleton serviceSingleton=null;
		if(ServiceInstanceSingleton.serviceInstanceSingleton==null){
			serviceSingleton=ServiceInstanceSingleton.getInstance();
		}else{
			serviceSingleton=ServiceInstanceSingleton.serviceInstanceSingleton;
		}
			
		serviceInstance=ServiceInstanceSingleton.serviceInstance;
		
		Folder rootFolder = serviceInstance.getRootFolder();
		
		ManagedEntity[] mes;
		try {
			mes = new InventoryNavigator(rootFolder).searchManagedEntities("VirtualMachine");
			for(int i=0;i<mes.length;i++){
				VirtualMachine vm=(VirtualMachine)mes[i];
				
				if(vmName.equals(vm.getName())){
					
					if(powerState.equals("poweredOff")){
						vm.powerOnVM_Task(null);
						return true;
					}else{
						vm.powerOffVM_Task();
						return true;
					}
				}
				
				}
		} catch (InvalidProperty e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (RuntimeFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
		
	}
	
	private void getVirtualMachineList(){
		ServiceInstance serviceInstance;
		ServiceInstanceSingleton serviceSingleton=null;
		if(ServiceInstanceSingleton.serviceInstanceSingleton==null){
			serviceSingleton=ServiceInstanceSingleton.getInstance();
		}else{
			serviceSingleton=ServiceInstanceSingleton.serviceInstanceSingleton;
		}
			
		serviceInstance=ServiceInstanceSingleton.serviceInstance;
		
		Folder rootFolder = serviceInstance.getRootFolder();
		
		ManagedEntity[] mes;
		try {
			mes = new InventoryNavigator(rootFolder).searchManagedEntities("VirtualMachine");
			for(int i=0;i<mes.length;i++){
				
				
				VirtualMachine vm=(VirtualMachine)mes[i];
				if(null!=vm && !vm.getConfig().template){
				virtualMachines.add(vm);
				}
				}
		} catch (InvalidProperty e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RuntimeFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
