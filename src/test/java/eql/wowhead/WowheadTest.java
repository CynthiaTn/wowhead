package eql.wowhead;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.openqa.selenium.JavascriptExecutor;


public class WowheadTest {
	
	WebDriver driver;
	static Logger logger = LoggerFactory.getLogger(WowheadTest.class);
	
	private String BROWSER =System.getProperty("browser");
	


	@Before

	public void setup() {
		
		 
		if (BROWSER.equalsIgnoreCase(("chrome"))) {
			System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver2.exe");
			driver = new ChromeDriver();
		}
		else if (BROWSER.equalsIgnoreCase(("firefox"))) {
			System.setProperty("webdriver.gecko.driver", "src/main/resources/driver/geckodriver.exe");
			driver =  new FirefoxDriver();
		}else if (BROWSER.equalsIgnoreCase(("edge"))) {
			System.setProperty("webdriver.edge.driver", "src/main/resources/driver/msedgedriver.exe");
			driver =  new EdgeDriver();
		}else if (BROWSER.equalsIgnoreCase(("explorer"))) {
			System.setProperty("webdriver.ie.driver", "src/main/resources/driver/IEDriverServer.exe");
			driver =  new InternetExplorerDriver();
		}else {
			
			logger.warn("This web browser doesn't exist");
		}
		
		driver.manage().window().maximize()	;
	

	}

	@After
	public void teardown() {
	driver.quit();
	}	
	
	@Test
	public void TestWohead() throws Exception {
	
	driver.get("https://fr.wowhead.com/");
	WebDriverWait wait  = new WebDriverWait (driver,20);
	
	
	Thread.sleep(5000);
	WebElement acceptCookies = driver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]"));
	acceptCookies.click();
	
	WebElement refusNotif = driver.findElement(By.xpath("//div[@class='notifications-dialog-buttons']/button[text()='Refuser']"));
	Thread.sleep(5000);
	wait.until(ExpectedConditions.elementToBeClickable(refusNotif));
	refusNotif.click();;
	
	

	
	WebElement elementSearch = driver.findElement(By.xpath("//*[@name='q']"));
	elementSearch.click();
	elementSearch.clear();
	elementSearch.sendKeys("Lardeur");
	
	
	
	WebElement btnSearch = driver.findElement(By.xpath("//*[@class='header-search-button fa fa-search']"));
	
	wait.until(ExpectedConditions.elementToBeClickable(btnSearch));
	
	btnSearch.click();
	
	
	driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

	
	WebElement btnBoss = driver.findElement(By.xpath("//*[@class='icon-boss-padded']/a[contains(.,'Lardeur')]"));
	
	
	try{
	btnBoss.click();
	} catch (org.openqa.selenium.TimeoutException te) {
		((JavascriptExecutor) driver).executeScript("window.stop();");
	}
	
	
	
	
	/////////////////Chahuteurs de cadavre////////////////////////////////////////////////////////////////////////////////////////////
	WebElement btnButin1 = driver.findElement(By.xpath("//*[@class='q3 listview-cleartext'][contains(.,'Chahuteurs de cadavre')]"));
	btnButin1.click();

	
	List<WebElement> liste = driver
			.findElements(By.xpath("//div[@class][@id]/table//span"));

	ArrayList<String> liste_col_ft = new ArrayList<String>();
	ArrayList<String> jdd_liste_car = new ArrayList<String>();

	for (WebElement l : liste) {
		liste_col_ft.add(l.getText());
	}

	jdd_liste_car.add("Niveau d'objet 57");
	jdd_liste_car.add("Tissu");
	jdd_liste_car.add("Armure : 2");
	jdd_liste_car.add("+12 Intelligence");
	jdd_liste_car.add("+18 Endurance");
	jdd_liste_car.add("Augmente votre score de hâte de +13");
	jdd_liste_car.add("+10 Versatilité");
	jdd_liste_car.add("36");
	jdd_liste_car.add("76");
	jdd_liste_car.add("39");
	
	
	assertEquals("Assert KO pour Chahuteurs de cadavre",jdd_liste_car,liste_col_ft);
	logger.info("Tout est présent : Chahuteurs de cadavre");
	
	
	

	try{
	driver.navigate().back();
	} catch (org.openqa.selenium.TimeoutException te) {
		((JavascriptExecutor) driver).executeScript("window.stop();");
	}
Thread.sleep(5000);
	
/////////////////Chausses de Lardeur////////////////////////////////////////////////////////////////////////////////////////////
	WebElement btnButin2 = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[text()='Chausses de Lardeur']"))));
	
	btnButin2.click();


	List<WebElement> liste2 = driver.findElements(By.xpath("//div[@class][@id]/table//span"));

	ArrayList<String> liste_col_2 = new ArrayList<String>();
	ArrayList<String> jdd_liste_car2 = new ArrayList<String>();

	for (WebElement l2 : liste2) {
	liste_col_2.add(l2.getText());
	}
	
	jdd_liste_car2.add("Niveau d'objet 57");
	jdd_liste_car2.add("Cuir");
	jdd_liste_car2.add("Armure : 6");
	jdd_liste_car2.add("+16 [Agilité or Intelligence]");
	jdd_liste_car2.add("+24 Endurance");
	jdd_liste_car2.add("Augmente votre score de coup critique de +21");
	jdd_liste_car2.add("+12 Versatilité");
	jdd_liste_car2.add("52");
	jdd_liste_car2.add("73");
	jdd_liste_car2.add("92");


