package com.parcel.service;

import java.util.List;

import com.parcel.entity.Invitation;
import com.parcel.entity.User_group;

/**
 * 그룹 서비스 인터페이스
 * @author user
 *
 */
public interface GroupService {
	
	/**
	 * 제품 인덱스로 그룹 정보를 불러오는 작업
	 * @param pidx
	 * @return
	 */
	public User_group getGroupInfoForProductInfo(int pidx);
	
	/**
	 * 그룹코드를 생성하는 작업
	 * @return
	 */
	public String makeUniquenessOfGroupCode();
	
	/**
	 * 그룹을 추가하는 작업
	 * @param group 추가 될 그룹 정보
	 * @return
	 */
	public boolean addGroup(User_group group);
	
	/**
	 * 그룹을 삭제하는 작업
	 * @param group 삭제될 그룹 정보
	 * @return
	 */
	public int deleteGroup(User_group group);

	/**
	 * 그룹에 가입하는 작업
	 * @param code 가입하려는 그룹의 코드
	 * @param pw 가입하려는 그룹의 비밀번호
	 * @param joiner 그룹에 가입하려는 사용자의 인덱스
	 * @return
	 */
	public boolean joinGroup(String code, String pw, int joiner) ;

	/**
	 * 사용자의 인덱스로 그 사용자의 그룹 리스트를 불러오는 작업
	 * @param uidx
	 * @return
	 */
	public List<User_group> getGroupList(int uidx);

	/**
	 * 사용자가 그룹에서 탈퇴하는 작업
	 * @param gidx 탈퇴하려 하는 그룹의 인덱스
	 * @param uidx 탈퇴하려는 사용자의 인덱스
	 * @return
	 */
	public boolean dropGroup(int gidx, int uidx);

	/**
	 * 그룹에 초대하는 작업
	 * @param invitation 초대방법 및 초대받은 사람의 정보
	 * @return
	 */
	public boolean inviteUserById(Invitation invitation);

	/**
	 * 초대한 사람이 보게될 초대한 리스트
	 * @param userIdx
	 * @return
	 */
	public List<Invitation> getInviteListByUserIdxForSender(int userIdx);

	/**
	 * 초대받은 사람이 보게될 초대받은 리스트
	 * @param userIdx
	 * @return
	 */
	public List<Invitation> getInviteListByUserIdxForReceiver(int userIdx);

	/**
	 * 초대 취소 작업
	 * @param ownerIdx 그룹 등록한 사용자의 인덱스
	 * @param deleteInvitationIdx 취소하려는 초대 인덱스
	 * @return
	 */
	public boolean cancleInvitation(int ownerIdx, int deleteInvitationIdx);

	/**
	 * 그룹 초대 수락 및 거절 작업
	 * @param invitation
	 * @return
	 */
	public boolean permitInvitation(Invitation invitation);

	/**
	 * 그룹 등록자를 가지고 그룹리스트를 불러오는 작업
	 * @param manager
	 * @return
	 */
	public List<User_group> getGroupListByManager(int manager);

	public boolean inviteUserByEmail(Invitation invitation);
}
