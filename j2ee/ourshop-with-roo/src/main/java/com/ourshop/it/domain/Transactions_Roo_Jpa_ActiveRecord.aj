// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ourshop.it.domain;

import com.ourshop.it.domain.Transactions;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Transactions_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Transactions.entityManager;
    
    public static final EntityManager Transactions.entityManager() {
        EntityManager em = new Transactions().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Transactions.countTransactionses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Transactions o", Long.class).getSingleResult();
    }
    
    public static List<Transactions> Transactions.findAllTransactionses() {
        return entityManager().createQuery("SELECT o FROM Transactions o", Transactions.class).getResultList();
    }
    
    public static Transactions Transactions.findTransactions(Long id) {
        if (id == null) return null;
        return entityManager().find(Transactions.class, id);
    }
    
    public static List<Transactions> Transactions.findTransactionsEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Transactions o", Transactions.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Transactions.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Transactions.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Transactions attached = Transactions.findTransactions(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Transactions.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Transactions.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Transactions Transactions.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Transactions merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}