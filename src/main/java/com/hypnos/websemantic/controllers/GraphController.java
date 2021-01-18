package com.hypnos.websemantic.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.hypnos.websemantic.services.DataSourceService;
import com.hypnos.websemantic.services.FileService;
import jdk.nashorn.internal.ir.BaseNode;
import org.apache.jena.graph.Graph;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.Triple;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.rdf.model.impl.ModelCom;
import org.apache.jena.rdf.model.impl.StatementImpl;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.sparql.graph.GraphFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
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

        return "You successfully uploaded " + file.getOriginalFilename() + "!<br>" + model.getGraph().toString().replace(";", ";<br>");
    }

    @GetMapping("/graph")
    public String listItems() throws JsonProcessingException {
        // create an empty model
        Model model = ModelFactory.createDefaultModel();

        // use the RDFDataMgr to find the input file
        InputStream in = RDFDataMgr.open("sample.rdf");
        if (in == null) {
            throw new IllegalArgumentException(
                    "File: records2.xml not found");
        }

        // read the RDF/XML file
        model.read(in, null);
        return model.getGraph().toString().replace(";", ";<br>");
    }

    @GetMapping("/graph/find/subject/{name}")
    public String findBasedOnSubject(@PathVariable(value = "name") String name) {
        // create an empty model
        Model model = ModelFactory.createDefaultModel();

        // use the RDFDataMgr to find the input file
        InputStream in = RDFDataMgr.open("sample.rdf");
        if (in == null) {
            throw new IllegalArgumentException(
                    "File: records2.xml not found");
        }

        // read the RDF/XML file
        model.read(in, null);

        String queryString = "PREFIX x: <https://www.toptools4learning.com#> " +
                "SELECT * " +
                "WHERE {?subject x:subject \"" + name + "\"} " +
                "LIMIT 100" ;
        Query query = QueryFactory.create(queryString);
        String result = "";
        try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
            ResultSet results = qexec.execSelect() ;
            for ( ; results.hasNext() ; )
            {
                QuerySolution soln = results.nextSolution();
                result += soln.get("subject").asNode().toString() + "<br>";
            }
        }

        return result.isEmpty() ? "No result" : result;
    }

    @GetMapping("/graph/find/category/{name}")
    public String findBasedOnCategory(@PathVariable(value = "name") String name) {
        // create an empty model
        Model model = ModelFactory.createDefaultModel();

        // use the RDFDataMgr to find the input file
        InputStream in = RDFDataMgr.open("sample.rdf");
        if (in == null) {
            throw new IllegalArgumentException(
                    "File: records2.xml not found");
        }

        // read the RDF/XML file
        model.read(in, null);

        String queryString = "PREFIX x: <https://www.toptools4learning.com#> " +
                "SELECT ?subject ?predicate ?object " +
                "WHERE {?subject x:category \"" + name + "\"} " +
                "LIMIT 100" ;
        Query query = QueryFactory.create(queryString);
        String result = "";
        try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
            ResultSet results = qexec.execSelect() ;
            for ( ; results.hasNext() ; )
            {
                QuerySolution soln = results.nextSolution();
                result += soln.get("subject").asNode().toString() + "<br>";
            }
        }

        return result.isEmpty() ? "No result" : result;
    }

    @GetMapping("/graph/top/{x}")
    public String findBasedTopX(@PathVariable(value = "x") int x) {
        // create an empty model
        Model model = ModelFactory.createDefaultModel();

        // use the RDFDataMgr to find the input file
        InputStream in = RDFDataMgr.open("sample.rdf");
        if (in == null) {
            throw new IllegalArgumentException(
                    "File: records2.xml not found");
        }

        // read the RDF/XML file
        model.read(in, null);

        String queryString = "PREFIX x: <https://www.toptools4learning.com#> " +
                "SELECT ?s ?x:position" +
                "WHERE {?s x:position ?x:position . FILTER(?x:position < 11)} " +
                //"WHERE {?s x:position ?position . FILTER(?position < 11) } " +
                //"FILTER (?position < " + x + ") " +
                "LIMIT 100" ;
        Query query = QueryFactory.create(queryString);
        String result = "";
        try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
            ResultSet results = qexec.execSelect() ;
            for ( ; results.hasNext() ; )
            {
                QuerySolution soln = results.nextSolution();
                result += soln.get("subject").asNode().toString() + "<br>";
            }
        }

        return result.isEmpty() ? "No result" : result;
    }

    @PostMapping("/graph/add")
    public String add(@RequestParam(value = "name") String name, @RequestParam(value = "position") Integer position,
                      @RequestParam(value = "category") String category, @RequestParam(value = "web_based") Integer web_based,
                      @RequestParam(value = "subject") String subject) {
        // create an empty model
        Model model = ModelFactory.createDefaultModel();

        // use the RDFDataMgr to find the input file
        InputStream in = RDFDataMgr.open("sample.rdf");
        if (in == null) {
            throw new IllegalArgumentException(
                    "File: records2.xml not found");
        }

        // read the RDF/XML file
        model.read(in, null);

        return model.getGraph().toString();
    }
}
