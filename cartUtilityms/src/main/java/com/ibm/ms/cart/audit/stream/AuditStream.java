package  com.ibm.ms.cart.audit.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface AuditStream {

	final String OUTPUT = "webaudit-out";
	
	@Output(OUTPUT)
	SubscribableChannel outboundAudit();
}
