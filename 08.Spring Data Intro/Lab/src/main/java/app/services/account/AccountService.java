package app.services.account;

import java.math.BigDecimal;

public interface AccountService {
    void withdrawMoney(BigDecimal money, long id);

    void transferMoney(BigDecimal money, long id);
}
