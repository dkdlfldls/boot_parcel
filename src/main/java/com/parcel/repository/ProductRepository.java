package com.parcel.repository;

import java.util.List;

import com.parcel.entity.Machine;
import com.parcel.entity.Product;
import com.parcel.util.Page;

public interface ProductRepository {

	public List<Machine> findAllMachineList();

	public int addProduct(Product product);

	public int updateProduct(Product product);
	
	public Product findProductByMachineAndMachine_code(Product product);
	
	public Product findProductByPidx(int pidx);

	public int updateProductByIdxForLock(int productIdx, boolean lock);

	public List<Product> findProductListByPage(Page page, boolean hasCategory, boolean hasKeyword);

	public int findCountProductByPage(Page page, boolean hasCategory, boolean hasKeyword);

}
