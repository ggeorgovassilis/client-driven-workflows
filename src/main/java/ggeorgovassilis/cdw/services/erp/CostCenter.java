/*
   Copyright 2016 George Georgovassilis

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0
 */
package ggeorgovassilis.cdw.services.erp;

import ggeorgovassilis.cdw.signature.SignedDocument;

/**
 * Models a cost center on which employees can charge expenses
 * @author george georgovassilis
 *
 */
public class CostCenter extends SignedDocument {

	public CostCenter(){
	}
	
	public CostCenter(String id, String label){
		this.id = id;
		this.label = label;
	}

	protected String id;
	protected String label;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
