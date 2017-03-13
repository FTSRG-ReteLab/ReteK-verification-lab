package hu.bme.mit.train.sensor;

import java.util.Timer;
import java.util.TimerTask;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

public class TrainSensorImpl implements TrainSensor {

	private TrainController controller;
	private int speedLimit = 5;
	private TrainUser user;
	
	private Timer timer = new Timer();


	public TrainSensorImpl(TrainController controller,TrainUser user) {
		this.controller = controller;
		this.user=user;
		
	}

	@Override
	public int getSpeedLimit() {
		return speedLimit;
	}

	@Override
	public void overrideSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		controller.setSpeedLimit(speedLimit);
		
		setAbsoluteMargin(speedLimit);
		setRelativeMargin(speedLimit);
		
	}

	private void setAbsoluteMargin(int speedLimit) {
		if(speedLimit < 0 || speedLimit > 500){
			user.setAlarmState(true);
		}
		else{
			user.setAlarmState(false);
		}
	}
	
	private void setRelativeMargin(int speedLimit) {
		if(user.getAlarmState()){
			return;
		}
		if(speedLimit < controller.getReferenceSpeed()*0.5){
			user.setAlarmState(true);
		}
		else{
			user.setAlarmState(false);
		}
	}

}
