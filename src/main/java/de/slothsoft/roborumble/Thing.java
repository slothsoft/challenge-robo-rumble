package de.slothsoft.roborumble;

public interface Thing {

	void execute();

	void collide(Robot robot);

	int getX();

	int getY();

}
