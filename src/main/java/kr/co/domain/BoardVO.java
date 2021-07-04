package kr.co.domain;

import java.io.Serializable;

public class BoardVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int bno;
	private String writer;
	private String title;
	private String content;
	private int readCnt;
	private String regDate;
	private String updateDate;
	private String[] files;

	

	public BoardVO() {

	}

	public BoardVO(int bno, String writer, String title, String content, int readCnt, String regDate, String updateDate,
			String[] files) {
		super();
		this.bno = bno;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.readCnt = readCnt;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.files = files;
	}
	
	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
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
	
	public String[] getFiles() {
		return files;
	}

	public void setFiles(String[] files) {
		this.files = files;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bno;
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
		BoardVO other = (BoardVO) obj;
		if (bno != other.bno)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", writer=" + writer + ", title=" + title + ", content=" + content + ", readCnt="
				+ readCnt + ", regDate=" + regDate + ", updateDate=" + updateDate + "]";
	}

}
