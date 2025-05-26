
import java.util.LinkedHashMap;
import java.util.Map;

public class Login {
	

	//Method that checks user name contains underscore and no more than five chars
	public static boolean checkUserName(String username) 
	{
		if (username.contains("_") && username.length() <= 5) 
		{
					return true;
		}
		else 
		{
				    return false;
		}			
				
	}
	
	
	//Method that checks password has at least 8 chars long, contain capital latter, number and special char
	public static boolean checkPasswordComplexity(String password) 
	{
		if (password.length() >= 8 && password.matches(".*[A-Z]*.") && 
				password.matches(".*[1-9]*.") && password.matches(".*[^a-zA-Z0-9_].*"))
		{
			return true;
		}
		else 
		{
			return false;
		}	
		
	}

	//Method that checks correct length and contains international country code
	public static boolean checkCellPhoneNumber(String CellNumber)
	{
		
		
		//checking if cell begins with "+" and and follows by the digits
		if (CellNumber.startsWith("+27") 
				&& CellNumber.substring(1).matches("\\d+")
				&& CellNumber.length() >=12 && CellNumber.length() <= 15 )	
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	//Method that return necessary registration massages.
	public static boolean registerUser(String username, String password, 
            Map<String, Map<String, String>> registeredUsers)
	{
	    
	  
		if (registeredUsers.containsKey(username)) 
		{
	        return false;
		}
		
			Map<String, String> userDetails = new LinkedHashMap<>();
		    
			userDetails.put("password", password); //Add password to the map
		    registeredUsers.put(username, userDetails); //Add username to the map 
			return true; 
	        
	}
	
	//Method that return necessary login status.
	public static String returnLoginStatus(String username, String userpassword, 
            Map<String, Map<String, String>> registeredUsers) 
	{
		if (!registeredUsers.containsKey(username)) //Checks if username does not already exist
		{
			return "A failed login";
		}
		String storedPassword = registeredUsers.get(username).get("password");
		return storedPassword.equals(userpassword) 
		        ? "Login successful"
		        : "Incorrect password";
		
	}

}


/*REFERENCES
 
 Here’s a simple Java method that checks if a phone number:

✅ Starts with the country code +27 (used in South Africa),

✅ Has the correct total length (e.g. 12 characters including +27),

✅ Contains only digits after the + sign.

CODE EXAMPLE
public class PhoneValidator {

    public static boolean isValidSAContactNumber(String number) {
        // Check if number starts with +27
        if (number == null || !number.startsWith("+27")) {
            return false;
        }

        // Check total length (should be 12: "+27" + 9 digits)
        if (number.length() != 12) {
            return false;
        }

        // Check that the rest are digits
        String digitsOnly = number.substring(1); // remove '+'
        return digitsOnly.matches("\\d+");
    }
}

OpenAI. (2025). ChatGPT (April 2025 version) [Large language model]. https://chat.openai.com
 
 */
