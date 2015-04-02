package org.usfirst.frc.team3946.robot.subsystems;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PDP extends Subsystem {
    
	PowerDistributionPanel panel = new PowerDistributionPanel();
	
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
    }
}

