// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ourshop.it.domain;

import com.ourshop.it.domain.TransactionInventory;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect TransactionInventory_Roo_Jpa_Entity {
    
    declare @type: TransactionInventory: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long TransactionInventory.id;
    
    @Version
    @Column(name = "version")
    private Integer TransactionInventory.version;
    
    public Long TransactionInventory.getId() {
        return this.id;
    }
    
    public void TransactionInventory.setId(Long id) {
        this.id = id;
    }
    
    public Integer TransactionInventory.getVersion() {
        return this.version;
    }
    
    public void TransactionInventory.setVersion(Integer version) {
        this.version = version;
    }
    
}
