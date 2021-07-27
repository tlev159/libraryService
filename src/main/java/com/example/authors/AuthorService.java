package com.example.authors;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorService {

    private AuthorRepository repository;

    private ModelMapper modelMapper;


    public List<AuthorDTO> listAll() {
        return repository.findAll().stream()
                .map(a->modelMapper.map(a, AuthorDTO.class))
                .collect(Collectors.toList());
    }

    public AuthorDTO save(CreateAuthorCommand command) {
        Author author = new Author(command.getName());
        repository.save(author);
        return modelMapper.map(author, AuthorDTO.class);
    }

    @Transactional
    public AuthorDTO addBook(Long id, CreateBookCommand command) {
        Author author = repository.findById(id).orElseThrow(()-> new IllegalArgumentException("Can not find author!"));
        author.addBook(new Book(command.getIsbn(), command.getTitle()));
        return modelMapper.map(author, AuthorDTO.class);
    }

    public void deleteAuthor(CreateDeleteAuthorCommand command) {
        repository.deleteById(command.getId());
    }
}
