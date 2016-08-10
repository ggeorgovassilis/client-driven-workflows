package ggeorgovassilis.cdw;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ggeorgovassilis.cdw.signature.DocumentSigner;
import ggeorgovassilis.cdw.signature.DocumentVerifier;
import ggeorgovassilis.cdw.signature.SignedDocument;
import ggeorgovassilis.cdw.signature.VerificationException;

@RestControllerAdvice
@Aspect
public class DocumentSigningAspect {

	@Autowired
	DocumentSigner documentSigner;
	@Autowired
	DocumentVerifier verifier;
	
	@Before("execution(* ggeorgovassilis.cdw.services.*.*.*(..))")
	public void anyServiceMethod(JoinPoint o) {
		for (Object arg:o.getArgs())
			if (arg instanceof SignedDocument){
				SignedDocument doc = (SignedDocument)arg;
				if (!verifier.verify(doc))
					throw new VerificationException("Document failed to verify");
			}
	}

	@AfterReturning(value = "@target(org.springframework.web.bind.annotation.RestController) && execution(* ggeorgovassilis.cdw.services.*.*.*(..))", returning = "document")
	public void signReturnValue(JoinPoint o, SignedDocument document) {
		if (document!=null){
			documentSigner.sign(document);
		}
	}
}
