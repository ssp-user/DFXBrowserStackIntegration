package com.automation.pages.third_party;

import com.automation.pages.appointment_manager.AM_FramePage;
import com.automation.pages.appointment_manager.AM_TimeAndAdvisorPage;
import com.automation.pages.common.WebPage;
import com.automation.utils.otherUtils.CommonMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;

public class VINGenerator extends WebPage {

	public static String vinNumber = null;
	private String randomVIN = null;
	private static boolean vinDecodeflag = false;
	//The vinlist as below is successfully validated/decoded for ANYTOWN USA AUTOMALL dealer.
    List<String> vinlist = Arrays.asList("4JGBF71E47A145522", "JN8AS58V39W175014", "2FTJW35M1MCA56835", "1HGCR2F39DA184940", "1G1JC12F747166430", 
    									 "JTHBE262175012894", "JF2SJABC3FH549917", "5J6RE4H34BL110723", "1FTEW1CP1FFA13977", "1GNFC16J67J160712",
    									 "KL4CJDSB2FB068646", "1C4NJDEB7FD162682", "3D7MX38C37G741624", "3D7KS28T39G503212", "4S4BRCAC9D3310253",    									 
    									 "JN8AZ1MW4AW129974", "5J6RE3H78BL006393", "1G4GA5ER8DF150839", "5J6RE3H71BL057864", "JTJHY7AX8D4087898",    									 
    									 "1C3CDFBB6FD243238", "JN8AZ18W09W143652", "KMHDN46D36U258823", "3VWRM71K49M167232", "1G4HP57247U125780");
	
    //The vinlist as below is successfully validated/decoded for ANYTOWN USA AUTOMALL dealer.
    List<String> walkinVinlist = Arrays.asList("WVGBE67L09D008002", "WVWFV7AJ0AW144030", "3N1AB7APXHY334219", "1N4BL21EX8N459000","4JGBF71E47A145522","1B3ES46C8YD795331");    

	public void captureVIN() {
		driver.findElement(findLocator("$.DFX_ThirdParty.VIN_GEN.lst_brand")).sendKeys("Audi");	
		((JavascriptExecutor)driver).executeScript("window.open();");	
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get("https://vpic.nhtsa.dot.gov/decoder");
		driver.switchTo().window(tabs.get(0));
		do {
			captureRandomGeneratorVIN();	
			driver.switchTo().window(tabs.get(1));	
			validateFromNHTSA(randomVIN);
			driver.switchTo().window(tabs.get(0));			
		} while (!vinDecodeflag);
	}

	public void captureRandomVIN() {
		sleep(5000);
		vinNumber = getElementTextWithException(findLocator("$.DFX_ThirdParty.VIN_GEN.Random_VIN_txt_code")).trim();
		System.out.println("VIN is "+vinNumber);
	}	
	
	public void captureRandomVINFromAPool() {
		vinNumber = getRandomVIN(vinlist);
		System.out.println("VIN selected from the pool is "+vinNumber);		
	}	
	
    public String getRandomVIN(List<String> list)     {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }	
    
	public void captureWalkInVIN() {
		pWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/a[@id='dashboard-create-bnt']")));		
		for (String walkInVIN : walkinVinlist) {
			System.out.println("VIN picked from walk-in pool is "+ walkInVIN);
			if (doesVINAppointmentExists(walkInVIN)) {
				vinNumber = walkInVIN;
				System.out.println("VIN choosen for the appointment is "+ walkInVIN);
				break;
			}
		}
	}	
	
	public boolean doesVINAppointmentExists(String VIN) {
		boolean vinflag = true;
		List<WebElement> vinList = driver.findElements(By.xpath("//*[text()='"+VIN+"']"));
		if (vinList.size() > 0) {
			vinflag = false;
		}
		return vinflag;
		
	}
	
	public int captureVehicleYear() {
		int modelYear = Integer.parseInt(driver.findElement(findLocator("$.DFX_ThirdParty.VIN_GEN.VIN_description")).getText().substring(17, 21));
		System.out.println("Year of model is "+ modelYear);
		return modelYear;
	}	
	
	public void captureRandomGeneratorVIN() {
		sleep(2000);
		do {
			driver.findElement(findLocator("$.DFX_ThirdParty.VIN_GEN.btn_generate")).click();			
			sleep(3000);
		} while (captureVehicleYear() < 2010);
		randomVIN = driver.findElement(findLocator("$.DFX_ThirdParty.VIN_GEN.VIN_txt_code")).getAttribute("value").trim();
		System.out.println("Random VIN selected for validation is "+randomVIN);
	}
	
	public void validateFromNHTSA(String vinToDecode) {
        clearAndSend(driver.findElement(findLocator("$.DFX_ThirdParty.NHTSA_PAGE.VIN_txt_code")),vinToDecode);		
		driver.findElement(findLocator("$.DFX_ThirdParty.NHTSA_PAGE.btn_decode")).click();	
		vinDecodeflag = driver.findElement(findLocator("$.DFX_ThirdParty.NHTSA_PAGE.VIN_decode_response")).getText().contains("0 - VIN decoded clean");
		if (vinDecodeflag) {
			vinNumber = vinToDecode;
			System.out.println("VIN successfully validated is "+vinNumber + ". This will be used in the scenario");			
		}
	}	
	
}
