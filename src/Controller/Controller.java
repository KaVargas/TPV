/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ClientDAO;
import Model.ConectionDB;
import Model.EmployeeDAO;
import Model.ProductDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author kened
 */
public class Controller {
    
    ConectionDB conn = new ConectionDB();

    public void conectarDB() {
        conn.conectionBD();
    }

    public void insertClient(ClientDAO client) {
        if(conn.insertClient(client)){
            JOptionPane.showMessageDialog(null, "Usuario ingresado exitosamente");
        }else{
            JOptionPane.showMessageDialog(null, "Algo pasó, intentalo de nuevo!");
        }
    }

    public boolean isComplete(ClientDAO client) {
        return (!(client.getCedula().isEmpty() || client.getNames().isEmpty() || client.getLastNames().isEmpty()
                || client.getCity().isEmpty() || client.getAddress().isEmpty() || client.getPhone().isEmpty()
                || client.getEmail().isEmpty()));
    }

    public boolean isComplete(EmployeeDAO employee) {
        return (!( employee.getCedula().isEmpty() || employee.getPassword().isEmpty()));
    }

    public void insertEmployee(EmployeeDAO employee) {
        if(conn.insertEmployee(employee)){
            JOptionPane.showMessageDialog(null, "Empleado ingresado exitosamente");
        }else{
            JOptionPane.showMessageDialog(null, "Algo pasó, intentalo de nuevo!");
        }
    }

    public ClientDAO getClient(String text) {
        ClientDAO client = conn.getClient(text);
        return client;
    }

    public boolean isComplete(ProductDAO product) {
        return (!(product.getDescription().isEmpty() || product.getCategory().isEmpty()));
    }

    public void insertProduct(ProductDAO product) {
        if(conn.insertProduct(product)){
            JOptionPane.showMessageDialog(null, "Producto ingresado exitosamente");
        }else{
            JOptionPane.showMessageDialog(null, "Algo pasó, intentalo de nuevo!");
        }
    }

    public ProductDAO getProduct(String text) {
        ProductDAO product = conn.getProduct(text);
        return product;
    }
}
