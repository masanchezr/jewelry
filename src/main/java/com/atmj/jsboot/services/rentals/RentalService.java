package com.atmj.jsboot.services.rentals;

import com.atmj.jsboot.services.dailies.Daily;

public interface RentalService {

	public Daily saveRental(Rental rental);

	public boolean existsLocalRental(Rental rental);
}
