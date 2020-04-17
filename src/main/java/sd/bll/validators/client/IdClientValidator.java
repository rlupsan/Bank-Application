package sd.bll.validators.client;

import sd.bll.validators.Validator;
import sd.model.Client;

public class IdClientValidator implements Validator<Client> {
    public void validate(Client client) {
        if (client.getId() < 0) {
            throw new IllegalArgumentException("The id cannot be negative!");
        }
    }
}
