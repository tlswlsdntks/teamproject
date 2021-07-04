package kr.co.domain;

import java.util.List;

public class PageTO<T> {

	private int curPage = 1;
	private int perPage = 10;
	private int perLine = 10;
	private int amount;
	private int totalPage;
	private int startNum;
	private int endNum;
	private int beginPageNum;
	private int finishedPageNum;
	private List<T> list;

	public PageTO() {
		executeChangeValue();

	}

	public PageTO(int curPage) {
		this.curPage = curPage;
		executeChangeValue();
	}

	public PageTO(int curPage, int perPage, int perLine, int amount, int totalPage, int startNum, int endNum,
			int beginPageNum, int finishedPageNum, List<T> list) {
		super();
		this.curPage = curPage;
		this.perPage = perPage;
		this.perLine = perLine;
		this.amount = amount;
		this.totalPage = totalPage;
		this.startNum = startNum;
		this.endNum = endNum;
		this.beginPageNum = beginPageNum;
		this.finishedPageNum = finishedPageNum;
		this.list = list;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
		executeChangeValue();
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
		executeChangeValue();
	}

	public int getPerLine() {
		return perLine;
	}

	public void setPerLine(int perLine) {
		this.perLine = perLine;
		executeChangeValue();
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
		executeChangeValue();
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getEndNum() {
		return endNum;
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}

	public int getBeginPageNum() {
		return beginPageNum;
	}

	public void setBeginPageNum(int beginPageNum) {
		this.beginPageNum = beginPageNum;
	}

	public int getFinishedPageNum() {
		return finishedPageNum;
	}

	public void setFinishedPageNum(int finishedPageNum) {
		this.finishedPageNum = finishedPageNum;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	private void executeChangeValue() {
		totalPage = (amount - 1) / perPage + 1;
		startNum = (curPage - 1) * perPage + 1;
		endNum = curPage * perPage;
		if (endNum > amount) {
			endNum = amount;
		}
		beginPageNum = ((curPage - 1) / perLine) * perLine + 1;
		finishedPageNum = beginPageNum + perLine - 1;
		if (finishedPageNum > totalPage) {
			finishedPageNum = totalPage;
		}
	}

}
