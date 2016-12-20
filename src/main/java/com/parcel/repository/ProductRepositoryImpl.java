package com.parcel.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.parcel.entity.Machine;
import com.parcel.entity.Product;
import com.parcel.service.ProductServiceImpl;
import com.parcel.util.Page;

/**
 * ProductRepository 구현 클래스
 *
 */
@Repository
public class ProductRepositoryImpl implements ProductRepository {

	private JdbcTemplate t;
	private ProductRowMapper rowMapper;
	@Autowired
	public ProductRepositoryImpl(DataSource dataSource) {
		t = new JdbcTemplate(dataSource);
		rowMapper = new ProductRowMapper();
	}
	
	@Override
	public List<Machine> findAllMachineList() {
		String sql = "SELECT * FROM machine WHERE state=1";
		return t.query(sql, (rs,no)->{
			Machine m = new Machine(
					rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getTimestamp(4)
			);
			return m;
		});
	}

	@Override
	public int addProduct(Product product) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateProduct(Product product) {
		String sql = "UPDATE product SET registrant=?, public_name=?, registration_date=now(), state=1 WHERE machine=? AND machine_code=?";
		
		return t.update(sql, product.getRegistrant(), product.getPublic_name(), product.getMachine(), product.getMachine_code());
	}

	@Override
	public Product findProductByMachineAndMachine_code(Product product) {
		String sql = "SELECT * FROM product WHERE machine=? AND machine_code=?";
		try {
			return t.queryForObject(sql, (rs, no)->{
				return new Product(rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getTimestamp(4),
						rs.getInt(5),
						rs.getInt(6),
						rs.getString(7),
						rs.getInt(8));
			}, product.getMachine(), product.getMachine_code());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Product findProductByPidx(int pidx) {
		String sql = "SELECT p.*, m.machine_name, u.name as registrant_name "
				+ "FROM product p, machine m, user u "
				+ "WHERE p.idx=? AND m.idx=p.machine AND u.idx=p.registrant;";
		try {
			return t.queryForObject(sql, (rs, no)->{
				return new Product(rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getTimestamp(4),
						rs.getInt(5),
						rs.getInt(6),
						rs.getString(7),
						rs.getInt(8),
						rs.getString(9),
						rs.getString(10));
			}, pidx);
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public int updateProductByIdxForLock(int productIdx, boolean lock) {
		String sql = "UPDATE product SET is_open=? WHERE idx=?";
		int tempLock;
		if(lock == ProductServiceImpl.LOCK) { //잠가야함
			tempLock = 1;
		} else { //열어야함
			tempLock = 0;
		}
		return t.update(sql, tempLock, productIdx);
		
	}

	@Override
	public List<Product> findProductListByPage(Page page, boolean hasCategory, boolean hasKeyword) {
		String sql;
		if (hasCategory && hasKeyword) {
			sql = "SELECT * FROM product WHERE state=1 AND " + page.getStrCategory()  +  " LIKE ? ORDER BY idx desc LIMIT ?,?";
			return t.query(sql, rowMapper, page.getkeywordForSqlLike(), page.getFirstContent(), page.getLastContent());
		} else if (hasCategory && !hasKeyword) {
			sql = "SELECT * FROM product WHERE state=1 AND (machine LIKE ? OR machine_code LIKE ? OR registrant LIKE ?) ORDER BY idx desc LIMIT ?,?"; //조건 다 LIKE해준다.
			return t.query(sql, rowMapper
					, page.getkeywordForSqlLike(),page.getkeywordForSqlLike(),page.getkeywordForSqlLike()
					, page.getFirstContent(), page.getLastContent());
		} else if (!hasCategory && hasKeyword) {
			sql = "SELECT * FROM product WHERE state=1 AND (machine LIKE ? OR machine_code LIKE ? OR registrant LIKE ?) ORDER BY idx desc LIMIT ?,?"; //조건 다 LIKE해준다.
			return t.query(sql, rowMapper
					, page.getkeywordForSqlLike(),page.getkeywordForSqlLike(),page.getkeywordForSqlLike()
					, page.getFirstContent(), page.getLastContent());
		} else if (!hasCategory && !hasKeyword) {
			sql = "SELECT * FROM product WHERE state=1 ORDER BY idx desc LIMIT ?,?"; //그냥 전부
			return t.query(sql, rowMapper, page.getFirstContent(), page.getLastContent());
		} else {
			return new ArrayList<Product>();
		}
	}
	
	@Override
	public int findCountProductByPage(Page page, boolean hasCategory, boolean hasKeyword) {
		String sql;
		if (hasCategory && hasKeyword) {
			System.out.println("44");
			sql = "SELECT COUNT(idx) FROM product WHERE state=1 AND " + page.getStrCategory() + " LIKE ?";
			return t.queryForObject(sql, Integer.class, page.getkeywordForSqlLike());
		} else if (hasCategory && !hasKeyword) {
			System.out.println("33");
			sql = "SELECT COUNT(idx) FROM product WHERE state=1 AND (machine LIKE ? OR machine_code LIKE ? OR registrant LIKE ?)" ; 
			return t.queryForObject(sql, Integer.class, page.getkeywordForSqlLike(),page.getkeywordForSqlLike(),page.getkeywordForSqlLike());
		} else if (!hasCategory && hasKeyword) {
			System.out.println("22");
			sql = "SELECT COUNT(idx) FROM product WHERE state=1 AND (machine LIKE ? OR machine_code LIKE ? OR registrant LIKE ?)"; 
			return t.queryForObject(sql, Integer.class
					, page.getkeywordForSqlLike(),page.getkeywordForSqlLike(),page.getkeywordForSqlLike());
		} else if (!hasCategory && !hasKeyword) {
			System.out.println("11");
			sql = "SELECT COUNT(idx) FROM product WHERE state=1 "; //그냥 전부
			return t.queryForObject(sql, Integer.class);
		} else {
			return 0;
		}
	}
	
	/*
		if (hasCategory && hasKeyword) {
			sql = "SELECT COUNT(idx) FROM product WHERE state=1 AND ? LIKE ? ORDER BY idx desc LIMIT ?,?";
			return t.query(sql, rowMapper, page.getStrCategory(), page.getkeywordForSqlLike(), page.getFirstContent(), page.getLastContent());
		} else if (hasCategory && !hasKeyword) {
			sql = "SELECT COUNT(idx) FROM product WHERE state=1 ORDER BY idx desc LIMIT ?,?"; //그냥 전부
			return t.query(sql, rowMapper, page.getFirstContent(), page.getLastContent());
		} else if (!hasCategory && hasKeyword) {
			sql = "SELECT COUNT(idx) FROM product WHERE state=1 AND machine LIKE ? OR machine_code LIKE ? OR registrant LIKE ? ORDER BY idx desc LIMIT ?,?"; //조건 다 LIKE해준다.
			return t.query(sql, rowMapper
					, page.getkeywordForSqlLike(),page.getkeywordForSqlLike(),page.getkeywordForSqlLike()
					, page.getFirstContent(), page.getLastContent());
		} else if (!hasCategory && !hasKeyword) {
			sql = "SELECT COUNT(idx) FROM product WHERE state=1 ORDER BY idx desc LIMIT ?,?"; //그냥 전부
			return t.query(sql, rowMapper, page.getFirstContent(), page.getLastContent());
		} else {
			return new ArrayList<Product>();
		}
		*/

}

class ProductRowMapper implements RowMapper<Product> {
	
	@Override
	public Product mapRow(ResultSet rs, int no) throws SQLException {
		Product p = new Product();
		p.setIdx(rs.getInt("idx"));
		p.setMachine(rs.getInt("machine"));
		p.setMachine_code(rs.getString("machine_code"));
		p.setRegistration_date(rs.getTimestamp("registration_date"));
		p.setRegistrant(rs.getInt("registrant"));
		p.setState(rs.getInt("state"));
		p.setPublic_name(rs.getString("public_name"));
		p.setIs_open(rs.getInt("is_open"));
		return p;
	}
	
}