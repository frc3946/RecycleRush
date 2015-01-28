package org.usfirst.frc.team3946.robot.subsystems;

import org.usfirst.frc.team3946.robot.commands.drive.FieldCentricDrivingCommand;

import libraries.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import static org.usfirst.frc.team3946.robot.RobotMap.*;

public class Drivetrain extends Subsystem {

    public static final double STRAFE_SPEED = 0.6;

    public Talon left = new Talon(driveLeftTalon);
    public Talon right = new Talon(driveRightTalon);
    public Talon strafe = new Talon(driveStrafeTalon);
    public BetterGyro gyro = new BetterGyro(driveGyro);

    private final WheelController leftWheel = new WheelController(left);
    private final WheelController rightWheel = new WheelController(right);
    private final WheelController strafeWheel = new WheelController(strafe);
    private final ThreeWheelDriveController driveController = new ThreeWheelDriveController(
            leftWheel, 
            rightWheel, 
            strafeWheel
    );

    private final SlideDrive slideDrive = new SlideDrive(driveController, gyro);
    private final ArcadeDrive arcadeDrive = new ArcadeDrive(driveController);

    public Drivetrain() {
        rightWheel.setInverted(true);
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new FieldCentricDrivingCommand());
        gyro.setPIDSourceParameter(PIDSource.PIDSourceParameter.kAngle);
    }
    
    public SlideDrive getSlideDrive() {
        return slideDrive;
    }

    public ArcadeDrive getArcadeDrive() {
        return arcadeDrive;
    }
}
