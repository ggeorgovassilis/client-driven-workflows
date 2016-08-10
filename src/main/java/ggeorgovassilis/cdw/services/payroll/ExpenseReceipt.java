package ggeorgovassilis.cdw.services.payroll;

import java.util.Date;

import ggeorgovassilis.cdw.services.erp.CostCenter;
import ggeorgovassilis.cdw.services.hr.PersonnelIdentifier;
import ggeorgovassilis.cdw.signature.SignedDocument;

public class ExpenseReceipt extends SignedDocument {

	protected PersonnelIdentifier pid;
	protected CostCenter costCenter;
	protected Date paydate;
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
