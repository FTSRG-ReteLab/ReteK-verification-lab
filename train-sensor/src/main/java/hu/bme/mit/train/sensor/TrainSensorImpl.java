package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;

public class TrainSensorImpl implements TrainSensor {

	private TrainController controller;
	private int speedLimit = 5;

	Timer timer = new Timer();


	public TrainSensorImpl(TrainController controller) {
		this.controller = controller;

		timer.scheduleAtFixedRate(new TimerTask() {
			  @Override
			  public void run() {
				  controller.followSpeed();
			  }
			},0, 1000);
	}

	@Override
	public int getSpeedLimit() {
		return speedLimit;
	}

	@Override
	public void overrideSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		controller.setSpeedLimit(speedLimit);
	}

}
