package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import functions.FunctionLibrary;

public class MainPage {
	WebDriver Driver;
	FunctionLibrary func = new FunctionLibrary();
	@FindBy(xpath = "/html/body/section/div/div[1]")
	private WebElement square1;
	@FindBy(xpath = "/html/body/section/div/div[2]")
	private WebElement square2;
	@FindBy(xpath = "/html/body/section/div/div[3]")
	private WebElement square3;
	@FindBy(xpath = "/html/body/section/div/div[4]")
	private WebElement square4;
	@FindBy(xpath = "/html/body/section/div/div[5]")
	private WebElement square5;
	@FindBy(xpath = "/html/body/section/div/div[6]")
	private WebElement square6;
	@FindBy(xpath = "/html/body/section/div/div[7]")
	private WebElement square7;
	@FindBy(xpath = "/html/body/section/div/div[8]")
	private WebElement square8;
	@FindBy(xpath = "/html/body/section/div/div[9]")
	private WebElement square9;
	@FindBy(xpath = "/html/body/section/button")
	private WebElement btnRestart;
	@FindBy(xpath = "/html/body/section/h2")
	private WebElement lblResult;
	@FindBy(xpath = "/html/body/section/h1")
	private WebElement lblTitle;
	// WebDriverWait wait = new WebDriverWait(Driver, 10);

	public MainPage(WebDriver Driver) {
		this.Driver = Driver;
		PageFactory.initElements(this.Driver, this);

	}


	//click a square and return the entered value based in input
	//value representing the number of the square starting from the top left to right.                        
	public String clickSquareReturnValue(String square) {
		WebElement anySquare = null;

		anySquare = Driver.findElement(By.xpath("/html/body/section/div/div[" + square + "]"));
		anySquare.click();

		String squareValue = anySquare.getText();

		return squareValue;

	}

	//Click a square based on entered input representing the square number from
	//top left to right.
	public void clickSquare(String square) {
		WebElement anySquare = null;

		anySquare = Driver.findElement(By.xpath("/html/body/section/div/div[" + square + "]"));
		anySquare.click();

	}
	
	//Return the text displayed on the lower label
	public String getLabelValue() {
		String strLabelValue = lblResult.getText();

		return strLabelValue;

	}

	//Return the text displayed din the title label
	public String getTitleValue() {
		String strTitleValue = lblTitle.getText();

		return strTitleValue;

	}

	//Take the input label text displayed and output which player it represents.
	public String labelReturnValue(String input) {
		String strResult = null;
		if (input.equalsIgnoreCase("It's X's Turn")) {
			strResult = "X";
		} else if (input.equalsIgnoreCase("It's O's Turn")) {
			strResult = "O";
		} else {
			strResult = "Neither";
		}

		return strResult;

	}
	
	//Click the restart button
	public void clickResetButton() {
		
		btnRestart.click();

	}

	//validate that all squares are empty and return boolean value of true or false
	public Boolean validateAllClear() {
		boolean boolStatus = true;
		int i = 1;
		String strSquareValue;
		WebElement anySquare = null;
		while (i < 10) {
			strSquareValue = Driver.findElement(By.xpath("/html/body/section/div/div[" + i + "]")).getText();
			if (strSquareValue.isEmpty()) {
				func.loginfo("Square" + i + " is now blank");
			} else {
				boolStatus = false;
				break;
			}
			i++;
		}

		return boolStatus;

	}
	
	//click on the squares based on input value of comma separated series 
	//of numbers representing the squares to choose from top left to right.
	public void playAGame(String strSeries) {
		String[] numbers = strSeries.split(",");
		String strResult = null;
		for (String i : numbers) {
			strResult = clickSquareReturnValue(i);
			func.loginfo("Square " + i + " value is: " + strResult);

		}
	}

	
}
