package org.usfirst.frc.team3946.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousLeft extends CommandGroup {
	
    public  AutonomousLeft() {    	
    	// Pick up tote and drive to safe zone.
    	addParallel(new AutonomousDrive(1.0, 0.5));
    	addSequential (new TimedLift(0.4));
    	addSequential(new AutonomousDrive(3.0, 0.5));

    	// Drop tote then back up slightly.
    	addParallel(new TimedLift(-0.4));
    	addSequential(new AutonomousDrive(0.5, -0.6));
    }
}
