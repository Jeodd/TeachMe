package fr.utbm.TeachMe.services;

import fr.utbm.TeachMe.entity.Location;
import fr.utbm.TeachMe.repository.LocationDao;

import java.util.List;

public class LocationService {
    LocationDao ldao = new LocationDao();
    public List<Location> getAllLocation(){
        return ldao.getAllLocations();
    }
}
