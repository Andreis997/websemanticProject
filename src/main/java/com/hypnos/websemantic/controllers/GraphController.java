package com.hypnos.websemantic.controllers;

import com.hypnos.websemantic.services.DataSourceService;
import com.hypnos.websemantic.services.FileService;
import org.apache.jena.graph.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

@RestController
public class GraphController {

    @Autowired
    private File xmlFile;

    @Autowired
    private File itemsXls;

    @Autowired
    private File itemXls;

    @Autowired
    private DataSourceService dataSourceService;

    @Autowired
    FileService fileService;

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
        fileService.uploadFile(file);
        return "You successfully uploaded " + file.getOriginalFilename() + "!";
    }

    @GetMapping("/graph")
    public String listItems() throws ParserConfigurationException, IOException, SAXException, TransformerException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);

        TransformerFactory tFactory = TransformerFactory.newInstance();
        StreamSource stylesource = new StreamSource(itemsXls);
        Transformer transformer = tFactory.newTransformer(stylesource);

        DOMSource source = new DOMSource(document);
        StringWriter w = new StringWriter();
        StreamResult result = new StreamResult(w);
        transformer.transform(source, result);
        w.flush();
        return w.toString();
    }
}
