package de.hbt.workshop.books;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Niko Köbler, https://www.n-k.de, @dasniko
 */
@ApplicationScoped
public class BookService {

    @Inject
    DynamoDbClient ddb;

    private static final String tableName = System.getenv("DYNAMODB_TABLE");

    Book findBook(String id) {
        Map<String, AttributeValue> item = ddb.getItem(builder -> builder.tableName(tableName)
                .key(Collections.singletonMap("id", AttributeValue.builder().s(id).build()))).item();
        return Book.from(item);
    }

    List<Book> getAllBooks() {
        ScanResponse scan = ddb.scan(builder -> builder.tableName(tableName));
        List<Book> books = new ArrayList<>();
        scan.items().forEach(item -> books.add(Book.from(item)));
        return books;
    }

    Book createBook(Book book) {
        book.setId(UUID.randomUUID().toString());
        ddb.putItem(builder -> builder.tableName(tableName).item(book.toItem()));
        return book;
    }

    void deleteBook(String id) {
        ddb.deleteItem(builder -> builder.tableName(tableName)
                .key(Collections.singletonMap("id", AttributeValue.builder().s(id).build())));
    }
}
