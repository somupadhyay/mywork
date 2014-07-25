package com.ourshop.it.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class TransactionInventory {


	@NotNull
    private Double quantity;

    @NotNull
    private Double rate;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Inventory> items = new HashSet<Inventory>();
}
