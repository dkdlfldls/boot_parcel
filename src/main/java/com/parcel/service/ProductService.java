package com.parcel.service;

import java.util.List;

import com.parcel.entity.Machine;
import com.parcel.entity.Product;
import com.parcel.entity.User;

public interface ProductService {
	//public
	
	public List<Machine> getMachineList();
	public String registerProductByUser(Product product);
	public Product getProductInfo(int pidx);
	public boolean lock(User user);
	public boolean open(User user);
}
