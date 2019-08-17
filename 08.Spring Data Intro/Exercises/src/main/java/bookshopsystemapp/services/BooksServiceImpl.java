package bookshopsystemapp.services;

import bookshopsystemapp.domain.entities.EnumeratedTypes.AgeRestrictionType;
import bookshopsystemapp.domain.entities.EnumeratedTypes.EditionType;
import bookshopsystemapp.domain.entities.models.Author;
import bookshopsystemapp.domain.entities.models.Book;
import bookshopsystemapp.domain.entities.models.Category;
import bookshopsystemapp.repositories.AuthorRepository;
import bookshopsystemapp.repositories.BookRepository;
import bookshopsystemapp.repositories.CategoryRepository;
import bookshopsystemapp.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class BooksServiceImpl implements BookService {

    private final static String BOOKS_FILE_PATH =
            "/Users/igor/Desktop/GIT_Projects/SoftUni-DatabaseAdvanced-Frameworks/08.Spring Data Intro/Exercises/src/main/resources/books.txt";
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    @Autowired
    public BooksServiceImpl(final BookRepository bookRepository,
                            final AuthorRepository authorRepository,
                            final CategoryRepository categoryRepository,
                            final FileUtil fileUtil) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedBooks() {
        if (this.bookRepository.count() != 0) return;

        String[] booksFileContent = this.fileUtil.getFileContent(BOOKS_FILE_PATH);
        for (String line : booksFileContent) {
            String[] tokens = line.split("\\s+");

            Book book = new Book();

            book.setAuthor(getRandomAuthor());

            EditionType editionType = EditionType.values()[Integer.parseInt(tokens[0].replaceAll("\\uFEFF", "").trim())];

            LocalDate date = LocalDate.parse(tokens[1], DateTimeFormatter.ofPattern("d/M/yyyy"));

            Integer copies = Integer.parseInt(tokens[2]);

            BigDecimal price = new BigDecimal(tokens[3]);

            AgeRestrictionType ageRestrictionType = AgeRestrictionType.values()[Integer.parseInt(tokens[4])];

            StringBuilder titleBuilder = new StringBuilder();

            for (int i = 5; i < tokens.length; i++) {
                titleBuilder.append(tokens[i]).append(" ");
            }

            String title = titleBuilder.toString().trim();

            Set<Category> categories = new HashSet<>();
            categories.add(getRandomCategory());
            book.setCategories(new HashSet<>(categories));
            book.setEditionType(editionType);
            book.setReleaseDate(date);
            book.setCopies(10);
            book.setPrice(price);
            book.setAgeRestriction(ageRestrictionType);
            book.setTitle(title);

            this.bookRepository.save(book);
        }
    }

    @Override
    public List<String> getAllBooksTitlesAfter() {
        LocalDate date = LocalDate.of(2000, 12, 31);


        List<Book> books = this.bookRepository.findAllByReleaseDateAfter(date);
        List<String> result = new ArrayList<>();

        for (Book book : books) {
            result.add(book.getTitle());
        }
        return result;
    }

    @Override
    public List<String> getAllAuthorsNamesWithBookReleaseDateBefore() {
        List<Book> allBooksByReleaseDateAfter =
                this.bookRepository.findAllByReleaseDateBefore(LocalDate.parse("1990-01-01"));

        return allBooksByReleaseDateAfter.stream()
                .map(e -> String.format("%s",
                        new StringBuilder().append(e.getAuthor().getFirstName())
                                .append(" ")
                                .append(e.getAuthor().getLastName())))
                .collect(Collectors.toList());
    }



    private Author getRandomAuthor() {
        Random random = new Random();
        return this.authorRepository
                .findById(random.nextInt((int) ((authorRepository.count() - 1) + 1)) + 1).get();
    }

    private Category getRandomCategory() {
        Random random = new Random();

        return this.categoryRepository
                .findById(random.nextInt((int) ((categoryRepository.count() - 1) + 1)) + 1).get();
    }
}
