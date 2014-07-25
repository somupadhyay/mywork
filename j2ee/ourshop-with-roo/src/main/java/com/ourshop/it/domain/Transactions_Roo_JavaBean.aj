// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ourshop.it.domain;

import com.ourshop.it.domain.Account;
import com.ourshop.it.domain.Party;
import com.ourshop.it.domain.TransactionInventory;
import com.ourshop.it.domain.Transactions;
import java.util.Date;
import java.util.Set;

privileged aspect Transactions_Roo_JavaBean {
    
    public Date Transactions.getTransactionDate() {
        return this.transactionDate;
    }
    
    public void Transactions.setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
    
    public Party Transactions.getParty() {
        return this.party;
    }
    
    public void Transactions.setParty(Party party) {
        this.party = party;
    }
    
    public Account Transactions.getAccount() {
        return this.account;
    }
    
    public void Transactions.setAccount(Account account) {
        this.account = account;
    }
    
    public String Transactions.getType() {
        return this.type;
    }
    
    public void Transactions.setType(String type) {
        this.type = type;
    }
    
    public Set<TransactionInventory> Transactions.getInventories() {
        return this.inventories;
    }
    
    public void Transactions.setInventories(Set<TransactionInventory> inventories) {
        this.inventories = inventories;
    }
    
    public String Transactions.getDescription() {
        return this.description;
    }
    
    public void Transactions.setDescription(String description) {
        this.description = description;
    }
    
    public Double Transactions.getTotalAmount() {
        return this.totalAmount;
    }
    
    public void Transactions.setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public Double Transactions.getAdvanceAmount() {
        return this.advanceAmount;
    }
    
    public void Transactions.setAdvanceAmount(Double advanceAmount) {
        this.advanceAmount = advanceAmount;
    }
    
    public Double Transactions.getDiscountAmount() {
        return this.discountAmount;
    }
    
    public void Transactions.setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }
    
    public Double Transactions.getDueAmount() {
        return this.dueAmount;
    }
    
    public void Transactions.setDueAmount(Double dueAmount) {
        this.dueAmount = dueAmount;
    }
    
}