package sd.bll;

import sd.bll.validators.Validator;
import sd.bll.validators.client.*;
import sd.dao.ClientDAO;
import sd.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientBLL {
    private static List<Validator<Client>> validators;

    public ClientBLL() {
        validators = new ArrayList<>();
        validators.add(new PNCValidator());
        validators.add(new AddressValidator());
        validators.add(new NameValidator());
        validators.add(new IdCardValidator());
        validators.add(new IdClientValidator());
    }

    public static int insertClient(Client client) {
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        return ClientDAO.insert(client);
    }

    public static int updateClient(Client client) {
        for (Validator<Client> v : validators) {
            v.validate(client);
        }
        return ClientDAO.update(client);
    }

    public Client findClientById(int idClient) {
        Client client = ClientDAO.findById(idClient);
        if (client == null) {
            throw new NoSuchElementException("The client with id =" + idClient + " was not found!");
        }
        return client;
    }
}
