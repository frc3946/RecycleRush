package org.usfirst.frc.team3946.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
/**
 *
 */
public class AutoDriveNoLift extends CommandGroup {
    
    public AutoDriveNoLift() {
    	addSequential(new AutonomousDrive(0.75, 0.7));
    	addSequential(new WaitCommand(7.0));
    	addSequential(new AutonomousDrive(2.5, 0.5));
    }
}
