import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class QRCodeGeneratorTest {

    WebDriver driver;

    @Before
    public void setUp() {
        // Set the path of the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");  // Update this with the correct path
        driver = new ChromeDriver();
        
        // Navigate to the local file path of your HTML app
        driver.get("file:///Users/macbookpro/Desktop/ValdrinQerimi/AI-Contact-QR/index.html");
    }

    @Test
    public void testFormSubmissionWithValidData() {
        // Locate the input fields and the button
        WebElement firstNameInput = driver.findElement(By.id("first-name"));
        WebElement lastNameInput = driver.findElement(By.id("last-name"));
        WebElement emailInput = driver.findElement(By.id("email"));
        WebElement phoneInput = driver.findElement(By.id("phone"));
        WebElement websiteInput = driver.findElement(By.id("website"));
        WebElement generateButton = driver.findElement(By.id("generate"));
        
        // Input valid data into the form
        firstNameInput.sendKeys("John");
        lastNameInput.sendKeys("Doe");
        emailInput.sendKeys("john.doe@example.com");
        phoneInput.sendKeys("+1234567890");
        websiteInput.sendKeys("https://johndoe.com");

        // Click the "Generate QR Code" button
        generateButton.click();

        // Verify if the QR code image is displayed
        WebElement qrCode = driver.findElement(By.cssSelector("#qr-code img"));
        Assert.assertTrue("QR Code should be displayed", qrCode.isDisplayed());

        // Verify if the download button is displayed
        WebElement downloadButton = driver.findElement(By.id("download"));
        Assert.assertTrue("Download button should be visible", downloadButton.isDisplayed());
    }

    @Test
    public void testFormSubmissionWithMissingRequiredFields() {
        // Locate the input fields and the button
        WebElement generateButton = driver.findElement(By.id("generate"));

        // Click the "Generate QR Code" button without filling the form
        generateButton.click();

        // Verify if the error message is displayed
        WebElement errorMessage = driver.findElement(By.id("error-message"));
        Assert.assertTrue("Error message should be shown for missing required fields", errorMessage.isDisplayed());
        Assert.assertEquals("Please fill in all required fields.", errorMessage.getText());
    }

    @Test
    public void testFormWithInvalidPhoneNumber() {
        // Locate the input fields and the button
        WebElement firstNameInput = driver.findElement(By.id("first-name"));
        WebElement lastNameInput = driver.findElement(By.id("last-name"));
        WebElement emailInput = driver.findElement(By.id("email"));
        WebElement phoneInput = driver.findElement(By.id("phone"));
        WebElement generateButton = driver.findElement(By.id("generate"));

        // Input valid data, but an invalid phone number
        firstNameInput.sendKeys("Alice");
        lastNameInput.sendKeys("Smith");
        emailInput.sendKeys("alice.smith@example.com");
        phoneInput.sendKeys("12345");  // Invalid phone number

        // Click the "Generate QR Code" button
        generateButton.click();

        // Verify if the error message is displayed for invalid phone number
        WebElement errorMessage = driver.findElement(By.id("error-message"));
        Assert.assertTrue("Error message should be shown for invalid phone number", errorMessage.isDisplayed());
        Assert.assertEquals("Please fill in all required fields.", errorMessage.getText());
    }

    @Test
    public void testFormWithValidPhoneAndNoWebsite() {
        // Locate the input fields and the button
        WebElement firstNameInput = driver.findElement(By.id("first-name"));
        WebElement lastNameInput = driver.findElement(By.id("last-name"));
        WebElement emailInput = driver.findElement(By.id("email"));
        WebElement phoneInput = driver.findElement(By.id("phone"));
        WebElement generateButton = driver.findElement(By.id("generate"));
        
        // Input valid data with no website
        firstNameInput.sendKeys("Bob");
        lastNameInput.sendKeys("Johnson");
        emailInput.sendKeys("bob.johnson@example.com");
        phoneInput.sendKeys("+123456789");

        // Click the "Generate QR Code" button
        generateButton.click();

        // Verify if the QR code image is displayed
        WebElement qrCode = driver.findElement(By.cssSelector("#qr-code img"));
        Assert.assertTrue("QR Code should be displayed", qrCode.isDisplayed());

        // Verify if the download button is displayed
        WebElement downloadButton = driver.findElement(By.id("download"));
        Assert.assertTrue("Download button should be visible", downloadButton.isDisplayed());
    }

    @After
    public void tearDown() {
        // Close the browser after each test
        driver.quit();
    }
}
