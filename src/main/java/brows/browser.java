package brows;

import Main.login;
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import database.Db;
import Main.*;
import static database.IQueries.settingQuery;
import java.io.File;
import java.sql.SQLException;
import util.io.file;

/**
 *
 * @author ramo828
 */
public class browser {

    public void browser() {
    }
    private static boolean stopVar = false;
    private static int myID = 0;
    private static String userTable = "users";
    private static String url = "https://web.whatsapp.com";

    private static void delay(int second, boolean mode) throws InterruptedException {
        if (mode) {
            Thread.sleep(second * 1000);
        } else {
            Thread.sleep(second);
        }
    }

    public static void setID(int ID) {
        myID = ID;
    }

    public static void setStop(boolean var) {
        stopVar = var;
    }

    public static int coin() throws SQLException {
        Db d = new Db();
        d.init();
        int coins = d.getDataInt(8, myID, userTable);
        return coins;
    }

    private static void setCoin(int coin) {
        Db d = new Db();
        d.init();
        d.setCoin(myID, coin);
    }

    private static int module(int coin, int count) {
        int value = count * (-1) + coin;
        return value;
    }

    private static boolean Shift(Character c) {
        return Character.isUpperCase(c);
    }

    public static void sendKeys(Robot robot, String keys) {
        for (char c : keys.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                throw new RuntimeException(
                        "Key code not found for character '" + c + "'");
            }
            robot.keyPress(keyCode);
            robot.delay(200);
            robot.keyRelease(keyCode);
            robot.delay(200);
        }
    }

    public static void sendKeys(Robot robot, String keys, int delay) {
        for (char c : keys.toCharArray()) {
            if (Shift(c)) {
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.delay(100);
            }
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                throw new RuntimeException(
                        "Key code not found for character '" + c + "'");
            }

            robot.keyPress(keyCode);
            robot.delay(delay);
            robot.keyRelease(keyCode);
            robot.delay(delay);

            if (Shift(c)) {
                robot.keyRelease(KeyEvent.VK_SHIFT);
                robot.delay(100);
            }
        }
    }

    private static void openUrl(String url) throws IOException, URISyntaxException {
        if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

            if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                java.net.URI uri = new java.net.URI(url);
                desktop.browse(uri);
            }
        }
    }

    private static void newMessageButton() {
        try {
            Robot r = new Robot();
            // Simulate a key press
            r.keyPress(KeyEvent.VK_CONTROL);
            r.keyPress(KeyEvent.VK_ALT);
            r.keyPress(KeyEvent.VK_N);
            r.keyRelease(KeyEvent.VK_CONTROL);
            r.keyRelease(KeyEvent.VK_ALT);
            r.keyRelease(KeyEvent.VK_N);
        } catch (AWTException e) {
        }
    }

    public void inputMessage(String text) {
        StringSelection stringSelection = new StringSelection(text.toString());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    private static void runUI() {
        new browserUI().setVisible(true);
    }

    private static void paste(Robot r) throws AWTException {
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_V);
        r.delay(200);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_V);
        r.delay(200);

    }

    private static void newTab(Robot r) throws AWTException {
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_T);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_T);
    }

    private static void selectAll(Robot r) throws AWTException {
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_A);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_A);
    }

    private static void copyAll(Robot r) throws AWTException {
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_C);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_C);
    }

    private static void closeTab(Robot r) throws AWTException {
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_W);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_W);
    }

    private static void algo(Robot r, int prefSelect) throws SQLException, IOException, AWTException, InterruptedException {
        String data;
        Db d = new Db();
        d.init();
        file f = new file();
        String fileName = d.getDataString(6, settingQuery);
        String dir = f.getDir();
        String fn = dir + "/" + fileName;

        f.setUrl(fn);
        File fd = f.fileInit();
        f.createFile(fd);
        System.out.println(fn);
        if (prefSelect == 0) {
            data = d.getDataString(10, myID, "users");
        } else {
            data = d.getDataString(11, myID, "users");
        }
        f.writeFile(fd, data);
        delay(100, false);
        newTab(r);
        delay(100, false);
        sendKeys(r, fn, 50);
        delay(100, false);
        enter(r);
        delay(1, true);
        selectAll(r);
        delay(300, false);
        copyAll(r);
        delay(500, false);
        closeTab(r);
    }

    private static void step(int stp, int second) throws AWTException, InterruptedException {
        Robot r = new Robot();
        for (int i = 0; i < stp; i++) {
            delay(second, false);
            r.keyPress(KeyEvent.VK_DOWN);
            r.keyRelease(KeyEvent.VK_DOWN);
        }

    }

    private static void enter(Robot r) {
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);
    }

    public void defaultBrowserWhatsapp(
            String findContact,
            String msg,
            int stepStart,
            int stepTime,
            boolean browserStatus,
            int startTime,
            int limit, int pref)
            throws IOException, URISyntaxException, InterruptedException, AWTException, SQLException {
        int coinMax = coin();
        int count = 0;
        int onlyOne = 0;
        Robot r = new Robot();
        access a = new access();
        Db db = new Db();
        db.init();

        if (browserStatus) {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
            }
            startTime += 10;
        }
        a.setID(myID);
        delay(startTime, true);
        if (!stopVar) {
            algo(r, pref);
        }
        while (true) {
            int maxMsg = db.getMaxMsg(myID);
            if (stopVar) {
                break;
            }
            if (db.isStatus_db()) {
                if (coinMax <= count) {
                    a.init();
                    a.setVisible(true);
                    login.alert("Balansınızda kifayət qədər coin yoxdur!\n Zəhmət olmasa baş menecerə müraciət edin", 1);
                    break;
                }
                if (count == limit - (stepStart)) {
                    a.init();
                    a.setVisible(true);
                    login.alert("Ayarladığınız limitə çatdı " + String.valueOf(stepStart + (count)), 1);
                    break;
                } else if (count > limit) {
                    a.init();
                    a.setVisible(true);
                    login.alert("Limiti keçdi", 1);
                    break;
                }

                newMessageButton();
                sendKeys(r, " " + findContact);
                step(stepStart + count, stepTime);
                enter(r);
                delay(1, true);
                paste(r);
                delay(1, true);
                enter(r);
                count++;
                
                if (stepStart < 1) {
                    db.setMaxMsg(myID, maxMsg + count);
                } else {
                    if(onlyOne == 0)
                    db.setMaxMsg(myID, maxMsg+(limit-stepStart));
                }
                if (stepStart < 1)
                setCoin(module(coinMax, count));
                else {
                    if(onlyOne == 0)
                setCoin(coinMax-(limit-stepStart));
                    onlyOne = 1;
                }
            } else {
                login.alert("Internet əlaqəsi kəsildi!", 1);
            }
        }

    }
}
