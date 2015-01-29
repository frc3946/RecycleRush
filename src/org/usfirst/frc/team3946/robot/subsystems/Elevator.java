package org.usfirst.frc.team3946.robot.subsystems;

import static org.usfirst.frc.team3946.robot.RobotMap.*;
import libraries.XboxController;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *	
 */
public class Elevator extends PIDSubsystem {
    Talon motor;
    Potentiometer pot;
    DigitalInput upperLimit, 
    		lowerLimit;
    
    int level;
    double[] potVoltage;
    
    public Elevator() {
        // TODO: As soon as we can test, work on these values.
    	super("Elevator", 1.0, 0.0, 0.0);
        setAbsoluteTolerance(0.005);
        getPIDController().setContinuous(false);

        upperLimit = new DigitalInput(uLimitSwitch);
        lowerLimit = new DigitalInput(lLimitSwitch);
        pot = new AnalogPotentiometer(liftPot);
        motor = new Talon(liftTalon);
        
        // Show everything in the LiveWindow
        LiveWindow.addSensor("Elevator", "Upper Limit Switch", upperLimit);
        LiveWindow.addSensor("Elevator", "Lower Limit Switch", lowerLimit);
        LiveWindow.addSensor("Elevator", "Potentiometer", (AnalogPotentiometer) pot);
        LiveWindow.addActuator("Elevator", "Motor", (Talon) motor);
        LiveWindow.addActuator("Elevator", "PID", getPIDController());
        
        // Array for potentiometer voltages that represent set heights.
        // Values are placeholders until we have a prototype to test.
        level = 0;
        potVoltage = new double[8];
        potVoltage[0] = 0.0;	// floor
        potVoltage[1] = 0.0;	// platform
        potVoltage[2] = 0.0;	// step
        potVoltage[3] = 0.0;	// two totes
        potVoltage[4] = 0.0;	// three totes
     	potVoltage[5] = 0.0;    // four totes
     	potVoltage[6] = 0.0;	// five totes
     	potVoltage[7] = 0.0;	// six totes
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
    
    // Automatic Control Stuff
    
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
    	if (upperLimit.get() == true || lowerLimit.get() == true) {
    		motor.set(0);
        } else {
        	motor.set(output);
        }
    }
    
    // Set maximum
    public void raiseLevel() {
    	level++;
    	if (level > 7) {
    		level = 7;
    	}
    	this.setSetpoint(potVoltage[level]);
    }
    
    // Set minimum
    public void lowerLevel() {
    	level--;
    	if (level < 0){
    		level = 0;
    	}
    	this.setSetpoint(potVoltage[level]);
    }
    
    // For use on Smart Dashboard
    public void setLevel(int level) {
    	this.setSetpoint(potVoltage[level]);
    }
    
}
