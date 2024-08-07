package com.flipkart.bean;

public class NetBanking {
	
    private String modeOfTransfer;
    private String transactionID;
    
    
    
    public NetBanking(String modeOfTransfer, String transactionID) {
		
		this.modeOfTransfer = modeOfTransfer;
		this.transactionID = transactionID;
	}
    
	public String getModeOfTransfer() {
		return modeOfTransfer;
	}
	public void setModeOfTransfer(String modeOfTransfer) {
		this.modeOfTransfer = modeOfTransfer;
	}
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
    
}
