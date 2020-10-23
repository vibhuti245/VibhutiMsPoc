package com.ibm.ms.audit.event;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AuditEvent {

	@Id
	private String id;
	@Column(length = 1024)
	private String payload;
	private Date timestamp;

	

	public AuditEvent(String id, String payload, Date timestamp) {
		super();
		this.id = id;
		this.payload = payload;
		this.timestamp = timestamp;
	}

	public AuditEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	//@Column(name="payload", length=1024)
	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
