package org.usfirst.frc.team3946.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public interface RobotMap {
	
	// Speed Controllers
	int driveLeftTalon = 1;				//T1
	int driveRightTalon = 2;			//T2
	int driveStrafeTalon = 6;			//T6
	int liftTalon1 = 4;					//T4
	int liftTalon2 = 5;					//T5
	int spare = 3;						//T3
	
	// Relays
	int spike1 = 1;					//SPK1
	int spike2 = 2;					//SPK2 +blue -common
	int spike3 = 3;					//SPK3 +red -green
	
	// Digital IOs
	Encoder liftEncoder = new Encoder(0, 1, true, EncodingType.k4X);
	Encoder leftEncoder = new Encoder(2, 3, false, EncodingType.k4X);
	Encoder rightEncoder = new Encoder(4, 5, true, EncodingType.k4X);
	int lCurbFeeler = 2;
	int rCurbFeeler = 3;
	int bottomSwitch = 6;
	int touchSensor = 8;
	
	// Analog IOs
	int driveGyro = 0;				//disabled
	int lRangeF = 2;				//RF1
	int rRangeF = 3;				//RF2
}
