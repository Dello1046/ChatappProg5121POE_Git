import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class Message 
{
	private String messageID;
	private String messageHash;
	private String recipient;
	private String content;
	
	//initiation as constructor
	public Message(String messageID, String messageHash, String recipient, String content)
	{
		this.messageID = messageID;
		this.messageHash = messageHash;
		this.recipient = recipient;
		this.content = content;
	}
	
	@Override
	public String toString()
	{
		return "ID: " + messageID + " | Hash: " + messageHash + 
				" | recipient " + recipient + " | Message:" +  content;
	
	}
	
	
	public static int messageCounter = 0;
	public static List<Message> messages = new ArrayList<>();
	
	//Method that generate 10-digit message ID
	public static String generateMessageID()
	{
		String id = String.valueOf((long)(Math.random() * 1_000_000_0000L));
		return id.length() > 10 ? id.substring(0, 10) : id;
    }
	
	//Method that ensures ten characters of massage ID
	public static boolean checkMessageID(String messageId)
	{
	
		if (messageId.length() <= 10 )
		{
			return true;
		}
		
		return false;
	}
	
	//Method that ensures that the recipient phone number is in correct format
	public static boolean checkRecipientCell(String recipientCell)
	{
		return recipientCell.startsWith("+27")
		       && recipientCell.substring(1).matches("\\d+")
		       && recipientCell.length() >= 12
		       && recipientCell.length() <= 15;
		       
	}
	
	//Method that create and returns hash from content
	public static String createMessageHash(String messageID, String messageContent)
	{
		
		if (messageContent.length() > 250)
		{
			System.out.println("Enter a message of less that 250 characters. ");
			return null;
		}
		
		String[] userMessage = messageContent.trim().split("\\s+");
		String firstUserMessage = userMessage.length > 0 ? userMessage[0] : "EMPTY";
		String lastUserMessage = userMessage.length > 1 ? userMessage[userMessage.length - 1] : firstUserMessage;
		
		return (messageID.substring(0,2) + ":" + messageCounter + ":" + firstUserMessage 
				+ lastUserMessage).toUpperCase();
	
		
	}
	
	//Method that give user options as to send, store and disregard the message
	public static String SentMessage(Scanner userMessage)
	{
		System.out.print("\nDo you want to send, store, or disregard this message? ");
		return userMessage.nextLine().trim().toLowerCase();
	}
	
	//Method that returns all the messages sent while the programme runs
	public static String printMessages()
	{
		
		if (messages.isEmpty()) 
			return "No messages sent.";
		StringBuilder sb = new StringBuilder();
		for (Message m : messages)
		{
			sb.append(m.toString()).append("\n");
		}
		
		return sb.toString();
		
	}
	
	//Method that returns number of message sent
	public static int returnTotalMessages()
	{
		return messages.size();
		
	}
	
	//Method that creat and Store messages in JSON.
	public static String storeMessages()
	{
		JSONArray JsonArray = new JSONArray();
		
		for (Message m : messages)
		{
			JSONObject obj = new JSONObject();
			obj.put("messageID", m.messageID);
			obj.put("messageHash", m.messageHash);
			obj.put("recipient", m.recipient);
			obj.put("content", m.content);
			JsonArray.put(obj);
			
		}
		
		return JsonArray.toString(2);
	}

}
