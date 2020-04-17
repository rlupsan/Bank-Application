package sd.bll.validators.client;

import sd.bll.validators.Validator;
import sd.model.Client;

import java.util.regex.Pattern;

public class IdCardValidator implements Validator<Client> {
    private static final String onlyNumbersRegex = "[a-zA-Z]{2}\\d{4}";

    /**
     * first 2 are letters, than 4 digits
     * source: https://stackoverflow.com/questions/10439666/regex-pattern-any-two-letters-followed-by-six-numbers
     */
    public void validate(Client t) {
        Pattern pattern = Pattern.compile(onlyNumbersRegex);
        if (!pattern.matcher(t.getIdCard()).matches()) {
            throw new IllegalArgumentException("IdCard is not valid!");
        }
    }
}
