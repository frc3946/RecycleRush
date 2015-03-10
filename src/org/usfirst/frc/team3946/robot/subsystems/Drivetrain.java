package org.usfirst.frc.team3946.robot.subsystems;

import org.usfirst.frc.team3946.robot.commands.drive.SlideDrivingCommand;

import libraries.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import static org.usfirst.frc.team3946.robot.RobotMap.*;


public class Drivetrain extends Subsystem {
	
	public static final double DRIVE_SPEED = 0.4;
    public static final double STRAFE_SPEED = 0.4;

    public Talon left = new Talon(driveLeftTalon);
    public Talon right = new Talon(driveRightTalon);
    public Talon strafe = new Talon(driveStrafeTalon);
    public BetterGyro gyro = new BetterGyro(driveGyro);

    private final WheelController leftWheel = new WheelController(left);
    private final WheelController rightWheel = new WheelController(right);
    private final WheelController strafeWheel = new WheelController(strafe);
    private final ThreeWheelController driveController = new ThreeWheelController(
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
        setDefaultCommand(new SlideDrivingCommand());
        gyro.setPIDSourceParameter(PIDSource.PIDSourceParameter.kAngle);
    }
    
    public SlideDrive getSlideDrive() {
        return slideDrive;
    }

    public ArcadeDrive getArcadeDrive() {
        return arcadeDrive;
    }
    
    public void rotate(double speed) {
    	left.set(speed);
    	right.set(speed);
    }
    
    public void driveStraight(double speed) {
    	double leftSpeed = speed;
    	double rightSpeed = speed;
    	double gyroValue;
    	gyroValue = (Math.abs(gyro.getAngle()) / 45);
    	if(gyro.getAngle() > 0){
    		leftSpeed = leftSpeed * (1 - gyroValue);
    		rightSpeed = rightSpeed * (1 + gyroValue);
    	} else {
    		leftSpeed = leftSpeed * (1 + gyroValue);
    		rightSpeed = rightSpeed * (1 - gyroValue);
    	}
    	left.set(leftSpeed);
    	right.set(-rightSpeed);
    }
    
    public void strafe(double speed) {
    	double strafeSpeed = speed;
    	strafe.set(strafeSpeed);
    }
    
    public BetterGyro getGyro() {
    	return gyro;
    }
    public void setMotor(double leftSpeed, double rightSpeed, double strafeSpeed){
    	left.set(leftSpeed);
    	right.set(rightSpeed);
    	strafe.set(strafeSpeed);
    }
}
