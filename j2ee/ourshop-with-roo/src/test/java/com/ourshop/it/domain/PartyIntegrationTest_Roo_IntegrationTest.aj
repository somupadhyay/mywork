// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ourshop.it.domain;

import com.ourshop.it.domain.Party;
import com.ourshop.it.domain.PartyDataOnDemand;
import com.ourshop.it.domain.PartyIntegrationTest;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect PartyIntegrationTest_Roo_IntegrationTest {
    
    declare @type: PartyIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: PartyIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml");
    
    declare @type: PartyIntegrationTest: @Transactional;
    
    @Autowired
    PartyDataOnDemand PartyIntegrationTest.dod;
    
    @Test
    public void PartyIntegrationTest.testCountPartys() {
        Assert.assertNotNull("Data on demand for 'Party' failed to initialize correctly", dod.getRandomParty());
        long count = Party.countPartys();
        Assert.assertTrue("Counter for 'Party' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void PartyIntegrationTest.testFindParty() {
        Party obj = dod.getRandomParty();
        Assert.assertNotNull("Data on demand for 'Party' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Party' failed to provide an identifier", id);
        obj = Party.findParty(id);
        Assert.assertNotNull("Find method for 'Party' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Party' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void PartyIntegrationTest.testFindAllPartys() {
        Assert.assertNotNull("Data on demand for 'Party' failed to initialize correctly", dod.getRandomParty());
        long count = Party.countPartys();
        Assert.assertTrue("Too expensive to perform a find all test for 'Party', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Party> result = Party.findAllPartys();
        Assert.assertNotNull("Find all method for 'Party' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Party' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void PartyIntegrationTest.testFindPartyEntries() {
        Assert.assertNotNull("Data on demand for 'Party' failed to initialize correctly", dod.getRandomParty());
        long count = Party.countPartys();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Party> result = Party.findPartyEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Party' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Party' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void PartyIntegrationTest.testFlush() {
        Party obj = dod.getRandomParty();
        Assert.assertNotNull("Data on demand for 'Party' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Party' failed to provide an identifier", id);
        obj = Party.findParty(id);
        Assert.assertNotNull("Find method for 'Party' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyParty(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Party' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void PartyIntegrationTest.testMergeUpdate() {
        Party obj = dod.getRandomParty();
        Assert.assertNotNull("Data on demand for 'Party' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Party' failed to provide an identifier", id);
        obj = Party.findParty(id);
        boolean modified =  dod.modifyParty(obj);
        Integer currentVersion = obj.getVersion();
        Party merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Party' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void PartyIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'Party' failed to initialize correctly", dod.getRandomParty());
        Party obj = dod.getNewTransientParty(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Party' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Party' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        Assert.assertNotNull("Expected 'Party' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void PartyIntegrationTest.testRemove() {
        Party obj = dod.getRandomParty();
        Assert.assertNotNull("Data on demand for 'Party' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Party' failed to provide an identifier", id);
        obj = Party.findParty(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'Party' with identifier '" + id + "'", Party.findParty(id));
    }
    
}
