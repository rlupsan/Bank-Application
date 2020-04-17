package sd.bll;

import sd.bll.validators.Validator;
import sd.bll.validators.employee.PasswordValidator;
import sd.bll.validators.employee.UsernameValidator;
import sd.dao.EmployeeDAO;
import sd.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeBLL {
    private List<Validator<Employee>> validators;

    public EmployeeBLL() {
        validators = new ArrayList<>();
        validators.add(new PasswordValidator());
        validators.add(new UsernameValidator());
    }

    public int insertEmployee(Employee employee) {
        for (Validator<Employee> v : validators) {
            v.validate(employee);
        }
        return EmployeeDAO.insert(employee);
    }

    public int updateEmployee(Employee employee) {
        for (Validator<Employee> v : validators) {
            v.validate(employee);
        }
        return EmployeeDAO.update(employee);
    }

    public String login(String username, String password) {
        Employee inDb = EmployeeDAO.findByUsername(username);
        if (inDb == null)
            return "login";
        if (inDb.getPassword().equals(password)) {
            return inDb.getRole().toString().toLowerCase();
        }
        return "login";
    }
}
