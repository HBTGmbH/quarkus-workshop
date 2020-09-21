package de.hbt.workshop.books;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    BookService service;

    @GET
    public List<Book> getAll() {
        return service.getAllBooks();
    }

    @GET
    @Path("{id}")
    public Book getById(@PathParam("id") String id) {
        return service.findBook(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Book createNew(Book book) {
        return service.createBook(book);
    }

    @DELETE
    @Path("{id}")
    public void deleteBook(@PathParam("id") String id) {
        service.deleteBook(id);
    }

}
