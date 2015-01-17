package org.usfirst.frc.team3946.robot.subsystems;

import edu.wpi.first.wpilibj.*;
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
    
    public Elevator() {
        // TODO: As soon as we can test, work on these values.
    	super("Elevator", 1.0, 0.0, 0.0);
        setAbsoluteTolerance(0.005);
        getPIDController().setContinuous(false);
        
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
        motor.set(output);
    }
}
