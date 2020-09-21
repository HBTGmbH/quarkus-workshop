package de.hbt.workshop.books;

import io.quarkus.qute.TemplateData;

import javax.ws.rs.FormParam;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
@TemplateData
public class BookForm {
    @FormParam("author")
    public String author;
    @FormParam("title")
    public String title;
}
