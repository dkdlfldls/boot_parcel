package com.parcel.repository;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.parcel.entity.Machine;
import com.parcel.util.Page;

@Repository
public class MachineRepositoryImpl implements MachineRepository {
	private Logger logger = LoggerFactory.getLogger(MachineRepositoryImpl.class);

	private JdbcTemplate t;
	
	@Autowired
	public MachineRepositoryImpl(DataSource dataSource) {
		t = new JdbcTemplate(dataSource);
	}
	
	
	@Override
	public int createMachine(Machine machine) {
		String sql = "INSERT INTO machine(machine_name) VALUES(?)";
		try {
			return t.update(sql, machine.getMachine_name());
		} catch (Exception e) {
			logger.debug(e.getMessage());
			return 0;
		}
	}

	@Override
	public Machine findMachineByIdx(Machine machine) {
		String sql = "SELECT * FROM machine WHERE idx=? AND state=1";
		try {
			return t.queryForObject(sql, (rs, no)->{
				Machine m = new Machine();
				m.setIdx(rs.getInt(1));
				m.setMachine_name(rs.getString(2));
				m.setState(rs.getInt(3));
				m.setSign_up_date(rs.getTimestamp(4));
				return m;
			}, machine.getIdx());
		} catch (Exception e) {
			logger.debug(e.getMessage());
			return null;
		}
	}

	@Override
	public List<Machine> findMachineListByPage(Page page) {
		String sql = "SELECT * FROM machine WHERE state=1 ORDER BY idx desc LIMIT ?,?";
		try {
			return t.query(sql, (rs, no)->{
				Machine m = new Machine();
				m.setIdx(rs.getInt(1));
				m.setMachine_name(rs.getString(2));
				m.setState(rs.getInt(3));
				m.setSign_up_date(rs.getTimestamp(4));
				return m;
			}, page.getFirstContent(), page.getLastContent());
		} catch (Exception e) {
			logger.debug(e.getMessage());
			return new ArrayList<Machine>();
		}
	}

	@Override
	public int updateMachine(Machine machine) {
		String sql = "UPDATE machine SET machine_name=?, sign_up_date=now() WHERE idx=?";
		try {
			return t.update(sql, machine.getMachine_name(), machine.getIdx());
		} catch (Exception e) {
			logger.debug(e.getMessage());
			return 0;
		}
	}


	@Override
	public int completelyDeleteMachine(Machine machine) {
		String sql = "DELETE from machine where idx=?";
		try {
			return t.update(sql, machine.getIdx());
		} catch (Exception e) {
			logger.debug(e.getMessage());
			return 0;
		}
	}


	@Override
	public int showingDeleteMachine(Machine machine) {
		String sql = "UPDATE machine SET state=3 WHERE idx=?";
		try {
			return t.update(sql, machine.getIdx());
		} catch (Exception e) {
			return 0;
		}
	}


	@Override
	public int countAllMachine() {
		String sql = "SELECT COUNT(idx) FROM machine where state=1";
		return t.queryForObject(sql, Integer.TYPE);
	}


	@Override
	public List<Machine> findMachineList() {
		String sql = "SELECT * FROM machine WHERE state=1 ORDER BY idx desc";
		try {
			return t.query(sql, (rs, no)->{
				Machine m = new Machine();
				m.setIdx(rs.getInt(1));
				m.setMachine_name(rs.getString(2));
				m.setState(rs.getInt(3));
				m.setSign_up_date(rs.getTimestamp(4));
				return m;
			});
		} catch (Exception e) {
			return new ArrayList<Machine>();
		}
	}



}
