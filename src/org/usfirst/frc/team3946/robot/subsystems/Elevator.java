package org.usfirst.frc.team3946.robot.subsystems;

import static org.usfirst.frc.team3946.robot.RobotMap.*;

import org.usfirst.frc.team3946.robot.commands.lift.ElevateWithTriggers;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *	
 */
public class Elevator extends PIDSubsystem {
    public Talon motor;
    public Potentiometer pot;
    public DigitalInput topSwitch; 
    public DigitalInput bottomSwitch;
    
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
    		12.1 / 12,
    		24.2 / 12,
    		36.3 / 12,
    		48.4 / 12,
    		60.5 / 12,
    		72.6 / 12
    };
    
    public int[] feet = {0, 1, 2, 3, 4, 5, 6};
    
    public Elevator() {
        // TODO: As soon as we can test, work on these values.
    	super("Elevator", 1.0, 0.0, 0.0);
        setAbsoluteTolerance(0.005);
        getPIDController().setContinuous(false);

        topSwitch = new DigitalInput(uLimitSwitch);
        bottomSwitch = new DigitalInput(lLimitSwitch);
        pot = new AnalogPotentiometer(liftPot);
        motor = new Talon(liftTalon);
        
        // Show everything in the LiveWindow
        LiveWindow.addSensor("Elevator", "Upper Limit Switch", topSwitch);
        LiveWindow.addSensor("Elevator", "Lower Limit Switch", bottomSwitch);
        LiveWindow.addSensor("Elevator", "Potentiometer", (AnalogPotentiometer) pot);
        LiveWindow.addActuator("Elevator", "Motor", (Talon) motor);
        LiveWindow.addActuator("Elevator", "PID", getPIDController());
    }
 
    public void initDefaultCommand() {
//    	setDefaultCommand(new ElevateWithTriggers());
    }
    
    public void log() {
    	SmartDashboard.putData("Elevator Potentiometer", (AnalogPotentiometer) pot);
    	SmartDashboard.putData("Top Reached", topSwitch);
    	SmartDashboard.putData("Bottom Reached", bottomSwitch);
    }
    
    // Manual Control Stuff
    public void elevate(double input) {
    	motor.set(input);
    }
    
    public void stop() {
    	motor.set(0);
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
    	if (topSwitch.get() == true || bottomSwitch.get() == true) {
    		motor.set(0);
        } else {
        	motor.set(output);
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
