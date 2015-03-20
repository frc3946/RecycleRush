package org.usfirst.frc.team3946.robot.commands;
//
//import org.usfirst.frc.team3946.robot.Robot;
//
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// *
// */
//public class AlignToStack extends Command {
//	
//	double rfReadingL;
//	double rfReadingR;
//	double lTarget;
//	double rTarget;
//	double lOffset;
//	double rOffset;
//	double turnCorrection = .1;
//	double pValue = .01;
//	double offsetThreshold = .1;
//
//    public AlignToStack() {
//    	requires(Robot.rangefinders);
//    	requires(Robot.drivetrain);
//    }
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//
//    }
//
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//    	rfReadingL = Robot.rangefinders.rf1.getDistance();
//    	rfReadingR = Robot.rangefinders.rf2.getDistance();
//    	lOffset = lTarget - rfReadingL;
//    	rOffset = rTarget - rfReadingR;
//    	double lSpeed = pValue * (lOffset*(1 + turnCorrection) - rOffset * turnCorrection);
//    	double rSpeed = pValue * (rOffset*(1 + turnCorrection) - lOffset * turnCorrection);
//    	Robot.drivetrain.left.set(lSpeed);
//    	Robot.drivetrain.right.set(rSpeed);
//    }
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//    	return (Math.abs(lOffset) < offsetThreshold && Math.abs(rOffset) < offsetThreshold);
//    }
//
//    // Called once after isFinished returns true
//    protected void end() {
//    }
//
//    // Called when another command which requires one or more of the same
//    // subsystems is scheduled to run
//    protected void interrupted() {
//    }
//}
