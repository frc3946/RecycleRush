package org.usfirst.frc.team3946.robot.subsystems;
//
//import org.usfirst.frc.team3946.robot.commands.misc.GetDistanceToObstacle;
//import org.usfirst.frc.team3946.robot.sensors.RangeFinder;
//
//import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import static org.usfirst.frc.team3946.robot.RobotMap.*;
//
//public class RangeFinders extends Subsystem {
//	public RangeFinder rf1 = new RangeFinder(lRangeF);
//    public RangeFinder rf2 = new RangeFinder(rRangeF);
//
//    public void initDefaultCommand() {
//    	setDefaultCommand(new GetDistanceToObstacle());
//    }
//
//    public double getLeftInches() {
//    	return rf1.getDistance();
//    }
//    public double getRightInches() {		
//    	return rf2.getDistance();   
//    }
//    
//    public void getReading() {
//    	SmartDashboard.putNumber("Left Inches", getLeftInches());
//    	SmartDashboard.putNumber("Right Inches", getRightInches());
//
//    }
//}
//
