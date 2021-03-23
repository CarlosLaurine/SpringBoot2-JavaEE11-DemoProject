package com.educandoweb.springBootStudies.entities.enums;

public enum OrderStatus {
	/*OBS1: As tested at H2 Database, Java will automatically assign
	  natural numbers to each Status starting at zero in a crescent 
	  order (0,1,2,3(...)) related to the order each one was stated
	  (for instance, in this case, PAID = 1 and CANCELED = 4)*/
	
	/*OBS2: Such automatic attribution, however, can generate
      a serious maintenance problem. In case someone inserts
      a new status at the middle of the status sequence 
      (between PAID and SHIPPED, for example), all the other
      status below the alteration will have an incorrect
      number attributed to it, since Java does not update
      the their former numbers. This will lead to major 
      errors at the Database and must be carefully avoided */
	
	/*OBS3: To solve such problem at a possible future scenario,
	  it is highly recommendable to make manual attributions 
	  between parenthesis of those int values while creating 
	  the Enum Entity */
	
	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	/*After the direct attribution of the int numbers, Java will
	  require further implementations shown below*/
	
	//Enum attribute for the int code related to the Enum type
	
	private int code;
	
	//Enum PRIVATE Constructor 
	
	private OrderStatus	(int code) {
		this.code = code;
	}
	
	//Get Method for int Code
	
	public int getCode () {
		return code;
	}
	/*Static Method to convert the int value (code) into an Enum type 
	 (3 into SHIPPED, for example)
	*/
	
	public static OrderStatus valueOf (int code) {
		
		//Using forEach method to go through OrderStatus Values using .values() function
		
		for(OrderStatus type: OrderStatus.values()) {
			//Setting condition to verify whether the code inserted exists as a type
			if(type.getCode() == code) {
				return type;
			}
		}
		
		throw new IllegalArgumentException("Invalid Input! No Order Status have the Inserted Code!!");
	}
}
