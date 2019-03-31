/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author kened
 */
public class ConectionDB {

    //------Atributos de la clase----------
    String jdbcDriver = ("com.mysql.jdbc.Driver");//drivers
    String jdbcSup = ("mysql");//base de datos
    String jdbcHost = ("localhost");//servidor
    String jdbcBd = ("tpv");//nombre de la base de datos
    String jdbcUser = ("root");//usuario
    String jdbcPwd = ("12345678");//pongo mi clave
    Connection con = null;

    public ConectionDB() {
    }

    public Connection conectionBD() {

        try {
            Class.forName(jdbcDriver);
            String strcon = "jdbc:" + jdbcSup + "://" + jdbcHost + "/" + jdbcBd;
            con = DriverManager.getConnection(strcon, jdbcUser, jdbcPwd);
            System.out.println(con.toString());
            return con;
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "mensaje fallo de conexion", JOptionPane.ERROR_MESSAGE);
            System.out.println("Driver Incorrecto" + ex.getMessage());
            return null;
        } catch (SQLException exsql) {
            JOptionPane.showMessageDialog(null, exsql.getMessage(), "mensaje", JOptionPane.ERROR_MESSAGE);
            System.out.println("BDD INCORRECTA" + exsql.getMessage());
            return null;
        }
    }

    public boolean insertClient(ClientDAO client) {

        String sql = "INSERT INTO person(person_id, name_person, lastName_person, city, address, phone, mail) VALUES(?,?,?,?,?,?,?)";

        try (Connection conn = this.conectionBD();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, client.getCedula());
            pstmt.setString(2, client.getNames());
            pstmt.setString(3, client.getLastNames());
            pstmt.setString(4, client.getCity());
            pstmt.setString(5, client.getAddress());
            pstmt.setString(6, client.getPhone());
            pstmt.setString(7, client.getEmail());
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean insertEmployee(EmployeeDAO employee) {
        String sql = "INSERT INTO employee(password, person_id) VALUES(?,?)";

        try (Connection conn = this.conectionBD();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, employee.getPassword());
            pstmt.setString(2, employee.getCedula());
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public ClientDAO getClient(String text) {

        try {

            Connection conn = conectionBD();
            // our SQL SELECT query. 
            // if you only need a few columns, specify them by name instead of using "*"
            String query = "SELECT * FROM person WHERE person_id LIKE '";
            query += text;
            query += "'";

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);
            ClientDAO client = new ClientDAO();
            while (rs.next()) {
                client.setNames(rs.getString("name_person"));
                client.setLastNames(rs.getString("lastName_person"));
                client.setCity(rs.getString("city"));
                client.setAddress(rs.getString("address"));
                client.setPhone(rs.getString("phone"));
                client.setEmail(rs.getString("mail"));
            }
            conn.close();
            return client;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean insertProduct(ProductDAO product) {
        String sql = "INSERT INTO product(product_id, description, price, category) VALUES(?,?,?,?)";

        try (Connection conn = this.conectionBD();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, product.getCode());
            pstmt.setString(2, product.getDescription());
            pstmt.setFloat(3, product.getPrice());
            pstmt.setString(4, product.getCategory());
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public ProductDAO getProduct(String text) {
        try {

            Connection conn = conectionBD();
            // our SQL SELECT query. 
            // if you only need a few columns, specify them by name instead of using "*"
            String query = "SELECT * FROM product WHERE product_id LIKE '";
            query += text;
            query += "'";

            // create the java statement
            Statement st = conn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);
            ProductDAO product = new ProductDAO();
            while (rs.next()) {
                product.setCode(rs.getInt("product_id"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getFloat("price"));
                product.setCategory(rs.getString("category"));
            }
            conn.close();
            return product;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
