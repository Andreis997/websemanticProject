package com.hypnos.websemantic.configurations;

import com.hypnos.websemantic.models.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Configuration
public class XMLConfiguration {

    public static String XML_FILE = "records.xml";

    @Bean
    public DataSource dataSource() throws JAXBException {
        JAXBContext jaxbContext;
        jaxbContext = JAXBContext.newInstance(DataSource.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        return (DataSource) jaxbUnmarshaller.unmarshal(new File(XML_FILE));
    }

}
