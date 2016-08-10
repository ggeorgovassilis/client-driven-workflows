package ggeorgovassilis.cdw.services.payroll;

import ggeorgovassilis.cdw.services.erp.CostCenter;
import ggeorgovassilis.cdw.services.hr.PersonnelIdentifier;

public class ExpenseClaim {

	protected PersonnelIdentifier pid;
	protected CostCenter costCenter;
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
