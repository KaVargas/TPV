/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author kened
 */
public class EmployeeDAO extends ClientDAO{
    private String password;

    public EmployeeDAO() {
    }

    public EmployeeDAO(String password) {
        this.password = password;
    }

    public EmployeeDAO(String password, String cedula, String names, String lastNames, String city, String address, String phone, String email) {
        super(cedula, names, lastNames, city, address, phone, email);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
