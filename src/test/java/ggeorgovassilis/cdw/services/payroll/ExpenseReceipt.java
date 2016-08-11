/*
   Copyright 2016 George Georgovassilis

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0
 */
package ggeorgovassilis.cdw.services.payroll;

import java.util.Date;

import ggeorgovassilis.cdw.services.erp.CostCenter;
import ggeorgovassilis.cdw.services.hr.PersonnelIdentifier;
import ggeorgovassilis.cdw.signature.SignedDocument;

/**
 * Models the receipt for a (successfully) processed expense claim
 * @author george georgovassilis
 *
 */

public class ExpenseReceipt extends SignedDocument {

	/**
	 * Employee to him the receipt is issued
	 */
	protected PersonnelIdentifier pid;
	
	/**
	 * Cost center which will be charged
	 */
	protected CostCenter costCenter;
	
	/**
	 * Date on which the claim will be reimbursed
	 */
	protected Date paydate;
	
	/**
	 * Amount (eg cents) of expense
	 */
	protected Long amount;

	public PersonnelIdentifier getPid() {
		return pid;
	}

	public void setPid(PersonnelIdentifier pid) {
		this.pid = pid;
	}

	public CostCenter getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(CostCenter costCenter) {
		this.costCenter = costCenter;
	}

	public Date getPaydate() {
		return paydate;
	}

	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}
}
