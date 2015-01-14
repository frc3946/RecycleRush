package org.usfirst.frc.team3946.robot.subsystems;

import org.usfirst.frc.team3946.robot.commands.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.buttons.XboxController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class DriveTrain extends Subsystem {
    private SpeedController left_motor, right_motor, 
    						strafe_motor;
    private RobotDrive drive;
    private AnalogInput left_rangefinder, 
						right_rangefinder;
    private Gyro gyro;

    public DriveTrain() {
    	super();
    	left_motor = new Talon(1);
    	right_motor = new Talon(2);
    	strafe_motor = new Talon(3);
    	drive = new RobotDrive(left_motor, right_motor);
    	left_rangefinder = new AnalogInput(1);
    	right_rangefinder = new AnalogInput(2);
    	gyro = new Gyro(1);
    	
    	LiveWindow.addActuator("Drivetrain", "Left Motor", (Talon) left_motor);
    	LiveWindow.addActuator("Drivetrain", "Right Motor", (Talon) right_motor);
    	LiveWindow.addActuator("Drivetrain", "Strafe Motor", (Talon) strafe_motor);
    	LiveWindow.addSensor("Drivetrain", "Left Rangefinder", (AnalogInput) left_rangefinder);
        LiveWindow.addSensor("Drivetrain", "Right Rangefinder", (AnalogInput) right_rangefinder);
        LiveWindow.addSensor("Drivetrain", "Gyro", gyro);
    }
    public void initDefaultCommand() {
        setDefaultCommand(new HDrive());
	
    }
    
    public void log() {
    	SmartDashboard.putNumber("Gyro", gyro.getAngle());
    }
    /**Arcade style driving for the drivetrain.
	 * @param left speed in range [-1,1]
	 * @param right speed in range [-1,1]
	 */
    public void drive(double left, double right) {
    	drive.arcadeDrive(left, right);
    }
    
    /**
	 * @param drive_controller xbox joystick to use to drive arcade style.
	 */
    public void drive(XboxController drive_controller) {
    	//drive(-drive_controller.getLeftStickX(), -drive_controller.getLeftStickY());
    }
    
    /**
	 * @return The robots heading in degrees.
	 */
	public double getHeading() {
		return gyro.getAngle();
	}

	/**
	 * Reset the robots sensors to the zero states.
	 */
	public void reset() {
		gyro.reset();
	}
	
	/**
	 * @return The distance to the obstacle detected by the rangefinder. 
	 */
	public double getDistanceToObstacle() {
		return(left_rangefinder.getAverageVoltage() + right_rangefinder.getAverageVoltage());
	}

}

