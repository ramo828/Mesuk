/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import database.Db;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import util.io.file;

/**
 *
 * @author ramo828
 */
public class main {

    
    public static void step(int stp, int second) throws AWTException, InterruptedException {
        Robot r = new Robot();
        for (int i = 1; i <= stp; i++) {

            r.delay(second);
            System.out.println(i);
        }

    }
    
    public static void main(String[] args) throws IOException, SQLException, AWTException, InterruptedException {
       step(10,100);
    }

}
