package com.parcel.repository;

import java.util.List;

import com.parcel.entity.User_group;
import com.parcel.entity.Group_member;
import com.parcel.entity.User;

/**
 * 그룹관련 DB작업 인터페이스
 *
 */
public interface GroupRepository {
	
	public User_group findGroupByProductIdx(int pidx);
	public List<User> findUserListByProductIdx(int pidx);
	public User_group findGroupByCode(String code);
	public int insertGroup(User_group group);
	public int insertGroupMember(Group_member member);
	public boolean isValidPw(User_group group);
	public int deleteGroupMemberByGroup(int idx) throws Exception;
	public int deleteGroupByIdx(int idx) throws Exception;
	public int insertGroupMemberByCodeAndPw(String code, String pw, int joiner);
	public Group_member findGroupMemberByCodeAndMember(String code, int joiner);
	public List<User_group> findGroupListByUserIdx(int uidx);
	public int deleteGroupMemberByGroupAndUser(int gidx, int uidx);
	public int checkGroupMemberByGroupIdxAndUserId(int group_idx, String receiver_id);
	public List<User_group> findGroupListByManager(int manager);
	public User_group findGroupByIdx(int group_idx);
	
	
}
