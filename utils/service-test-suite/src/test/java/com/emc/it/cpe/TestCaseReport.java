/**
 * 
 */
package com.emc.it.cpe;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author upadhs5
 *
 */
public class TestCaseReport {

    public static final ConcurrentHashMap<String, String> soapTestReport = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<String, String> apiKeyTestReport = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<String, String> basicAuthTestReport = new ConcurrentHashMap<>();
    public static final ConcurrentHashMap<String, String> oAuthTestReport = new ConcurrentHashMap<>();
    
}
