package com.parcel.repository;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TestRepository {

	private JdbcTemplate t;
	
	@Autowired
	public TestRepository(DataSource dataSource) {
		t = new JdbcTemplate(dataSource);
	}
	
	public int addState(String label) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO state (label) values(?)";
		
		return t.update(sql, label);
	}

}
