
public class Environment {
	private static Environment m_instance;
	private double inTemp;
	private int outTemp;

	private Environment() {
		inTemp = 27;
		outTemp = 35;
	}

	public static Environment getInstance() {
		if (m_instance == null) {
			m_instance = new Environment();
		}
		return m_instance;
	}
	
	public double getEnvironmentTemp() {
		return inTemp;
	}
	public int getOutTemp() {
		return outTemp;
	}
	public void setTemp(double t) {
		inTemp = t;
	}
}