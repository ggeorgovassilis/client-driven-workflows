package ggeorgovassilis.cdw.services.hr;

import ggeorgovassilis.cdw.signature.SignedDocument;

public class PersonnelIdentifier extends SignedDocument {

	protected String name;
	protected String personnelId;
	protected String email;
	protected String employmentStatus;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPersonnelId() {
		return personnelId;
	}

	public void setPersonnelId(String personnelId) {
		this.personnelId = personnelId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmploymentStatus() {
		return employmentStatus;
	}

	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}
}
