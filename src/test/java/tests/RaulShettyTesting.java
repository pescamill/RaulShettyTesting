package tests;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RaulShettyTesting {
	
	private static String URI = "https://rahulshettyacademy.com/AutomationPractice/";
	private static WebDriver driver;
	
	
	// Setup the URL
	@BeforeTest
	public static void setup() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
		driver = new ChromeDriver();
		driver.get(URI);
	}
	
	// Click a radio button and assert it's selected
	@Test(priority = 1)
	public static void radioButtonTest() {
		WebElement optionOneRadio = driver.findElement(By.cssSelector("input[value='radio1']"));
		
		optionOneRadio.click();
		assert(optionOneRadio.isSelected());
	}
	
	// Assert the drop down options are displayed
	@Test(priority = 1)
	public static void dropDownTest() {
		WebElement dropDown = driver.findElement(By.cssSelector("select[id='dropdown-class-example']"));
		dropDown.click();
		
		WebElement option1 = driver.findElement(By.cssSelector("option[value='option1"));
		WebElement option2 = driver.findElement(By.cssSelector("option[value='option2"));
		WebElement option3 = driver.findElement(By.cssSelector("option[value='option3"));
		assert(option1.isDisplayed());
		assert(option2.isDisplayed());
		assert(option3.isDisplayed());
	}
	
	// Assert the selected option is displayed first
	@Test(priority = 1)
	public static void dropDownSelect() {
		WebElement dropDown = driver.findElement(By.cssSelector("select[id='dropdown-class-example']"));
		dropDown.click();
		WebElement option2 = driver.findElement(By.cssSelector("option[value='option2']"));
		option2.click();
		Select select = new Select(driver.findElement(By.cssSelector("select[id='dropdown-class-example']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Option2");
	}
	
	// Assert the first check box is working
	@Test(priority = 1)
	public static void checkBoxTestA() {
		WebElement checkBox = driver.findElement(By.id("checkBoxOption1"));
		checkBox.click();
		assert(checkBox.isEnabled());
	}
	
	// Assert the second check box is working
	@Test(priority = 1)
	public static void checkBoxTestB() {
		WebElement checkBox = driver.findElement(By.id("checkBoxOption2"));
		checkBox.click();
		assert(checkBox.isEnabled());
	}
	
	// Assert the third check box is working
	@Test(priority = 1)
	public static void checkBoxTestC() {
		WebElement checkBox = driver.findElement(By.id("checkBoxOption3"));
		checkBox.click();
		assert(checkBox.isEnabled());
	}
	
	// Assert the Title of the new Window is correct after clicking the open window button
	@Test(priority = 1)
	public static void openWindowTest() {
		
		String winHandleBefore = driver.getWindowHandle();

		WebElement openWindow = driver.findElement(By.id("openwindow"));
		openWindow.click();
		
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		
		Assert.assertEquals(driver.getTitle(), "QA Click Academy | Selenium,Jmeter,SoapUI,Appium,Database testing,QA Training Academy");		
		driver.close();

		// Switch back to original window 
		driver.switchTo().window(winHandleBefore);
		Assert.assertEquals(driver.getTitle(), "Practice Page");
	}
	
	// Assert the Title of the new Tab is correct after clicking the open tab button
	@Test(priority = 1)
	public static void openTabTest() {
		
		String winHandleBefore = driver.getWindowHandle();

		WebElement openTab= driver.findElement(By.id("opentab"));
		openTab.click();
		
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		
		Assert.assertEquals(driver.getTitle(), "Rahul Shetty Academy");
		
		driver.close();

		// Switch back to original window 
		driver.switchTo().window(winHandleBefore);
		
		Assert.assertEquals(driver.getTitle(), "Practice Page");
	}
	
	// Assert both alerts work and display the correct text after entering the input
	@Test(priority = 1)
	public static void alertBtnTest() {
		WebElement alertBtn = driver.findElement(By.id("alertbtn"));
		WebElement confirmBtn = driver.findElement(By.id("confirmbtn"));
		WebElement inputAlert = driver.findElement(By.xpath("//input[@id='name']"));
		inputAlert.sendKeys("Pablo");
		alertBtn.click();
		Assert.assertEquals(driver.switchTo().alert().getText(), "Hello Pablo, share this practice page and share your knowledge");
		driver.switchTo().alert().accept();
		inputAlert.sendKeys("Pablo");
		confirmBtn.click();
		Assert.assertEquals(driver.switchTo().alert().getText(), "Hello Pablo, Are you sure you want to confirm?");
		driver.switchTo().alert().dismiss();
	}
	
	
	// Assert all instructors name are Rahul Shetty
	@Test(priority = 1)
	public static void tableTestInstructor() {
		WebElement CoursesTable = driver.findElement(By.cssSelector("table[name='courses']"));
		List<WebElement> tableRows = CoursesTable.findElements(By.tagName("tr"));
		int rowsNum = tableRows.size();
		
		for(int i = 1; i < rowsNum; i++) {
			List<WebElement> tds = tableRows.get(i).findElements(By.tagName("td"));
			Assert.assertEquals(tds.get(0).getText(), "Rahul Shetty");
		}
	}
	
	// Assert all Amounts in the Fixed Table are greater than 0
	@Test(priority = 1)
	public static void tableTest() {
		WebElement EmployeesTable = driver.findElement(By.cssSelector("div[class='tableFixHead']"));
		List<WebElement> tableRows = EmployeesTable.findElements(By.tagName("tr"));
		int rowsNum = tableRows.size();
		int totalAmount = 0;
		WebElement totalAmountEl = driver.findElement(By.cssSelector("div[class='totalAmount']"));
		
		for(int i = 1; i < rowsNum; i++) {
			List<WebElement> tds = tableRows.get(i).findElements(By.tagName("td"));
			totalAmount += Integer.parseInt(tds.get(3).getText());
		}
		
		String messageAmount = "Total Amount Collected: " + totalAmount;
		Assert.assertEquals(messageAmount, totalAmountEl.getText());
	}
	
	
	// Check the mouse hover button displays correctly the options Top and Reload
	@Test(priority = 1)
	public static void mouseHoverTest() throws InterruptedException {
		Actions action = new Actions(driver);
		WebElement mouseHover = driver.findElement(By.xpath("//button[@id='mousehover']"));
		action.moveToElement(mouseHover).perform();
		action.moveToElement(mouseHover);
		WebElement topOption = driver.findElement(By.cssSelector("a[href='#top']"));
		WebElement reloadOption = driver.findElement(By.xpath("//a[text()='Reload']"));
		assert(topOption.isDisplayed());
		assert(reloadOption.isDisplayed());
	}
	
	// Scroll the iFrame and make sure the Button join now is present
	@Test(priority = 2)
	public static void iFrameTest() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1250)", "");
		driver.switchTo().frame("courses-iframe");
		js.executeScript("window.scrollBy(0,350)", "");
		WebElement joinNowBtn = driver.findElement(By.xpath("//a[text()='JOIN NOW']"));
		assert(joinNowBtn.isDisplayed());
		joinNowBtn.click();
	}
	
	@AfterTest
	public static void endTesting() {
		driver.close();
		driver.quit();
	}
	

}
