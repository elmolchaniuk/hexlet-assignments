package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ConstraintViolationException;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    public BookRepository bookRepository;

    @Autowired
    public AuthorRepository authorRepository;

    @Autowired
    public BookMapper bookMapper;

    public List<BookDTO> getAll() {
        var books = bookRepository.findAll();
        var result = books.stream()
                .map(bookMapper::map)
                .toList();
        return result;
    }

    public BookDTO create(BookCreateDTO bookData) {
        authorRepository.findById(bookData.getAuthorId())
                .orElseThrow(() -> new ConstraintViolationException("Author with such id not Found"));
        var book = bookMapper.map(bookData);
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public BookDTO findById(Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        return bookMapper.map(book);
    }

    public BookDTO update(BookUpdateDTO bookData, Long id) {
        authorRepository.findById(bookData.getAuthorId())
                .orElseThrow(() -> new ConstraintViolationException("Author with such id not Found"));
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        bookMapper.update(bookData, book);
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

}
