package hotelResevationSystem;

public class ReservationSystemTester {
	//put static variables here
	
	
	public static void main(String args[]) {
		System.out.println("Starting application");
		System.out.println();

		//creates the Model View objects and attaches controllers
		HotelReservationSystem hrs = new HotelReservationSystem();
		HotelReservationViewer hrv = new HotelReservationViewer(hrs);
		
		//adds view to the model to transition between view contents
		hrs.attachView(hrv);
		
	}

}
