package sd.bll.validators.account;

import sd.bll.validators.Validator;
import sd.model.Account;

public class IdAccountValidator implements Validator<Account> {
    public void validate(Account account) {
        if (account.getId() < 0) {
            throw new IllegalArgumentException("The id cannot be negative!");
        }
    }
}
