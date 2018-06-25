package com.fongwell.satchi.crm.core.customer.domain.aggregate;

import com.fongwell.satchi.crm.core.support.ddd.AbstractAggregateRoot;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by docker on 5/17/18.
 */
@Entity
@Table(name = "crm_customer_tags")
public class CustomerTags extends AbstractAggregateRoot {


    @ElementCollection(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @CollectionTable(
            name = "crm_customer_tag",
            indexes = {@Index(name = "crm_customer_tag_idx", columnList = "customer_id")},
            joinColumns = @JoinColumn(name = "customer_id")
    )
    @Column(name = "tag")
    private Set<String> tags;

    public CustomerTags(final long customerId, final Set<String> tags) {
        this.id = customerId;
        this.tags = tags;
    }

    CustomerTags() {
    }

    public Collection<String> updateTags(Set<String> tags) {

        if (this.tags.isEmpty()) {
            this.tags = tags;
            return tags;
        }

        Set<String> newTags = new HashSet<>(((int) (tags.size() / 0.75f)) + 1);
        for (final String tag : tags) {
            if (!this.tags.contains(tag)) {
                newTags.add(tag);
            }

        }

        this.tags = tags;
        return newTags;
    }

    public Set<String> getTags() {
        return tags;
    }
}
