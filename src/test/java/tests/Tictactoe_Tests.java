package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseClass;
import functions.FunctionLibrary;
import pages.MainPage;

public class Tictactoe_Tests extends BaseClass{
	
	FunctionLibrary func = new FunctionLibrary();
	
	@Test(enabled = true,groups ={"All"})
	public void validateX() {

		MainPage mainPage = new MainPage(Driver);
		String strLabel = mainPage.getLabelValue();
		String strLabelValue = mainPage.labelReturnValue(strLabel);
		String strValue = mainPage.clickSquareReturnValue("1");
		mainPage.clickResetButton();
		assertEquals(strValue, strLabelValue);
		
		
	}
	
	@Test(enabled = true,groups ={"All"})
	public void validateO() {
		 MainPage mainPage = new MainPage(Driver);
		mainPage.clickSquare("1");
		String strLabel = mainPage.getLabelValue();
		String strLabelValue = mainPage.labelReturnValue(strLabel);
		String strValue = mainPage.clickSquareReturnValue("2");
		mainPage.clickResetButton();
		assertEquals(strValue, strLabelValue);
		
		
	}
	@Test(enabled = true,groups ={"All"})
	public void validateResetButton() {
		 MainPage mainPage = new MainPage(Driver);
		mainPage.playAGame("1,9,3,7,8,2,4,5,6");
		String strLabel = mainPage.getLabelValue();
		System.out.println("Label: "+ strLabel);
		mainPage.clickResetButton();
		
		assertTrue(mainPage.validateAllClear());
		
	}
	
	@Test(enabled = true,groups ={"All"})
	public void validateTieGame() {
		 MainPage mainPage = new MainPage(Driver);
		mainPage.playAGame("1,9,3,7,8,2,4,5,6");
		String strLabel = mainPage.getLabelValue();
		System.out.println("Label: "+ strLabel);
		mainPage.clickResetButton();
		assertEquals(strLabel,"Game ended in a draw!");
		
	}
	@Test(enabled = true,groups ={"All"})
	public void validateXWins() {
		
		 MainPage mainPage = new MainPage(Driver);
		mainPage.playAGame("1,4,2,5,3");
		String strLabel = mainPage.getLabelValue();
		System.out.println("Label: "+ strLabel);
		mainPage.clickResetButton();
		
		assertEquals(strLabel,"Player X has won!");
		
	}

	@Test(enabled = true,groups ={"All"})
	public void validateOWins() {
		
		MainPage mainPage = new MainPage(Driver);
		mainPage.playAGame("1,4,2,5,7,6");
		String strLabel = mainPage.getLabelValue();
		System.out.println("Label: "+ strLabel);
		mainPage.clickResetButton();
		
		assertEquals(strLabel,"Player O has won!");
		
	}
	
	@Test(enabled = true,groups ={"All","Smoke"})
	public void validateTitleLabel() {
		
		MainPage mainPage = new MainPage(Driver);
				
		assertEquals(mainPage.getTitleValue(),"Tic Tac Toe");
		
	}
	
	@Test(enabled = true,groups ={"All","Smoke"},dataProvider="getTicTacToeData")
	public void TicTacTowDataDrivenTest(Map<String, String> Data) {
		String testName = Data.get("TestName");
		String runTest = Data.get("RunTest");
		String squareSelection = Data.get("SquareSelection");
		String labelExpect = Data.get("LabelExpectedResult");
		
		if(runTest.equals("Y")) {
			
		
		func.loginfo("######### Running"+testName+"##########");
		MainPage mainPage = new MainPage(Driver);
		mainPage.playAGame(squareSelection);
		String strLabel = mainPage.getLabelValue();
		System.out.println("Label: "+ strLabel);
		mainPage.clickResetButton();
		
		assertEquals(strLabel,labelExpect);
		}
		else{
			
			func.loginfo("######### Skipping "+ testName+"##########");
		}
	}
	
	@DataProvider
	public Object[][] getTicTacToeData() throws Exception {
		return func.getDataFromCSV("TicTacTowDataDrivenTest", appProps.getProperty("TicTacToeDataFile"));
	}
	
}
