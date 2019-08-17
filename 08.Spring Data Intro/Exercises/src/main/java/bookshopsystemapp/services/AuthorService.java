package bookshopsystemapp.services;

import bookshopsystemapp.domain.entities.models.Author;

import java.util.List;

public interface AuthorService {

    void seedAuthors();
    List<String> getAllActorsWithABook();


}
