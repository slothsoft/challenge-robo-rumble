package de.slothsoft.roborumble.contrib2;

import de.slothsoft.roborumble.Robot;

public class Pear implements Robot {

	@Override
	public void execute(Context context) {
		// nothing to do
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Pear;
	}

	@Override
	public int hashCode() {
		return 42;
	}

	@Override
	public String toString() {
		return "PearPositioner";
	}

}
