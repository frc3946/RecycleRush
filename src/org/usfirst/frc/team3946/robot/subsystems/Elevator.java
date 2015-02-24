package org.usfirst.frc.team3946.robot.subsystems;

import static org.usfirst.frc.team3946.robot.RobotMap.*;

import org.usfirst.frc.team3946.robot.commands.lift.ElevateWithTriggers;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator extends PIDSubsystem {
    public Talon motor1 = new Talon(liftTalon1);
    public Talon motor2 = new Talon(liftTalon2);
    public Potentiometer pot = new AnalogPotentiometer(liftPot);
    public DigitalInput top = new DigitalInput(topLS);
    public DigitalInput upper = new DigitalInput(upperLS);
    public DigitalInput lower = new DigitalInput(lowerLS);
    public DigitalInput bottom = new DigitalInput(bottomLS);
    
	static double p = 0.01;
	static double i = 0.0;
	static double d = 0.0;
	double height;
    int level = 0;

    public double[] potVolts = {
    		0,
    		.167,
    		.334,
    		.501,
    		.635,
    		.881,
    		1
    };
    

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
        
        // Show everything in the LiveWindow
//        LiveWindow.addSensor("Elevator", "Potentiometer", (AnalogPotentiometer) pot);
//        LiveWindow.addActuator("M1", "Motor1", (Talon) motor1);
//        LiveWindow.addActuator("M2", "Motor2", (Talon) motor2);
//        LiveWindow.addActuator("Elevator", "PID", getPIDController());
    }
 
    public void initDefaultCommand() {
    	setDefaultCommand(new ElevateWithTriggers());
    }
    
    public void elevate(double input) {
    	// Turns motors off when limit switches are engaged.
		if ((top.get() == true && input > 0) || (bottom.get() == true && input < 0)) {
			motor1.set(0);
			motor2.set(0);
			return;
		}
		// Slow motors down when secondary switches are engaged.
		if ((upper.get() == true && input > 0) || (lower.get() == true && input < 0)) {
			motor1.set(input * 0.5);
			motor2.set(input * 0.5);
			return;
		} else {
	    	motor1.set(input);
	    	motor2.set(input);
	    }
    }
    
    public void stop() {
    	motor1.set(0);
    	motor2.set(0);
    }
    
    public void log() {
    	SmartDashboard.putData("Potentiometer", (AnalogPotentiometer) pot);
    	SmartDashboard.putData("At Top", top);
    	SmartDashboard.putData("Near Top", upper);
    	SmartDashboard.putData("Near Bottom", lower);
    	SmartDashboard.putData("At Bottom", bottom);
    }
      
    /**
     * Use the potentiometer as the PID sensor. This method is automatically
     * called by the subsystem.
     */
    protected double returnPIDInput() {
    	int firstPoint = 0;
		int secondPoint = 1;
    	double volts = pot.get();
    	while(secondPoint < feet.length){
    		if(volts >= potVolts[firstPoint] ||
				(volts <= potVolts[secondPoint])){
    			double slope = potVolts[secondPoint] - potVolts[firstPoint];
    			slope = slope / (feet[secondPoint] - feet[firstPoint]);
    			double intercept = potVolts[firstPoint] - feet[firstPoint] * slope;
    			double distance = (volts - intercept) / slope;
    			SmartDashboard.putNumber("Elevator Height: ", distance);
    			return distance;
    		}
    		else{
    			firstPoint++;
    			secondPoint++;
    		}
		 
    		if(secondPoint >= potVolts.length){
    			return 0.0;
    		}
    	}
    	return 0.0;
    }
    
    /**
     * Use the motor as the PID output. This method is automatically called by
     * the subsystem.
     */
    protected void usePIDOutput(double output) {
        // Turns motor off when limit switches are engaged.
    	if ((top.get() == true && output > 0) || (bottom.get() == true && output < 0)) {
    		motor1.set(0);
    		motor2.set(0);
    		return;
    	}
    	// Slow the motor down when secondary switches are engaged.
    	if ((upper.get() == true && output > 0) || (lower.get() == true && output < 0)) {
    		motor1.set(output * 0.5);
    		motor2.set(output * 0.5);
    		return;
        } else {
        	motor1.set(output);
        	motor2.set(output);
        	return;
        }
    }
    
    // Set maximum
    public void incLevel() {
    	level++;
    	if (level > setPoints.length) {
    		level = setPoints.length;
    	}
    	this.setSetpoint(setPoints[level]);
    }
    
    // Set minimum
    public void decLevel() {
    	level--;
    	if (level < 0){
    		level = 0;
    	}
    	this.setSetpoint(setPoints[level]);
    }
    
    public void setLevel(int level) {
    	this.setSetpoint(setPoints[level]);
    }
    
}
