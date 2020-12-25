//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.10.23 at 08:50:01 AM PDT 
//


package org.openpnp.model.eagle.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "description",
    "plain",
    "libraries",
    "attributes",
    "variantdefs",
    "classes",
    "designrules",
    "autorouter",
    "elements",
    "signals",
    "errors"
})
@XmlRootElement(name = "board")
public class Board {

    protected Description description;
    protected Plain plain;
    protected Libraries libraries;
    protected Attributes attributes;
    protected Variantdefs variantdefs;
    protected Classes classes;
    protected Designrules designrules;
    protected Autorouter autorouter;
    protected Elements elements;
    protected Signals signals;
    protected Errors errors;

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link Description }
     *     
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setDescription(Description value) {
        this.description = value;
    }

    /**
     * Gets the value of the plain property.
     * 
     * @return
     *     possible object is
     *     {@link Plain }
     *     
     */
    public Plain getPlain() {
        return plain;
    }

    /**
     * Sets the value of the plain property.
     * 
     * @param value
     *     allowed object is
     *     {@link Plain }
     *     
     */
    public void setPlain(Plain value) {
        this.plain = value;
    }

    /**
     * Gets the value of the libraries property.
     * 
     * @return
     *     possible object is
     *     {@link Libraries }
     *     
     */
    public Libraries getLibraries() {
        return libraries;
    }

    /**
     * Sets the value of the libraries property.
     * 
     * @param value
     *     allowed object is
     *     {@link Libraries }
     *     
     */
    public void setLibraries(Libraries value) {
        this.libraries = value;
    }

    /**
     * Gets the value of the attributes property.
     * 
     * @return
     *     possible object is
     *     {@link Attributes }
     *     
     */
    public Attributes getAttributes() {
        return attributes;
    }

    /**
     * Sets the value of the attributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Attributes }
     *     
     */
    public void setAttributes(Attributes value) {
        this.attributes = value;
    }

    /**
     * Gets the value of the variantdefs property.
     * 
     * @return
     *     possible object is
     *     {@link Variantdefs }
     *     
     */
    public Variantdefs getVariantdefs() {
        return variantdefs;
    }

    /**
     * Sets the value of the variantdefs property.
     * 
     * @param value
     *     allowed object is
     *     {@link Variantdefs }
     *     
     */
    public void setVariantdefs(Variantdefs value) {
        this.variantdefs = value;
    }

    /**
     * Gets the value of the classes property.
     * 
     * @return
     *     possible object is
     *     {@link Classes }
     *     
     */
    public Classes getClasses() {
        return classes;
    }

    /**
     * Sets the value of the classes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Classes }
     *     
     */
    public void setClasses(Classes value) {
        this.classes = value;
    }

    /**
     * Gets the value of the designrules property.
     * 
     * @return
     *     possible object is
     *     {@link Designrules }
     *     
     */
    public Designrules getDesignrules() {
        return designrules;
    }

    /**
     * Sets the value of the designrules property.
     * 
     * @param value
     *     allowed object is
     *     {@link Designrules }
     *     
     */
    public void setDesignrules(Designrules value) {
        this.designrules = value;
    }

    /**
     * Gets the value of the autorouter property.
     * 
     * @return
     *     possible object is
     *     {@link Autorouter }
     *     
     */
    public Autorouter getAutorouter() {
        return autorouter;
    }

    /**
     * Sets the value of the autorouter property.
     * 
     * @param value
     *     allowed object is
     *     {@link Autorouter }
     *     
     */
    public void setAutorouter(Autorouter value) {
        this.autorouter = value;
    }

    /**
     * Gets the value of the elements property.
     * 
     * @return
     *     possible object is
     *     {@link Elements }
     *     
     */
    public Elements getElements() {
        return elements;
    }

    /**
     * Sets the value of the elements property.
     * 
     * @param value
     *     allowed object is
     *     {@link Elements }
     *     
     */
    public void setElements(Elements value) {
        this.elements = value;
    }

    /**
     * Gets the value of the signals property.
     * 
     * @return
     *     possible object is
     *     {@link Signals }
     *     
     */
    public Signals getSignals() {
        return signals;
    }

    /**
     * Sets the value of the signals property.
     * 
     * @param value
     *     allowed object is
     *     {@link Signals }
     *     
     */
    public void setSignals(Signals value) {
        this.signals = value;
    }

    /**
     * Gets the value of the errors property.
     * 
     * @return
     *     possible object is
     *     {@link Errors }
     *     
     */
    public Errors getErrors() {
        return errors;
    }

    /**
     * Sets the value of the errors property.
     * 
     * @param value
     *     allowed object is
     *     {@link Errors }
     *     
     */
    public void setErrors(Errors value) {
        this.errors = value;
    }

}
