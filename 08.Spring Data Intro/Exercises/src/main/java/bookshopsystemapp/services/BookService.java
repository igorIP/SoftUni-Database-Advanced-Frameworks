package bookshopsystemapp.services;


import bookshopsystemapp.domain.entities.models.Author;

import java.util.List;

public interface BookService {

    void seedBooks();

    List<String> getAllBooksTitlesAfter();
    List<String> getAllAuthorsNamesWithBookReleaseDateBefore();

}
