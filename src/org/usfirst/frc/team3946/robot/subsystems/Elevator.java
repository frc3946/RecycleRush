package org.usfirst.frc.team3946.robot.subsystems;

import org.usfirst.frc.team3946.robot.Robot;
import org.usfirst.frc.team3946.robot.RobotMap;
import org.usfirst.frc.team3946.robot.commands.lift.ElevateWithTriggers;
import org.usfirst.frc.team3946.robot.commands.misc.SetLEDColors;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator extends PIDSubsystem {
    
	public Talon motor1 = new Talon(RobotMap.liftTalon1);
    public Talon motor2 = new Talon(RobotMap.liftTalon2);
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
    	setDefaultCommand(new ElevateWithTriggers());
    	//encode.setPIDSourceParameter(PIDSourceParameter.kDistance);
    }
    
    public void switchOverride () {
    	override = !override;
    	SmartDashboard.putBoolean("Override?", override);
    }
    
    public void elevate(double input) {
	    

    	double encoder = encode.getDistance();
    	Command command;
    	if(encoder > 10){
    		command = new SetLEDColors(2);
    		command.start();
    	}else if(encoder > 20){
    		command = new SetLEDColors(3);
    		command.start();
    	}else if(encoder < 10){
    		command = new SetLEDColors(1);
    		command.start();
    	}else if(encoder >30){
    		command = new SetLEDColors(4);
    		command.start();
    	}
    	
    	
//		ledChooser.addObject("White", new SetLEDColors(1));
//		ledChooser.addObject("Red", new SetLEDColors(2));
//		ledChooser.addObject("Blue", new SetLEDColors(3));
//		ledChooser.addDefault("Green", new SetLEDColors(4));
//		ledChooser.addObject("Yellow", new SetLEDColors(5));
//		ledChooser.addObject("Cyan", new SetLEDColors(6));
//		ledChooser.addObject("Magenta", new SetLEDColors(7));
    	
    	
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
    	if (override == true || bottom.get() == false) {		
	    	motor1.set(input);
			motor2.set(input);
			return;
	    } else if (bottom.get() == true && input < 0) {
			stop();
			return;
		}
    }
    
    public void stop() {
    	motor1.set(0);
    	motor2.set(0);
    }
    
    //---------------------------------------//
    
    /**
     * Use the potentiometer as the PID sensor. This method is automatically
     * called by the subsystem.
     */
    protected double returnPIDInput() {
    	height = encode.getDistance();
    	return height;
//    	int firstPoint = 0;
//		int secondPoint = 1;
//    	double volts = pot.get();
//    	while(secondPoint < feet.length){
//    		if(volts >= potVolts[firstPoint] ||
//				(volts <= potVolts[secondPoint])){
//    			double slope = potVolts[secondPoint] - potVolts[firstPoint];
//    			slope = slope / (feet[secondPoint] - feet[firstPoint]);
//    			double intercept = potVolts[firstPoint] - feet[firstPoint] * slope;
//    			double distance = (volts - intercept) / slope;
//    			SmartDashboard.putNumber("Elevator Height: ", distance);
//    			return distance;
//    		}
//    		else{
//    			firstPoint++;
//    			secondPoint++;
//    		}
//		 
//    		if(secondPoint >= potVolts.length){
//    			return 0.0;
//    		}
//    	}
    	
    	return encode.getDistance();
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
        	motor1.set(output * setMotorDirection);
        	motor2.set(output * setMotorDirection);
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
