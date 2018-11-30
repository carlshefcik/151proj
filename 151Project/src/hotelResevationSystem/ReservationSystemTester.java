package hotelResevationSystem;

public class ReservationSystemTester {
	//put static variables here
	
	
	public static void main(String args[]) {
		System.out.println("Starting application");
		System.out.println();

		
		HotelReservationSystem hrs = new HotelReservationSystem();
		HotelReservationViewer hrv = new HotelReservationViewer(hrs);
		
	}

}
