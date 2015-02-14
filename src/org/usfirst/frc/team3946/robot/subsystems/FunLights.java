
package org.usfirst.frc.team3946.robot.subsystems;

import org.usfirst.frc.team3946.robot.RobotMap;
import org.usfirst.frc.team3946.robot.commands.SetLEDColors;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class FunLights extends Subsystem {
	
	private Relay spk1 = new Relay(RobotMap.spike1);
	private Relay spk2 = new Relay(RobotMap.spike2); 
	
	public FunLights() {}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new SetLEDColors(0));
    }
    
    public Alliance getAlliance() {
    	return DriverStation.getInstance().getAlliance();
    }
    
    public void setAllianceColors() {
    	if (getAlliance() == Alliance.Red) {
    		spk1.set(Relay.Value.kReverse);
    		spk2.set(Relay.Value.kOn);
    	}
    	if (getAlliance() == Alliance.Blue) {
    		spk1.set(Relay.Value.kOn);
    		spk2.set(Relay.Value.kReverse);
    	}
    	if (getAlliance() == Alliance.Invalid) {
    		spk1.set(Relay.Value.kForward);
    		spk2.set(Relay.Value.kOn);
    	}
    }
    
    public void Off() {
		spk1.set(Relay.Value.kOn);
    	spk2.set(Relay.Value.kOn);
    } 
    
    public void White() {
		spk1.set(Relay.Value.kOff);
    	spk2.set(Relay.Value.kReverse);
    }
    
    public void Red() {
		spk1.set(Relay.Value.kReverse);
		spk2.set(Relay.Value.kOn);
	}
    
    public void Blue() {
		spk1.set(Relay.Value.kOn);
		spk2.set(Relay.Value.kReverse);
	}
    
	public void Green() {
		spk1.set(Relay.Value.kForward);
		spk2.set(Relay.Value.kOn);
	}
    
    public void Yellow() {
    	spk1.set(Relay.Value.kForward);
    	spk2.set(Relay.Value.kOn);
    }
    
    public void Cyan() {
    	spk1.set(Relay.Value.kReverse);
    	spk2.set(Relay.Value.kReverse);
    }
    
    public void Magenta() {
    	spk1.set(Relay.Value.kOff);
    	spk2.set(Relay.Value.kReverse);
    }
}