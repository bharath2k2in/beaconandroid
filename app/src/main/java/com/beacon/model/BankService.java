package com.beacon.model;

import java.util.List;

public class BankService {
	private String ID;
	private List<Service> serviceList;
	
	
	public List<Service> getServiceList() {
		return serviceList;
	}
	public void setServiceList(List<Service> serviceList) {
		this.serviceList = serviceList;
	}

	private class Service{
		private String serviceName;

		public String getServiceName() {
			return serviceName;
		}

		public void setServiceName(String serviceName) {
			this.serviceName = serviceName;
		}
	}
	
	@Override
	public String toString() {
		return " ID : " + ID + " Service List " + serviceList;
	}
}
