package sd.bll.validators.client;

import sd.bll.validators.Validator;
import sd.model.Client;

import java.util.regex.Pattern;

public class NameValidator implements Validator<Client> {
    private static final String nameRegex = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";

    /**
     * source: https://www.regextester.com/93648
     * applies to all possible names
     */
    public void validate(Client client) {
        Pattern pattern = Pattern.compile(nameRegex);
        if (!pattern.matcher(client.getName()).matches()) {
            throw new IllegalArgumentException("Name is not well written!");
        }
    }
}
