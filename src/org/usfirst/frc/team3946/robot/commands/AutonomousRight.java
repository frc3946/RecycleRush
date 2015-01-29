package org.usfirst.frc.team3946.robot.commands;

import org.usfirst.frc.team3946.robot.commands.drive.ResetGyro;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousRight extends CommandGroup {
    
    public  AutonomousRight() {
    	addSequential(new ResetGyro());
    }
}