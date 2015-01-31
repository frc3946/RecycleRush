package org.usfirst.frc.team3946.robot.commands;

import static org.usfirst.frc.team3946.robot.Robot.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team3946.robot.commands.drive.TurnToAngle;

/**
 *
 */
public class AutonomousDrive extends CommandGroup {

    public AutonomousDrive() {
    	drivetrain.getSlideDrive().drive(0, 0, 0);
    	addSequential(new TurnToAngle(30.00));
    }
}