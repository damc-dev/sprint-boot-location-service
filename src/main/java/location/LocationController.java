package location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/location")
public class LocationController {

	@Autowired
	LocationRepository repository;
	
	@RequestMapping(method={RequestMethod.GET}, produces="application/json")
	@ResponseBody
	public Iterable<Location> listLocations() {
		return repository.findAll();
	}
	
	@RequestMapping(value="/{id}", method={RequestMethod.GET}, produces="application/json")
	@ResponseBody
	public Location getLocation(@PathVariable Long id) {
		return repository.findOne(id);
	}
	
	@RequestMapping(method={RequestMethod.POST}, headers="Accept=application/json", produces="application/json", consumes="application/json")
	@ResponseBody
	public Location createLocation(@RequestBody Location location) {
		System.out.println("Saved: " + location.toString());
		location.setCreatedDate(new java.sql.Date(new java.util.Date().getTime()));
		location.setLastAccessed(new java.sql.Date(new java.util.Date().getTime()));
		location.setIsActive(true);
		return repository.save(location);
	}
	
}
