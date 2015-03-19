package org.usfirst.frc.team3946.robot.commands;

import org.usfirst.frc.team3946.robot.commands.drive.ResetGyro;

import edu.wpi.first.wpilibj.command.CommandGroup;
//import org.usfirst.frc.team3946.robot.commands.lift.RaiseElevatorLevel;

/**
 *
 */
public class AutonomousOff extends CommandGroup {
     
    public  AutonomousOff() {
    	addSequential(new ResetGyro());
//    	
//    	//pick up tote
//    	addSequential(new IncLiftSetpoint());
//    	
//    	//drive to safe zone
//    	addSequential(new AutonomousDrive(3));
//    	addSequential(new TurnToAngle(-90));
//    	addSequential(new AutonomousDrive(7));
//    	addSequential(new TurnToAngle(90));
//    	addSequential(new AutonomousDrive(5));
    }
}
