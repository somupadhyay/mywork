package com.ourshop.it.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Transactions {

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date transactionDate;

    @ManyToOne
    private Party party;

    @ManyToOne
    private Account account;

    @NotNull
    private String type;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<TransactionInventory> inventories = new HashSet<TransactionInventory>();

    private String description;

    @NotNull
    private Double totalAmount;

    private Double advanceAmount;

    private Double discountAmount;
    
    private Double dueAmount;
}
