package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book book) {
        Optional<Book> existingBookOptional = bookRepository.findById(id);
        if (existingBookOptional.isPresent()) {
            Book existingBook = existingBookOptional.get();
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setPublicationYear(book.getPublicationYear());
            existingBook.setIsbn(book.getIsbn());
            // Set other properties as needed
            return bookRepository.save(existingBook);
        } else {
            // Handle if the book with the given id is not found
            return null;
        }
    }

    public boolean deleteBook(Long id) {
        Optional<Book> existingBookOptional = bookRepository.findById(id);
        if (existingBookOptional.isPresent()) {
            bookRepository.delete(existingBookOptional.get());
            return true;
        } else {
            // Handle if the book with the given id is not found
            return false;
        }
    }

    // Implement other service methods as needed
}
