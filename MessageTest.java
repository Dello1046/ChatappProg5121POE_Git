import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MessageTest {

	@Test
    public void createMassageHash_Valid() 
    {
        String messageContent = "Hi Mike, can you join us for dinner tonight";
        boolean isValid = Message.createMessageHash(messageContent, messageContent) != null;
       
        assertEquals(true, isValid);  
    }
	
	 @Test
	    public void createMassageHash_Invalid() 
	    {
	        String messageContent = "Message exceeds 250 characters by a number here, please reduce size."
	        		+ "Still try to add more words or characters to make it over two hundred and fifty."
	        		+ "I have the to still even add more because i don't thing characters are more than "
	        		+ "the quantity required.";
	        boolean invalid = Message.createMessageHash(messageContent, messageContent) != null;
	        
	        assertEquals(false, invalid);
	    }
	 
	 @Test
	    public void checkRecipientCell_Valid() 
	    {
	        String recipientCell = "+27718693002";
	        boolean isValid = Message.checkRecipientCell(recipientCell);
	       
	        assertEquals(true, isValid);  
	    }
	 
	 @Test
	    public void checkRecipientCell_Invalid() 
	    {
	        String recipientCell = "08575975889";
	        boolean isInvalid = Message.checkRecipientCell(recipientCell);
	       
	        assertEquals(false, isInvalid);
	    }
	 
	 @Test
	    public void checkRecipientCell_7Valid() 
	    {
	        String recipientCell = "+27718693002";
	        boolean isValid = Message.checkRecipientCell(recipientCell);
	       
	        assertEquals(true, isValid);  
	    }

}
