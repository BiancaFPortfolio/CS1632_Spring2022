import java.util.*;

public class CoffeeMakerQuestImpl implements CoffeeMakerQuest {

	// TODO: Add more member variables and methods as needed.
	Player player;
	List<Room> roomList;
	Room curr = null;
	List<String> adjectives;
	List<String> furnishing;
	boolean gameOver;
	Room LastRoomOnNorth;
	Room firstRoom;
	
	
	public CoffeeMakerQuestImpl() {
		super();
		//p = new Player();
		roomList = new ArrayList<Room>();
		gameOver = false;
		adjectives = new ArrayList<String>();
		furnishing = new ArrayList<String>();
		// TODO
	}
	

	/**
	 * Whether the game is over. The game ends when the player drinks the coffee.
	 * 
	 * @return true if successful, false otherwise
	 */
	public boolean isGameOver() {
		// TODO
		return gameOver;
	}

	/**
	 * Set the player to p.
	 * 
	 * @param p the player
	 */
	public void setPlayer(Player p) {
		// TODO
		this.player = p;
	}
	
	/**
	 * Add the first room in the game. If room is null or if this not the first room
	 * (there are pre-exiting rooms), the room is not added and false is returned.
	 * 
	 * @param room the room to add
	 * @return true if successful, false otherwise
	 */
	public boolean addFirstRoom(Room room) {
		// TODO
		if(room != null && roomList.isEmpty() == true) {
			LastRoomOnNorth = room;
			roomList.add(room);
			adjectives.add(room.getAdjective());
			furnishing.add(room.getDescription());
			firstRoom = room;
			return true;
		}
		
		return false;
	}

	/**
	 * Attach room to the northern-most room. If either room, northDoor, or
	 * southDoor are null, the room is not added. If there are no pre-exiting rooms,
	 * the room is not added. If room is not a unique room (a pre-exiting room has
	 * the same adjective or furnishing), the room is not added. If all these tests
	 * pass, the room is added. Also, the north door of the northern-most room is
	 * labeled northDoor and the south door of the added room is labeled southDoor.
	 * Of course, the north door of the new room is still null because there is
	 * no room to the north of the new room.
	 * 
	 * @param room      the room to add
	 * @param northDoor string to label the north door of the current northern-most room
	 * @param southDoor string to label the south door of the newly added room
	 * @return true if successful, false otherwise
	 */
	public boolean addRoomAtNorth(Room room, String northDoor, String southDoor) {
		// TODO
		if(northDoor == null || southDoor == null || room == null) {
			return false;
		}
		if(roomList.isEmpty() == true) {
			return false;
		}
		//for(int i = 0; i<roomList.size(); i++) {
			//if(room.getAdjective() == roomList.get(i).getAdjective() || room.getFurnishing() == roomList.get(i).getFurnishing()) {
			//	return false;
			//}
		
		if (adjectives.contains (room.getAdjective()) || furnishing.contains(room.getFurnishing()))
			return false;
		
		
		
		//room.setSouthDoor(southDoor);
		//roomList.get(roomList.size()-1).setNorthDoor(northDoor);
		roomList.add(room);
		adjectives.add(room.getAdjective());
		furnishing.add(room.getFurnishing());
		

		room.setSouthDoor(southDoor);
		LastRoomOnNorth.setNorthDoor(northDoor);
		LastRoomOnNorth = room;
		
		
		
		return true;
	}

	/**
	 * Returns the room the player is currently in. If location of player has not
	 * yet been initialized with setCurrentRoom, returns null.
	 * 
	 * @return room player is in, or null if not yet initialized
	 */ 
	public Room getCurrentRoom() {
		// TODO
		
		return curr;
	}
	
	/**
	 * Set the current location of the player. If room does not exist in the game,
	 * then the location of the player does not change and false is returned.
	 * 
	 * @param room the room to set as the player location
	 * @return true if successful, false otherwise
	 */
	public boolean setCurrentRoom(Room room) {
		// TODO
		
		if (!(roomList.contains(room)))
			return false;
			
		curr = room;
		
		return true;
	}
	
