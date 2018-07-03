package com.fongwell.satchi.crm.core.customer.dto;

import com.fongwell.satchi.crm.core.customer.domain.value.Sex;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by docker on 3/19/18.
 */
public class CustomerRegisterRequest implements Serializable {


    @NotEmpty(message = "name.empty")
    private String name;
    @NotEmpty(message = "mobile.empty")
    private String mobile;
//
//    private String password;


    private Date dob;

    private Sex sex;

    public Sex getSex() {
        return sex;
    }

    public void setSex(final Sex sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(final String mobile) {
        this.mobile = mobile;
    }

//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(final String password) {
//        this.password = password;


//    }

    public Date getDob() {
        return dob;
    }

    public void setDob(final Date dob) {
        this.dob = dob;
    }


}
