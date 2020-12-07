package com.hypnos.websemantic.models;

import lombok.Data;

// https://spring.io/guides/gs/handling-form-submission/
@Data
public class Item {
    public String name;
    public String description;
    public String link;
    public String style;
    public String category;
    public String web_based;
    public String price;
    public String subjects;
    public String creator;
    public String img;
}
