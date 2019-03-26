/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
}
