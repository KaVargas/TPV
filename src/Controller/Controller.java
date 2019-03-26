/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ConectionDB;

/**
 *
 * @author kened
 */
public class Controller {
    
    ConectionDB conn = new ConectionDB();

    public void conectarDB() {
        conn.conectionBD();
    }
    
}
