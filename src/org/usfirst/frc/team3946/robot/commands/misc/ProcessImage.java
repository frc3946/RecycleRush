package org.usfirst.frc.team3946.robot.commands.misc;

import org.usfirst.frc.team3946.robot.Robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ProcessImage extends Command {

    public ProcessImage() {
        // Use requires() here to declare subsystem dependencies
          requires(Robot.camera);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Image sourceImage = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
    	Robot.camera.getImageFromCamera(sourceImage);
    
//    	Image threshold = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);
//    	Range red = new Range(0, 225);
//    	Range green = new Range(200, 255);
//    	Range blue = new Range(0, 253);
//    	NIVision.imaqColorThreshold(threshold, sourceImage, 0, ColorMode.RGB, red, green, blue);
//    	
//    	
//    	Image morphology = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);;
//    	NIVision.imaqMorphology(morphology, threshold, MorphologyMethod.PCLOSE, new StructuringElement(3,3,1));
    	//Timer.delay(0.005);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
