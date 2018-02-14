package me.travisgray.Repositories;

import me.travisgray.Models.Apartment;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ${TravisGray} on 2/13/2018.
 */
public interface ApartmentRepository extends CrudRepository<Apartment, Long> {
}
