public class Agent {
	private Environment e;
	private int changeTemp; // 1 means increase temp, -1 means decrease temp, 0 means don't change temp
	private double eTemp;
	private double outTemp;
	private double ACTemp;

	public void sense() {
		e = Environment.getInstance();
		e.getEnvironmentTemp();
		eTemp = e.getEnvironmentTemp();
		outTemp = e.getOutTemp();
		AC a = new AC();
		ACTemp = a.getACTemp();
	}

	public int decide() {
		if(eTemp <= ACTemp) {
			changeTemp = 1;
		} else if (eTemp >= outTemp) {
			changeTemp = -1;
		}
		
	//	if (onOff == true) {
			//if (eTemp > ACTemp) {
				//changeTemp = -1;
		//	}
			//if (eTemp <= ACTemp) {
				//changeTemp = 1;
				
			//}
		//} else if (onOff == false) {
			//if (eTemp < outTemp) {
				//changeTemp = 1;
			//}
			//if (eTemp >= outTemp) {
				//changeTemp = -1;
			//}
		//}
		return changeTemp;
	}

	public double act() {
		//System.out.print(changeTemp);

		double finalTemp = 0;
		if (changeTemp == 1) {
			double n = eTemp;
			n += 0.01;
			e.setTemp(n);
			finalTemp = eTemp;
		} else if (changeTemp == -1) {
			double n2 = eTemp;
			n2 -= 0.01;
			e.setTemp(n2);
			finalTemp = eTemp;
		} else {
			e.setTemp(eTemp);
			finalTemp = eTemp;
		}
		return finalTemp;
	}
}
