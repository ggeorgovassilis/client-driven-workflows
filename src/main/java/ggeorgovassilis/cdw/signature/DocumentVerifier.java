package ggeorgovassilis.cdw.signature;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import ggeorgovassilis.cdw.time.Clock;

@Component
public class DocumentVerifier {

	@Autowired
	Clock clock;

	ObjectMapper mapper = new ObjectMapper();

	public boolean verify(SignedDocument document) {

		try {
			Signature signature = document.getSignature();
			if (signature == null)
				return false;
			String claimedHashCode = signature.getHashcode();
			signature.setHashcode(null);

			String json = mapper.writeValueAsString(document);
			signature.setHashcode(claimedHashCode);
			
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] result = md5.digest(json.getBytes());
			String realHashCode = Base64.getEncoder().encodeToString(result);
			if (!realHashCode.equals(claimedHashCode))
				return false;
			Date now = clock.getNow();
			if (now.after(signature.getValidUntil()))
				return false;
			return true;
		} catch (Exception e) {
			throw new VerificationException(e.getMessage());
		}

	}
}
