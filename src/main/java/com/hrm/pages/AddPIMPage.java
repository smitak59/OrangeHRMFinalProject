package com.hrm.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.hrm.keyword.Keyword;

public class AddPIMPage {
	private static final Logger log = Logger.getLogger(Keyword.class);
	WebDriver driver;
	static Keyword keyword = new Keyword();
	@FindBy(xpath = "//span[text()='PIM']")
	WebElement PIMButton;

	@FindBy(xpath = "(//a[normalize-space()='Add Employee'])[1]")
	WebElement addEmpBtn;

	@FindBy(xpath = "//a[normalize-space()='Employee List']")
	WebElement employeeListTab;

	@FindBy(xpath = "//input[@name='firstName']")
	WebElement firstNameField;

	@FindBy(xpath = "//input[@name='lastName']")
	WebElement lastNameField;

	@FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
	WebElement employeeIdField;

	@FindBy(xpath = "//button[normalize-space()='Save']")
	WebElement saveButton;

	@FindBy(xpath = "//span[@class='oxd-switch-input oxd-switch-input--active --label-right']")
	WebElement createLoginDetailsToggle;

	@FindBy(xpath = "//input[@autocomplete='off'][1]")
	WebElement usernameField;

	@FindBy(xpath = "//input[@type='password'][1]")
	WebElement passwordField;

	@FindBy(xpath = "(//input[@type='password'])[2]")
	WebElement confirmPasswordField;

	@FindBy(xpath = "//div[@class='oxd-table-body']//div[contains(@class,'oxd-table-row')]")
	List<WebElement> employeeTableRows;

	@FindBy(xpath = "//input[@placeholder='Type for hints...'][1]")
	WebElement employeeNameSearchField;

	@FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
	WebElement employeeIdSearchField;

	@FindBy(xpath = "//button[normalize-space()='Search']")
	WebElement searchButton;

	@FindBy(xpath = "//button[normalize-space()='Reset']")
	WebElement resetButton;

	@FindBy(xpath = "//span[@class='oxd-text oxd-text--span']")
	List<WebElement> RecordsFoundMessage;

	@FindBy(xpath = "//div[@role='row']")
	List<WebElement> searchSuggestions;

	@FindBy(xpath = "//div[@class='orangehrm-container']")
	List<WebElement> searchresult;

	@FindBy(xpath = "//button[@class='oxd-icon-button oxd-icon-button--solid-main employee-image-action']")
	WebElement PlusIconforimage;

	@FindBy(xpath = "//h6[normalize-space()='Personal Details']")
	WebElement confirmationMessage;

	@FindBy(xpath = "((//div[@role='row'])[2]/div[@role='cell'])[2]")
	WebElement FirstIDFromSearchedResult;

	@FindBy(xpath = "//ul[@class='oxd-pagination__ul']/li")
	List<WebElement> totalLinksElements;

	@FindBy(xpath = "//div[@class='oxd-table-card']")
	List<WebElement> row;

	@FindBy(xpath = "//i[@class='oxd-icon bi-trash']")
	WebElement DeleteIconInTable;

	@FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']")
	WebElement ConfirmDeleteButton;

	@FindBy(xpath = "(//span[@class='oxd-text oxd-text--span'])[1]")
	WebElement DeleteRecordmsgButton;

	@FindBy(xpath = "//div[@role='row']")
	List<WebElement> RowsAfterReset;

	@FindBy(css = "div[class='orangehrm-horizontal-padding orangehrm-vertical-padding'] span[class='oxd-text oxd-text--span']")
	WebElement RecordMsgAfterReset;

	public AddPIMPage() {
		PageFactory.initElements(Keyword.driver, this);
	}

	public void AddEmpWithLoginDetails() {
		createLoginDetailsToggle.click();
		usernameField.sendKeys("Linamathur");
		passwordField.sendKeys("Password1234");
		confirmPasswordField.sendKeys("Password1234");

	}

	public void ResetMethod() {
		resetButton.click();
		String message = "";
		if (RowsAfterReset.size() > 1) {
			message = RecordMsgAfterReset.getText();
		}
		System.out.println(message);
	}

	public void DeleteEmp() {
		keyword.implicittWait();
		DeleteIconInTable.click();
		ConfirmDeleteButton.click();
		String msg = DeleteRecordmsgButton.getText();
		keyword.implicittWait();
		Assert.assertEquals(msg, "No Records Found");
		System.out.println("Employee deleted");
	}

	public void clickOnPIMTab() {
		PIMButton.click();

	}

