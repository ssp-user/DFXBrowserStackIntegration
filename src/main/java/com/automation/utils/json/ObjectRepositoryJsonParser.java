package com.automation.utils.json;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;

import com.jayway.jsonpath.JsonPath;

public class ObjectRepositoryJsonParser {
	static File jsonFile;

	public ObjectRepositoryJsonParser(String filename) {
		jsonFile = new File(filename);
	}

	public By getObjectLocator(String jsonpath) {
		String locatorProperty = null;
		try {
			locatorProperty = JsonPath.read(jsonFile, jsonpath).toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String locatorType = locatorProperty.split(";")[0];
		String locatorValue = locatorProperty.split(";")[1];

		By locator = null;
		switch (locatorType.toLowerCase()) {
		case "id":
			locator = By.id(locatorValue);
			break;
		case "name":
			locator = By.name(locatorValue);
			break;

		case "class":
			locator = By.className(locatorValue);
			break;
		case "css":
			locator = By.cssSelector(locatorValue);
			break;
		case "link":
			locator = By.linkText(locatorValue);
			break;
		case "partial":
			locator = By.partialLinkText(locatorValue);
			break;
		case "tag":
			locator = By.tagName(locatorValue);
			break;
		case "xpath":
			locator = By.xpath(locatorValue);
			break;
		}
		return locator;
	}
}
