// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ourshop.it.domain;

import com.ourshop.it.domain.Inventory;
import com.ourshop.it.domain.InventoryDataOnDemand;
import com.ourshop.it.domain.InventoryIntegrationTest;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect InventoryIntegrationTest_Roo_IntegrationTest {
    
    declare @type: InventoryIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: InventoryIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    declare @type: InventoryIntegrationTest: @Transactional;
    
    @Autowired
    InventoryDataOnDemand InventoryIntegrationTest.dod;
    
    @Test
    public void InventoryIntegrationTest.testCountInventorys() {
        Assert.assertNotNull("Data on demand for 'Inventory' failed to initialize correctly", dod.getRandomInventory());
        long count = Inventory.countInventorys();
        Assert.assertTrue("Counter for 'Inventory' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void InventoryIntegrationTest.testFindInventory() {
        Inventory obj = dod.getRandomInventory();
        Assert.assertNotNull("Data on demand for 'Inventory' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Inventory' failed to provide an identifier", id);
        obj = Inventory.findInventory(id);
        Assert.assertNotNull("Find method for 'Inventory' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Inventory' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void InventoryIntegrationTest.testFindAllInventorys() {
        Assert.assertNotNull("Data on demand for 'Inventory' failed to initialize correctly", dod.getRandomInventory());
        long count = Inventory.countInventorys();
        Assert.assertTrue("Too expensive to perform a find all test for 'Inventory', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Inventory> result = Inventory.findAllInventorys();
        Assert.assertNotNull("Find all method for 'Inventory' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Inventory' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void InventoryIntegrationTest.testFindInventoryEntries() {
        Assert.assertNotNull("Data on demand for 'Inventory' failed to initialize correctly", dod.getRandomInventory());
        long count = Inventory.countInventorys();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Inventory> result = Inventory.findInventoryEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Inventory' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Inventory' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void InventoryIntegrationTest.testFlush() {
        Inventory obj = dod.getRandomInventory();
        Assert.assertNotNull("Data on demand for 'Inventory' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Inventory' failed to provide an identifier", id);
        obj = Inventory.findInventory(id);
        Assert.assertNotNull("Find method for 'Inventory' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyInventory(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Inventory' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void InventoryIntegrationTest.testMergeUpdate() {
        Inventory obj = dod.getRandomInventory();
        Assert.assertNotNull("Data on demand for 'Inventory' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Inventory' failed to provide an identifier", id);
        obj = Inventory.findInventory(id);
        boolean modified =  dod.modifyInventory(obj);
        Integer currentVersion = obj.getVersion();
        Inventory merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Inventory' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void InventoryIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'Inventory' failed to initialize correctly", dod.getRandomInventory());
        Inventory obj = dod.getNewTransientInventory(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Inventory' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Inventory' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        Assert.assertNotNull("Expected 'Inventory' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void InventoryIntegrationTest.testRemove() {
        Inventory obj = dod.getRandomInventory();
        Assert.assertNotNull("Data on demand for 'Inventory' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Inventory' failed to provide an identifier", id);
        obj = Inventory.findInventory(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'Inventory' with identifier '" + id + "'", Inventory.findInventory(id));
    }
    
}