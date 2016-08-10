package ggeorgovassilis.cdw.services.payroll;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ggeorgovassilis.cdw.time.Clock;

@RestController
@RequestMapping(value = "/api/payroll")
public class PayrollService {

	@Autowired
	Clock clock;
	
	@RequestMapping(method=RequestMethod.POST, value="/expenseclaim")
	public ExpenseReceipt submitExpenseClaim(@RequestBody ExpenseClaim claim){
		if (claim.getAmount()<1 || claim.getAmount()>10000)
			throw new RuntimeException("Illegal amount range");
		ExpenseReceipt receipt = new ExpenseReceipt();
		receipt.setAmount(claim.getAmount());
		receipt.setCostCenter(claim.getCostCenter());
		receipt.setPid(claim.getPid());
		
		Calendar c = new GregorianCalendar();
		c.setTime(clock.getNow());
		c.set(Calendar.DATE, 1);
		c.add(Calendar.MONTH, 1);
		receipt.setPaydate(c.getTime());
		return receipt;
	}
}
