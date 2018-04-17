package MainActivity;

import Respositories.Author.AuthorRepository;

import java.util.Scanner;

import static Respositories.Database.DatabaseRepositoryInterface.DATABASE;

public class MainActivity {

    public static void main(String[]args)
    {
        Scanner input = new Scanner(System.in);

        AuthorRepository authorRepository = new AuthorRepository(DATABASE);
        authorRepository.getAllAuthors();
        authorRepository.deleteAuthor(input.nextLine());
    }
}
