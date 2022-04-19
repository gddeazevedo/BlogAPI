package com.example.blog.service;

import com.example.blog.repository.AuthorRepository;
import com.example.blog.entity.Author;
import com.example.blog.dto.request.AuthorDTO;
import com.example.blog.exception.AuthorNotFoundException;
import com.example.blog.exception.AuthorNotValidException;
import com.example.blog.exception.EmailAlreadyTakenException;
import com.example.blog.helper.AuthorHelper;
import com.example.blog.mapper.AuthorMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    AuthorMapper mapper = AuthorMapper.INSTANCE;
    
    @Autowired
    private AuthorRepository repository;

    @Autowired
    private AuthorHelper helper;

    public List<AuthorDTO> findAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toDTO)
            .collect(Collectors.toList());
    }

    public AuthorDTO findBy(Long id) throws AuthorNotFoundException {
        Author author = repository.findById(id).orElseThrow(() ->
            new AuthorNotFoundException(id)
        );

        return mapper.toDTO(author);
    }

    public AuthorDTO create(AuthorDTO authorDTO) throws EmailAlreadyTakenException, AuthorNotValidException {
        raiseExceptionIfEmailIsTaken(authorDTO.getEmail());
        helper.raiseExceptionIfAttributesAreNotValid(authorDTO);

        Author author = mapper.toModel(authorDTO);

        return mapper.toDTO(repository.save(author));
    }

    public AuthorDTO update(Long id, AuthorDTO authorDTO) throws AuthorNotFoundException, AuthorNotValidException {
        Author authorToUpdate = repository.findById(id).orElseThrow(() ->
            new AuthorNotFoundException()
        );

        helper.raiseExceptionIfAttributesAreNotValid(authorDTO);

        authorToUpdate.setName(authorDTO.getName());
        authorToUpdate.setEmail(authorDTO.getEmail());

        return mapper.toDTO(repository.save(authorToUpdate));
    }

    public void delete(Long id) throws AuthorNotFoundException {
        Author author = repository.findById(id).orElseThrow(() ->
            new AuthorNotFoundException(id)
        );

        repository.delete(author);
    }

    private void raiseExceptionIfEmailIsTaken(String email) throws EmailAlreadyTakenException{
        Optional<Author> optionalAuthor = repository.findByEmail(email);

        if (optionalAuthor.isPresent()) {
            throw new EmailAlreadyTakenException(email);
        }
    }
}