	/**
	 * Get the instructions string command prompt. It returns the following prompt:
	 * " INSTRUCTIONS (N,S,L,I,D,H) > ".
	 * 
	 * @return command prompt string
	 */
	public String getInstructionsString() {
		// TODO
		return " INSTRUCTIONS (N,S,L,I,D,H) > ";
	}
	
	/**
	 * Processes the user command given in String cmd and returns the response
	 * string. For the list of commands, please see the Coffee Maker Quest
	 * requirements documentation (note that commands can be both upper-case and
	 * lower-case). For the response strings, observe the response strings printed
	 * by coffeemaker.jar. The "N" and "S" commands potentially change the location
	 * of the player. The "L" command potentially adds an item to the player
	 * inventory. The "D" command drinks the coffee and ends the game. Make
     * sure you use Player.getInventoryString() whenever you need to display
     * the inventory.
	 * 
	 * @param cmd the user command
	 * @return response string for the command
	 */
	public String processCommand(String cmd) {
		// TODO
		String result = "";
		String str = "";
		
		
		if (cmd.equalsIgnoreCase ("L")) 
		{
			Item item = curr.getItem();
			switch(item){
			case SUGAR:
				str ="There might be something here...\nYou found some sweet sugar!\n";
				break;
			case COFFEE:
				str = "There might be something here...\nYou found some caffeinated coffee!\n";
				break;
			case CREAM:
				str = "There might be something here...\nYou found some creamy cream!\n";
				break;
			case NONE:
				str = "You don't see anything out of the ordinary.\n";
				break;	
			}
			player.addItem (item);
			return str;
		}
		else if (cmd.equalsIgnoreCase("I"))
			return player.getInventoryString();
		else if (cmd.equalsIgnoreCase("H"))
			return "N - Go north\nS - Go south\nL - Look and collect any items in the room\nI - Show inventory of items collected\nD - Drink coffee made from items in inventory\n";
		else if (cmd.equalsIgnoreCase("N")) {
			if (curr != LastRoomOnNorth) 
			{
				curr = roomList.get(roomList.indexOf(curr) + 1);
				return "";
			}
				return "A door in that direction does not exist.\n";}
		else if (cmd.equalsIgnoreCase("S")) {
				if (curr != firstRoom) {
					 setCurrentRoom(roomList.get(roomList.indexOf(curr) - 1));
					return"";}  
					return "A door in that direction does not exist.\n";}
		else if (cmd.equalsIgnoreCase("D")) 
		{
			if (player.checkCoffee() && player. checkSugar() && player.checkCream()) {
				result = player.getInventoryString();
				result += "\nYou drink the beverage and are ready to study!\nYou win!\n"; }
			else {
				result = player.getInventoryString() + "\n";				
				result += helperMethod();
				}
			gameOver = true;		
			return result;
		}
		return "";
	}
	
	private String helperMethod()
	{
		String message = "";
		if (!player.checkCoffee() && player. checkSugar() && player.checkCream())
			message = "You drink the sweetened cream, but without caffeine you cannot study.\nYou lose!\n";

		else if (player.checkCoffee() && !(player. checkSugar()) && player.checkCream()) 
			message = "Without sugar, the coffee is too bitter. You cannot study.\nYou lose!\n";
		else if (player.checkCoffee() && player. checkSugar() && !(player.checkCream())) 
				message = "Without cream, you get an ulcer and cannot study.\nYou lose!\n";
		else if (player.checkCoffee() && !(player. checkSugar()) && !(player.checkCream())) 
			message = "Without cream, you get an ulcer and cannot study.\nYou lose!\n";
		else if (!(player.checkCoffee()) && !(player. checkSugar()) && player.checkCream()) 
			message = "You drink the cream, but without caffeine, you cannot study.\nYou lose!\n";
		else if (!(player.checkCoffee()) && player. checkSugar() && !(player.checkCream())) 
			message = "You eat the sugar, but without caffeine, you cannot study.\nYou lose!\n";
		else if (!(player.checkCoffee()) && !(player. checkSugar()) && !(player.checkCream())) 
			message = "You drink the air, as you have no coffee, sugar, or cream.\nThe air is invigorating, but not invigorating enough. You cannot study.\nYou lose!\n";
		
		return message;
	}
	
}