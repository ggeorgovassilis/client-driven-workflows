/*
   Copyright 2016 George Georgovassilis

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0
 */
package ggeorgovassilis.cdw.services.payroll;

import ggeorgovassilis.cdw.services.erp.CostCenter;
import ggeorgovassilis.cdw.services.hr.PersonnelIdentifier;

/**
 * Models an expense claim
 * @author george georgovassilis
 *
 */

public class ExpenseClaim {

	/**
	 * Employee who is submitting the claim
	 */
	protected PersonnelIdentifier pid;
	
	/**
	 * Cost center on which the claim should be charged
	 */
	protected CostCenter costCenter;
	
	/**
	 * Actual expense incurred (eg cents)
	 */
	protected Long amount;

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

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

}
