package com.je.services.rentals;

import com.je.services.dailies.Daily;

public interface RentalService {

	public Daily saveRental(Rental rental);

	public boolean existsLocalRental(Rental rental);
}
