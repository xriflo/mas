package utils;

public class HasProjecterConstraint extends Constraint{
	boolean flag;

	public HasProjecterConstraint(boolean flag) {
		super();
		this.flag = flag;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (flag ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HasProjecterConstraint other = (HasProjecterConstraint) obj;
		if (flag != other.flag)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(HasProjecter: " + flag +")";
	}
	
}
