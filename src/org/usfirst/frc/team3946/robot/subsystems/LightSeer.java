package org.usfirst.frc.team3946.robot.subsystems;

import org.usfirst.frc.team3946.robot.RobotMap;
import org.usfirst.frc.team3946.robot.sensors.LightSensor;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LightSeer extends Subsystem {
	
	LightSensor sightuno = new LightSensor(RobotMap.lightseeruno);
	LightSensor sightdos = new LightSensor(RobotMap.lightseerdos);
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {}
    
    public void isTrue(){
    	sightuno.getValue();
    	sightdos.getValue();
    }
    
    public void log() {
    	SmartDashboard.putData("LightSeerUno", (LightSensor) sightuno);
    	SmartDashboard.putData("LightSeerDos", (LightSensor) sightdos);
    }
		
	}


