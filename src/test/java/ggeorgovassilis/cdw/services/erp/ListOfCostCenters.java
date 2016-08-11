/*
   Copyright 2016 George Georgovassilis

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0
 */
package ggeorgovassilis.cdw.services.erp;

import java.util.ArrayList;
import java.util.List;

import ggeorgovassilis.cdw.signature.SignedDocument;

/**
 * Models a list of cost centers
 * @author george georgovassilis
 *
 */

public class ListOfCostCenters extends SignedDocument{

	protected List<CostCenter> costCenters = new ArrayList<CostCenter>();

	public List<CostCenter> getCostCenters() {
		return costCenters;
	}

	public void setCostCenters(List<CostCenter> costCenters) {
		this.costCenters = costCenters;
	}
}
