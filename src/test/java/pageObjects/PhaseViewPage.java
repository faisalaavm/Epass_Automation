package pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PhaseViewPage extends BasePage {
	WebDriverWait wait;
	/**
	 * Constructor for PhasePage.
	 * @param driver The WebDriver instance to use.
	 */
	public PhaseViewPage(WebDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	// ------------------- Web Elements -------------------

	@FindBy(xpath = "//h4[@class='main-title mb-0']")
	WebElement mainTitleElement;


	@FindBy(xpath = "//div[@class='p-4 card-body']//p[@class='mb-1 text-secondary h6']")
	List<WebElement> addressLines;

	@FindBy(xpath = "//h4[normalize-space(text())='Edit Phase']")
	WebElement editPageTitle;

	@FindBy(xpath = "//button[contains(@class, 'btn-primary') and normalize-space(text())='Save']")
	WebElement editPageSaveButton;

	@FindBy(xpath = "//div[contains(text(), 'Phase updated successfully')]")
	WebElement phaseUpdatedSuccessMessage;

	@FindBy(xpath = "//button[text()='No' and contains(@class, 'btn-secondary')]")
	WebElement deleteConfirmNoButton;

	@FindBy(xpath = "//button[contains(@class, 'btn-primary') and text()='Yes']")
	WebElement deleteConfirmYesButton;

	@FindBy(xpath = "//div[contains(text(), 'Phase deleted successfully')]")
	WebElement phaseDeletedSuccessMessage;






	// ---------- Actions with Explicit Wait ----------

	/**
	 * Gets the text of the main title element on the Phase View page.
	 * @return The text of the main title, trimmed of leading and trailing whitespace.
	 */
	public String getMainTitleText() {
		wait.until(ExpectedConditions.visibilityOf(mainTitleElement));
		return mainTitleElement.getText().trim();
	}


	/**
	 * Clicks the eye icon for the specified phase name in the paginated list.
	 * @param phaseName The name of the phase to locate and click its eye icon.
	 */
	public void clickPhaseEyeIcon(String phaseName) {
		boolean phaseFound = false;

		while (!phaseFound) {
			// Get all phase cards on the current page
			List<WebElement> phaseCards = driver.findElements(By.xpath("//div[contains(@class,'card') and .//h5]"));

			for (WebElement card : phaseCards) {
				String cardText = card.getText().toLowerCase();
				if (cardText.contains(phaseName.toLowerCase())) {
					WebElement eyeIcon = card.findElement(By.xpath(".//a[contains(@href,'details')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", eyeIcon);
					wait.until(ExpectedConditions.elementToBeClickable(eyeIcon)).click();
					phaseFound = true;
					break;
				}
			}

			if (!phaseFound) {
				List<WebElement> nextButtonList = driver.findElements(
						By.xpath("//ul[contains(@class,'pagination')]//a[contains(@class,'page-link') and .//span[text()='Next']]")
						);

				if (nextButtonList.isEmpty() || !nextButtonList.get(0).isEnabled()) {
					throw new NoSuchElementException("Phase with name '" + phaseName + "' not found.");
				}

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextButtonList.get(0));

				wait.until(ExpectedConditions.stalenessOf(phaseCards.get(0)));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h5")));
			}
		}
	}


	/**
	 * Retrieves the full address text displayed on the Phase View page.
	 * @return A string containing the concatenated address lines.
	 */
	public String getAddressText() {
		StringBuilder address = new StringBuilder();
		for (WebElement line : addressLines) {
			address.append(line.getText()).append(" ");
		}
		return address.toString().trim();
	}

	/**
	 * Clicks the edit icon for the specified phase name in the paginated list.
	 * @param phaseName The name of the phase to locate and click its edit icon.
	 */


	public void clickPhaseEditIcon(String phaseName) {
		boolean phaseFound = false;

		while (!phaseFound) {
			// Get all phase cards on the current page
			List<WebElement> phaseCards = driver.findElements(By.xpath("//div[contains(@class,'card') and .//h5]"));

			for (WebElement card : phaseCards) {
				String cardText = card.getText().toLowerCase();
				if (cardText.contains(phaseName.toLowerCase())) {
					WebElement editIcon = card.findElement(By.xpath(".//a[contains(@href,'create-phase')]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", editIcon);
					wait.until(ExpectedConditions.elementToBeClickable(editIcon)).click();
					phaseFound = true;
					break;
				}
			}

			if (!phaseFound) {
				List<WebElement> nextButtonList = driver.findElements(
						By.xpath("//ul[contains(@class,'pagination')]//a[contains(@class,'page-link') and .//span[text()='Next']]")
						);

				if (nextButtonList.isEmpty() || !nextButtonList.get(0).isEnabled()) {
					throw new NoSuchElementException("Phase with name '" + phaseName + "' not found.");
				}

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextButtonList.get(0));

				wait.until(ExpectedConditions.stalenessOf(phaseCards.get(0)));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h5")));
			}
		}
	}

	/**
	 * Gets the title text of the Phase Edit page.
	 * @return The text of the edit page title.
	 */
	public String getEditPageTitleText() {
		wait.until(ExpectedConditions.visibilityOf(editPageTitle));
		return editPageTitle.getText().trim();
	}

	/**
	 * Clicks the Save button on the Edit page.
	 */
	public void clickEditPhaseSaveButton() {
		wait.until(ExpectedConditions.elementToBeClickable(editPageSaveButton));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", editPageSaveButton);
	}

	/**
	 * Gets the success message displayed after updating a phase.
	 * @return The text of the success message.
	 */

	public String getPhaseUpdatedSuccessMessage() {
		wait.until(ExpectedConditions.visibilityOf(phaseUpdatedSuccessMessage));
		return phaseUpdatedSuccessMessage.getText().trim();
	}
	/**
	 * Clicks the delete icon for the specified phase name in the paginated list.
	 * @param phaseName The name of the phase to locate and click its delete icon.
	 */

	public void clickPhaseDeleteIcon(String phaseName) {
		boolean phaseFound = false;

		while (!phaseFound) {
			// Get all phase cards on the current page
			List<WebElement> phaseCards = driver.findElements(By.xpath("//div[contains(@class,'card') and .//h5]"));

			for (WebElement card : phaseCards) {
				String cardText = card.getText().toLowerCase();
				if (cardText.contains(phaseName.toLowerCase())) {
					// Locate delete icon by index (3rd anchor tag)
					WebElement deleteIcon = card.findElement(By.xpath(".//a[@class='text-primary'][3]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deleteIcon);
					wait.until(ExpectedConditions.elementToBeClickable(deleteIcon)).click();
					phaseFound = true;
					break;
				}
			}

			if (!phaseFound) {
				List<WebElement> nextButtonList = driver.findElements(
						By.xpath("//ul[contains(@class,'pagination')]//a[contains(@class,'page-link') and .//span[text()='Next']]")
						);

				if (nextButtonList.isEmpty() || !nextButtonList.get(0).isEnabled()) {
					throw new NoSuchElementException("Phase with name '" + phaseName + "' not found.");
				}

				((JavascriptExecutor) driver).executeScript("arguments[0].click();", nextButtonList.get(0));
				wait.until(ExpectedConditions.stalenessOf(phaseCards.get(0)));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h5")));
			}
		}

	}

	/**
	 * Clicks the "No" button to cancel deletion of a phase.
	 */
	public void cancelDelete() {
		wait.until(ExpectedConditions.elementToBeClickable(deleteConfirmNoButton)).click();
	}

	/**
	 * Clicks the "Yes" button to confirm deletion of a phase.
	 */

	public void confirmDelete() {
		wait.until(ExpectedConditions.elementToBeClickable(deleteConfirmYesButton)).click();
	}
	/**
	 * Gets the success message displayed after deleting a phase.
	 * @return The text of the success message.
	 */
	public String getPhaseDeletedSuccessMessage() {
		wait.until(ExpectedConditions.visibilityOf(phaseDeletedSuccessMessage));
		return phaseDeletedSuccessMessage.getText().trim();
	}

	public void scrollToTop() {
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,100)");
	}

}

