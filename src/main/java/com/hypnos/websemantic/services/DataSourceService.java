package com.hypnos.websemantic.services;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.net.URL;
import java.util.Objects;
import jdk.nashorn.internal.objects.annotations.Property;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class DataSourceService {

    public static String SCHEMA_FILE = "validation.xsd";

    public boolean validate(String newXml) {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        try {
            Schema schema = schemaFactory.newSchema(new Source[] {
                    new StreamSource(
                            new File(SCHEMA_FILE))
            });
            schema.newValidator().validate(new StreamSource(new StringReader(newXml)));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
