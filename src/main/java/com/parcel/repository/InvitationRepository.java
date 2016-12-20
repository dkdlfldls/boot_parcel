package com.parcel.repository;

import java.util.List;

import com.parcel.entity.Invitation;

/**
 * 초대관련 DB작업 인터페이스
 *
 */
public interface InvitationRepository {
	
	
	
	public int insertInviteByReceiverId(Invitation invitation);

	public List<Invitation> findInviteListByUserIdxForSender(int userIdx);

	public Invitation findInviteByGroup_idAndReceiver_idAndState(Invitation invitation);

	public List<Invitation> findInviteListByUserIdxForReceiver(int userIdx);

	public boolean checkInviteBySenderAndIdx(int ownerIdx, int deleteInvitationIdx);

	public int updateInviteForCancle(int deleteInvitationIdx);
}
