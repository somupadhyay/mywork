// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ourshop.it.domain;

import com.ourshop.it.domain.Inventory;

privileged aspect Inventory_Roo_JavaBean {
    
    public String Inventory.getItemName() {
        return this.itemName;
    }
    
    public void Inventory.setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    public String Inventory.getItemDescription() {
        return this.itemDescription;
    }
    
    public void Inventory.setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
    
    public Double Inventory.getItemCost() {
        return this.itemCost;
    }
    
    public void Inventory.setItemCost(Double itemCost) {
        this.itemCost = itemCost;
    }
    
}
