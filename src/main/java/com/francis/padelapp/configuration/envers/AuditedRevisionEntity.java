package com.francis.padelapp.configuration.envers;

        import lombok.Data;
        import org.hibernate.envers.DefaultRevisionEntity;
        import org.hibernate.envers.RevisionEntity;

        import javax.persistence.Entity;

@Data
@Entity
@RevisionEntity(AuditingRevisionListener.class)
public class AuditedRevisionEntity extends DefaultRevisionEntity {

    private static final long serialVersionUID = 1L;

    private String user;
}