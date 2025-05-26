import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LoginTest {
	
	Login Login_Obj = new Login();
	
    public LoginTest() {
    	
    }

    @Test
    public void testCheckUserName_Valid() 
    {
        String username = "Kyl_1";
        boolean isValid = Login.checkUserName(username);
       
        assertEquals(true, isValid);  
    }
	
    @Test
    public void testCheckUserName_Invalid() 
    {
        String username = "kyle!!!!!!!";
        boolean invalid = Login.checkUserName(username);
        
        assertEquals(false, invalid);
    }
    
    @Test
    public void testCheckPasswordComplexity_IsValid() 
    {
    	String password = "Ch&&sec@ke99!";
    	boolean valid = Login.checkPasswordComplexity(password);
    	
    	assertEquals(true, valid);
    }
    
    @Test
    public void testCheckPasswordComplexity_Invalid() 
    {
    	String password = "password";
    	Login.checkPasswordComplexity(password);
    	
    	assertEquals(false, Login.checkPasswordComplexity(password));
    }
    
    @Test
    public void testCellphoneNumberIsValid() 
    {
    	String phoneNumber = "+27838968976";
    	Login.checkCellPhoneNumber(phoneNumber);
    	
    	assertEquals(true, Login.checkCellPhoneNumber(phoneNumber));
    }
    
    @Test
    public void testInvalidCountrycode()
    {
    	String phoneNumber = "08966553";
    	assertEquals(false, Login.checkCellPhoneNumber(phoneNumber));
    			
    }
    
    //Following tests are assertTrue/False
    @Test
    public void testLoginSuccessful() 
    {
    	assertTrue(Login.checkUserName("Kyl_1"));
    	assertTrue(Login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }
    
    @Test
    public void testLoginFailed()
    {
    	assertFalse(Login.checkUserName("kyle!!!!!!!"));
    	assertFalse(Login.checkPasswordComplexity("password"));
    }
    
    @Test
    public void usernameFormat_ValidTest()
    {
    	assertTrue(Login.checkUserName("Kyl_1"));
    }
    
    @Test
    public void usernameformat_InvalidTest()
    {
        String username = "kyle!!!!!!!";
    	assertFalse(Login.checkUserName(username));
    	
    }
    
    @Test
    public void passwordComplexityCheck_ValidTest()
    {
    	String password = "Ch&&sec@ke99!";
    	assertTrue(Login.checkPasswordComplexity(password));
    }
    
    @Test
    public void passwordComplexityCheck_InvalidTest()
    {
        boolean expected = Login.checkPasswordComplexity("password");
        assertFalse(expected);
    }
    
    @Test
    public void cellPhoneValid_formatTest()
    {
    	boolean phoneValid = Login.checkCellPhoneNumber("+27838968976");
    	assertTrue(phoneValid);
    }
    
    @Test
    public void cellPhoneInvalid_fortmatTest()
    {
    	String phoneInvalid = "08966553";
    	assertFalse(Login.checkCellPhoneNumber(phoneInvalid));
    }

}
