package test.selenium.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Browser {

    public static void main(String [] args) throws InterruptedException, IOException {
//        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        Dimension size = driver.manage().window().getSize();

//        int height = driver.manage().window().getSize().getHeight();
        int width = size.getWidth();
        System.out.println(width);

//        int height = driver.manage().window().getSize().getHeight();
        int height = size.getHeight();
        System.out.println(height);

        driver.manage().window().setSize(new Dimension(width/2, height/2));
        Thread.sleep(1000);

        driver.manage().window().maximize();
        Thread.sleep(1000);

        driver.manage().window().minimize();
        Thread.sleep(1000);

        driver.manage().window().fullscreen();
        Thread.sleep(1000);

        driver.manage().window().setSize(new Dimension(width/2, height/2));
        Thread.sleep(1000);

        driver.get("https://google.com");
        String originalWindow = driver.getWindowHandle();

        File scrGoogleFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrGoogleFile, new File("./screenshots/googleImage.png"));

        WebElement element = driver.findElement(By.cssSelector(".lnXdpd"));
        File scrElementFile = element.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrElementFile, new File("./screenshots/elementImage.png"));

        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebElement button = driver.findElement(By.id("gbqfbb"));
        js.executeScript("arguments[0].click();", button);
        Thread.sleep(5000);
        js.executeScript("console.log('hello world')");
        Thread.sleep(10000);

        driver.navigate().to("https://automationstepbystep.com");

        File scrStepByStepFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrStepByStepFile, new File("./screenshots/stepByStepImage.png"));

        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());

        driver.navigate().back();
        Thread.sleep(1000);
        driver.navigate().forward();
        Thread.sleep(1000);
        driver.navigate().refresh();
        Thread.sleep(1000);

        driver.switchTo().window(originalWindow);
        Thread.sleep(1000);

        driver.switchTo().newWindow(WindowType.WINDOW);
        Thread.sleep(1000);
        driver.switchTo().newWindow(WindowType.TAB);
        Thread.sleep(1000);
        driver.switchTo().window(originalWindow);

        Thread.sleep(1000);
        driver.close();
        driver.quit();
    }

}