package com.parcel.util;

/**
 * 페이징 정보를 만드는 페이징 클래스
 * @author user
 *
 */
public class Page {
	
	private int currentPage;
	
	private int firstPagination;
	private int lastPagination;
	private int maxPageination;
	private int paginationSize;
	
	private int firstContent;//0번쨰 부터 시작한다.
	private int lastContent;
	private int shownContentListSize;
	private int contentListSize; //db를 통해서 가져와야 한다.
	
	private String keyword;
	
	private int category;
	private String strCategory;
	
	public Page() {
		this.currentPage = 1;
		this.paginationSize = 5;
		this.shownContentListSize = 5;
	}

	//페이지 정보 set
	public void setPageInfo(int contentListSize) {
		this.contentListSize = contentListSize;
		this.maxPageination = (int)(Math.ceil((double)contentListSize / (double)shownContentListSize));
		this.lastPagination = (int)(Math.ceil((double)currentPage / (double)paginationSize)) * paginationSize;
		this.firstPagination = lastPagination - paginationSize + 1;
		
		if (lastPagination > maxPageination) {
			lastPagination = maxPageination;
		}
		this.lastContent = currentPage * shownContentListSize - 1; //0번쨰부터 시작
		this.firstContent = lastContent - shownContentListSize + 1; //
	}
	
	public String getkeywordForSqlLike() {
		if (keyword == null) {
			return "%%";
		} else {
			return "%" + keyword + "%";
		}
	}
	
	
	//getter, setter
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getFirstPagination() {
		return firstPagination;
	}

	public void setFirstPagination(int firstPagination) {
		this.firstPagination = firstPagination;
	}

	public int getLastPagination() {
		return lastPagination;
	}

	public void setLastPagination(int lastPagination) {
		this.lastPagination = lastPagination;
	}

	public int getPaginationSize() {
		return paginationSize;
	}

	public void setPaginationSize(int paginationSize) {
		this.paginationSize = paginationSize;
	}

	public int getFirstContent() {
		return firstContent;
	}

	public void setFirstContent(int firstContent) {
		this.firstContent = firstContent;
	}

	public int getLastContent() {
		return lastContent;
	}

	public void setLastContent(int lastContent) {
		this.lastContent = lastContent;
	}

	public int getShownContentListSize() {
		return shownContentListSize;
	}

	public void setShownContentListSize(int shownContentListSize) {
		this.shownContentListSize = shownContentListSize;
	}

	public int getContentListSize() {
		return contentListSize;
	}

	public void setContentListSize(int contentListSize) {
		this.contentListSize = contentListSize;
	}

	public int getMaxPageination() {
		return maxPageination;
	}

	public void setMaxPageination(int maxPageination) {
		this.maxPageination = maxPageination;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getStrCategory() {
		return strCategory;
	}

	public void setStrCategory(String strCategory) {
		this.strCategory = strCategory;
	}

	@Override
	public String toString() {
		return "Page [currentPage=" + currentPage + ", firstPagination=" + firstPagination + ", lastPagination="
				+ lastPagination + ", maxPageination=" + maxPageination + ", paginationSize=" + paginationSize
				+ ", firstContent=" + firstContent + ", lastContent=" + lastContent + ", shownContentListSize="
				+ shownContentListSize + ", contentListSize=" + contentListSize + ", keyword=" + keyword + ", category="
				+ category + ", strCategory=" + strCategory + "]";
	}

	

	
	
	
	
}
