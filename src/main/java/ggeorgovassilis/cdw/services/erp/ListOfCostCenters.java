package ggeorgovassilis.cdw.services.erp;

import java.util.ArrayList;
import java.util.List;

import ggeorgovassilis.cdw.signature.SignedDocument;

public class ListOfCostCenters extends SignedDocument{

	protected List<CostCenter> costCenters = new ArrayList<CostCenter>();

	public List<CostCenter> getCostCenters() {
		return costCenters;
	}

	public void setCostCenters(List<CostCenter> costCenters) {
		this.costCenters = costCenters;
	}
}
