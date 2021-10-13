/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healing.stream.bot;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import java.awt.*;


/**
 *
 * @author zakir
 */
public class HealingStreamBOT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");

      Healing_Stream_Bot_Main main = new Healing_Stream_Bot_Main();
      main.setVisible(true);
    }
    
}
