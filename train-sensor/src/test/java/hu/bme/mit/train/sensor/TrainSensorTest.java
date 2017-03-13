package hu.bme.mit.train.sensor;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

import static org.mockito.Mockito.*;

public class TrainSensorTest {

	TrainController mockTC;
	TrainUser mockTU;
	TrainSensorImpl trainSensor;
	
	
    @Before
    public void before() {
    	mockTC=mock(TrainController.class);
    	mockTU=mock(TrainUser.class);
    	trainSensor=new TrainSensorImpl(mockTC, mockTU);    	
    }

    @Test
    public void testAbsoluteMarginMin() {
    	trainSensor.overrideSpeedLimit(-10);
    	verify(mockTU,times(2)).setAlarmState(true);
    }
    
    @Test
    public void testAbsoluteMarginMax() {
    	trainSensor.overrideSpeedLimit(600);
    	verify(mockTU,times(1)).setAlarmState(true);
    }
    
    @Test
    public void testAbsoluteMarginBetween() {
    	trainSensor.overrideSpeedLimit(300);
    	verify(mockTU,times(0)).setAlarmState(true);
    }
    
    @Test
    public void testRelativeMarginTooSlow() {
    	when(mockTC.getReferenceSpeed()).thenReturn(50);
    	trainSensor.overrideSpeedLimit(10);
    	verify(mockTU,times(1)).setAlarmState(true);
    }
}
