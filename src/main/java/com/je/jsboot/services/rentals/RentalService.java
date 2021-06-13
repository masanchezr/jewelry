package com.je.jsboot.services.rentals;

import com.je.jsboot.services.dailies.Daily;

public interface RentalService {

	public Daily saveRental(Rental rental);

	public boolean existsLocalRental(Rental rental);
}