	public void MouseKeyMethodForEmpID() throws AWTException {
		Robot robo = new Robot();
		employeeIdField.sendKeys(Keys.CONTROL + "a");
		robo.keyPress(KeyEvent.VK_BACK_SPACE);
		robo.keyPress(KeyEvent.VK_TAB);
	}

	public void clickOnAddempBtn() {
		addEmpBtn.click();
	}

	public void clickEmployeeListTab() {
		employeeListTab.click();

	}

	public void enterFirstName() {
		firstNameField.sendKeys("Lina");

	}

	public void addImage() throws IOException, AWTException {

		PlusIconforimage.click();
		keyword.implicittWait();
		Robot robo = new Robot();
		robo.delay(2000);
		StringSelection ss = new StringSelection(
				" D:\\myWorkspace\\FinalOrangeHRM\\src\\test\\resources\\testdata\\EmpProfilePic.jpeg");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		robo.keyPress(KeyEvent.VK_CONTROL);
		robo.keyPress(KeyEvent.VK_V);

		robo.keyRelease(KeyEvent.VK_CONTROL);
		robo.keyRelease(KeyEvent.VK_V);
		robo.keyPress(KeyEvent.VK_ENTER);
		robo.keyRelease(KeyEvent.VK_ENTER);
	}

	public void empcreatedmsg() {
		String EmpAddMsgMessage = confirmationMessage.getText();
		if (EmpAddMsgMessage.contains("Personal Details")) {
			System.out.println("Employee added successfully!");
		} else {
			System.out.println("Failed to add employee!");
		}
		Assert.assertEquals("Personal Details", EmpAddMsgMessage);

	}

	public void enterLastName() {
		lastNameField.sendKeys("Mathur");

	}
	
	public void enterFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

//	public void enterEmployeeId() {
//		employeeIdField.sendKeys("0118");
//
//	}
	
	public String enterEmployeeId() {
		Random r=new Random();
		int R= ThreadLocalRandom.current().nextInt(1000);
		String empID=empID="0" +R;
		System.out.println(empID);
		employeeIdField.sendKeys(empID);
		return empID;

	}

	public void clickSaveButton() {
		saveButton.click();

	}

	public void toggleCreateLoginDetails() {
		createLoginDetailsToggle.click();
	}

	public void enterUsername() {
		usernameField.sendKeys("LinaMathur");
	}

	public void enterPassword() {
		passwordField.sendKeys("Password123");

	}

	public void enterConfirmPassword() {
		confirmPasswordField.sendKeys("Password123");

	}

	public void searchEmployeeByName() {
		clickEmployeeListTab();
		employeeNameSearchField.sendKeys("Lina");
		searchButton.click();
	}

	public void searchEmployeeById() {
		clickEmployeeListTab();
		employeeIdSearchField.sendKeys("0115");
		searchButton.click();
		keyword.implicittWait();

	}

	public void searchEmpIDresult() {
		String message_actual = "";
		if (searchSuggestions.size() > 1) {
			message_actual = FirstIDFromSearchedResult.getText();
		}
		System.out.println("search emp id...." + message_actual);
		String message_expected = "0115";
		Assert.assertTrue(message_actual.contains(message_expected));
	}

	public void clickFirstSearchSuggestion() {
		if (!searchSuggestions.isEmpty()) {
			(searchSuggestions.get(0)).click();

		}
	}

	public void RecordsFoundMessageDisplayed() {
		keyword.implicittWait();
		String expected_message = "Record Found";
		String message_actual = RecordsFoundMessage.get(0).getText();
		System.out.println(message_actual);
		Assert.assertTrue(expected_message.contains("Record Found"),
				"Expected 'Record Found' in message, but got: " + expected_message);

	}

	public void resetSearch() {
		resetButton.click();
		;

	}

	public void GetAllListOfUsers() {
		keyword.implicittWait();
		int totalLinks = totalLinksElements.size();
		for (int i = 0; i < totalLinks; i++)// 0,1,2,3,
		{

			try {
				String currentLinkText = totalLinksElements.get(i).getText();
				int page = Integer.parseInt(currentLinkText);
				System.out.println("Page: " + page);

				totalLinksElements.get(i).click();

				keyword.implicittWait();

				for (WebElement rowElement : row) {
					String empNo = rowElement.findElement(By.xpath("./div/div[2]")).getText();
					String firstName = rowElement.findElement(By.xpath("./div/div[3]")).getText();
					String lastName = rowElement.findElement(By.xpath("./div/div[4]")).getText();

					System.out.println(empNo + " " + firstName + " " + lastName);
				}
			} catch (Exception e) {
				System.out.println("Not a number.");
			}

			keyword.implicittWait();
		}
	}
}