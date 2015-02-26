package org.usfirst.frc.team3946.robot.commands;

import org.usfirst.frc.team3946.robot.commands.drive.*;
import org.usfirst.frc.team3946.robot.commands.lift.*;
import org.usfirst.frc.team3946.robot.commands.misc.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousRight extends CommandGroup {
    
    public  AutonomousRight() {
    	addSequential(new ResetGyro());
    	
    	//pick up tote
    	addSequential(new IncLiftSetpoint());
    	
    	//drive to safe zone
    	addSequential(new AutonomousDrive(3));
    	addSequential(new TurnToAngle(-90));
    	addSequential(new AutonomousDrive(12));
    	addSequential(new TurnToAngle(90));
    	addSequential(new AutonomousDrive(5));
    	
    }
}