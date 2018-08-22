package ua.com.rozetka;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutoTests {

    private WebDriver driver;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
}
    @After
    public void tearDown(){
    driver.close();
 }

    @Test
    public void firstTest() {

        driver.get("http://www.rozetka.com.ua");

        driver.findElement(By.xpath("//a[@name='signin']")).click();
        driver.findElement(By.xpath("//input[@name='login']")).sendKeys("mailforautotest@ukr.net");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("19Aug2018");
        driver.findElement(By.xpath("//button[@name='auth_submit']")).click();

        driver.findElement(By.xpath("//a[contains(text(),'Товары для дома')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Мебель')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Бескаркасная мебель')]")).click();

        Assert.assertEquals("They are not equals", "https://rozetka.com.ua/beskarkasnaya-mebel/c154006/", driver.getCurrentUrl());

        }

        @Test
        public void secondTest() throws InterruptedException {

            driver.get("https://rozetka.com.ua/beskarkasnaya-mebel/c154006/");
            driver.findElement(By.xpath("//a[contains(text(),'Кресло Злая Птица Bel.i.v. Red (1162)')]")).click();
            driver.findElement(By.xpath("//button[@name='topurchases']")).click();

            Thread.sleep(1000);
            driver.get("https://my.rozetka.com.ua/cart/");
            
            Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'Кресло Злая Птица Bel.i.v. Red (1162)')]")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.xpath("//button[contains(text(),'Оформить заказ')]")).isEnabled());
    }
}
