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
    
    public int[] feet = {1, 2, 3, 4, 5, 6};
    
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
    	setDefaultCommand(new ElevateWithTriggers());
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
        return pot.get();
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
    	if (level > 7) {
    		level = 7;
    	}
    	this.setSetpoint(potVolts[level]);
    }
    
    // Set minimum
    public void decLevel() {
    	level--;
    	if (level < 0){
    		level = 0;
    	}
    	this.setSetpoint(potVolts[level]);
    }
    
    public void setLevel(int level) {
    	this.setSetpoint(potVolts[level]);
    }
    
}
