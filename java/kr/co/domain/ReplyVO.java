package kr.co.domain;

import java.io.Serializable;

public class ReplyVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int rno;
	private int bno;
	private String replyer;
	private String replyText;
	private String regDate;
	private String updateDate;

	public ReplyVO() {

	}

	public ReplyVO(int rno, int bno, String replyer, String replyText, String regDate, String updateDate) {
		super();
		this.rno = rno;
		this.bno = bno;
		this.replyer = replyer;
		this.replyText = replyText;
		this.regDate = regDate;
		this.updateDate = updateDate;
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getReplyer() {
		return replyer;
	}

	public void setReplyer(String replyer) {
		this.replyer = replyer;
	}

	public String getReplyText() {
		return replyText;
	}

	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rno;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReplyVO other = (ReplyVO) obj;
		if (rno != other.rno)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReplyVO [rno=" + rno + ", bno=" + bno + ", replyer=" + replyer + ", replyText=" + replyText
				+ ", regDate=" + regDate + ", updateDate=" + updateDate + "]";
	}

}
