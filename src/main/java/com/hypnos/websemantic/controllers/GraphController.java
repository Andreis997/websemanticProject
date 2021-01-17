package com.hypnos.websemantic.controllers;

import com.hypnos.websemantic.services.DataSourceService;
import com.hypnos.websemantic.services.FileService;
import org.apache.jena.graph.Graph;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.sparql.graph.GraphFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class GraphController {

    @Autowired
    FileService fileService;
    @Autowired
    private File xmlFile;
    @Autowired
    private File itemsXls;
    @Autowired
    private File itemXls;
    @Autowired
    private DataSourceService dataSourceService;

    @GetMapping("/graph/uploadFile")
    public String index() {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "  <h1>Spring Boot File Upload Example</h1>\n" +
                "  <hr/>\n" +
                "  <h4>Upload Single File:</h4>\n" +
                "  <form method=\"POST\" th:action=\"/graph/uploadFile\" enctype=\"multipart/form-data\">\n" +
                "    <input type=\"file\" name=\"file\"/> <br/><br/>\n" +
                "    <button type=\"submit\">Submit</button>\n" +
                "  </form>\n" +
                "  <hr/>\n" +
                "  <div th:if=\"${message}\">\n" +
                "    <h2 th:text=\"${message}\"/>\n" +
                "  </div>\n" +
                "</body>\n" +
                "</html>";
    }

    @PostMapping("/graph/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        // create an empty model
        Model model = ModelFactory.createDefaultModel();

        // use the RDFDataMgr to find the input file
        InputStream in = RDFDataMgr.open(fileService.uploadFile(file));
        if (in == null) {
            throw new IllegalArgumentException(
                    "File: records2.xml not found");
        }

        // read the RDF/XML file
        model.read(in, null);

        return "You successfully uploaded " + file.getOriginalFilename() + "!<br>" + model.getGraph().toString();
    }

    @GetMapping("/graph")
    public String listItems() throws ParserConfigurationException, IOException, SAXException, TransformerException {
        // create an empty model
        Model model = ModelFactory.createDefaultModel();

        // use the RDFDataMgr to find the input file
        InputStream in = RDFDataMgr.open("records2.xml");
        if (in == null) {
            throw new IllegalArgumentException(
                    "File: records2.xml not found");
        }

        // read the RDF/XML file
        model.read(in, null);

        // write it to standard out
        model.write(System.out);
        return "";
    }
}
