package org.usfirst.frc.team3946.robot;

import org.usfirst.frc.team3946.robot.commands.*;
import org.usfirst.frc.team3946.robot.commands.misc.*;
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
 * 
 * TODO: 
 * 1. Update lift to use encoder.
 * 2. Update drivetrain to use encoder.
 * 3. Gyro????
 * 4. Autonomous pickup
 */
public class Robot extends IterativeRobot {
    Command autonomousCommand;
    Command funCommand;
    Command vlCommand;
    
    public static OI oi;
    public static PDP pdp;
    public static Drivetrain drivetrain;
//    public static RangeFinders rangefinders;
    public static Elevator elevator;
    public static ToteContactSensors toteContact;
	public static FunLights lights;
	public static Camera camera; 
	public static VisionLights vl;
	
    private final SendableChooser autonomousChooser = new SendableChooser();
    private final SendableChooser ledChooser = new SendableChooser();
    private final SendableChooser vlChooser = new SendableChooser();
      
     //* This function is run when the robot is first started up and should be
     //* used for any initialization code.
     //*
    public void robotInit() {  	
    	// Initialize all subsystems.
    	pdp = new PDP();
    	drivetrain = new Drivetrain();
    	elevator = new Elevator();
//    	rangefinders = new RangeFinders();
    	toteContact = new ToteContactSensors();
    	lights = new FunLights();
    	vl = new VisionLights();
    	
    	camera = new Camera();
    	new Thread(camera).start();

		oi = new OI();
    	
        autonomousChooser.addDefault("No Autonomous", new AutonomousNone());
        autonomousChooser.addObject("Left", new AutonomousLeft());
        
        SmartDashboard.putData("Autonomous Mode", autonomousChooser);
        
        ledChooser.addObject("Off", new SetLEDColors(0));
		ledChooser.addObject("White", new SetLEDColors(1));
		ledChooser.addObject("Red", new SetLEDColors(2));
		ledChooser.addObject("Blue", new SetLEDColors(3));
		ledChooser.addDefault("Green", new SetLEDColors(4));
		ledChooser.addObject("Yellow", new SetLEDColors(5));
		ledChooser.addObject("Cyan", new SetLEDColors(6));
		ledChooser.addObject("Magenta", new SetLEDColors(7));
		
		SmartDashboard.putData("LED Color", ledChooser);
        
		vlChooser.addDefault("Cam LEDs Off", new SetVisionLights(0));
		vlChooser.addObject("Cam LEDs On", new SetVisionLights(1));

		SmartDashboard.putData("Vision Lights", vlChooser);
		
        // Show what command the subsystem is running on the SmartDashboard.
        SmartDashboard.putData(drivetrain);
        SmartDashboard.putData(elevator);
//        SmartDashboard.putData(rangefinders);
        SmartDashboard.putData(toteContact);
        SmartDashboard.putData(lights);
    }

    /**
     * This function is called when the disabled button is hit.
     */
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
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        Object color = ledChooser.getSelected();
    	if (color != null && color instanceof Command) {
    		funCommand = (Command) color;
            funCommand.start();
    	}
    	Object lights = vlChooser.getSelected();
    	if (lights != null && lights instanceof Command) {
    		vlCommand = (Command) lights;
    		vlCommand.start();
    	}
    	log();
    }

	/**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public void log() {
    	pdp.log();
    	elevator.log();
    	toteContact.log();
    }
}