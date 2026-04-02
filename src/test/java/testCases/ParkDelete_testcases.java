package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CreateCompanyPage;
import pageObjects.LoginPage;
import pageObjects.ParkPhaseBuildingDelete;
import testBase.BaseClass;

public class ParkDelete_testcases extends BaseClass {

	@Test (priority=1,groups="Positive",description="Verify whether admin can delete a park from the list of parks")
	public void deletePark() throws InterruptedException {
		LoginPage lp=new LoginPage(driver);
		lp.setuserName(p.getProperty("username"));
		lp.setpassWord(p.getProperty("password"));
		lp.setSubmit();
		CreateCompanyPage Cp= new CreateCompanyPage(driver);
		Cp.clickViewAllPark();
		ParkPhaseBuildingDelete epark=new ParkPhaseBuildingDelete(driver);
		
		epark.deletePark(p.getProperty("RandomParkName"));
		epark.confirmDelete();
		Thread.sleep(3000);
				}
}
