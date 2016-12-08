package com.parcel.repository;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.parcel.entity.Invitation;

@Repository
public class InvitationRepositoryImpl implements InvitationRepository {

private JdbcTemplate t; 
	
	@Autowired
	public InvitationRepositoryImpl(DataSource dataSource) {
		t = new JdbcTemplate(dataSource);
	}
	
	@Override
	public int insertInviteByReceiverId(Invitation invitation) {
		String sql = "INSERT INTO group_invitation(group_idx, sender, receiver, type, receiver_id) "
				+ "VALUES(?, ?, (SELECT idx as receiver FROM user WHERE id=?), ?, ?)";
		// TODO Auto-generated method stub
		
		return t.update(sql, invitation.getGroup_idx(), invitation.getSender(), invitation.getReceiver_id(), invitation.getType(), invitation.getReceiver_id());
	}

	@Override
	public List<Invitation> findInviteListByUserIdxForSender(int userIdx) {
		String sql = "SELECT * FROM group_invitation WHERE sender=? AND state=1";
		try {
			return t.query(sql, (rs, no)->{
				Invitation i = new Invitation();
				i.setIdx(rs.getInt(1));
				i.setGroup_idx(rs.getInt(2));
				i.setReceiver(rs.getInt(3));
				i.setSender(rs.getInt(4));
				i.setTime(rs.getTimestamp(5));
				i.setState(rs.getInt(6));
				i.setType(rs.getString(7));
				i.setReceiver_id(rs.getString(8));
				return i;
			}, userIdx);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Invitation>();
		}
		
	}

	@Override
	public Invitation findInviteByGroup_idAndReceiver_idAndState(Invitation invitation) {
		String sql ="SELECT idx FROM group_invitation WHERE group_idx=? AND receiver_id=? AND state=1";
			
		try {
			return t.queryForObject(sql, (rs, no)->{
				Invitation i = new Invitation();
				i.setIdx(rs.getInt(1));
				return i;
			}, invitation.getGroup_idx(), invitation.getReceiver_id());
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public List<Invitation> findInviteListByUserIdxForReceiver(int userIdx) {
		String sql = "SELECT gi.*, u.id, ug.group_name "
				+ "FROM group_invitation gi, user u, user_group ug "
				+ "WHERE gi.receiver=? AND gi.state=1 AND gi.sender=u.idx AND gi.group_idx=ug.idx AND gi.type='id'";
		try {
			return t.query(sql, (rs, no)->{
				Invitation i = new Invitation();
				i.setIdx(rs.getInt(1));
				i.setGroup_idx(rs.getInt(2));
				i.setReceiver(rs.getInt(3));
				i.setSender(rs.getInt(4));
				i.setTime(rs.getTimestamp(5));
				i.setState(rs.getInt(6));
				i.setType(rs.getString(7));
				i.setReceiver_id(rs.getString(8));
				i.setSender_id(rs.getString(9));
				i.setGroup_name(rs.getString(10));
				return i;
			}, userIdx);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Invitation>();
		}
	}

	@Override
	public boolean checkInviteBySenderAndIdx(int ownerIdx, int deleteInvitationIdx) {
		// TODO Auto-generated method stub
		String sql = "SELECT COUNT(idx) FROM group_invitation WHERE sender=? AND idx=? AND state=1";
		if (t.queryForObject(sql, Integer.TYPE,  ownerIdx, deleteInvitationIdx) > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int updateInviteForCancle(int deleteInvitationIdx) {
		// TODO Auto-generated method stub
		String sql = "UPDATE group_invitation SET state=3 WHERE idx=?";
		return t.update(sql, deleteInvitationIdx);
	}



}
