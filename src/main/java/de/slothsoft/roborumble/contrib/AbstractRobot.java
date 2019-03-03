package de.slothsoft.roborumble.contrib;

import java.util.Objects;

import de.slothsoft.roborumble.Robot;

/**
 * Abstract base class for {@link Robot}s that does {@link #equals(Object)} and
 * {@link #hashCode()} correctly.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public abstract class AbstractRobot implements Robot {

	@Override
	public int hashCode() {
		return 37 * (getDisplayName() == null ? 3 : getDisplayName().hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		final AbstractRobot that = (AbstractRobot) obj;
		if (!Objects.equals(getDisplayName(), that.getDisplayName())) return false;
		return true;
	}

	@Override
	public String toString() {
		return getDisplayName();
	}

}
