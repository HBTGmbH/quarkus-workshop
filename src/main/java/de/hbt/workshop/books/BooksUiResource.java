package de.hbt.workshop.books;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
@Path("/books")
@Produces(MediaType.TEXT_HTML)
public class BooksUiResource {

    @Inject
    BookService service;
    @Inject
    Template list;
    @Inject
    Template book;

    @GET
    public TemplateInstance listBooks() {
        List<Book> books = service.getAllBooks();
        return list.data("books", books)
            .data("total", books.size());
    }

    @GET
    @Path("{id}")
    public TemplateInstance getBook(@PathParam("id") String id) {
        Book b = service.findBook(id);
        return book.data(b);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createBook(@BeanParam BookForm form) {
        Book book = new Book();
        book.setAuthor(form.author);
        book.setTitle(form.title);
        book = service.createBook(book);
        return Response.status(301).location(URI.create("/books/" + book.getId())).build();
    }
}
