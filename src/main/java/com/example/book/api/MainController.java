package com.example.book.api;

import com.example.book.dao.BookRepository;
import com.example.book.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/book")
@RestController
public class MainController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public @ResponseBody Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping(path = "{id}")
    public @ResponseBody Optional<Book> getBookById(@PathVariable("id") Integer id) {
        return bookRepository.findById(id);
    }

    @PostMapping
    public @ResponseBody String addBook(@RequestBody Book book) {
        Book data = new Book(book.getTitle(), book.getAuthor(), book.getDescription());
        Book newBook = new Book("", "", "");
        newBook.setTitle(data.getTitle());
        newBook.setAuthor(data.getAuthor());
        newBook.setDescription(data.getDescription());
        bookRepository.save(newBook);
        return "Saved";
    }

    @PutMapping(path = "{id}")
    public @ResponseBody String updateBook(@PathVariable("id") Integer id, @RequestBody Book book) {
        Book data = new Book(book.getTitle(), book.getAuthor(), book.getDescription());
        Book updateBook = bookRepository.findById(id).get();
        updateBook.setTitle(data.getTitle());
        updateBook.setAuthor(data.getAuthor());
        updateBook.setDescription(data.getDescription());
        bookRepository.save(updateBook);
        return "Updated";
    }

    @DeleteMapping(path = "{id}")
    public @ResponseBody String deleteBook(@PathVariable("id") Integer id) {
        bookRepository.deleteById(id);
        return "Deleted";
    }
}
