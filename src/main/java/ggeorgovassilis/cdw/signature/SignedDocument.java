package ggeorgovassilis.cdw.signature;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder(alphabetic=true)
public abstract class SignedDocument implements Serializable {

	protected Signature signature;

	public Signature getSignature() {
		return signature;
	}

	public void setSignature(Signature signature) {
		this.signature = signature;
	}
}
