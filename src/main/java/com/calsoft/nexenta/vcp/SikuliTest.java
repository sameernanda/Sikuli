package com.calsoft.nexenta.vcp;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.nexenta.ftaf.utilities.connectors.fusionGUI.ExcelPojoForGUI;

public class SikuliTest extends SikuliTestBase{

	@ BeforeTest
	public void setUp(){  
		_sikuliestMethods.open_Browser("chrome");
		_sikuliestMethods.open_URL(new ExcelPojoForGUI("", "", "", "", "https://172.17.111.66:9443/vsphere-client/#", ""));
		_sikuliestMethods.enter_Text(new ExcelPojoForGUI("", "", "", "E:/Client/SIKULI/VCP_Images/username.PNG", "root", ""));
		_sikuliestMethods.enter_Text(new ExcelPojoForGUI("", "", "", "E:/Client/SIKULI/VCP_Images/password.PNG", "vmware", ""));
		_sikuliestMethods.click_Link(new ExcelPojoForGUI("", "", "", "E:/Client/SIKULI/VCP_Images/login.PNG", "", ""));

	}  

	@Test  
	public void testLogo() throws Exception{  
		_sikuliestMethods.verify_Image(new ExcelPojoForGUI("", "", "", "E:/Client/SIKULI/VCP_Images/verifyHome.PNG", "", ""));
	} 

	@ AfterTest  
	public void tearDown() throws InterruptedException{		
		_sikuliestMethods.quit_Browser();
	}  
}