package sd.bll.validators.client;

import sd.bll.validators.Validator;
import sd.model.Client;

import java.util.regex.Pattern;

/**
 * Good for testing:
 * https://regex101.com/
 */
public class AddressValidator implements Validator<Client> {
    private static final String addressRegex = "\\d{1,5}\\s\\w.\\s(\\b\\w*\\b\\s){1,2}\\w*\\.";

    /**
     * ex: 253 N. Cherry St.
     * 202 N. Brancusi St.
     * (number) N. (name in letters) St.
     * <p>
     * source:https://stackoverflow.com/questions/11456670/regular-expression-for-address-field-validation
     */
    public void validate(Client client) {
        Pattern pattern = Pattern.compile(addressRegex);
        if (!pattern.matcher(client.getAddress()).matches()) {
            throw new IllegalArgumentException("Address is not well written!");
        }
    }
}
