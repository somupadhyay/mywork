package com.ourshop.it.domain;

import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Party {

    @NotNull
    private String partyName;

    private String govtRegistration;

    @NotNull
    private String address;

    @NotNull
    private String area;

    @NotNull
    private String contactPerson;

    @NotNull
    private String partyType;

    private String mobile;

    private String phone;

    private String email;

    private String alternateEmail;
}
