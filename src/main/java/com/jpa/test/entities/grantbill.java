package com.jpa.test.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class grantbill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	
	private int billid;
	
	private String custname;
	
	private double granttotal;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getBillid() {
		return billid;
	}

	public void setBillid(int billid) {
		this.billid = billid;
	}

	public String getCustname() {
		return custname;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public double getGranttotal() {
		return granttotal;
	}

	public void setGranttotal(double granttotal) {
		this.granttotal = granttotal;
	}

	@Override
	public String toString() {
		return "grantbill [ID=" + ID + ", billid=" + billid + ", custname=" + custname + ", granttotal=" + granttotal
				+ "]";
	}

	public grantbill() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
