package com.qa.pojo;

public class ManualFees {
	public int LNTAcctNbr;
	public int LNTAcctName;
	public ManualFees(int lNTAcctNbr, int lNTAcctName) {
		super();
		LNTAcctNbr = lNTAcctNbr;
		LNTAcctName = lNTAcctName;
	}
	public int getLNTAcctNbr() {
		return LNTAcctNbr;
	}
	public void setLNTAcctNbr(int lNTAcctNbr) {
		LNTAcctNbr = lNTAcctNbr;
	}
	public int getLNTAcctName() {
		return LNTAcctName;
	}
	public void setLNTAcctName(int lNTAcctName) {
		LNTAcctName = lNTAcctName;
	}
	

}
