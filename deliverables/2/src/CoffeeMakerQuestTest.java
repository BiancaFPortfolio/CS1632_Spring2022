import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.mockito.*;
import static org.mockito.Mockito.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CoffeeMakerQuestTest {

	CoffeeMakerQuest cmq;
	Player player;
	Room room1;	// Small room
	Room room2;	// Funny room
	Room room3;	// Refinanced room
	Room room4;	// Dumb room
	Room room5;	// Bloodthirsty room
	Room room6;	// Rough room

	@Before
	public void setup() {
		// 0. Turn on bug injection for Player and Room.
		Config.setBuggyPlayer(true);
		Config.setBuggyRoom(true);
		
		// 1. Create the Coffee Maker Quest object and assign to cmq.
		cmq = CoffeeMakerQuest.createInstance();

		// TODO: 2. Create a mock Player and assign to player and call cmq.setPlayer(player). 
		// Player should not have any items (no coffee, no cream, no sugar)
		player = Mockito.mock(Player.class);
		cmq.setPlayer(player);

		// TODO: 3. Create mock Rooms and assign to room1, room2, ..., room6.
		// Mimic the furnishings / adjectives / items of the rooms in the original Coffee Maker Quest.
		room1 = Mockito.mock(Room.class);
		room2 = Mockito.mock(Room.class);
		room3 = Mockito.mock(Room.class);
		room4 = Mockito.mock(Room.class);
		room5 = Mockito.mock(Room.class);
		room6 = Mockito.mock(Room.class);
		
		// TODO: 4. Add the rooms created above to mimic the layout of the original Coffee Maker Quest.
		when(room1.getAdjective()).thenReturn("Small");
		when(room1.getItem()).thenReturn(Item.CREAM);
		when(room1.getFurnishing()).thenReturn("Quaint sofa");
		when(room2.getAdjective()).thenReturn("Funny");
		when(room2.getItem()).thenReturn(Item.NONE);
		when(room2.getFurnishing()).thenReturn("Sad record player");
		when(room3.getAdjective()).thenReturn("Refinanced");
		when(room3.getItem()).thenReturn(Item.COFFEE);
		when(room3.getFurnishing()).thenReturn("Tight pizza");
		when(room4.getAdjective()).thenReturn("Dumb");
		when(room4.getItem()).thenReturn(Item.NONE);
		when(room4.getFurnishing()).thenReturn("Flat energy drink");
		when(room5.getAdjective()).thenReturn("Bloodthirsty");
		when(room5.getItem()).thenReturn(Item.NONE);
		when(room5.getFurnishing()).thenReturn("Beautiful bag of money");
		when(room6.getAdjective()).thenReturn("Rough");
		when(room6.getItem()).thenReturn(Item.SUGAR);
		when(room6.getFurnishing()).thenReturn("Perfect air hockey table");

		cmq.addFirstRoom(room1);
		cmq.addRoomAtNorth(room2, "Magenta", "Massive");
		cmq.addRoomAtNorth(room3, "Beige", "Smart");
		cmq.addRoomAtNorth(room4, "Dead", "Slim");
		cmq.addRoomAtNorth(room5, "Vivacious", "Sandy");
		cmq.addRoomAtNorth(room6, "Purple", "Minimalist");
	}

	@After
	public void tearDown() {
	}
	
	/**
	 * Test case for String getInstructionsString().
	 * Preconditions: None.
	 * Execution steps: Call cmq.getInstructionsString().
	 * Postconditions: Return value is " INSTRUCTIONS (N,S,L,I,D,H) > ".
	 */
	@Test
	public void testGetInstructionsString() {
		// TODO
		assertEquals(cmq.getInstructionsString(), " INSTRUCTIONS (N,S,L,I,D,H) > ");
	}
	
	/**
	 * Test case for boolean addFirstRoom(Room room).
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                Create a mock room and assign to myRoom.
	 * Execution steps: Call cmq.addFirstRoom(myRoom).
	 * Postconditions: Return value is false.
	 */
	@Test
	public void testAddFirstRoom() {
		// TODO
		Room myRoom = Mockito.mock(Room.class);
		assertFalse(cmq.addFirstRoom(myRoom));
	}
	
	/**
	 * Test case for boolean addRoomAtNorth(Room room, String northDoor, String southDoor).
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                Create a mock "Fake" room with "Fake bed" furnishing with no item, and assign to myRoom.
	 * Execution steps: Call cmq.addRoomAtNorth(myRoom, "North", "South").
	 * Postconditions: Return value is true.
	 *                 room6.setNorthDoor("North") is called.
	 *                 myRoom.setSouthDoor("South") is called.
	 */
	@Test
	public void testAddRoomAtNorthUnique() {
		// TODO
		Room myRoom = Mockito.mock(Room.class);
		when(myRoom.getAdjective()).thenReturn("Fake");
		when(myRoom.getFurnishing()).thenReturn("Fake bed");
		when(myRoom.getItem()).thenReturn(Item.NONE);

		assertTrue(cmq.addRoomAtNorth(myRoom, "North", "South"));
		Mockito.verify(room6, Mockito.times(1)).setNorthDoor("North");
		Mockito.verify(myRoom, Mockito.times(1)).setSouthDoor("South");
	}
	
	/**
	 * Test case for boolean addRoomAtNorth(Room room, String northDoor, String southDoor).
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                Create a mock "Fake" room with "Flat energy drink" furnishing with no item, and assign to myRoom.
	 * Execution steps: Call cmq.addRoomAtNorth(myRoom, "North", "South").
	 * Postconditions: Return value is false.
	 *                 room6.setNorthDoor("North") is not called.
	 *                 myRoom.setSouthDoor("South") is not called.
	 */
	@Test
	public void testAddRoomAtNorthDuplicate() {
		// TODO
		Room myRoom = Mockito.mock(Room.class);
		when(myRoom.getAdjective()).thenReturn("Fake");
		when(myRoom.getFurnishing()).thenReturn("Flat energy drink");
		when(myRoom.getItem()).thenReturn(Item.NONE);

		assertFalse(cmq.addRoomAtNorth(myRoom, "North", "South"));
		Mockito.verify(myRoom, Mockito.times(0)).setNorthDoor("North");
		Mockito.verify(myRoom, Mockito.times(0)).setSouthDoor("South");
	}
	
	/**
	 * Test case for Room getCurrentRoom().
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                cmq.setCurrentRoom(Room) has not yet been called.
	 * Execution steps: Call cmq.getCurrentRoom().
	 * Postconditions: Return value is null.
	 */
	@Test
	public void testGetCurrentRoom() {
		// TODO
		assertEquals(cmq.getCurrentRoom(), null);
	}
	
	/**
	 * Test case for void setCurrentRoom(Room room) and Room getCurrentRoom().
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                cmq.setCurrentRoom(Room room) has not yet been called.
	 * Execution steps: Call cmq.setCurrentRoom(room3).
	 *                  Call cmq.getCurrentRoom().
	 * Postconditions: Return value of cmq.setCurrentRoom(room3) is true. 
	 *                 Return value of cmq.getCurrentRoom() is room3.
	 */
	@Test
	public void testSetCurrentRoom() {
		// TODO
		assertTrue(cmq.setCurrentRoom(room3));
		assertEquals(cmq.getCurrentRoom(), room3);
	}
	
	/**
	 * Test case for String processCommand("I").
	 * Preconditions: Player does not have any items.
	 * Execution steps: Call cmq.processCommand("I").
	 * Postconditions: Return value is "YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n".
	 */
	@Test
	public void testProcessCommandI() {
		// TODO
		when(player.checkCoffee()).thenReturn(false);
		when(player.checkCream()).thenReturn(false);
		when(player.checkSugar()).thenReturn(false);
		when(player.getInventoryString()).thenReturn("YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n");
		
		assertEquals(cmq.processCommand("I"), "YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n");
	}
	
	/**
	 * Test case for String processCommand("l").
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                cmq.setCurrentRoom(room1) has been called.
	 * Execution steps: Call cmq.processCommand("l").
	 * Postconditions: Return value is "There might be something here...\nYou found some creamy cream!\n".
	 *                 player.addItem(Item.CREAM) is called.
	 */
	@Test
	public void testProcessCommandLCream() {
		// TODO
		cmq.setCurrentRoom(room1);

		assertEquals(cmq.processCommand("l"), "There might be something here...\nYou found some creamy cream!\n");
		Mockito.verify(player, Mockito.times(1)).addItem(Item.CREAM);
	}
	
	/**
	 * Test case for String processCommand("n").
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                cmq.setCurrentRoom(room4) has been called.
	 * Execution steps: Call cmq.processCommand("n").
	 *                  Call cmq.getCurrentRoom().
	 * Postconditions: Return value of cmq.processCommand("n") is "".
	 *                 Return value of cmq.getCurrentRoom() is room5.
	 */
	@Test
	public void testProcessCommandN() {
		// TODO
		cmq.setCurrentRoom(room4);
		
		assertEquals(cmq.processCommand("n"), "");
		assertEquals(cmq.getCurrentRoom(), room5);
	}
	
	/**
	 * Test case for String processCommand("s").
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                cmq.setCurrentRoom(room1) has been called.
	 * Execution steps: Call cmq.processCommand("s").
	 *                  Call cmq.getCurrentRoom().
	 * Postconditions: Return value of cmq.processCommand("s") is "A door in that direction does not exist.\n".
	 *                 Return value of cmq.getCurrentRoom() is room1.
	 */
	@Test
	public void testProcessCommandS() {
		// TODO
		cmq.setCurrentRoom(room1);

		assertEquals(cmq.processCommand("s"), "A door in that direction does not exist.\n");
		assertEquals(cmq.getCurrentRoom(), room1);
	}
	
	/**
	 * Test case for String processCommand("D").
	 * Preconditions: Player has no items.
	 * Execution steps: Call cmq.processCommand("D").
	 *                  Call cmq.isGameOver().
	 * Postconditions: Return value of cmq.processCommand("D") is "YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n\nYou drink the air, as you have no coffee, sugar, or cream.\nThe air is invigorating, but not invigorating enough. You cannot study.\nYou lose!\n".
	 *                 Return value of cmq.isGameOver() is true.
	 */
	@Test
	public void testProcessCommandDLose() {
		// TODO
		when(player.checkCoffee()).thenReturn(false);
		when(player.checkCream()).thenReturn(false);
		when(player.checkSugar()).thenReturn(false);
		when(player.getInventoryString()).thenReturn("YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n");

		assertEquals(cmq.processCommand("D"), "YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n\nYou drink the air, as you have no coffee, sugar, or cream.\nThe air is invigorating, but not invigorating enough. You cannot study.\nYou lose!\n");
		assertTrue(cmq.isGameOver());
	}
	
	/**
	 * Test case for String processCommand("D").
	 * Preconditions: Player has all 3 items (coffee, cream, sugar).
	 * Execution steps: Call cmq.processCommand("D").
	 *                  Call cmq.isGameOver().
	 * Postconditions: Return value of cmq.processCommand("D") is "You have a cup of delicious coffee.\nYou have some fresh cream.\nYou have some tasty sugar.\n\nYou drink the beverage and are ready to study!\nYou win!\n".
	 *                 Return value of cmq.isGameOver() is true.
	 */
	@Test
	public void testProcessCommandDWin() {
		// TODO
		when(player.checkCoffee()).thenReturn(true);
		when(player.checkCream()).thenReturn(true);
		when(player.checkSugar()).thenReturn(true);
		when(player.getInventoryString()).thenReturn("You have a cup of delicious coffee.\nYou have some fresh cream.\nYou have some tasty sugar.\n");
		
		assertEquals(cmq.processCommand("D"), "You have a cup of delicious coffee.\nYou have some fresh cream.\nYou have some tasty sugar.\n\nYou drink the beverage and are ready to study!\nYou win!\n");
		assertTrue(cmq.isGameOver());
	}
	
	// TODO: Put in more unit tests of your own making to improve coverage!
	/**
	 * Test case for testWinConditions.
	 * Preconditions: Player has 1 item (sugar).
	 * Execution steps: Call cmq.processCommand("D").
	 *                  Call cmq.isGameOver().
	 * Postconditions: Return value of cmq.processCommand("D") is "YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYou have some tasty sugar.\n\nYou eat the sugar, but without caffeine, you cannot study.\nYou lose!\n"
	 *                 Return value of cmq.isGameOver() is true.
	 */
	@Test
	public void testWinConditionsSugar() {
		when(player.checkSugar()).thenReturn(true);
		when(player.checkCoffee()).thenReturn(false);
		when(player.checkCream()).thenReturn(false);
		when(player.getInventoryString()).thenReturn("YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYou have some tasty sugar.\n");
		
		assertEquals(cmq.processCommand("D"), "YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYou have some tasty sugar.\n\nYou eat the sugar, but without caffeine, you cannot study.\nYou lose!\n");
		assertTrue(cmq.isGameOver());
	}
	
	/**
	 * Test case for String processCommand("l").
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                cmq.setCurrentRoom(room6) has been called.
	 * Execution steps: Call cmq.processCommand("l").
	 * Postconditions: Return value is "There might be something here...\nYou found some sweet sugar!\n".
	 *                 player.addItem(Item.SUGAR) is called.
	 */
	@Test
	public void testProcessCommandLSugar() {
		// TODO
		cmq.setCurrentRoom(room6);

		assertEquals(cmq.processCommand("l"), "There might be something here...\nYou found some sweet sugar!\n");
		Mockito.verify(player, Mockito.times(1)).addItem(Item.SUGAR);
	}
	
	/**
	 * Test case for String processCommand("h").
	 * Preconditions:
	 *              
	 * Execution steps: Call cmq.processCommand("h").
	 * Postconditions: Return value is "N - Go north\nS - Go south\nL - Look and collect any items in the room\nI - Show inventory of items collected\nD - Drink coffee made from items in inventory\n"
	 *                 
	 */
	@Test 
	public void testProcessCommandH() {
		assertEquals(cmq.processCommand("h"), "N - Go north\nS - Go south\nL - Look and collect any items in the room\nI - Show inventory of items collected\nD - Drink coffee made from items in inventory\n");
	}
	
	/**
	 * Test case for testWinConditions.
	 * Preconditions: Player has 2 items (cream, sugar).
	 * Execution steps: Call cmq.processCommand("D").
	 *                  Call cmq.isGameOver().
	 * Postconditions: Return value of cmq.processCommand("D") is "YOU HAVE NO COFFEE!\nYou have some fresh cream.\nYou have some tasty sugar.\n\nYou drink the sweetened cream, but without caffeine you cannot study.\nYou lose!\n"
	 *                 Return value of cmq.isGameOver() is true.
	 */
	@Test
	public void testWinConditionsCreamSugar() {
		when(player.checkSugar()).thenReturn(true);
		when(player.checkCoffee()).thenReturn(false);
		when(player.checkCream()).thenReturn(true);
		when(player.getInventoryString()).thenReturn("YOU HAVE NO COFFEE!\nYou have some fresh cream.\nYou have some tasty sugar.\n");
		
		assertEquals(cmq.processCommand("D"), "YOU HAVE NO COFFEE!\nYou have some fresh cream.\nYou have some tasty sugar.\n\nYou drink the sweetened cream, but without caffeine you cannot study.\nYou lose!\n");
		assertTrue(cmq.isGameOver());
	}
	
	/**
	 * Test case for testWinConditions.
	 * Preconditions: Player has 1 item (cream).
	 * Execution steps: Call cmq.processCommand("D").
	 *                  Call cmq.isGameOver().
	 * Postconditions: Return value of cmq.processCommand("D") is "YOU HAVE NO COFFEE!\nYou have some fresh cream.\nYOU HAVE NO SUGAR!\n\nYou drink the cream, but without caffeine, you cannot study.\nYou lose!\n"
	 *                 Return value of cmq.isGameOver() is true.
	 */
	@Test
	public void testWinConditionsCream() {
		when(player.checkSugar()).thenReturn(false);
		when(player.checkCoffee()).thenReturn(false);
		when(player.checkCream()).thenReturn(true);
		when(player.getInventoryString()).thenReturn("YOU HAVE NO COFFEE!\nYou have some fresh cream.\nYOU HAVE NO SUGAR!\n");
		
		assertEquals(cmq.processCommand("D"), "YOU HAVE NO COFFEE!\nYou have some fresh cream.\nYOU HAVE NO SUGAR!\n\nYou drink the cream, but without caffeine, you cannot study.\nYou lose!\n");
		assertTrue(cmq.isGameOver());
	}
	
	/**
	 * Test case for String processCommand("k").
	 * Preconditions: 
	 * Execution steps: Call cmq.processCommand("n").
	 *                  Call cmq.getCurrentRoom().
	 * Postconditions: Return value of cmq.processCommand("k") is "What?".
	 *               
	 */
	@Test
	public void testProcessCommandInvalid() {
		// TODO	
		assertEquals(cmq.processCommand("k"), "What?");
	}
	
	/**
	 * Test case for String processCommand("l").
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                cmq.setCurrentRoom(room3) has been called.
	 * Execution steps: Call cmq.processCommand("l").
	 * Postconditions: Return value is "There might be something here...\nYou found some caffeinated coffee!\n".
	 *                 player.addItem(Item.COFFEE) is called.
	 */
	@Test
	public void testProcessCommandLCoffee() {
		// TODO
		cmq.setCurrentRoom(room3);

		assertEquals(cmq.processCommand("l"), "There might be something here...\nYou found some caffeinated coffee!\n");
		Mockito.verify(player, Mockito.times(1)).addItem(Item.COFFEE);
	}
	
	/**
	 * Test case for String processCommand("l").
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                cmq.setCurrentRoom(room2) has been called.
	 * Execution steps: Call cmq.processCommand("l").
	 * Postconditions: Return value is "There might be something here...\nYou found some caffeinated coffee!\n".
	 *                 player.addItem(Item.COFFEE) is called.
	 */
	@Test
	public void testProcessCommandLNone() {
		// TODO
		cmq.setCurrentRoom(room2);

		assertEquals(cmq.processCommand("l"), "You don't see anything out of the ordinary.\n");
	}
}
