import database.student.StudentService;
import database.utils.BUGUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class tests {
    private StudentService service;

    @BeforeEach
    void init() {

        service = new StudentService();
    }

//    @Test
//    void verifyAccount1() {
//        assertTrue(service.verifyAccount("greg_hamerly1@baylor.edu"));
//    }
//
//    @Test
//    void verifyAccount2() {
//        assertFalse(service.verifyAccount("renadommmmemailallalal@baylor.edu"));
//    }

//    @Test
//    void testEmail1(){
//        service.sendPasswordReset("Gabriel_Goulis1@baylor.edu");
//    }

    @Test
    void createAccount(){
        String password = BUGUtils.controller.generatePassword(8);
        service.sendRegisterEmail("bryce_robinson1@baylor.edu", password);
        BUGUtils.controller.registerStudent("gabriel_goulis1", password, "Gabriel Goulis", "8325442556", "gabriel_goulis1@baylor.edu");

    }


}
