package org.usfirst.frc.team3946.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousLeft extends CommandGroup {
    
    public  AutonomousLeft() {
        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	//pick up our tote
    	addSequential(new RaiseElevatorLevel());
    	addSequential(new LockElevator());
    	//move forward
    	addSequential (new AutoDriveForward(1.4));
    }
}
