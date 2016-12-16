package com.parcel.service;

import java.util.List;

import com.parcel.entity.Log;
import com.parcel.entity.Machine;
import com.parcel.entity.Product;
import com.parcel.util.Page;

public interface AdminService {
	
	public List<Log> getLogList(Page page);

	public List<Log> getSearchResultOfLogList(Page page);

	public List<Machine> getMachineListByPage(Page page);

	public List<Machine> getMachineList();

	public boolean modifyMachineName(Machine machine);

	public boolean addMachine(Machine machine);

	public boolean deleteMachine(Machine machine);

	public List<Product> getProductList(Page page);

}
