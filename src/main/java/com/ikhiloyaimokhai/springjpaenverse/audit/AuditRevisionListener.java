package com.ikhiloyaimokhai.springjpaenverse.audit;

import org.hibernate.envers.RevisionListener;

public class AuditRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        AuditRevisionEntity auditRevisionEntity = (AuditRevisionEntity) revisionEntity;

        auditRevisionEntity.setUsername("Ikhiloya");
        auditRevisionEntity.setIpAddress("10.223.15.11");
    }

}