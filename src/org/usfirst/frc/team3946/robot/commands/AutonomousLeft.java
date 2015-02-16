package org.usfirst.frc.team3946.robot.commands;

import org.usfirst.frc.team3946.robot.commands.drive.*;
import org.usfirst.frc.team3946.robot.commands.lift.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousLeft extends CommandGroup {
    
    public  AutonomousLeft() {
    	addSequential(new ResetGyro());
    	
    	//picks up tote
    	addSequential(new IncLiftSetpoint());
    	
    	//drives to safe zone
    	addSequential(new AutonomousDrive(2));
    }
}
