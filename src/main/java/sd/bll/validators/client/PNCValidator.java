package sd.bll.validators.client;

import sd.bll.validators.Validator;
import sd.model.Client;

import java.util.regex.Pattern;

public class PNCValidator implements Validator<Client> {
    private static final String onlyNumbers = "^[1-2][2-9][0-9][0-1][0-9][0-3][0-9][0-9][0-9]$";

    /**
     * 1- men, 2 - women
     * next to digits are the year when born
     * next to digits are the month when born
     * next to digits are the day when born
     * next to digits are random just to be different from person to person
     * ex: 297120177
     */
    public void validate(Client t) {
        Pattern pattern = Pattern.compile(onlyNumbers);
        if (!pattern.matcher(t.getPnc()).matches()) {
            throw new IllegalArgumentException("PNC is not valid!");
        }
    }

}