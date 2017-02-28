package com.calsoft.nexenta.vcp;

import org.openqa.selenium.WebDriver;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class SikuliTestBase {

	protected Screen m_screen;  
	WebDriver driver ;
	SikuliTestMethods _sikuliestMethods = new SikuliTestMethods();
	
	Pattern loginImage = new Pattern("E:/Nexenta/SIKULI/VCP_Images/login.PNG");  
	Pattern usernameImage = new Pattern("E:/Nexenta/SIKULI/VCP_Images/username.PNG");  
	Pattern passwordImage = new Pattern("E:/Nexenta/SIKULI/VCP_Images/password.PNG");  
	Pattern verifyImage = new Pattern("E:/Nexenta/SIKULI/VCP_Images/verifyHome.PNG");
}
