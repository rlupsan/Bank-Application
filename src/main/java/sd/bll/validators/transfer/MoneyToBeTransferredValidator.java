package sd.bll.validators.transfer;

import sd.bll.validators.Validator;
import sd.model.Transaction;


public class MoneyToBeTransferredValidator implements Validator<Transaction> {
    public void validate(Transaction transaction) {
        if (transaction.getAmountMoneyTransferred() < 0) {
            throw new IllegalArgumentException("The amount of money cannot be negative!");
        }
    }
}
