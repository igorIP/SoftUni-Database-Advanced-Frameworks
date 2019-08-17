package app;

import app.models.Account;
import app.models.User;
import app.repositories.AccountRepository;
import app.repositories.UserRepository;
import app.services.account.AccountService;
import app.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {

    private final UserService userService;
    private final AccountService accountService;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public ConsoleRunner(final UserService userService,
                         final AccountService accountService,
                         final UserRepository userRepository,
                         final AccountRepository accountRepository) {
        this.userService = userService;
        this.accountService = accountService;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUserName("John");
        user.setAge(22);
        user.setAccounts(new HashSet<>());

        Account account = new Account();
        account.setBalance(BigDecimal.valueOf(25000));
        account.setUser(user);

        user.getAccounts().add(account);

        this.userService.registerUser(user);

        this.accountService.withdrawMoney(BigDecimal.valueOf(20000), user.getId());
        this.accountService.transferMoney(BigDecimal.valueOf(10000), user.getId());

        User user1 = this.userRepository.findByUserName("John");
        System.out.println(user1);

        List<Account> accounts = this.accountRepository.findAll();
        System.out.print(accounts.toString());
    }
}
