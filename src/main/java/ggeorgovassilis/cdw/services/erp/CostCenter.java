package ggeorgovassilis.cdw.services.erp;

import ggeorgovassilis.cdw.signature.SignedDocument;

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
