package com.hypnos.websemantic.controllers;

import com.hypnos.websemantic.models.DataSource;
import com.hypnos.websemantic.models.Item;
import com.hypnos.websemantic.services.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@RestController
public class TeachingToolsController {
    @Autowired
    private File xmlFile;

    @Autowired
    private File itemsXls;

    @Autowired
    private File itemXls;

    @Autowired
    private DataSourceService dataSourceService;

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

    @GetMapping(value = "item/specific")
    public String listSpecificItems(
            @RequestParam(value = "xDays", defaultValue = "0", required = false) Integer xDays,
            @RequestParam(value = "category", required = false) String category
    ) throws TransformerException, ParserConfigurationException, XPathExpressionException, IOException, SAXException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = null;
        if ("Engineering".equalsIgnoreCase(category)) {
            expression = "//tool[subjects/subject=\"Various\"]|//tool[subjects/subject=\"Engineering\"]";
        } else {
            expression = "//tool[subjects/subject=\"" + category + "\"]";
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);
        NodeList node = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

        TransformerFactory tFactory = TransformerFactory.newInstance();
        StreamSource stylesource = new StreamSource(itemsXls);
        Transformer transformer = tFactory.newTransformer(stylesource);

        DocumentBuilderFactory dbf =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder builderr = dbf.newDocumentBuilder();
        Document doc = builderr.newDocument();
        Element element = doc.createElement("tools_list");
        doc.appendChild(element);
        for (int i = 0; i < node.getLength(); ++i) {
            element.insertBefore(doc.adoptNode(node.item(i).cloneNode(true)), element.getLastChild());
        }
        Transformer tf =
                TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        Writer out = new StringWriter();
        tf.transform(new DOMSource(doc), new StreamResult(out));

        DOMSource source = new DOMSource(doc);
        StringWriter w = new StringWriter();
        StreamResult result = new StreamResult(w);
        transformer.transform(source, result);
        w.flush();
        return w.toString();
    }

    @GetMapping("/item/{id}")
    public String showItem(@PathVariable String id) throws IOException, SAXException, ParserConfigurationException, XPathExpressionException, TransformerException, XPathExpressionException {
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "//tool[@id=" + "\"" + id + "\"" + "]";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);
        Node node = (Node) xPath.compile(expression).evaluate(document, XPathConstants.NODE);

        TransformerFactory tFactory = TransformerFactory.newInstance();
        StreamSource stylesource = new StreamSource(itemXls);
        Transformer transformer = tFactory.newTransformer(stylesource);

        DocumentBuilderFactory dbf =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder builderr = dbf.newDocumentBuilder();
        Document doc = builderr.newDocument();
        doc.appendChild(doc.adoptNode(node.cloneNode(true)));

        DOMSource source = new DOMSource(doc);
        StringWriter w = new StringWriter();
        StreamResult result = new StreamResult(w);
        transformer.transform(source, result);
        w.flush();
        return w.toString();
    }

    @PostMapping("/item/add")
    public String addItemPost(@ModelAttribute Item item) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document doc = documentBuilder.parse(xmlFile);

        Element root = doc.getDocumentElement();
        Element toolElement = doc.createElement("tool");
        root.appendChild(toolElement);

        Element name = doc.createElement("name");
        name.insertBefore(doc.createTextNode(item.name), name.getLastChild());
        toolElement.insertBefore(name, toolElement.getLastChild());

        Element description = doc.createElement("description");
        description.insertBefore(doc.createTextNode(item.description), description.getLastChild());
        toolElement.insertBefore(description, toolElement.getLastChild());

        Element link = doc.createElement("link");
        link.insertBefore(doc.createTextNode(item.link), link.getLastChild());
        toolElement.insertBefore(link, toolElement.getLastChild());

        Element style = doc.createElement("style");
        style.insertBefore(doc.createTextNode(item.style), style.getLastChild());
        toolElement.insertBefore(style, toolElement.getLastChild());

        Element category = doc.createElement("category");
        category.insertBefore(doc.createTextNode(item.category), category.getLastChild());
        toolElement.insertBefore(category, toolElement.getLastChild());

        Element web_based = doc.createElement("web_based");
        web_based.insertBefore(doc.createTextNode(item.web_based), web_based.getLastChild());
        toolElement.insertBefore(web_based, toolElement.getLastChild());

        Element price = doc.createElement("price");
        price.insertBefore(doc.createTextNode(item.price), price.getLastChild());
        toolElement.insertBefore(price, toolElement.getLastChild());


        Element subjects = doc.createElement("subjects");
        for (String s : item.subjects.split(",")){
            Element subject = doc.createElement("subject");
            subject.insertBefore(doc.createTextNode(s), subject.getLastChild());
            subjects.insertBefore(subject, subjects.getLastChild());
        }

        toolElement.insertBefore(subjects, toolElement.getLastChild());

        Element creator = doc.createElement("creator");
        creator.insertBefore(doc.createTextNode(item.creator), creator.getLastChild());
        toolElement.insertBefore(creator, toolElement.getLastChild());

        Element img = doc.createElement("img");
        img.insertBefore(doc.createTextNode(item.img != null ? item.img : ""), img.getLastChild());
        toolElement.insertBefore(img, toolElement.getLastChild());

        Transformer tf =
                TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        Writer out = new StringWriter();
        tf.transform(new DOMSource(doc), new StreamResult(out));

        if (dataSourceService.validate(out.toString())) {
            tf.transform(new DOMSource(doc), new StreamResult(out));
            StreamResult file = new StreamResult(xmlFile);
            //write data
            tf.transform(new DOMSource(doc), file);
            return "Validation success";
        }
        return "Validation failed";
    }

    @GetMapping("/item/add")
    public String addItem() {

        //Read file content into the string with - Files.lines(Path path, Charset cs)
        StringBuilder contentBuilder = new StringBuilder();

        try (
                Stream<String> stream = Files.lines(Paths.get("src/main/resources/static/addItem.html"), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentBuilder.toString();
    }
}
