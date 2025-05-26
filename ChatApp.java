/*
 * @Autho_Mhlongo Khonzinkosi
 * 
 * Stu_ST10465413
 * 
 * This application allows a user to register, login and display login status after.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class ChatApp 
{

		//store registered users information
		public static Map<String, Map<String, String>> registeredUsers = new HashMap<>();
		
			public static void main(String[] args) 
			
			{
				Scanner userInput = new Scanner(System.in);
				
				String username;
				String password;
				String phoneNumber;
				
				registeredUsers.clear();
				//welcome massage
				System.out.println("Welcome to the Prog chat");
				
				//Prompting the user for registration
				System.out.print("Enter username: ");
				username = userInput.nextLine();
				
	            //Method that checks method checkUserName if is valid and display
				while (!Login.checkUserName(username)) {
				    System.out.println("\nUser name is not "
				            + "\ncorrectly formatted, "
				            + "\nplease ensure that your"
				            + "\nusername contains"
				            + "\nan underscore "
				            + "\nand is no more than"
				            + "\nfive characters in length.");
				    
				    // Prompting the user to try again
				    System.out.print("Enter username: ");
				    username = userInput.nextLine();
				}
				System.out.println("\nUser name successfully captured."); // Exiting after a valid user name
				
				
				//Prompt the user to enter password
				System.out.print("Enter password: ");
				password = userInput.nextLine();
			    
				while (!Login.checkPasswordComplexity(password)) 
				{
					System.out.println("\nPassword is not "
							+ "\ncorrectly formatted; please "
							+ "\nensure that the pasword contains "
							+ "\nat least eight characters,"
							+ "\na capital latter, a number and "
							+ "\na special character.");
			
					// Prompting the user to try again
					System.out.print("Enter password: ");
					password = userInput.nextLine();
				}
				System.out.println("\nPassword successfully captured."); // Exiting after a valid user password			
				
				
				//Prompting the user
				System.out.print("Enter phone number: ");
				phoneNumber = userInput.nextLine();
				
				while (!Login.checkCellPhoneNumber(phoneNumber)) 
				{
					System.out.println("\nCell phone number incorrectly "
							+ "\nformated or does not contain "
							+ "\ninternational code."); 
					
					// Prompting the user to try again
					System.out.print("Enter phone number: ");
					phoneNumber = userInput.nextLine();
					}
				System.out.println("\nCell phone number successfully added."); // Exiting after a valid user cell phone
				
				
				// Storing user details
		        Map<String, String> userDetails = new HashMap<>();
		        userDetails.put("password", password); //Add password to the map and store actual put password
		        userDetails.put("phoneNumber", phoneNumber); //Add numbers to the map and store actual numbers from the user
		        
		        //Calls method that return necessary registration massages for verification
		        if (!registeredUsers.containsKey(username)) 
		        {
		            registeredUsers.put(username, userDetails);   
		        } 
		        
		     // Immediate login check
	            String loginStatus = Login.returnLoginStatus(username, password, registeredUsers);
	            
	            if (loginStatus.equals("Login successful")) 
	            {
	                System.out.println("\n       Welcome to QuickChat");
	            }

		        
		        
		        //part 2 start here for MAIN MENU
				int Option = 0;
				while (Option != 3)
		        {
					String Input = JOptionPane.showInputDialog(null,
					"\nChoose the option below:\n" +
			        "Option 1) Send Messages\n" +
			        "Option 2) Show recently sent messges\n" +
			        "Option 3) Quit",
			        "Quick Menu", JOptionPane.QUESTION_MESSAGE
			       
			        );
					
					if (userInput == null)
					{
						//Exit
						break;
					}
					
					try 
					{
						Option = Integer.parseInt(Input);
					}
					catch (NumberFormatException e)
					{
						JOptionPane.showMessageDialog(null, "Please enter a valid number.");
						continue;
					}
					
					
					if (Option == 1)
		        	{
		        		sendMessageHandling(userInput);
		        	}
					else if (Option == 2)
		        	{
		        		JOptionPane.showMessageDialog(null, "Coming Soon!");
		        	
		        	}
					else if (Option == 3)
					{
						JOptionPane.showMessageDialog(null, "Thanks for using QuickChat!");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Invalid option.");
					}
					
		        }
		   
			        
			
		    }
			
			// Helper method
			public static void sendMessageHandling(Scanner scanner)
			{
				System.out.print("\nHow many messages do you wish to enter (1-4)? ");
				int count = 0;
				
				try 
				{
					count = Integer.parseInt(scanner.nextLine());
					
				}
				catch (NumberFormatException e)
				{
					System.out.println("Invalid number.");
					return;
				}
				
				if (count < 1 || count > 4)
				{
					System.out.println("Please enter between 1 and 4");
					return;
				}
				
				// Recording number of massages were sent before
				int before = Message.messageCounter; 
				for (int i = 0; i < count; i++)
				{
					createMessage(scanner);
					
				}
				
				//displating how many massage were sent
				int sentNow = Message.messageCounter - before;
				JOptionPane.showMessageDialog(null,"Total message sent in this session: " + sentNow, "Message Count", JOptionPane.INFORMATION_MESSAGE);
				
				
			}
			
			public static void createMessage(Scanner scanner)
			{
				String messageID = Message.generateMessageID();
				
				System.out.println("\nEnter recipient number (start with +27): ");
				String recipient = scanner.nextLine();
				
				if (!Message.checkRecipientCell(recipient))
				{
					System.out.println("Invalid recipient number.");
					return;
				}
				
				System.out.print("\nEnter message (max 250 characters.): ");
				String content = scanner.nextLine();
				if (content.length() > 250)
				{
					int excess = content.length() - 250;
					System.out.println("\nMessage exceeds 250 characters by "
							+ excess + ", please reduce size.");
					
				}
				else 
				{
					System.out.println("\n       Message ready to send.");
					
				}
				
				String hash = Message.createMessageHash(messageID, content);
				if (hash == null)
					return;
				
				String action = Message.SentMessage(scanner);
				if (action.equals("send") || action.equals("store"))
				{
					Message msg = new Message(messageID, hash, recipient, content);
					Message.messages.add(msg);
				    Message.messageCounter++;
				    
				    //Showing details in JOptiionPane
				    String messageInfo = "Message Sent!\n\n" 
				                       + "Message ID: " + messageID + "\n"
				                       + "Message Hash: " + hash + "\n"
				                       + "Recipient: " + recipient + "\n"
				                       + "Message: " + content;
				    JOptionPane.showMessageDialog(null, messageInfo, "Message Details", JOptionPane.INFORMATION_MESSAGE);
				    
				    System.out.println("Message " + action + "ed.");    
			   }
				else
				{
					System.out.println("Message disregarded.");
				}
	       }	
}		

