package de.hbt.workshop.books;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
public class Book {
    private String id;
    private String author;
    private String title;

    public Book(String id, String title, String author) {
        this.id = id;
        this.author = author;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    static Book from(Map<String, AttributeValue> item) {
        AttributeValue id = item.get("id");
        AttributeValue title = item.get("title");
        AttributeValue author = item.get("author");
        return new Book(id.s(), title.s(), author.s());
    }

    Map<String, AttributeValue> toItem() {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("id", AttributeValue.builder().s(id).build());
        item.put("title", AttributeValue.builder().s(title).build());
        item.put("author", AttributeValue.builder().s(author).build());
        return item;
    }
}
