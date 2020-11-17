package org.com.test.AutoHero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class SearchTest {
	WebDriver driver;
	@org.testng.annotations.Test
	public void Test() throws InterruptedException {
		String localDir = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", localDir + "\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
        driver.get("https://www.autohero.com/de/search/");
        driver.findElement(By.id("firstRegistrationFilter")).click();
        driver.findElement(By.id("firstRegistrationFilter")).click();
        driver.findElement(By.id("firstRegistrationFilter")).click();
        System.out.println("yes");

        //Selecting Range FIlter
        WebElement rangeStartFilter =  driver.findElement(By.xpath("//div[@aria-labelledby='firstRegistrationFilter']//select[@id=\"rangeStart\"]"));
        Select minYear = new Select(rangeStartFilter);
        Thread.sleep(3000);
        minYear.selectByVisibleText("2015");
        
        //Select cars by price descending
        WebElement SortFilter =  driver.findElement(By.xpath("//select[@id='sortBy']"));
        Select sortCars = new Select(SortFilter);
        Thread.sleep(3000);
        sortCars.selectByValue("2");
        
        //Click footer
        driver.findElement(By.xpath("//div[@class='copyright___21Cvn']")).click();
        
        //Click title
        driver.findElement(By.xpath("//*[@id='app']/div/main/div/h1")).click();
        Thread.sleep(4000);
        
        //Verify the results
        List<WebElement> elementList =  driver.findElements(By.xpath("//div[@class='ReactVirtualized__Grid__innerScrollContainer']/div//li[@class='specItem___2gMHn'][1]"));
        System.out.println(elementList);
        List<Integer> yearValues = new ArrayList<Integer>();

        
        for(WebElement ele : elementList) {
        int year = Integer.parseInt(ele.getText());
        yearValues.add(year);
        
        if(year>=2015) {
            System.out.println(ele.getText());
            System.out.println("Filter worked");
        }
        else {
            System.out.println(ele.getText());
            System.out.println("Filter not worked");
        }
        }
        
        System.out.println("Verifying Sorting filter");
        System.out.println(yearValues);
        List<Integer> tmp = new ArrayList(yearValues);
        Collections.sort(tmp);
        Collections.reverse(tmp);
        System.out.println(tmp);
        boolean sorted = tmp.equals(yearValues);
        Assert.assertTrue(sorted);
       
	}
	
		@AfterMethod
		public void BrowserQuit() {
		        driver.quit();

			}
	
	
}
