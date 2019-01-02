package com.overstock.sui.testscripts;

import java.util.ArrayList;
import java.util.List;

import com.overstock.sui.testbase.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.google.common.base.Strings;

/**
 *  Either there will be Left Navigation or Top Navigation
 */
public class TC_TopNavigation extends TestBase {

	/*


	int linksCount = 0;
	int i = 0;
	String notWorkingUrlTitle = "";

	@Test(priority = 1)
	public void validateTopNav() throws InterruptedException {
		List<WebElement> linksinTopNav = driver.findElements(By.className("top-nav-links-line"));
		linksCount = linksinTopNav.size();
		System.out.println("Links present under Top Navigation: " + linksCount);
		String[] linkTexts = new String[linksinTopNav.size()];
		for (WebElement elements : linksinTopNav) {
			linkTexts[i] = elements.getText();
			System.out.println("Link Text: " + linkTexts[i]);
			i++;

		}
		for (String linkstoVerify : linkTexts) {
			driver.findElement(By.linkText(linkstoVerify)).click();
			if (driver.getTitle().equals(notWorkingUrlTitle)) {
				System.out.println("\"" + linkstoVerify + "\"" + " is not working.");
			} else {
				System.out.println("Test Pass!!! Link for " + linkstoVerify + " is working. Link URL is: "
						+ driver.getCurrentUrl());
			}
			driver.navigate().back();
		}

	}

	@Test(priority = 2)
	public void childMenuForFurniture() throws InterruptedException {
		try {
			Actions mysublink = new Actions(driver);
			mysublink.moveToElement(driver.findElement(By.linkText("Furniture"))).build().perform();
			List<WebElement> linksinTopNav = driver.findElements(By.className("first-sub-cat-heading"));

			linksCount = linksinTopNav.size();
			System.out.println("Links present under Top Navigation: " + linksCount);
			List<String> linkTexts = new ArrayList<>();
			for (WebElement elements : linksinTopNav) {
				mysublink.moveToElement(driver.findElement(By.linkText("Furniture"))).build().perform();
				if (Strings.isNullOrEmpty(elements.getText())) {
					continue;
				}
				linkTexts.add(elements.getText());
				System.out.println("Link Text: " + elements.getText());

			}
			Thread.sleep(2000);

			for (String linkstoVerify : linkTexts) {

				mysublink.moveToElement(driver.findElement(By.linkText("Furniture"))).build().perform();

				driver.findElement(By.linkText(linkstoVerify)).click();
				if (driver.getTitle().equals(notWorkingUrlTitle)) {
					System.out.println("\"" + linkstoVerify + "\"" + " is not working.");
				} else {
					System.out.println("Test Pass!!! Link for " + linkstoVerify + " is working. Link URL is: "
							+ driver.getCurrentUrl());
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test(priority = 3)
	public void childMenuForRugs() throws InterruptedException {
		try {
			Actions mysublink = new Actions(driver);
			mysublink.moveToElement(driver.findElement(By.linkText("Rugs"))).build().perform();
			List<WebElement> linksinTopNav = driver.findElements(By.className("first-sub-cat-heading"));

			linksCount = linksinTopNav.size();
			System.out.println("Links present under Top Navigation: " + linksCount);
			List<String> linkTexts = new ArrayList<>();
			for (WebElement elements : linksinTopNav) {
				mysublink.moveToElement(driver.findElement(By.linkText("Rugs"))).build().perform();
				if (Strings.isNullOrEmpty(elements.getText())) {
					continue;
				}
				linkTexts.add(elements.getText());
				System.out.println("Link Text: " + elements.getText());

			}
			Thread.sleep(2000);

			for (String linkstoVerify : linkTexts) {

				mysublink.moveToElement(driver.findElement(By.linkText("Rugs"))).build().perform();

				driver.findElement(By.linkText(linkstoVerify)).click();
				if (driver.getTitle().equals(notWorkingUrlTitle)) {
					System.out.println("\"" + linkstoVerify + "\"" + " is not working.");
				} else {
					System.out.println("Test Pass!!! Link for " + linkstoVerify + " is working. Link URL is: "
							+ driver.getCurrentUrl());
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test(priority = 4)
	public void childMenuForDecor() throws InterruptedException {
		try {
			Actions mysublink = new Actions(driver);
			mysublink.moveToElement(driver.findElement(By.linkText("Decor"))).build().perform();
			List<WebElement> linksinTopNav = driver.findElements(By.className("first-sub-cat-heading"));

			linksCount = linksinTopNav.size();
			System.out.println("Links present under Top Navigation: " + linksCount);
			List<String> linkTexts = new ArrayList<>();
			for (WebElement elements : linksinTopNav) {
				mysublink.moveToElement(driver.findElement(By.linkText("Decor"))).build().perform();
				if (Strings.isNullOrEmpty(elements.getText())) {
					continue;
				}
				linkTexts.add(elements.getText());
				System.out.println("Link Text: " + elements.getText());

			}
			Thread.sleep(2000);

			for (String linkstoVerify : linkTexts) {

				mysublink.moveToElement(driver.findElement(By.linkText("Decor"))).build().perform();

				driver.findElement(By.linkText(linkstoVerify)).click();
				if (driver.getTitle().equals(notWorkingUrlTitle)) {
					System.out.println("\"" + linkstoVerify + "\"" + " is not working.");
				} else {
					System.out.println("Test Pass!!! Link for " + linkstoVerify + " is working. Link URL is: "
							+ driver.getCurrentUrl());
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test(priority = 5)
	public void childMenuForBedBath() throws InterruptedException {
		try {
			Actions mysublink = new Actions(driver);
			mysublink.moveToElement(driver.findElement(By.linkText("Bed & Bath"))).build().perform();
			List<WebElement> linksinTopNav = driver.findElements(By.className("first-sub-cat-heading"));

			linksCount = linksinTopNav.size();
			System.out.println("Links present under Top Navigation: " + linksCount);
			List<String> linkTexts = new ArrayList<>();
			for (WebElement elements : linksinTopNav) {
				mysublink.moveToElement(driver.findElement(By.linkText("Bed & Bath"))).build().perform();
				if (Strings.isNullOrEmpty(elements.getText())) {
					continue;
				}
				linkTexts.add(elements.getText());
				System.out.println("Link Text: " + elements.getText());

			}
			Thread.sleep(2000);

			for (String linkstoVerify : linkTexts) {

				mysublink.moveToElement(driver.findElement(By.linkText("Bed & Bath"))).build().perform();

				driver.findElement(By.linkText(linkstoVerify)).click();
				if (driver.getTitle().equals(notWorkingUrlTitle)) {
					System.out.println("\"" + linkstoVerify + "\"" + " is not working.");
				} else {
					System.out.println("Test Pass!!! Link for " + linkstoVerify + " is working. Link URL is: "
							+ driver.getCurrentUrl());
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test(priority = 6)
	public void childMenuForHomeImprovement() throws InterruptedException {
		try {
			Actions mysublink = new Actions(driver);
			mysublink.moveToElement(driver.findElement(By.linkText("Home Improvement"))).build().perform();
			List<WebElement> linksinTopNav = driver.findElements(By.className("first-sub-cat-heading"));

			linksCount = linksinTopNav.size();
			System.out.println("Links present under Top Navigation: " + linksCount);
			List<String> linkTexts = new ArrayList<>();
			for (WebElement elements : linksinTopNav) {
				mysublink.moveToElement(driver.findElement(By.linkText("Home Improvement"))).build().perform();
				if (Strings.isNullOrEmpty(elements.getText())) {
					continue;
				}
				linkTexts.add(elements.getText());
				System.out.println("Link Text: " + elements.getText());

			}
			Thread.sleep(2000);

			for (String linkstoVerify : linkTexts) {

				mysublink.moveToElement(driver.findElement(By.linkText("Home Improvement"))).build().perform();

				driver.findElement(By.linkText(linkstoVerify)).click();
				if (driver.getTitle().equals(notWorkingUrlTitle)) {
					System.out.println("\"" + linkstoVerify + "\"" + " is not working.");
				} else {
					System.out.println("Test Pass!!! Link for " + linkstoVerify + " is working. Link URL is: "
							+ driver.getCurrentUrl());
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test(priority = 7)
	public void childMenuForKitchen() throws InterruptedException {
		try {
			Actions mysublink = new Actions(driver);
			mysublink.moveToElement(driver.findElement(By.linkText("Kitchen"))).build().perform();
			List<WebElement> linksinTopNav = driver.findElements(By.className("first-sub-cat-heading"));
			linksCount = linksinTopNav.size();
			System.out.println("Links present under Top Navigation: " + linksCount);
			List<String> linkTexts = new ArrayList<>();
			for (WebElement elements : linksinTopNav) {
				mysublink.moveToElement(driver.findElement(By.linkText("Kitchen"))).build().perform();
				if (Strings.isNullOrEmpty(elements.getText())) {
					continue;
				}
				linkTexts.add(elements.getText());
				System.out.println("Link Text: " + elements.getText());

			}
			Thread.sleep(2000);

			for (String linkstoVerify : linkTexts) {

				mysublink.moveToElement(driver.findElement(By.linkText("Kitchen"))).build().perform();

				driver.findElement(By.linkText(linkstoVerify)).click();
				if (driver.getTitle().equals(notWorkingUrlTitle)) {
					System.out.println("\"" + linkstoVerify + "\"" + " is not working.");
				} else {
					System.out.println("Test Pass!!! Link for " + linkstoVerify + " is working. Link URL is: "
							+ driver.getCurrentUrl());
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test(priority = 8)
	public void childMenuForOutdoor() throws InterruptedException {
		try {
			Actions mysublink = new Actions(driver);
			mysublink.moveToElement(driver.findElement(By.linkText("Outdoor"))).build().perform();
			List<WebElement> linksinTopNav = driver.findElements(By.className("first-sub-cat-heading"));

			linksCount = linksinTopNav.size();
			System.out.println("Links present under Top Navigation: " + linksCount);
			List<String> linkTexts = new ArrayList<>();
			for (WebElement elements : linksinTopNav) {
				mysublink.moveToElement(driver.findElement(By.linkText("Outdoor"))).build().perform();
				if (Strings.isNullOrEmpty(elements.getText())) {
					continue;
				}
				linkTexts.add(elements.getText());
				System.out.println("Link Text: " + elements.getText());

			}
			Thread.sleep(2000);

			for (String linkstoVerify : linkTexts) {

				mysublink.moveToElement(driver.findElement(By.linkText("Outdoor"))).build().perform();

				driver.findElement(By.linkText(linkstoVerify)).click();
				if (driver.getTitle().equals(notWorkingUrlTitle)) {
					System.out.println("\"" + linkstoVerify + "\"" + " is not working.");
				} else {
					System.out.println("Test Pass!!! Link for " + linkstoVerify + " is working. Link URL is: "
							+ driver.getCurrentUrl());
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test(priority = 9)
	public void childMenuForJewelry() throws InterruptedException {
		try {
			Actions mysublink = new Actions(driver);
			mysublink.moveToElement(driver.findElement(By.linkText("Jewelry"))).build().perform();
			List<WebElement> linksinTopNav = driver.findElements(By.className("first-sub-cat-heading"));

			linksCount = linksinTopNav.size();
			System.out.println("Links present under Top Navigation: " + linksCount);
			List<String> linkTexts = new ArrayList<>();
			for (WebElement elements : linksinTopNav) {
				mysublink.moveToElement(driver.findElement(By.linkText("Jewelry"))).build().perform();
				if (Strings.isNullOrEmpty(elements.getText())) {
					continue;
				}
				linkTexts.add(elements.getText());
				System.out.println("Link Text: " + elements.getText());

			}
			Thread.sleep(2000);

			for (String linkstoVerify : linkTexts) {

				mysublink.moveToElement(driver.findElement(By.linkText("Jewelry"))).build().perform();

				driver.findElement(By.linkText(linkstoVerify)).click();
				if (driver.getTitle().equals(notWorkingUrlTitle)) {
					System.out.println("\"" + linkstoVerify + "\"" + " is not working.");
				} else {
					System.out.println("Test Pass!!! Link for " + linkstoVerify + " is working. Link URL is: "
							+ driver.getCurrentUrl());
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test(priority = 10)
	public void childMenuForWatches() throws InterruptedException {
		try {
			Actions mysublink = new Actions(driver);
			mysublink.moveToElement(driver.findElement(By.linkText("Watches"))).build().perform();
			List<WebElement> linksinTopNav = driver.findElements(By.className("first-sub-cat-heading"));

			linksCount = linksinTopNav.size();
			System.out.println("Links present under Top Navigation: " + linksCount);
			List<String> linkTexts = new ArrayList<>();
			for (WebElement elements : linksinTopNav) {
				mysublink.moveToElement(driver.findElement(By.linkText("Watches"))).build().perform();
				if (Strings.isNullOrEmpty(elements.getText())) {
					continue;
				}
				linkTexts.add(elements.getText());
				System.out.println("Link Text: " + elements.getText());

			}
			Thread.sleep(2000);

			for (String linkstoVerify : linkTexts) {

				mysublink.moveToElement(driver.findElement(By.linkText("Watches"))).build().perform();

				driver.findElement(By.linkText(linkstoVerify)).click();
				if (driver.getTitle().equals(notWorkingUrlTitle)) {
					System.out.println("\"" + linkstoVerify + "\"" + " is not working.");
				} else {
					System.out.println("Test Pass!!! Link for " + linkstoVerify + " is working. Link URL is: "
							+ driver.getCurrentUrl());
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test(priority = 11)
	public void childMenuForWomen() throws InterruptedException {
		try {
			Actions mysublink = new Actions(driver);
			mysublink.moveToElement(driver.findElement(By.linkText("Women"))).build().perform();
			List<WebElement> linksinTopNav = driver.findElements(By.className("first-sub-cat-heading"));

			linksCount = linksinTopNav.size();
			System.out.println("Links present under Top Navigation: " + linksCount);
			List<String> linkTexts = new ArrayList<>();
			for (WebElement elements : linksinTopNav) {
				mysublink.moveToElement(driver.findElement(By.linkText("Women"))).build().perform();
				if (Strings.isNullOrEmpty(elements.getText())) {
					continue;
				}
				linkTexts.add(elements.getText());
				System.out.println("Link Text: " + elements.getText());

			}
			Thread.sleep(2000);

			for (String linkstoVerify : linkTexts) {

				mysublink.moveToElement(driver.findElement(By.linkText("Women"))).build().perform();

				driver.findElement(By.linkText(linkstoVerify)).click();
				if (driver.getTitle().equals(notWorkingUrlTitle)) {
					System.out.println("\"" + linkstoVerify + "\"" + " is not working.");
				} else {
					System.out.println("Test Pass!!! Link for " + linkstoVerify + " is working. Link URL is: "
							+ driver.getCurrentUrl());
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test(priority = 12)
	public void childMenuForMen() throws InterruptedException {
		try {
			Actions mysublink = new Actions(driver);
			mysublink.moveToElement(driver.findElement(By.linkText("Men"))).build().perform();
			List<WebElement> linksinTopNav = driver.findElements(By.className("first-sub-cat-heading"));

			linksCount = linksinTopNav.size();
			System.out.println("Links present under Top Navigation: " + linksCount);
			List<String> linkTexts = new ArrayList<>();
			for (WebElement elements : linksinTopNav) {
				mysublink.moveToElement(driver.findElement(By.linkText("Men"))).build().perform();
				if (Strings.isNullOrEmpty(elements.getText())) {
					continue;
				}
				linkTexts.add(elements.getText());
				System.out.println("Link Text: " + elements.getText());

			}
			Thread.sleep(2000);

			for (String linkstoVerify : linkTexts) {

				mysublink.moveToElement(driver.findElement(By.linkText("Men"))).build().perform();

				driver.findElement(By.linkText(linkstoVerify)).click();
				if (driver.getTitle().equals(notWorkingUrlTitle)) {
					System.out.println("\"" + linkstoVerify + "\"" + " is not working.");
				} else {
					System.out.println("Test Pass!!! Link for " + linkstoVerify + " is working. Link URL is: "
							+ driver.getCurrentUrl());
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test(priority = 13)
	public void childMenuForKidsBaby() throws InterruptedException {
		try {
			Actions mysublink = new Actions(driver);
			mysublink.moveToElement(driver.findElement(By.linkText("Kids & Baby"))).build().perform();
			List<WebElement> linksinTopNav = driver.findElements(By.className("first-sub-cat-heading"));

			linksCount = linksinTopNav.size();
			System.out.println("Links present under Top Navigation: " + linksCount);
			List<String> linkTexts = new ArrayList<>();
			for (WebElement elements : linksinTopNav) {
				mysublink.moveToElement(driver.findElement(By.linkText("Kids & Baby"))).build().perform();
				if (Strings.isNullOrEmpty(elements.getText())) {
					continue;
				}
				linkTexts.add(elements.getText());
				System.out.println("Link Text: " + elements.getText());

			}
			Thread.sleep(2000);

			for (String linkstoVerify : linkTexts) {

				mysublink.moveToElement(driver.findElement(By.linkText("Kids & Baby"))).build().perform();

				driver.findElement(By.linkText(linkstoVerify)).click();
				if (driver.getTitle().equals(notWorkingUrlTitle)) {
					System.out.println("\"" + linkstoVerify + "\"" + " is not working.");
				} else {
					System.out.println("Test Pass!!! Link for " + linkstoVerify + " is working. Link URL is: "
							+ driver.getCurrentUrl());
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Test(priority = 14)
	public void childMenuForMore() throws InterruptedException {
		try {
			Actions mysublink = new Actions(driver);
			mysublink.moveToElement(driver.findElement(By.linkText("More"))).build().perform();
			List<WebElement> linksinTopNav = driver.findElements(By.className("first-sub-cat-heading"));

			linksCount = linksinTopNav.size();
			System.out.println("Links present under Top Navigation: " + linksCount);
			List<String> linkTexts = new ArrayList<>();
			for (WebElement elements : linksinTopNav) {
				mysublink.moveToElement(driver.findElement(By.linkText("More"))).build().perform();
				if (Strings.isNullOrEmpty(elements.getText())) {
					continue;
				}
				linkTexts.add(elements.getText());
				System.out.println("Link Text: " + elements.getText());

			}
			Thread.sleep(2000);

			for (String linkstoVerify : linkTexts) {

				mysublink.moveToElement(driver.findElement(By.linkText("More"))).build().perform();

				driver.findElement(By.linkText(linkstoVerify)).click();
				if (driver.getTitle().equals(notWorkingUrlTitle)) {
					System.out.println("\"" + linkstoVerify + "\"" + " is not working.");
				} else {
					System.out.println("Test Pass!!! Link for " + linkstoVerify + " is working. Link URL is: "
							+ driver.getCurrentUrl());
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}




	*/



}
