package org.usfirst.frc.team3946.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousLeft extends CommandGroup {
    public  AutonomousLeft() {    	
    	// Pick up tote and drive to safe zone.
    	addSequential(new AutonomousDrive(1.0, 0.5));
    	addParallel  (new TimedLift(0.4));
    	addSequential(new AutonomousDrive(2.0, 0.5));

    	// Drop tote then back up slightly.
    	//addSequential(new TimedLift(-0.4);
    	//addSequential(new AutonomousDrive(0.5, -0.6));
    }
}
