package com.hypnos.websemantic.controllers;

import com.hypnos.websemantic.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

@RestController
public class TeachingToolsController {
    @Autowired
    private File xmlFile;

    @Autowired
    private File itemsXls;

    @GetMapping("/list")
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

    public String listSpecificItems(
            @RequestParam(value = "xDays", defaultValue = "0", required = false) Integer xDays,
            @RequestParam(value = "category", defaultValue = "", required = false) String category
    ) {
        return "";
    }

    @GetMapping("/item/")
    public String showItem(@RequestParam String id) {
        return "";
    }

    @PostMapping("/item/add")
    public String addItem(@ModelAttribute Item item) {
        return "";
    }
}
