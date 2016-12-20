package com.parcel.repository;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.parcel.entity.Group_member;
import com.parcel.entity.User;
import com.parcel.entity.User_group;

/**
 * GroupRepository 구현 클래스
 *
 */
@Repository
public class GroupRepositoryImpl implements GroupRepository {

	private JdbcTemplate t;
	private final int FAIL = 0;
	
	@Autowired
	public GroupRepositoryImpl(DataSource dataSource) {
		t = new JdbcTemplate(dataSource);
	}
	
	@Override
	public User_group findGroupByProductIdx(int pidx) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM user_group ug WHERE ug.product=?";
		
		try {
			return t.queryForObject(sql, (rs,no)->{
				User_group group = new User_group();
				group.setIdx(rs.getInt("idx"));
				group.setGroup_name(rs.getString("group_name"));
				group.setManager(rs.getInt("manager"));
				group.setProduct(rs.getInt("product"));
				group.setPw(rs.getString("pw"));
				group.setCode(rs.getString("code"));
				return group;
			}, pidx);
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public List<User> findUserListByProductIdx(int pidx) {
		System.out.println(pidx);
		String sql ="SELECT u.name , u.idx "
				+ "FROM user u "
				+ "WHERE u.idx in (SELECT gm.member FROM group_member gm WHERE gm.group=(SELECT ug.idx FROM user_group ug WHERE ug.product=?))";
		
		try {
			return t.query(sql, (rs, no)->{
				User user = new User();
				user.setName(rs.getString("name"));
				user.setIdx(rs.getInt("idx"));
				return user;
			}, pidx);
			
		} catch(Exception e) {
			return null;
		}
		
		
	}

	@Override
	public User_group findGroupByCode(String code) {
		
		String sql = "SELECT * FROM user_group WHERE code=?";
		try {
			return t.queryForObject(sql, (rs,no)->{ //이거랑 똑같은 로우맵퍼 또생기면 그냥 클래스 만들어서 쓰자
				User_group group = new User_group();
				group.setIdx(rs.getInt("idx"));
				group.setGroup_name(rs.getString("group_name"));
				group.setManager(rs.getInt("manager"));
				group.setProduct(rs.getInt("product"));
				group.setPw(rs.getString("pw"));
				group.setCode(rs.getString("code"));
				return group;
			}, code);
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public int insertGroup(User_group group) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO user_group (group_name, manager, product, pw, code) values(?, ?, ?, ?, ?)";
		try {
			return t.update(sql, group.getGroup_name(), group.getManager(), group.getProduct(), group.getPw(), group.getCode());
		} catch (Exception e) {
			return FAIL;
		}
		
	}

	@Override
	public int insertGroupMember(Group_member member) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO `parcel`.`group_member` (`group`, `member`) VALUES (?, ?)";
		try {
			return t.update(sql, member.getGroup(), member.getMember());
		} catch (Exception e) {
			e.printStackTrace();
			return FAIL;
		}
		
		
	}

	@Override
	public boolean isValidPw(User_group group) {
		
		User_group temp;
		String sql = "SELECT idx FROM user_group WHERE idx=? AND pw=?";
		try {
			temp = t.queryForObject(sql, (rs,no)->{
				User_group g = new User_group();
				g.setIdx(rs.getInt(1));
				return g;
			}, group.getIdx(), group.getPw());
		} catch (Exception e) {
			temp = null;
		}
		if (temp != null) {
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public int deleteGroupMemberByGroup(int idx) throws Exception {
		String sql = "DELETE FROM parcel.group_member WHERE parcel.group_member.group=?";
		return t.update(sql, idx);
	}

	@Override
	public int deleteGroupByIdx(int idx) throws Exception {
		String sql = "DELETE FROM user_group WHERE idx=?";
		return t.update(sql, idx);
	}

	@Override
	public int insertGroupMemberByCodeAndPw(String code, String pw, int joiner) {
		String sql = "INSERT INTO group_member (group_member.group, member) SELECT ug.idx as 'group', ? as member FROM user_group ug WHERE code=? AND pw=? ";
		return t.update(sql, joiner, code, pw);
	}

	@Override
	public Group_member findGroupMemberByCodeAndMember(String code, int joiner) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM group_member gm WHERE gm.group = (SELECT ug.idx from user_group ug WHERE ug.code=?) AND gm.member = ?";
		try {
			return t.queryForObject(sql, (rs,no)->{
				Group_member gm = new Group_member();
				gm.setIdx(rs.getInt(1));
				gm.setGroup(rs.getInt(2));
				gm.setMember(rs.getInt(3));
				gm.setState(rs.getInt(4));
				return gm;
			}, code, joiner);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<User_group> findGroupListByUserIdx(int uidx) {
		// TODO Auto-generated method stub
		String sql = "SELECT ug.group_name "
						+ ", u.name as managerName "
						+ ", p.public_name as productName "
						+ ", (SELECT count(gm.idx) FROM group_member gm WHERE gm.group = ug.idx) as groupMemberCnt "
						+ ", p.idx as product "
						+ ", ug.idx "
						+ ", ug.manager " 
					+"FROM user_group ug, user u, product p "  
					+"WHERE ug.idx in (SELECT gm.group FROM group_member gm WHERE gm.member = ?) " 
						+ " AND u.idx=ug.manager" 
						+ " AND p.idx = ug.product";
		try {
			return t.query(sql, (rs,no)->{
				User_group g = new User_group();
				g.setGroup_name(rs.getString(1));
				g.setManagerName(rs.getString(2));
				g.setProductName(rs.getString(3));
				g.setGroupMemberCnt(rs.getInt(4));
				g.setProduct(rs.getInt(5));
				g.setIdx(rs.getInt(6));
				g.setManager(rs.getInt(7));
				return g;
			}, uidx);
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			return null;
		}
	}

	@Override
	public int deleteGroupMemberByGroupAndUser(int gidx, int uidx) {
		String sql ="DELETE FROM group_member WHERE group_member.group=? AND group_member.member=?";
		
		return t.update(sql, gidx, uidx);
	}

	@Override
	public int checkGroupMemberByGroupIdxAndUserId(int group_idx, String receiver_id) {
		// TODO Auto-generated method stub
		String sql ="SELECT COUNT(idx) FROM group_member gm WHERE gm.group=? AND gm.member = (SELECT idx FROM user WHERE id=?);";
		return t.queryForObject(sql, Integer.TYPE, group_idx, receiver_id);
		
	}

	@Override
	public List<User_group> findGroupListByManager(int manager) {
		String sql = "SELECT * From user_group WHERE manager=?";
		try {
			return t.query(sql, (rs, no)->{
				User_group ug = new User_group();
				ug.setIdx(rs.getInt(1));
				ug.setGroup_name(rs.getString(2));
				ug.setManager(rs.getInt(3));
				ug.setProduct(rs.getInt(4));
				ug.setState(rs.getInt(5));
				ug.setPw(rs.getString(6));
				ug.setCode(rs.getString(7));
				return ug;
			}, manager);
		} catch (Exception e) {
			return new ArrayList<User_group>(); 
		}
	}

	@Override
	public User_group findGroupByIdx(int group_idx) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM user_group WHERE idx=?";
		try {
			return t.queryForObject(sql, (rs, no)->{
				User_group ug = new User_group();
				ug.setIdx(rs.getInt(1));
				ug.setGroup_name(rs.getString(2));
				ug.setManager(rs.getInt(3));
				ug.setProduct(rs.getInt(4));
				ug.setState(rs.getInt(5));
				ug.setPw(rs.getString(6));
				ug.setCode(rs.getString(7));
				return ug;
			}, group_idx);
		} catch (Exception e) {
			return null;
		}
	}

}
