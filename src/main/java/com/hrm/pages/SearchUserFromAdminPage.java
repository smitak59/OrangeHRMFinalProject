package com.hrm.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.hrm.keyword.Keyword;

public class SearchUserFromAdminPage {

	WebDriver driver;

	@FindBy(xpath = "//label[contains(text(),'Username')]/following::input[1]")
	WebElement userName;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement searchBtn;

	@FindBy(xpath = "//div[@class='oxd-table-header']//div[@role='row']")
	List<WebElement> allCellFromTable;

	@FindBy(xpath = ".//div[@role='cell'][3]")
	List<WebElement> searchDetails;
	//// div[@class='oxd-table-card']//div[2]//div[1]

	@FindBy(xpath = "//div[@class='oxd-table-body']//div[@role='row']")
	List<WebElement> tableRows;

	@FindBy(xpath = "//button[normalize-space()='Reset']")
	WebElement resetBtn;

	public SearchUserFromAdminPage(WebDriver driver) {
		PageFactory.initElements(Keyword.driver, this);
	}

	public void enterUserNameForSearch(String uname) {
		userName.sendKeys(uname);
	}

	public void clickOnSearchBtn() {
		searchBtn.click();
	}

	public List<WebElement> getAllSearchDataFromTable() {

		return allCellFromTable;

	}

	public List<WebElement> getSearchDetails() {
		return searchDetails;
	}

	public List<Map<String, String>> getSearchResults() {
		List<Map<String, String>> results = new ArrayList<>();

		List<WebElement> rows = tableRows;

		for (WebElement row : rows) {
			List<WebElement> columns = row.findElements(By.xpath("./div"));
			Map<String, String> rowData = new HashMap<>();
			rowData.put("username", columns.get(1).getText().trim());
			rowData.put("role", columns.get(2).getText().trim());
			rowData.put("empName", columns.get(3).getText().trim());
			rowData.put("status", columns.get(4).getText().trim());
			results.add(rowData);
		}

		return results;
	}
	
	public void clickOnResetBtn() {
		resetBtn.click();
	}
	
	 public boolean areSearchFieldsCleared() {
	        String username = userName.getAttribute("value");
	        return username.isEmpty();
	    }

}
