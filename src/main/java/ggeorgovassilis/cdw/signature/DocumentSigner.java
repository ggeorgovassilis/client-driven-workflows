package ggeorgovassilis.cdw.signature;

import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import ggeorgovassilis.cdw.time.Clock;

@Component
public class DocumentSigner {

	@Autowired
	Clock clock;

	ObjectMapper mapper = new ObjectMapper();

	public void sign(SignedDocument document) {
		try {
			Date expiration = clock.getNowPlus(60);
			Signature signature = new Signature();
			signature.setHashcode(null);
			signature.setValidUntil(expiration);
			document.setSignature(signature);
			
			String json = mapper.writeValueAsString(document);
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] result = md5.digest(json.getBytes());
			String base64HashCode = Base64.getEncoder().encodeToString(result);
			document.getSignature().setHashcode(base64HashCode);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
