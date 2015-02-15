package org.usfirst.frc.team3946.robot.subsystems;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PDP extends Subsystem {
    
	PowerDistributionPanel panel = new PowerDistributionPanel();
	double maxA1 = 0;
	double maxA2 = 0;
	
	public PDP() {
		LiveWindow.addSensor("PDP", "PowerDistributionPanel", panel);
	}
	
    public void initDefaultCommand() {
    }
    
    public double getCurrent(int channel) {
    	return panel.getCurrent(channel);
    }
    
    public double getTotalCurrent() {
    	return panel.getTotalCurrent();
    }
    
    public void log() {
		SmartDashboard.putData("PowerDistributionPanel", panel);
    	maxA1 = Math.max(getCurrent(1), maxA1);
    	maxA2 = Math.max(getCurrent(2), maxA2);
        SmartDashboard.putNumber("M1 Max Current", maxA1);
        SmartDashboard.putNumber("M2 Max Current", maxA2);
    }
}

