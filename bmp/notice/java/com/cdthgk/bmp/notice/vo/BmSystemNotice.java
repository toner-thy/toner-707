package com.cdthgk.bmp.notice.vo;

import java.util.Date;

import com.thgk.platform.core.BaseDomain;

public class BmSystemNotice extends BaseDomain{
	private static final long serialVersionUID = 1L;

	private String noticeId;
	private String noticeName;
	private String noticeContent;
	private String noticePublisher;
	private Date noticePublishDate;
	private Integer noticeStatus;
	/**
	 * @return the noticeId
	 */
	public String getNoticeId() {
		return noticeId;
	}
	/**
	 * @param noticeId the noticeId to set
	 */
	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}
	/**
	 * @return the noticeName
	 */
	public String getNoticeName() {
		return noticeName;
	}
	/**
	 * @param noticeName the noticeName to set
	 */
	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName;
	}
	/**
	 * @return the noticeContent
	 */
	public String getNoticeContent() {
		return noticeContent;
	}
	/**
	 * @param noticeContent the noticeContent to set
	 */
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	/**
	 * @return the noticePublisher
	 */
	public String getNoticePublisher() {
		return noticePublisher;
	}
	/**
	 * @param noticePublisher the noticePublisher to set
	 */
	public void setNoticePublisher(String noticePublisher) {
		this.noticePublisher = noticePublisher;
	}
	/**
	 * @return the noticePublishDate
	 */
	public Date getNoticePublishDate() {
		return noticePublishDate;
	}
	/**
	 * @param noticePublishDate the noticePublishDate to set
	 */
	public void setNoticePublishDate(Date noticePublishDate) {
		this.noticePublishDate = noticePublishDate;
	}
	/**
	 * @return the noticeStatus
	 */
	public Integer getNoticeStatus() {
		return noticeStatus;
	}
	/**
	 * @param noticeStatus the noticeStatus to set
	 */
	public void setNoticeStatus(Integer noticeStatus) {
		this.noticeStatus = noticeStatus;
	}

}
