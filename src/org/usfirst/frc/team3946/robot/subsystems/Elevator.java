package org.usfirst.frc.team3946.robot.subsystems;

import static org.usfirst.frc.team3946.robot.RobotMap.*;

import org.usfirst.frc.team3946.robot.Robot;
import org.usfirst.frc.team3946.robot.commands.lift.ElevateWithTriggers;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator extends PIDSubsystem {
    public Talon motor1 = new Talon(liftTalon1);
    public Talon motor2 = new Talon(liftTalon2);
    public Encoder encode = new Encoder(encoderA, encoderB, true, EncodingType.k4X);
    public DigitalInput lower = new DigitalInput(lowerLS);
    public DigitalInput bottom = new DigitalInput(bottomLS);
    
	static double p = 0.01;
	static double i = 0.0;
	static double d = 0.0;
	double height;
    int level = 0;
    int setMotorDirection;
    boolean override = false;
    
    public double[] setPoints = {
    		0,
      		(12.1 / 12),
      		(24.2 / 12),
      		(36.3 / 12),
      		(48.4 / 12),
      		(60.5 / 12),
      		(72.6 / 12)
    };
    
    public int[] feet = {0, 1, 2, 3, 4, 5, 6};

    public Elevator() {
    	super("Elevator", p, i, d);
        setAbsoluteTolerance(0.005);     
        getPIDController().setContinuous(true);
        encode.setPIDSourceParameter(PIDSourceParameter.kDistance);
        if (Robot.isReal()) {
        	encode.setDistancePerPulse(0.1272345);
        } else {
        	encode.setDistancePerPulse((4.0/*in*/*Math.PI)/(360.0*12.0/*in/ft*/));
        }
        // 0.01060287527213352263064593786011 inches per pulse
        // 94.314039761290039374540986558176  pulses per inch
        // 0.12723450326560227156775125432132 feet per pulse
        // 1131.7684771354804724944918386981 pulses per foot
        
		LiveWindow.addSensor("DriveTrain", "Encoder", encode);
        LiveWindow.addActuator("Elevator", "PID", getPIDController());
    }
 
    public void initDefaultCommand() {
    	setDefaultCommand(new ElevateWithTriggers());
    }
    
    public void elevate(double input) {
//	    if (override == true) {
//	    	motor1.set(input);
//			motor2.set(input);
//			return;
//	    }
//	    
//	    // Turns motors off when limit switches are engaged.
//		if (bottom.get() == true && input < 0) {
//			motor1.set(0);
//			motor2.set(0);
//			return;
//		}
//		// Slow motors down when secondary switches are engaged.
//		else if (lower.get() == true && input < 0) {
//			motor1.set(input * 0.5);
//			motor2.set(input * 0.5);
//			return;
//		} 
//		// Take the raw input if no switches are engaged.
//		else {
	    	motor1.set(input);
	    	motor2.set(input);
//		}
    }
    
    public void stop() {
    	motor1.set(0);
    	motor2.set(0);
    }
    
    public void log() {
    	SmartDashboard.putData("Near Bottom", lower);
    	SmartDashboard.putData("At Bottom", bottom);
    	SmartDashboard.putNumber("Lift Distance", encode.getDistance());
    }
      
    /**
     * Use the potentiometer as the PID sensor. This method is automatically
     * called by the subsystem.
     */
    protected double returnPIDInput() {
    	height = encode.getDistance();
    	return height;
    }
    
    /**
     * Use the motor as the PID output. This method is automatically called by
     * the subsystem.
     */
    protected void usePIDOutput(double output) {
//    	if (override == true) {
//	    	motor1.set(input);
//			motor2.set(input);
//			return;
//	    }    	
    	
    	// Disables motors when absolute limit switches are engaged.
    	if (bottom.get() == true && output < 0) {
    		motor1.set(0);
    		motor2.set(0);
    		return;
    	}
    	
    	// Slows motors down when secondary switches are engaged.
    	if (lower.get() == true && output < 0) {
    		motor1.set(output * 0.5 * setMotorDirection);
    		motor2.set(output * 0.5 * setMotorDirection);
    		return;
        } else {
        	motor1.set(output * setMotorDirection);
        	motor2.set(output * setMotorDirection);
        	return;
        }
    }
    
    public Encoder getEncoder() {
    	return encode;
    }
    
    public void switchOverride () {
    	override = !override;
    	SmartDashboard.putBoolean("Override?", override);
    }
    
    // Set maximum
    public void incLevel() {
    	setMotorDirection = 1;
    	this.setSetpoint(12.1);
    }
    
    // Set minimum
    public void decLevel() {
    	setMotorDirection = -1;
    	this.setSetpoint(12.1);
    }
    
    public void setLevel(int level) {
    	this.setSetpoint(setPoints[level]);
    }
    
}
