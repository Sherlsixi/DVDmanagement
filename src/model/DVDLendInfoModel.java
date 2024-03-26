package model;

public class DVDLendInfoModel {
	private int id;
	private int dvdID;
	private String lendDate;
	private String returnDate;
	private int price;
	private int cost;
	private String createTime;
	private String lastUpdateTime;
	
	public int getDvdID() {
		return dvdID;
	}
	public void setDvdID(int dvdID) {
		this.dvdID = dvdID;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public String getLendDate() {
		return lendDate;
	}
	public void setLendDate(String lendDate) {
		this.lendDate = lendDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
