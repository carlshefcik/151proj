package hotelResevationSystem;

import java.util.ArrayList;
import java.util.HashMap;

public class HotelReservationSystem {
	
	private Room[] luxoryRooms;
	private Room[] economicRooms;
	private HashMap<User, Room> accounts;
	
	public HotelReservationSystem(int luxory, int economic)
	{
		luxoryRooms = new Room[luxory];
		for(int i = 0; i < luxory ; i++)
		{
			luxoryRooms[i] = new Luxory();
		}
	}

}
