package MainActivity;

import Respositories.Author.AuthorRepository;
import Respositories.Category.CategoryRepository;
import Respositories.News.NewsRepository;

import java.util.Scanner;

import static Respositories.Database.DatabaseRepositoryInterface.DATABASE;

public class MainActivity {

    public static void main(String[]args)
    {
        Scanner input = new Scanner(System.in);
        NewsRepository authorRepository = new NewsRepository(DATABASE);

        authorRepository.orderByAuthor();
    }
}
