package sd.bll;

import sd.bll.validators.Validator;
import sd.bll.validators.account.AmountMoneyValidator;
import sd.bll.validators.account.IdAccountValidator;
import sd.dao.AccountDAO;
import sd.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AccountBLL {
    private static List<Validator<Account>> validators;

    public AccountBLL() {
        validators = new ArrayList<>();
        validators.add(new AmountMoneyValidator());
        validators.add(new IdAccountValidator());
    }

    public static int insertAccount(Account account) {
        for (Validator<Account> v : validators) {
            v.validate(account);
        }
        return AccountDAO.insert(account);
    }

    public static int updateAccount(Account account) {
        for (Validator<Account> v : validators) {
            v.validate(account);
        }
        return AccountDAO.update(account);
    }

    public static int updateMoneyOnlyAccount(Account account) {
        for (Validator<Account> v : validators) {
            v.validate(account);
        }
        return AccountDAO.updateMoneyOnly(account);
    }

    public Account findAccountById(int idAccount) {
        Account account = AccountDAO.findById(idAccount);
        if (account == null) {
            throw new NoSuchElementException("The account with id =" + idAccount + " was not found!");
        }
        return account;
    }
}
