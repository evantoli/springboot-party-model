package au.com.softwarekitchen.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.Instant;

public abstract class AbstractModel<ID extends Serializable> implements Model<ID> {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected ID id;

    @CreatedDate
    protected Instant createdAt;

    @CreatedBy
    protected String createdBy;

    @LastModifiedDate
    protected Instant lastModified;

    @LastModifiedBy
    protected String lastModifiedBy;

    public AbstractModel() {
    }

    public AbstractModel(ID id ) {
        this.id = id;
    }

    @Override
    public ID getId() {
        return id;
    }

    public void setId(final ID id) {
        this.id = id;
    }

    @Override
    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public Instant getLastModified() {
        return lastModified;
    }

    public void setLastModified(final Instant lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(final String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
}
