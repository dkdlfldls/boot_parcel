package com.parcel.repository;

import java.util.List;

import com.parcel.entity.Machine;
import com.parcel.entity.Product;

public interface ProductRepository {

	public List<Machine> getMachineList();

	public int addProduct(Product product);

	public int updateProduct(Product product);
	
	public Product findProductByMachineAndMachine_code(Product product);
	
	public Product getProductInfo(int pidx);

	public int updateProductByIdxForLock(int productIdx, boolean lock);

}
