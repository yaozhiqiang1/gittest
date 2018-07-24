package com.fongwell.satchi.crm.core.credit.domain.entity;

import com.fongwell.satchi.crm.core.credit.domain.value.CreditSource;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by docker on 5/7/18.
 */
@Entity
@Table(name = "crm_credit_source_configuration")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", length = 10)
public class CreditSourceConfiguration {

    @Id
    @GenericGenerator(name = "snowflake", strategy = "com.fongwell.satchi.crm.core.support.jpa.hibernate.SnowflakeIdGenerator")
    @GeneratedValue(generator = "snowflake")
    private Long id;

    @Column(name = "parent_id")

    private long parentId;

    @Column(name = "type", updatable = false, insertable = false)
    private String type;

    private Integer credit;

    private Boolean enabled;

    protected CreditSourceConfiguration(long parentId, CreditSource type) {
        this.type = type.getType();
        this.parentId = parentId;

    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(final Integer credit) {
        this.credit = credit;
    }

    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }

    public CreditSourceConfiguration() {
    }

    public String getType() {
        return type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getEnabled() {
        return enabled;
    }
}