	assertEquals("Assert KO pour Chausses de Lardeur",jdd_liste_car2,liste_col_2);
	logger.info("Tout est présent pour : Chausses de Lardeur");

	
	try{
	driver.navigate().back();
	} catch (org.openqa.selenium.TimeoutException te) {
		((JavascriptExecutor) driver).executeScript("window.stop();");
	}
	Thread.sleep(5000);
	
	/////////////////Pioche en fer froid////////////////////////////////////////////////////////////////////////////////////////////
	WebElement btnButin3 = driver.findElement(By.xpath("//a[text()='Pioche en fer froid']"));
	wait.until(ExpectedConditions.elementToBeClickable(btnButin3));
	btnButin3.click();
	
	
	List<WebElement> liste3 = driver.findElements(By.xpath("//div[@class][@id]/table//span"));
	
	ArrayList<String> liste_col_3 = new ArrayList<String>();
	ArrayList<String> jdd_liste_car3 = new ArrayList<String>();
	
	for (WebElement l3 : liste3) {
	liste_col_3.add(l3.getText());
	}
	
	jdd_liste_car3.add("Niveau d'objet 57");
	jdd_liste_car3.add("Hache");
	jdd_liste_car3.add("Dégâts : 4 - 10");
	jdd_liste_car3.add("+16 Force");
	jdd_liste_car3.add("+24 Endurance");
	jdd_liste_car3.add("Augmente votre score de coup critique de +13");
	jdd_liste_car3.add("Augmente votre score de hâte de +19");
	jdd_liste_car3.add("\"Peut aussi servir de pioche de mineur.\"");
	jdd_liste_car3.add("95");
	jdd_liste_car3.add("85");
	jdd_liste_car3.add("40");
	
	assertEquals("Assert KO pour Pioche en fer froid",jdd_liste_car3,liste_col_3);
	logger.info("Tout est présent pour : Pioche en fer froid");
	
	
	try{
	driver.navigate().back();
	} catch (org.openqa.selenium.TimeoutException te) {
		((JavascriptExecutor) driver).executeScript("window.stop();");
	}
	Thread.sleep(6000);
	
	/*
	/////////////////grèves-de-geôlier-volées////////////////////////////////////////////////////////////////////////////////////////////
	WebElement btnButin4 = driver.findElement(By.xpath("//a[text()='Grèves de geôlier volées']"));
	//wait.until(ExpectedConditions.elementToBeClickable(btnButin4));
	btnButin4.click();

	
	List<WebElement> liste4 = driver.findElements(By.xpath("//div[@class][@id]/table//span"));
	
	ArrayList<String> liste_col_4 = new ArrayList<String>();
	ArrayList<String> jdd_liste_car4 = new ArrayList<String>();
	
	for (WebElement l4 : liste4) {
	liste_col_4.add(l4.getText());
	}
	
	jdd_liste_car4.add("Niveau d'objet 57");
	jdd_liste_car4.add("Mailles");
	jdd_liste_car4.add("Armure : 8");
	jdd_liste_car4.add("+16 [Agilité or Intelligence]");
	jdd_liste_car4.add("+24 Endurance");
	jdd_liste_car4.add("Augmente votre score de coup critique de +19");
	jdd_liste_car4.add("Augmente votre score de hâte de +12");
	jdd_liste_car4.add("51");
	jdd_liste_car4.add("59");

	
	assertEquals("Assert KO pour Grèves-de-geôlier-volées",jdd_liste_car4,liste_col_4);
	logger.info("Tout est présent pour : Grèves-de-geôlier-volées");
	
	

	try{
	driver.navigate().back();
	} catch (org.openqa.selenium.TimeoutException te) {
		((JavascriptExecutor) driver).executeScript("window.stop();");
	}
	Thread.sleep(5000);
	
	/////////////////espauliers-de-tourne-clé////////////////////////////////////////////////////////////////////////////////////////////
	WebElement btnButin5 = driver.findElement(By.xpath("//div[@class]/a[@href='/item=151074/espauliers-de-tourne-clé']"));
	wait.until(ExpectedConditions.elementToBeClickable(btnButin5));
	btnButin5.click();

	
	List<WebElement> liste5 = driver.findElements(By.xpath("//div[@class][@id]/table//span"));
	
	ArrayList<String> liste_col_5 = new ArrayList<String>();
	ArrayList<String> jdd_liste_car5 = new ArrayList<String>();
	
	for (WebElement l5: liste5) {
	liste_col_4.add(l5.getText());
	}
	
	jdd_liste_car5.add("Niveau d'objet 57");
	jdd_liste_car5.add("Mailles");
	jdd_liste_car5.add("Armure : 8");
	jdd_liste_car5.add("+16 [Agilité or Intelligence]");
	jdd_liste_car5.add("+24 Endurance");
	jdd_liste_car5.add("Augmente votre score de coup critique de +19");
	jdd_liste_car5.add("Augmente votre score de hâte de +12");
	jdd_liste_car5.add("51");
	jdd_liste_car5.add("59");

	
	assertEquals("Assert KO pour Espauliers-de-tourne-clé",jdd_liste_car5,liste_col_5);
	logger.info("Tout est présent pour : Espauliers-de-tourne-clé"); 
	*/
	
	}
	

}
