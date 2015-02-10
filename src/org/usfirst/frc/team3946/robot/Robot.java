package org.usfirst.frc.team3946.robot;

import org.usfirst.frc.team3946.robot.commands.*;
import org.usfirst.frc.team3946.robot.subsystems.*;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    Command autonomousCommand;
    private final SendableChooser autonomousChooser = new SendableChooser();
    private final SendableChooser ledChooser = new SendableChooser();
    public static OI oi;
    public static Drivetrain drivetrain;
    public static Elevator elevator;
    public static CurbFeeler curbfeeler;
    public static LightSeer lightseers;
     //* This function is run when the robot is first started up and should be
     //* used for any initialization code.
     //*/
    public void robotInit() {  	
    	// Initialize all subsystems.
    	drivetrain = new Drivetrain();
    	elevator = new Elevator();
        oi = new OI();

    	curbfeeler = new CurbFeeler();
    	lightseers = new LightSeer();
        autonomousChooser.addDefault("Center", new AutonomousCenter());
        autonomousChooser.addObject("Left", new AutonomousLeft());
        autonomousChooser.addObject("Right", new AutonomousRight());
        SmartDashboard.putData("Autonomous Mode", autonomousChooser);
        ledChooser.addDefault("Green", new LedGreen());
        ledChooser.addObject("Red", new LedRed());
        ledChooser.addObject("Blue", new LedBlue());
        SmartDashboard.putData("LED Color", ledChooser);
        
        
        // Show what command the subsystem is running on the SmartDashboard.
        SmartDashboard.putData(drivetrain);
        SmartDashboard.putData(curbfeeler);
        SmartDashboard.putData(lightseers);
    }

    /**
     * This function is called when the disabled button is hit.
     */
    
   //
    public void disabledInit(){

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        //if (autonomousCommand != null) 
    	Object selection = autonomousChooser.getSelected();
    	if (selection != null && selection instanceof Command) {
            autonomousCommand = (Command) selection;
            autonomousCommand.start();
    	} else {
    		System.out.println("No autonomous mode selected.");
    	}
    }

    /**
     * This function is called periodically during autonomous.
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) {
        	autonomousCommand.cancel();
        }
        Robot.drivetrain.getSlideDrive().resetGyro();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    public void log() {
    	curbfeeler.log();
    	lightseers.log();
    }
}