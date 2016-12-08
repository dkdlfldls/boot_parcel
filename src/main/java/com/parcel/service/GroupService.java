package com.parcel.service;

import java.util.List;

import com.parcel.entity.Invitation;
import com.parcel.entity.User_group;

public interface GroupService {
	public User_group getGroupInfoForProductInfo(int pidx);
	
	public String makeUniquenessOfGroupCode();

	public boolean addGroup(User_group group);

	public int deleteGroup(User_group group);

	public boolean joinGroup(String code, String pw, int joiner) ;

	public List<User_group> getGroupList(int uidx);

	public boolean dropGroup(int gidx, int uidx);

	public boolean inviteUserById(Invitation invitation);

	public List<Invitation> getInviteListByUserIdxForSender(int userIdx);

	public List<Invitation> getInviteListByUserIdxForReceiver(int userIdx);

	public boolean cancleInvitation(int ownerIdx, int deleteInvitationIdx);

	public boolean permitInvitation(Invitation invitation);

	public List<User_group> getGroupListByManager(int manager);

	public boolean inviteUserByEmail(Invitation invitation);
}
