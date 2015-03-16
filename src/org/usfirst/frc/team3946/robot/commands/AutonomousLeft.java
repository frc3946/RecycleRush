package org.usfirst.frc.team3946.robot.commands;

import org.usfirst.frc.team3946.robot.commands.drive.*;
import org.usfirst.frc.team3946.robot.commands.lift.*;
import org.usfirst.frc.team3946.robot.commands.misc.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousLeft extends CommandGroup {
    
    public  AutonomousLeft() {    	
    	//picks up tote
    	//(new IncLiftSetpoint());
    	
    	//drives to safe zone
    	addSequential(new AutonomousDrive(1));
    	addSequential(new TimedLift());
    	//addSequential(new TurnToAngle(90));
    }
}
