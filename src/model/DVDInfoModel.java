package model;

import java.util.Date;

import utils.State;

public class DVDInfoModel {
	private int id;
	private State state;
	private String name;
	private int count;
	private String deleteFlag;
	private Date createTime;
	private Date lastUpdateTime;
	private Date lastLendDate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public Date getLastLendDate() {
		return lastLendDate;
	}
	public void setLastLendDate(Date lastLendDate) {
		this.lastLendDate = lastLendDate;
	}
	
	

}
