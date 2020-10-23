package com.ibm.ms.audit.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.ms.audit.event.AuditEvent;

public interface AuditEventRepository extends JpaRepository<AuditEvent, String>{

}
