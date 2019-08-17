package bookshopsystemapp.services;

import bookshopsystemapp.domain.entities.models.Author;
import bookshopsystemapp.domain.entities.models.Category;
import bookshopsystemapp.repositories.AuthorRepository;
import bookshopsystemapp.repositories.CategoryRepository;
import bookshopsystemapp.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final static String AUTHORS_FILE_PATH =
            "/Users/igor/Desktop/GIT_Projects/SoftUni-DatabaseAdvanced-Frameworks/08.Spring Data Intro/Exercises/src/main/resources/authors.txt";
    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    @Autowired
    public AuthorServiceImpl(final AuthorRepository authorRepository,
                             final FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() {
        if (this.authorRepository.count() != 0) return;

        String[] authorFileContent = this.fileUtil.getFileContent(AUTHORS_FILE_PATH);
        for (String line : authorFileContent) {
            Author author = new Author();

            String[] tokens = line.split("\\s+");

            System.out.println();

            author.setFirstName(tokens[0]);
            author.setLastName(tokens[1]);

            this.authorRepository.saveAndFlush(author);
        }
    }

    @Override
    public List<String> getAllActorsWithABook() {
        System.out.println(this.authorRepository.findAuthorsInDesce());
        return null;
    }
}

