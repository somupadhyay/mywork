//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.05.23 at 05:53:50 PM IST 
//


package org.soap.demo.employee.domain;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.soap.demo.employee.domain package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _EmployeeResponse_QNAME = new QName("http://www.soap.org/demo/employee/domain", "EmployeeResponse");
    private final static QName _EmployeeRequest_QNAME = new QName("http://www.soap.org/demo/employee/domain", "EmployeeRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.soap.demo.employee.domain
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EmployeeRequestType }
     * 
     */
    public EmployeeRequestType createEmployeeRequestType() {
        return new EmployeeRequestType();
    }

    /**
     * Create an instance of {@link EmployeeResponseType }
     * 
     */
    public EmployeeResponseType createEmployeeResponseType() {
        return new EmployeeResponseType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmployeeResponseType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.soap.org/demo/employee/domain", name = "EmployeeResponse")
    public JAXBElement<EmployeeResponseType> createEmployeeResponse(EmployeeResponseType value) {
        return new JAXBElement<EmployeeResponseType>(_EmployeeResponse_QNAME, EmployeeResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmployeeRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.soap.org/demo/employee/domain", name = "EmployeeRequest")
    public JAXBElement<EmployeeRequestType> createEmployeeRequest(EmployeeRequestType value) {
        return new JAXBElement<EmployeeRequestType>(_EmployeeRequest_QNAME, EmployeeRequestType.class, null, value);
    }

}
