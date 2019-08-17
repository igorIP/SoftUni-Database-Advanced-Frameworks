package bookshopsystemapp.services;

import bookshopsystemapp.domain.entities.models.Category;
import bookshopsystemapp.repositories.CategoryRepository;
import bookshopsystemapp.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final static String CATEGORIES_FILE_PATH =
            "/Users/igor/Desktop/GIT_Projects/SoftUni-DatabaseAdvanced-Frameworks/08.Spring Data Intro/Exercises/src/main/resources/categories.txt";
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;


    @Autowired
    public CategoryServiceImpl(final CategoryRepository categoryRepository,
                               final FileUtil fileUtil) {
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedCategories() {
        if (this.categoryRepository.count() != 0) {
            System.out.println("REPO IS NOT EMPTY, exiting");
            return;
        }

        String[] categoriesFileContent = this.fileUtil.getFileContent(CATEGORIES_FILE_PATH);

        for (String line : categoriesFileContent) {
            Category category = new Category();
            category.setName(line);

            this.categoryRepository.saveAndFlush(category);
        }
    }
}
