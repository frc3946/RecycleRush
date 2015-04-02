package org.usfirst.frc.team3946.robot.subsystems;

import org.usfirst.frc.team3946.robot.Robot;
import org.usfirst.frc.team3946.robot.RobotMap;
//import org.usfirst.frc.team3946.robot.commands.lift.ElevateWithTriggers;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator extends PIDSubsystem {
    
	public Talon motor = new Talon(RobotMap.liftTalon);
    public Encoder encode = RobotMap.liftEncoder;
    public DigitalInput bottom = new DigitalInput(RobotMap.bottomSwitch);
    
	static double p = 0.01;
	static double i = 0.0;
	static double d = 0.0;
	double height;
    int level = 0;
    int setMotorDirection;
    boolean override = false;
    
//    public double[] potVolts = {
//    		0, .167, .334, .501, .635, .881, 1
//    };
    
    public double[] setPoints = {
    		 0,
      		(12.1 / 12),
      		(24.2 / 12),
      		(36.3 / 12),
      		(48.4 / 12),
      		(60.5 / 12),
      		(72.6 / 12)
    };
    
    public int[] feet = {
    		0, 1, 2, 3, 4, 5, 6
    };

    public Elevator() {
    	super("Elevator", p, i, d);
        setAbsoluteTolerance(0.005);     
        getPIDController().setContinuous(true);
        if (Robot.isReal()) {
        	encode.setDistancePerPulse(0.1272345);
	    } else {
	    	encode.setDistancePerPulse((4.0/*in*/*Math.PI)/(360.0*12.0/*in/ft*/));
	    }
	    // 0.01060287527213352263064593786011 inches per pulse
	    // 94.314039761290039374540986558176  pulses per inch
        
	    // 0.12723450326560227156775125432132 feet per pulse
	    // 1131.7684771354804724944918386981 pulses per foot
    }
 
    public void initDefaultCommand() {
    	//setDefaultCommand(new ElevateWithTriggers());
    	//encode.setPIDSourceParameter(PIDSourceParameter.kDistance);
    }
    
    public void switchOverride () {
    	override = !override;
    	SmartDashboard.putBoolean("Override?", override);
    }
    
    public void elevate(double input) {
	    
//    	double height = encode.getDistance();
//    	Command command;
//    	if(height > 10){
//    		command = new SetLEDColors(2);
//    		command.start();
//    	} else if(height > 20) {
//    		command = new SetLEDColors(3);
//    		command.start();
//    	} else if(height < 10) {
//    		command = new SetLEDColors(1);
//    		command.start();
//    	} else if(height > 30) {
//    		command = new SetLEDColors(4);
//    		command.start();
//    	}
    	
		// Take the raw input if no switches are engaged.
    	if (override == true /*|| bottom.get() == false*/) {		
	    	motor.set(input);
			return;
	    } else if (bottom.get() == true && input < 0) {
			stop();
			return;
		} else {
			motor.set(input);
		}
    }
    
    public void stop() {
    	motor.set(0);
    }
    
    //---------------------------------------//
    
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
        // Prevents lift from smacking the floor.
    	if (bottom.get() == true && output < 0) {
    		stop();
    		return;
        } else {
        	motor.set(output * setMotorDirection);
        	return;
        }
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
    
    //---------------------------------------//
    
    public void log() {
    	SmartDashboard.putData("At Bottom?", bottom);
    	SmartDashboard.putNumber("Tote Height", encode.getDistance());
    }
}
