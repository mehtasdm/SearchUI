package com.overstock.sui.pagelibrary;

//import com.gargoylesoftware.htmlunit.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by smehta on 5/3/2017.
 * SUI-155 - Create Search Navigation Feedback Questionnaire on PLP - DA
 * BtnYes
 * 1. Comment should accept multi-line input
 * 2. Comment should accept no input
 * 3. Thank you for your feedback! - message should be displayed
 * 4. After Submit - Yes button should be disabled
 * 5. After Submit - No button should be disabled
 * *
 * BtnNo
 * 1. Choose an Issue
 * 2. I need Customer Service
 * 3. Having trouble filtering/sorting
 * 4. Can’t find the desired product
 * 5. The product tile looks wrong
 * 6. Page is broken/won’t load
 * 7. Can you add a feature?
 */
public class SrcNavFeedbackQuestionnairePLP {
    //
    WebDriver driver;
    WebDriverWait wait;
    //
    @FindBy(css = "button.btn.sn-btn.btn-default[value='true']")
    WebElement btnYes;
    @FindBy(css = "button.btn.sn-btn.btn-default[value='false']")
    WebElement btnNo;
    @FindBy(css = ".btn.sn-btn.btn-primary.feedback-submit-button")
    WebElement btnSubmit;
    @FindBy(css = "textarea.feedback-comment")
    WebElement strComment;
    @FindBy(css = "p.success")                                                      // Thank you for your feedback!
    WebElement msgSuccess;
    @FindBy(css = "p.feedback-footer-message")                                      // If you need further help or information please visit our Online Help Center
    WebElement msgFooter;
    @FindBy(css = "div.feedback-form>h3.feedback-title")                            // Did you find what you were looking for?
    WebElement feedbackTitle;
    @FindBy(css = "div.feedback-comment-wrapper h3.feedback-title:nth-child(1)")    // What best describes your issue?
    WebElement feedbackIssueTitle1;
    @FindBy(css = "div.feedback-comment-wrapper h3.feedback-title:nth-child(2)")   // Please leave some comments about your experience.
    WebElement feedbackIssueTitle2;
//    @FindBy(css = "textarea.feedback-comment")
//    WebElement feedbackComment;
    @FindBy(css = "p.error")                                                        // Comments are required to submit feedback
    WebElement msgError;
//    @FindBy(css = "select#feedback-issues")
//    WebElement feedbackIssuesDropdown;
    @FindBy(css = "div.feedback-form > a")                                                           // Contact customer care.
    WebElement cCCC;
    //
    public SrcNavFeedbackQuestionnairePLP(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //
    public void clickBtnYes() {
        btnYes.click();
    }
    //
    public void clickBtnNo() {
        btnNo.click();
    }
    //
    public void clickBtnSubmit() {
        btnSubmit.click();
    }
    //
    public void setComment(String strComment) {
        WebElement feedbackComment = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("textarea.feedback-comment")));
        feedbackComment.sendKeys(strComment);
//        feedbackComment.sendKeys(strComment);
    }
    //
    public boolean verifyBtnYesEnabled() {
        return (btnYes.isEnabled());
    }
    //
    public boolean verifyBtnNoEnabled() {
        return (btnNo.isEnabled());
    }
    //
    public boolean verifySuccessMsg() {
        return (msgSuccess.getText().contains("Thank you for your feedback!"));
    }
    //
    public boolean verifyFooterMsg() {
        return (msgFooter.getText().contains("If you need further help or information please visit"));
    }
    //
    public boolean verifyFeedbackTitle() {
        return (feedbackTitle.getText().contains("Did you find what you were looking for?"));
    }
    //
    public boolean verifyErrorMsg() {
        return (msgError.getText().contains("Comments are required to submit feedback"));
    }
    //
    public boolean verifyFeedbackIssueTitle1() {
        return (feedbackIssueTitle1.getText().contains("What best describes your issue?"));
    }
    //
    public boolean verifyFeedbackIssueTitle2() {
        return  (feedbackIssueTitle2.getText().contains("Please leave some comments about your experience."));
    }
    //
    public void setFeedbackIssuesDropdown(String issuesDropdown) {
        Select feedbackIssuesDropdown = new Select(driver.findElement(By.cssSelector("select#feedback-issues")));
        feedbackIssuesDropdown.selectByVisibleText(issuesDropdown);
    }
    //
    public boolean verifyCCC() {
        return (cCCC.getText().contains("Contact customer care."));
    }
    //
    public void clickCCC() {
        cCCC.click();
    }
    //
}