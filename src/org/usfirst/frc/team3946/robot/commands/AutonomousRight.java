package org.usfirst.frc.team3946.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team3946.robot.RobotMap;

/**
 *
 */
public class AutonomousRight extends CommandGroup {
    
    public  AutonomousRight() {
        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	//pick up our tote
    	addSequential(new RaiseElevatorLevel());
    	addSequential(new LockElevator());
    	//turn right
    	addSequential(new Turn(90));
    	//move forward
    	addSequential (new AutoDrive(x));
    	//turn left
    	addSequential(new Turn(90));
    	//move forward
    	addSequential (new AutoDrive(x));
    	addSequential (new Turn(90));
    }
}