package bookshopsystemapp.controller;

import bookshopsystemapp.services.AuthorService;
import bookshopsystemapp.services.BookService;
import bookshopsystemapp.services.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.List;

//this is ConsoleRunner class
@Controller
public class BookShopController implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;


    public BookShopController(final CategoryService categoryService,
                              final AuthorService authorService,
                              final BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }


    @Override
    public void run(String... args) throws Exception {
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();

//        List<String> allBooksTitlesAfter = this.bookService.getAllBooksTitlesAfter();
//        System.out.println((String.join("\r\n", allBooksTitlesAfter)));
//
//        System.out.println("==========********************===============");
//
//        List<String> allAuthorsNamesWithBookReleaseDateAfter =
//                this.bookService.getAllAuthorsNamesWithBookReleaseDateBefore();
//        System.out.println(String.join("\r\n", allAuthorsNamesWithBookReleaseDateAfter));

        System.out.println("==========********************===============");

        System.out.println(this.authorService.getAllActorsWithABook());
        //System.out.println(this.bookService.getAllActorsWithABook());
    }
}
