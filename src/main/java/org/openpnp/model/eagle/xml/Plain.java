//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.10.23 at 08:50:01 AM PDT 
//


package org.openpnp.model.eagle.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "polygonOrWireOrTextOrDimensionOrCircleOrRectangleOrFrameOrHole"
})
@XmlRootElement(name = "plain")
public class Plain {

    @XmlElements({
        @XmlElement(name = "polygon", type = Polygon.class),
        @XmlElement(name = "wire", type = Wire.class),
        @XmlElement(name = "text", type = Text.class),
        @XmlElement(name = "dimension", type = Dimension.class),
        @XmlElement(name = "circle", type = Circle.class),
        @XmlElement(name = "rectangle", type = Rectangle.class),
        @XmlElement(name = "frame", type = Frame.class),
        @XmlElement(name = "hole", type = Hole.class)
    })
    protected List<Object> polygonOrWireOrTextOrDimensionOrCircleOrRectangleOrFrameOrHole;

    /**
     * Gets the value of the polygonOrWireOrTextOrDimensionOrCircleOrRectangleOrFrameOrHole property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the polygonOrWireOrTextOrDimensionOrCircleOrRectangleOrFrameOrHole property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPolygonOrWireOrTextOrDimensionOrCircleOrRectangleOrFrameOrHole().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Polygon }
     * {@link Wire }
     * {@link Text }
     * {@link Dimension }
     * {@link Circle }
     * {@link Rectangle }
     * {@link Frame }
     * {@link Hole }
     * 
     * 
     */
    public List<Object> getPolygonOrWireOrTextOrDimensionOrCircleOrRectangleOrFrameOrHole() {
        if (polygonOrWireOrTextOrDimensionOrCircleOrRectangleOrFrameOrHole == null) {
            polygonOrWireOrTextOrDimensionOrCircleOrRectangleOrFrameOrHole = new ArrayList<>();
        }
        return this.polygonOrWireOrTextOrDimensionOrCircleOrRectangleOrFrameOrHole;
    }

}
