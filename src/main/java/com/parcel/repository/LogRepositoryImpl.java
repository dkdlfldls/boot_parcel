package com.parcel.repository;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.parcel.entity.Log;
import com.parcel.util.Page;

/**
 * LogRepository 구현 클래스
 *
 */
@Repository
public class LogRepositoryImpl implements LogRepository {

	private Logger logger = LoggerFactory.getLogger(LogRepositoryImpl.class);
	private JdbcTemplate t;
	
	@Autowired
	public LogRepositoryImpl(DataSource dataSource) {
		t = new JdbcTemplate(dataSource);
	}
	
	@Override
	public int insertLog(Log log) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO log (user, user_group, group_member, machine, product, delivery_record, receive_record, message, log, detail) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
		try {
			return t.update(sql, log.getUser(), log.getUser_group(), log.getGroup_member(), log.getMachine()
					, log.getProduct(), log.getDelivery_record(), log.getReceive_record(), log.getMessage(), log.getLog(), log.getDetail());
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
			return 0;
		}
	}

	@Override
	public int findCountAllLog() {
		String sql = "SELECT COUNT(idx) FROM log";
		
		return t.queryForObject(sql, Integer.TYPE);
	}

	@Override
	public List<Log> findLogByPage(Page page) {
		String sql = "SELECT * FROM log ORDER BY idx desc LIMIT ?,?";
		try {
			return t.query(sql, (rs, no)->{
				Log log = new Log();
				log.setIdx(rs.getInt(1));
				log.setUser(rs.getInt(2));
				log.setUser_group(rs.getInt(3));
				log.setGroup_member(rs.getInt(4));
				log.setMachine(rs.getInt(5));
				log.setProduct(rs.getInt(6));
				log.setDelivery_record(rs.getInt(7));
				log.setReceive_record(rs.getInt(8));
				log.setMessage(rs.getInt(9));
				log.setLog(rs.getString(10));
				log.setState(rs.getInt(11));
				log.setTime(rs.getTimestamp(12));
				log.setDetail(rs.getInt(13));
				return log;
			}, page.getFirstContent(), page.getLastContent());
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Log>();
		}
	}

	@Override
	public int findCountLogBySearch(Page page) {
		String sql = "SELECT count(idx) FROM log WHERE log LIKE ?";
		return t.queryForObject(sql, Integer.TYPE, page.getkeywordForSqlLike());
	}

	@Override
	public List<Log> findLogByPageAndSearch(Page page) {
		String sql = "SELECT * FROM log WHERE log LIKE ? ORDER BY idx desc LIMIT ?,?";
		try {
			return t.query(sql, (rs, no)->{
				Log log = new Log();
				log.setIdx(rs.getInt(1));
				log.setUser(rs.getInt(2));
				log.setUser_group(rs.getInt(3));
				log.setGroup_member(rs.getInt(4));
				log.setMachine(rs.getInt(5));
				log.setProduct(rs.getInt(6));
				log.setDelivery_record(rs.getInt(7));
				log.setReceive_record(rs.getInt(8));
				log.setMessage(rs.getInt(9));
				log.setLog(rs.getString(10));
				log.setState(rs.getInt(11));
				log.setTime(rs.getTimestamp(12));
				log.setDetail(rs.getInt(13));
				return log;
			}, page.getkeywordForSqlLike(), page.getFirstContent(), page.getLastContent());
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Log>();
		}
	}

	@Override
	public int findCountLog(Page page) {
		String sql = "SELECT COUNT(idx) FROM log WHERE detail=?";
		
		return t.queryForObject(sql, Integer.TYPE, page.getCategory());
	}

	@Override
	public List<Log> findLogByPageAndCategory(Page page) {
		String sql = "SELECT * FROM log WHERE detail=? ORDER BY idx desc LIMIT ?,?";
		try {
			return t.query(sql, (rs, no)->{
				Log log = new Log();
				log.setIdx(rs.getInt(1));
				log.setUser(rs.getInt(2));
				log.setUser_group(rs.getInt(3));
				log.setGroup_member(rs.getInt(4));
				log.setMachine(rs.getInt(5));
				log.setProduct(rs.getInt(6));
				log.setDelivery_record(rs.getInt(7));
				log.setReceive_record(rs.getInt(8));
				log.setMessage(rs.getInt(9));
				log.setLog(rs.getString(10));
				log.setState(rs.getInt(11));
				log.setTime(rs.getTimestamp(12));
				log.setDetail(rs.getInt(13));
				return log;
			},page.getCategory(), page.getFirstContent(), page.getLastContent());
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Log>();
		}
	}

	@Override
	public int findCountLogBySearchAndCategory(Page page) {
		String sql = "SELECT count(idx) FROM log WHERE log LIKE ? AND detail=?";
		return t.queryForObject(sql, Integer.TYPE, page.getkeywordForSqlLike(), page.getCategory());
	}

	@Override
	public List<Log> findLogByPageAndSearchAndCategory(Page page) {
		String sql = "SELECT * FROM log WHERE log LIKE ? AND detail=? ORDER BY idx desc LIMIT ?,?";
		try {
			return t.query(sql, (rs, no)->{
				Log log = new Log();
				log.setIdx(rs.getInt(1));
				log.setUser(rs.getInt(2));
				log.setUser_group(rs.getInt(3));
				log.setGroup_member(rs.getInt(4));
				log.setMachine(rs.getInt(5));
				log.setProduct(rs.getInt(6));
				log.setDelivery_record(rs.getInt(7));
				log.setReceive_record(rs.getInt(8));
				log.setMessage(rs.getInt(9));
				log.setLog(rs.getString(10));
				log.setState(rs.getInt(11));
				log.setTime(rs.getTimestamp(12));
				log.setDetail(rs.getInt(13));
				return log;
			}, page.getkeywordForSqlLike(), page.getCategory(), page.getFirstContent(), page.getLastContent());
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Log>();
		}
	}

	

}
