package com.example.authors;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping
    public List<AuthorDTO> listAllAuthor() {
        return service.listAll();
    }

    @PostMapping
    public AuthorDTO createAuthor(@RequestBody CreateAuthorCommand command) {
        return service.save(command);
    }

    @PostMapping("/{id}/book")
    public AuthorDTO addBook(@PathVariable("id") long id, @RequestBody CreateBookCommand command) {
        return service.addBook(id, command);
    }

    @DeleteMapping
    public void deleteAuthor(@RequestBody CreateDeleteAuthorCommand command) {
        service.deleteAuthor(command);
    }
}
