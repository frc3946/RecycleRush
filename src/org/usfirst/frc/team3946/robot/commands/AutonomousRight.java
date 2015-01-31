package org.usfirst.frc.team3946.robot.commands;

import org.usfirst.frc.team3946.robot.commands.drive.ResetGyro;
import org.usfirst.frc.team3946.robot.commands.drive.TurnToAngle;
import org.usfirst.frc.team3946.robot.commands.lift.LockElevatorPosition;
import org.usfirst.frc.team3946.robot.commands.lift.RaiseElevatorLevel;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousRight extends CommandGroup {
    
    public  AutonomousRight() {
    	addSequential(new ResetGyro());
    	
    	//pick up tote
    	addSequential(new RaiseElevatorLevel());
    	addSequential(new LockElevatorPosition());
    	
    	//drive to safe zone
    	addSequential(new AutonomousDrive(1));
    	addSequential(new TurnToAngle(-90));
    	addSequential(new AutonomousDrive(1));
    	addSequential(new TurnToAngle(90));
    	addSequential(new AutonomousDrive(1));
    	
    }
}