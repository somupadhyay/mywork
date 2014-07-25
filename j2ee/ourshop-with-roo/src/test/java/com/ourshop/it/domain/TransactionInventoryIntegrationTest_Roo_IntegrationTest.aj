// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ourshop.it.domain;

import com.ourshop.it.domain.TransactionInventory;
import com.ourshop.it.domain.TransactionInventoryDataOnDemand;
import com.ourshop.it.domain.TransactionInventoryIntegrationTest;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect TransactionInventoryIntegrationTest_Roo_IntegrationTest {
    
    declare @type: TransactionInventoryIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: TransactionInventoryIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    declare @type: TransactionInventoryIntegrationTest: @Transactional;
    
    @Autowired
    TransactionInventoryDataOnDemand TransactionInventoryIntegrationTest.dod;
    
    @Test
    public void TransactionInventoryIntegrationTest.testCountTransactionInventorys() {
        Assert.assertNotNull("Data on demand for 'TransactionInventory' failed to initialize correctly", dod.getRandomTransactionInventory());
        long count = TransactionInventory.countTransactionInventorys();
        Assert.assertTrue("Counter for 'TransactionInventory' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void TransactionInventoryIntegrationTest.testFindTransactionInventory() {
        TransactionInventory obj = dod.getRandomTransactionInventory();
        Assert.assertNotNull("Data on demand for 'TransactionInventory' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'TransactionInventory' failed to provide an identifier", id);
        obj = TransactionInventory.findTransactionInventory(id);
        Assert.assertNotNull("Find method for 'TransactionInventory' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'TransactionInventory' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void TransactionInventoryIntegrationTest.testFindAllTransactionInventorys() {
        Assert.assertNotNull("Data on demand for 'TransactionInventory' failed to initialize correctly", dod.getRandomTransactionInventory());
        long count = TransactionInventory.countTransactionInventorys();
        Assert.assertTrue("Too expensive to perform a find all test for 'TransactionInventory', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<TransactionInventory> result = TransactionInventory.findAllTransactionInventorys();
        Assert.assertNotNull("Find all method for 'TransactionInventory' illegally returned null", result);
        Assert.assertTrue("Find all method for 'TransactionInventory' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void TransactionInventoryIntegrationTest.testFindTransactionInventoryEntries() {
        Assert.assertNotNull("Data on demand for 'TransactionInventory' failed to initialize correctly", dod.getRandomTransactionInventory());
        long count = TransactionInventory.countTransactionInventorys();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<TransactionInventory> result = TransactionInventory.findTransactionInventoryEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'TransactionInventory' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'TransactionInventory' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void TransactionInventoryIntegrationTest.testFlush() {
        TransactionInventory obj = dod.getRandomTransactionInventory();
        Assert.assertNotNull("Data on demand for 'TransactionInventory' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'TransactionInventory' failed to provide an identifier", id);
        obj = TransactionInventory.findTransactionInventory(id);
        Assert.assertNotNull("Find method for 'TransactionInventory' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyTransactionInventory(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'TransactionInventory' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void TransactionInventoryIntegrationTest.testMergeUpdate() {
        TransactionInventory obj = dod.getRandomTransactionInventory();
        Assert.assertNotNull("Data on demand for 'TransactionInventory' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'TransactionInventory' failed to provide an identifier", id);
        obj = TransactionInventory.findTransactionInventory(id);
        boolean modified =  dod.modifyTransactionInventory(obj);
        Integer currentVersion = obj.getVersion();
        TransactionInventory merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'TransactionInventory' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void TransactionInventoryIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'TransactionInventory' failed to initialize correctly", dod.getRandomTransactionInventory());
        TransactionInventory obj = dod.getNewTransientTransactionInventory(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'TransactionInventory' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'TransactionInventory' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        Assert.assertNotNull("Expected 'TransactionInventory' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void TransactionInventoryIntegrationTest.testRemove() {
        TransactionInventory obj = dod.getRandomTransactionInventory();
        Assert.assertNotNull("Data on demand for 'TransactionInventory' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'TransactionInventory' failed to provide an identifier", id);
        obj = TransactionInventory.findTransactionInventory(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'TransactionInventory' with identifier '" + id + "'", TransactionInventory.findTransactionInventory(id));
    }
    
}
