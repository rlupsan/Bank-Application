package sd.bll;

import sd.bll.validators.Validator;
import sd.bll.validators.transfer.FromAccountIdValidator;
import sd.bll.validators.transfer.MoneyToBeTransferredValidator;
import sd.bll.validators.transfer.ToAccountIdValidator;
import sd.dao.TransactionDAO;
import sd.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionBLL {
    private List<Validator<Transaction>> validators;

    public TransactionBLL() {
        validators = new ArrayList<>();
        validators.add(new FromAccountIdValidator());
        validators.add(new ToAccountIdValidator());
        validators.add(new MoneyToBeTransferredValidator());
    }

    public int insertTransaction(Transaction transaction) {
        for (Validator<Transaction> v : validators) {
            v.validate(transaction);
        }
        return TransactionDAO.insert(transaction);
    }

}
