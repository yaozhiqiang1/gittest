package com.fongwell.satchi.crm.core.customer.domain.aggregate;

import com.fongwell.base.snowflake.Snowflake;
import com.fongwell.satchi.crm.core.customer.domain.value.Sex;
import com.fongwell.satchi.crm.core.customer.dto.CustomerData;
import com.fongwell.satchi.crm.core.support.ddd.AbstractAggregateRoot;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * Created by docker on 2/25/18.
 */
@Entity
@Table(name = "fw_customer", indexes = {
        @Index(name = "fw_customer_mobile_idx", columnList = "mobile", unique = true),
        @Index(name = "fw_customer_number_idx", columnList = "number", unique = true)})
@SQLDelete(sql = "update fw_customer set deleted = true, version = version + 1 where id = ? and version = ?")
public class Customer extends AbstractAggregateRoot {


    @Column(name = "number", length = 20)
    private String number;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "dob", columnDefinition = "date")
    private Date dob;

    @Column(name = "sex", length = 10)
    @Enumerated(EnumType.STRING)
    private Sex sex;

    private String note;

    @Column(name = "mobile", length = 20)
    private String mobile;

    private Long storeId;

    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @CollectionTable(
            name = "fw_customer_staff",
            indexes = {@Index(name = "fw_customer_staff_idx", columnList = "customer_id")},
            joinColumns = @JoinColumn(name = "customer_id")
    )
    @Column(name = "staff_id")
    private Collection<Long> staffIds;


    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @CollectionTable(
            name = "fw_customer_tag",
            indexes = {@Index(name = "fw_customer_tag_idx", columnList = "customer_id")},
            joinColumns = @JoinColumn(name = "customer_id")
    )
    @Column(name = "tag")
    private Set<String> tags;

    @Column(name = "headImageUrl", length = 500)
    private String headImageUrl;

    private String passwordHash;

    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @CollectionTable(
            name = "fw_customer_role_ref",
            indexes = {@Index(name = "fw_customer_role_ref_idx", columnList = "customer_id")},
            joinColumns = @JoinColumn(name = "customer_id")
    )
    @Column(name = "role_id")
    private Collection<Long> roleIds;

  /*  public Customer(final String number, final String name, final String mobile, final String passwordHash) {
        this.number = number;
        this.name = name;
        this.mobile = mobile;
        this.passwordHash = passwordHash;
    }
*/
  public Customer(final String number, final String name, final String mobile) {
      this.number = number;
      this.name = name;
      this.mobile = mobile;
  }


    public Customer(String number, CustomerData data) {

        this.number = number;
        this.id = Snowflake.id();
        this.createdDate = new Date();
        this.update(data);
    }

    Customer() {
    }

    public void update(CustomerData data) {
        this.name = data.getName();
        this.dob = data.getDob();
        this.note = data.getNote();
        this.sex = data.getSex();
        this.mobile = StringUtils.normalizeSpace(data.getMobile());
        this.storeId = data.getStoreId();

    }

    public void updateTags(Set<String> newTags) {
        this.tags = newTags;
    }

    public void setDob(final Date dob) {
        this.dob = dob;
    }

    public void setSex(final Sex sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
}
