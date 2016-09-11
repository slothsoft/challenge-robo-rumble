package de.slothsoft.roborumble.contrib;

import java.util.Objects;

import de.slothsoft.roborumble.Robot;

public abstract class AbstractRobot implements Robot {

	@Override
	public int hashCode() {
		return 37 * (getDisplayName() == null ? 3 : getDisplayName().hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AbstractRobot that = (AbstractRobot) obj;
		if (!Objects.equals(getDisplayName(), that.getDisplayName())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return getDisplayName();
	}

}
