package moneyBook;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Money {
	Money(){}
	private SimpleStringProperty whendate;
	private SimpleStringProperty describe;
	private SimpleIntegerProperty used;
	private SimpleStringProperty pay;
	public Money(String whendate, 
			String describe, int used,
			String pay) {
		super();
		this.whendate = new SimpleStringProperty(whendate);
		this.describe = new SimpleStringProperty(describe);
		this.used = new SimpleIntegerProperty(used);
		this.pay = new SimpleStringProperty(pay);
	}
	public String getWhendate() {
		return this.whendate.get();
	}
	public void setWhendate(String whendate) {
		this.whendate.set(whendate);
	}
	public String getDescribe() {
		return this.describe.get();
	}
	public void setDescribe(String describe) {
		this.describe.set(describe);
	}
	public int getUsed() {
		return this.used.get();
	}
	public void setUsed(int used) {
		this.used.set(used);
	}
	public String getPay() {
		return this.pay.get();
	}
	public void setPay(String pay) {
		this.pay.set(pay);
	}
	
	
	
}
