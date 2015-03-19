package org.usfirst.frc.team3946.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousLeft extends CommandGroup {
    public  AutonomousLeft() {    	
    	//picks up tote
    	//drives to safe zone
    	addSequential(new AutonomousDrive(1));
    	addParallel(new TimedLift(0.4));
    	addSequential(new AutonomousDrive(4));
    	addSequential(new TimedLift(-0.4));
    }
}
