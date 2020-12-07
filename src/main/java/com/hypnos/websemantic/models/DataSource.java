package com.hypnos.websemantic.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

//https://howtodoinjava.com/jaxb/read-xml-to-java-object/
//https://www.baeldung.com/jackson-xml-serialization-and-deserialization
@XmlRootElement(name = "tools_list")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class DataSource implements Serializable {
    private static final long serialVersionUID = 1L;
}
