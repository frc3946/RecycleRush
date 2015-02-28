package org.usfirst.frc.team3946.robot.subsystems;

import java.awt.Panel;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PDP extends Subsystem {
    
	PowerDistributionPanel panel = new PowerDistributionPanel();
    
	public PDP() {
	
	}
        
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public double getElevatorCurrent1(){
    	return panel.getCurrent(14);
    }
    public double getElevatorCurrent2(){
    	return panel.getCurrent(15);
    }
    
    
}

