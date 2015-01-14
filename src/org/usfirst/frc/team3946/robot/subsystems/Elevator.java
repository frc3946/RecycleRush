package org.usfirst.frc.team3946.robot.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends PIDSubsystem {
    private SpeedController elevator_motor;
    private Potentiometer elevator_potentiometer;
    private DigitalInput upper_limit_switch, 
    					 lower_limit_switch;
    
    public Elevator() {
        super(1.0, 0.0, 0.0);
        
        getPIDController().setContinuous(false);
        setAbsoluteTolerance(0.005);
        elevator_motor = new Talon(4);
        
        // Show everything in the LiveWindow
        LiveWindow.addActuator("Elevator", "Motor", (Talon) elevator_motor);
        LiveWindow.addSensor("Elevator", "Potentiometer", (AnalogPotentiometer) elevator_potentiometer);
        LiveWindow.addSensor("Elevator", "Top Limit Switch", (DigitalInput) upper_limit_switch);
        LiveWindow.addSensor("Elevator", "Bottom Limit Switch", (DigitalInput) lower_limit_switch);
        LiveWindow.addActuator("Elevator", "PID", getPIDController());

    }
    
    public void initDefaultCommand() {}
    
	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */
    public void log() {
    	SmartDashboard.putData("Elevator Potentiometer", (AnalogPotentiometer) elevator_potentiometer);
    }
    
    /**
     * Use the potentiometer as the PID sensor. This method is automatically
     * called by the subsystem.
     */
    protected double returnPIDInput() {
        return elevator_potentiometer.get();
    }
    
    /**
     * Use the motor as the PID output. This method is automatically called by
     * the subsystem.
     */
    protected void usePIDOutput(double d) {
        elevator_motor.set(d);
    }
}
