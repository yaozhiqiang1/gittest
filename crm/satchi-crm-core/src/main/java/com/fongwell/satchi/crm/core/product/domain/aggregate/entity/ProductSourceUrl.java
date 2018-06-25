package com.fongwell.satchi.crm.core.product.domain.aggregate.entity;


import javax.persistence.*;

/**
 * Created by roman on 18-4-4.
 */
@Entity
@Table(name = "crm_product_source")
public class ProductSourceUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String sourceUrl;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    @Override
    public int hashCode() {
        return id == null?0:id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(!(obj instanceof ProductSourceUrl)){
            return false;
        }
        ProductSourceUrl that = (ProductSourceUrl) obj;
        if(that.id != null || !(hashCode() == that.hashCode())){
            return true;
        }
        return false;
    }
}
