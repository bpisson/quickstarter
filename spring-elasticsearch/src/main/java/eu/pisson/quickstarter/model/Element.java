package eu.pisson.quickstarter.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Model object.
 * 
 * @author Bertrand Pisson
 *
 */
public class Element {

	@Getter
	@Setter
	private long id;

	@Getter
	@Setter
	private String name;
}
