package sd;

import org.junit.Assert;
import org.junit.Test;
import sd.dao.EmployeeDAO;
import sd.model.Employee;
import sd.model.Role;

public class EmployeeDaoTest {

    @Test
    public void testFindByUsername() {
        Employee employee = EmployeeDAO.findByUsername("roxie97");
        Assert.assertEquals(employee.getUsername(), "roxie97");
        Assert.assertEquals(employee.getRole(), Role.ADMIN);
    }
}
