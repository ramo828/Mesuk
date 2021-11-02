/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import Main.login;
import java.awt.Button;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ramo828
 */
public class test {

    public static void main(String[] args) throws SQLException {
        Db d = new Db();
        d.init();

        
         //d.inHesabat(1, 2, 500);
        //String Server = Db.getDataString(10, "Select * from settings");;
        System.out.println(d.getMaxMsg(1));
    }
}
