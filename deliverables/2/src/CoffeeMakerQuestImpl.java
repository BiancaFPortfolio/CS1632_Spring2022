import static org.mockito.Mockito.RETURNS_MOCKS;

import java.util.*;

public class CoffeeMakerQuestImpl implements CoffeeMakerQuest {

	// TODO: Add more member variables and methods as needed.
	Player player;
	Room startRoom;
	Room northRoom;
	Room currentRoom;
	boolean gameOver;
	ArrayList<Room> roomList;
	int playerLocation;

	CoffeeMakerQuestImpl() {
		// TODO
		player = null;
		startRoom = null;
		gameOver = false;
		northRoom = startRoom;
		roomList = new ArrayList<Room>();
		currentRoom = null;
		playerLocation = -1;
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
		player = p;
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
		if(room == null || startRoom != null) {
			return false;
		} else {
			roomList.add(room);
			startRoom = room;
			northRoom = startRoom;
			return true;
		}
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
		if(room == null || northDoor == null || southDoor == null || startRoom == null) {
			return false;
		}

		for(int i = 0; i < roomList.size(); i++) {
			if(roomList.get(i).getAdjective().equals(room.getAdjective()) || roomList.get(i).getFurnishing().equals(room.getFurnishing())) {
				return false;
			}
		}

		northRoom.setNorthDoor(northDoor);
		room.setSouthDoor(southDoor);
		northRoom = room;
		roomList.add(northRoom);

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
		return currentRoom;
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
		for(int i = 0; i < roomList.size(); i++) {
			if(roomList.get(i).getAdjective().equals(room.getAdjective())) {
				playerLocation = i;
				currentRoom = roomList.get(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Get the instructions string command prompt. It returns the following prompt:
	 * " INSTRUCTIONS (N,S,L,I,D,H) > ".
	 * 
	 * @return comamnd prompt string
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
		String str = "";
		Room r = new Room("", "", Item.NONE);
		switch(cmd) {
			case "N": case "n":
				if(playerLocation == roomList.size()-1 || playerLocation == -1) {
					str = "A door in that direction does not exist.\n";
				} else {
					playerLocation++;
					currentRoom = roomList.get(playerLocation);
				}
				break;
			case "S": case "s":
				if(playerLocation <= 0) {
					str = "A door in that direction does not exist.\n";
				} else {
					playerLocation--;
					currentRoom = roomList.get(playerLocation);
				}
				break;
			case "L": case "l":
				if(currentRoom.getItem().equals(Item.COFFEE)) {
					str = "There might be something here...\nYou found some caffeinated coffee!\n";
					player.addItem(Item.COFFEE);
				} else if(currentRoom.getItem().equals(Item.CREAM)) {
					str = "There might be something here...\nYou found some creamy cream!\n";
					player.addItem(Item.CREAM);
				} else if(currentRoom.getItem().equals(Item.SUGAR)) {
					str = "There might be something here...\nYou found some sweet sugar!\n";
					player.addItem(Item.SUGAR);
				} else {
					str = "You don't see anything out of the ordinary.\n";
				}
				break;
			case "D": case "d":
				str = player.getInventoryString();
				str += "\n";
				str += winConditions(player);
				return str;
			case "I": case "i":
				str = player.getInventoryString();
				break;
			case "H": case "h":
				str = "N - Go north\nS - Go south\nL - Look and collect any items in the room\nI - Show inventory of items collected\nD - Drink coffee made from items in inventory\n";
				break;
			default:
				str = "What?";
				break;
		}
		
		return str;
	}
	
	private String winConditions(Player p) {
		gameOver = true;
		if(p.checkCoffee() && p.checkCream() && p.checkSugar()) {
			return "You drink the beverage and are ready to study!\nYou win!\n";
		} else if(!p.checkCoffee() && !p.checkCream() && !p.checkSugar()) {
			return "You drink the air, as you have no coffee, sugar, or cream.\nThe air is invigorating, but not invigorating enough. You cannot study.\nYou lose!\n";
		} else if(p.checkCoffee() && p.checkCream()) {
			return "Without sugar, the coffee is too bitter. You cannot study.\nYou lose!\n";
		} else if(p.checkCoffee() && p.checkSugar()) {
			return "Without cream, you get an ulcer and cannot study.\nYou lose!\n";
		} else if(p.checkCream() && p.checkSugar()) {
			return "You drink the sweetened cream, but without caffeine you cannot study.\nYou lose!\n";
		} else if(p.checkCoffee()) {
			return "Without cream, you get an ulcer and cannot study.\nYou lose!\n";
		} else if(p.checkCream()) {
			return "You drink the cream, but without caffeine, you cannot study.\nYou lose!\nå";
		} else {
			return "You eat the sugar, but without caffeine, you cannot study.\nYou lose!\n";
		}
	}
}
