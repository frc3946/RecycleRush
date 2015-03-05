
package org.usfirst.frc.team3946.robot.subsystems;

import org.usfirst.frc.team3946.robot.RobotMap;
import org.usfirst.frc.team3946.robot.commands.misc.SetLEDColors;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FunLights extends Subsystem {
	
	private Relay r3 = new Relay(RobotMap.spike3); //SPK3 +red -green
	private Relay r2 = new Relay(RobotMap.spike2); //SPK2 +blue -common
	
	public FunLights() {}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new SetLEDColors(0));
    }
    
    public Alliance getAlliance() {
    	return DriverStation.getInstance().getAlliance();
    }
    
	public void setAllianceColors() {
    	if (getAlliance() == Alliance.Red) {
    		Red();
    	}
    	if (getAlliance() == Alliance.Blue) {
    		Blue();
    	}
    	if (getAlliance() == Alliance.Invalid) {
    		Green();
    	}
    }
    
    public void Off() {
		r3.set(Relay.Value.kOn);
    	r2.set(Relay.Value.kOn);
    } 
    
    public void White() {
		r3.set(Relay.Value.kOff);
    	r2.set(Relay.Value.kReverse);
    }
    
    public void Red() {
		r3.set(Relay.Value.kReverse);
		r2.set(Relay.Value.kOn);
	}
    
    public void Blue() {
		r3.set(Relay.Value.kOn);
		r2.set(Relay.Value.kReverse);
	}
    
	public void Green() {
		r3.set(Relay.Value.kForward);
		r2.set(Relay.Value.kOn);
	}
    
    public void Yellow() {
    	r3.set(Relay.Value.kOff);
    	r2.set(Relay.Value.kOn);
    }
    
    public void Cyan() {
    	r3.set(Relay.Value.kForward);
    	r2.set(Relay.Value.kReverse);
    }
    
    public void Magenta() {
    	r3.set(Relay.Value.kReverse);
    	r2.set(Relay.Value.kReverse);
    }
}