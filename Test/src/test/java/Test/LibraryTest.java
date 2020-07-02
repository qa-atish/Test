package Test;

import static org.junit.Assert.assertTrue;

import org.testng.annotations.Test;

import com.sentieo.baseClass.BaseClass;
import com.sentieo.pages.PageObejcts;

public class LibraryTest extends BaseClass {

	PageObejcts page = new PageObejcts(driver);

	@Test(groups = "sanity,regression,smoke", description = "open the URL and enter the email's")
	public void testFreeTrial() throws Exception {

		driver.get(appUrl);
		Thread.sleep(3000);
		page = new PageObejcts(driver);
		boolean getValue = page.getEmailCount("abc@gmail.com", "abc@carrothr.com", 1);
		if (getValue) {
			page = new PageObejcts(driver);
			boolean status = page.verifyTheSecondPage("Check your email");
			if (!status) {
				assertTrue("Second Page is not visible", false);
			}
		}
	}

	@Test(groups = "sanity,regression,smoke", description = "verify the flow of feature", priority = 1)
	public void fetauretest() throws Exception {

		page = new PageObejcts(driver);
		boolean status = page.selectTheButton("Features", "Anniversaries & Birthdays");
		if (!status) {
			assertTrue("Anniversaries & Birthdays tab is not active yet", false);
		}
	}

	@Test(groups = "sanity,regression,smoke", description = "verify the complete flow of contact us ", priority = 2)
	public void contactUs() throws Exception {

		page = new PageObejcts(driver);
		driver.switchTo().defaultContent();
		page.contactUS();
		page = new PageObejcts(driver);
		boolean status = page.setTextIntercom("slack");
		if (status) {
			boolean suggestionVisible = page.getSuggestions();
			if (!suggestionVisible) {
				assertTrue("Suggestion not visible", false);
			}
		} else {
			assertTrue("Intercom is not opened", false);
		}
	}
}
