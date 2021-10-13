/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healing.stream.bot;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author zakir
 */
public class AutoReg extends Thread{


    String firstname="";
    String lastname="";
    String email="";
    String mobilenumber="";
    private void nameandemail(){
        try {
            BufferedReader br = null;
            br = new BufferedReader(new FileReader("C:\\ChromeDriver\\Leads.csv"));
            String line = "";

            String splitBy = ",";
            while ((line = br.readLine()) != null) {
                String[] all = line.split(splitBy);    // use comma as separator
                firstname = all[0];
                lastname = all[1];
                email = all[2];
                break;
            }
            br.close();
        }catch (Exception e){}

    }

    private void getmobilenumber(){
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        driver.get("https://www.randomlists.com/phone-numbers?dup=false&qty=1");
        mobilenumber= driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Options'])[1]/following::li[1]")).getText();
        driver.close();
        driver.switchTo().window(tabs2.get(0));
    }
    private void keepchecking() {
        if (Healing_Stream_Bot_Main.status==false){
            Healing_Stream_Bot_Main.statusLBL.setText("Qquiting...");
            driver.quit();
        }
    }

    public static int createRandomnumber() {
        int min = 0;
        int max = 50;
        int number = 0;
        number = (int)(Math.random()*(max-min+1)+min);
        return number;
    }
    public WebDriver driver;

    @Override
    public void run() {
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() { public void run() {  keepchecking(); } },0,  1000);  // run every three seconds

        ChromeOptions options = new ChromeOptions();

        ChromeDriverService driverService = ChromeDriverService.createDefaultService();

        driver = new ChromeDriver(driverService, options);


        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.manage().window().maximize();

        driver.get("https://healingstreams.tv/3days/online_reg.php");
        driver.findElement(By.xpath("//*[@id='button']")).click();
        nameandemail();
        driver.findElement(By.xpath("//div[9]/form/inpur/div/div/input")).click();
        driver.findElement(By.xpath("//div[9]/form/inpur/div/div/input")).clear();
        driver.findElement(By.xpath("//div[9]/form/inpur/div/div/input")).sendKeys(firstname);
        driver.findElement(By.xpath("//div[9]/form/inpur/div/div[2]/input")).click();
        driver.findElement(By.xpath("//div[9]/form/inpur/div/div[2]/input")).clear();
        driver.findElement(By.xpath("//div[9]/form/inpur/div/div[2]/input")).sendKeys(lastname);
        driver.findElement(By.id("email")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("country1")).click();
        int number= createRandomnumber();

        new Select(driver.findElement(By.id("country1"))).selectByIndex(number);
        driver.findElement(By.id("state1")).click();
        new Select(driver.findElement(By.id("state1"))).selectByIndex(1);
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='State or Province (Select country first)'])[1]/following::div[2]")).click();
        driver.findElement(By.xpath("//div[9]/form/inpur/div[5]/div/input")).clear();
        driver.findElement(By.xpath("//div[9]/form/inpur/div[5]/div/input")).sendKeys("OELOSER");
        driver.findElement(By.xpath("//div[9]/form/inpur/div[6]/input")).click();
        driver.findElement(By.id("virtual2")).click();
        driver.findElement(By.id("ptranslate2")).click();
        driver.findElement(By.xpath("//div[9]/form/inpur/input")).click();
        //2nddddddddddddddddddddddddddddddddddddd Page
        driver.findElement(By.id("gender")).click();
        new Select(driver.findElement(By.id("gender"))).selectByVisibleText("MALE");
        driver.findElement(By.id("phone")).click();
        driver.findElement(By.id("phone")).click();
        driver.findElement(By.id("phone")).clear();
        ((JavascriptExecutor)driver).executeScript("window.open(\"\", '_blank').focus();");
        getmobilenumber();
        driver.findElement(By.id("phone")).sendKeys(mobilenumber);
        driver.findElement(By.id("translate")).click();
        new Select(driver.findElement(By.id("translate"))).selectByVisibleText("English");
        driver.findElement(By.id("cond")).click();
        driver.findElement(By.id("cond")).clear();
        driver.findElement(By.id("cond")).sendKeys("ILL");
        driver.findElement(By.id("duration")).click();
        driver.findElement(By.id("duration")).clear();
        driver.findElement(By.id("duration")).sendKeys("Condition");
        driver.findElement(By.id("nok")).click();
        driver.findElement(By.id("nok")).clear();
        driver.findElement(By.id("nok")).sendKeys(firstname+lastname);
        driver.findElement(By.id("nok_phone")).click();
        driver.findElement(By.id("nok_phone")).clear();

        driver.findElement(By.id("nok_phone")).sendKeys(mobilenumber);
        driver.findElement(By.id("file")).sendKeys("C:\\ChromeDriver\\Buster.crx");
        driver.findElement(By.id("virtual2")).click();
        driver.findElement(By.xpath("//input[@value='Submit']")).click();
    }
}
