package de.slothsoft.roborumble;

/**
 * An object that is present on the {@link Map}.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public interface Thing {

	void execute();

	void collide(Robot robot);

	int getX();

	int getY();

}
