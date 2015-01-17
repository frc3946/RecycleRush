package org.usfirst.frc.team3946.robot.subsystems;

import org.usfirst.frc.team3946.robot.Robot;
import org.usfirst.frc.team3946.robot.commands.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.XboxController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem {
    private SpeedController leftMotor, rightMotor;
    private RobotDrive drive;
    private AnalogInput leftRangeFinder, rightRangeFinder;
    private Gyro gyro;

    public DriveTrain() {
    	super();
    	leftMotor = new Talon(1);
    	rightMotor = new Talon(2);
    	drive = new RobotDrive(leftMotor, rightMotor);
    	leftRangeFinder = new AnalogInput(1);
    	rightRangeFinder = new AnalogInput(2);
    	gyro = new Gyro(1);
    	
		// Show everything in the LiveWindow
    	LiveWindow.addActuator("Drivetrain", "Left Motor", (Talon) leftMotor);
    	LiveWindow.addActuator("Drivetrain", "Right Motor", (Talon) rightMotor);
    	LiveWindow.addSensor("Drivetrain", "Left Rangefinder", (AnalogInput) leftRangeFinder);
        LiveWindow.addSensor("Drivetrain", "Right Rangefinder", (AnalogInput) rightRangeFinder);
        LiveWindow.addSensor("Drivetrain", "Gyro", gyro);
    }
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoystick());
	
    }
    
    public void log() {
    	SmartDashboard.putNumber("Gyro", gyro.getAngle());
    }
        
    /**Arcade style driving for the drivetrain.
	 * @param left speed in range [-1,1]
	 * @param right speed in range [-1,1]
	 */
    public void drive(double move, double rotate) {
    	drive.arcadeDrive(move, rotate);
    }
    
    /**
	 * @param controller xbox joystick to use to drive arcade style.
	 */
    public void drive(XboxController controller) {
    	drive(controller.getLeftStickY(), controller.getLeftStickX());
    }
    
    public void stop() {
    	Robot.drivetrain.drive(0, 0);
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
		//leftRangeFinder.reset();
		//rightRangeFinder.reset();
	}
	
	/**
	 * @return The distance to the obstacle detected by the range finders. 
	 */
	public double getDistanceToObstacle() {
		return(leftRangeFinder.getAverageVoltage() + rightRangeFinder.getAverageVoltage());
	}

}

