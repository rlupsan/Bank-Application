package sd.bll.validators.transfer;

import sd.bll.validators.Validator;
import sd.model.Transaction;

public class ToAccountIdValidator implements Validator<Transaction> {
    public void validate(Transaction transaction) {
        if (transaction.getToAccount() < 0) {
            throw new IllegalArgumentException("The id cannot be negative!");
        }
    }
}