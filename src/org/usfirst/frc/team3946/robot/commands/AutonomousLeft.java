package org.usfirst.frc.team3946.robot.commands;

import org.usfirst.frc.team3946.robot.commands.drive.ResetGyro;
import org.usfirst.frc.team3946.robot.commands.lift.LockElevatorPosition;
import org.usfirst.frc.team3946.robot.commands.lift.RaiseElevatorLevel;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousLeft extends CommandGroup {
    
    public  AutonomousLeft() {
    	addSequential(new ResetGyro());
    	
    	//picks up tote
    	addSequential(new RaiseElevatorLevel());
    	addSequential(new LockElevatorPosition());
    	
    	//drives to safe zone
    	addSequential(new AutonomousDrive(1));
    }
}
