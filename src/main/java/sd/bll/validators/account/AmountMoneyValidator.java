package sd.bll.validators.account;

import sd.bll.validators.Validator;
import sd.model.Account;

public class AmountMoneyValidator implements Validator<Account> {

    public void validate(Account account) {
        if (account.getAmountMoney() < 0) {
            throw new IllegalArgumentException("Amount of money cannot be negative!");
        }
    }
}
