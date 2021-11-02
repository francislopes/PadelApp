package com.francis.padelapp.configuration.envers;

        import org.hibernate.envers.RevisionListener;
        import org.springframework.security.core.context.SecurityContextHolder;

public class AuditingRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {

        AuditedRevisionEntity auditedRevisionEntity = (AuditedRevisionEntity) revisionEntity;

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        auditedRevisionEntity.setUser(userName);
    }
}
