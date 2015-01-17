package org.usfirst.frc.team3946.robot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.XboxController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *	TODO: Add code for some brake.
 */
public class Elevator extends PIDSubsystem {
    private SpeedController motor;
    private Potentiometer pot;
    private DigitalInput upperLimitSwitch, 
    					 lowerLimitSwitch;
    int level;
    double[] levelVoltage;
    
    public Elevator() {
        // TODO: As soon as we can test, work on these values.
    	super("Elevator", 1.0, 0.0, 0.0);
        setAbsoluteTolerance(0.005);
        getPIDController().setContinuous(false);
        
        // Array for potentiometer voltages that represent set heights.
        // Values are placeholders until we have a prototype to test.
        level = 0;
        levelVoltage = new double[8];
        levelVoltage[0] = 0.0;	// floor
        levelVoltage[1] = 0.3;	// platform
        levelVoltage[2] = 1.0;	// step
        levelVoltage[3] = 1.5;	// two totes
        levelVoltage[4] = 2.0;	// three totes
     	levelVoltage[5] = 3.0;  // four totes
     	levelVoltage[6] = 4.0;	// five totes
     	levelVoltage[7] = 5.0;	// six totes
 
        upperLimitSwitch = new DigitalInput(1);
        lowerLimitSwitch = new DigitalInput(2);
        pot = new AnalogPotentiometer(3);
        motor = new Talon(4);
        
        // Show everything in the LiveWindow
        LiveWindow.addSensor("Elevator", "Upper Limit Switch", upperLimitSwitch);
        LiveWindow.addSensor("Elevator", "Lower Limit Switch", lowerLimitSwitch);
        LiveWindow.addSensor("Elevator", "Potentiometer", (AnalogPotentiometer) pot);
        LiveWindow.addActuator("Elevator", "Motor", (Talon) motor);
        LiveWindow.addActuator("Elevator", "PID", getPIDController());

    }
    
    public void initDefaultCommand() {}
    
    public void log() {
    	SmartDashboard.putData("Elevator Potentiometer", (AnalogPotentiometer) pot);
    }
    
    // Manual Control Stuff
    
    public void elevate(double input) {
    	motor.set(input);
    }
    
    public void elevate(XboxController controller) {
    	elevate(controller.getThrottle());
    }    
    
    public void stop() {
    	motor.set(0);
    }
    
    // Automagic Control Stuff
    
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
    	if (upperLimitSwitch.get() == true ||
        	lowerLimitSwitch.get() == true) {
        		motor.set(0);
        }else{
        	motor.set(output);
        }
    }
    
    public void raiseLevel() {
    	level++;
    	if (level > 7) {
    		level = 7;
    	}
    	this.setSetpoint(levelVoltage[level]);
    }
    
    public void lowerLevel() {
    	level--;
    	if (level < 0){
    		level = 0;
    	}
    	this.setSetpoint(levelVoltage[level]);
    }
    
    public void setLevel(int level) {
    	this.setSetpoint(levelVoltage[level]);
    }
    
}
