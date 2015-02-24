package org.usfirst.frc.team3946.robot.commands.misc;

import org.usfirst.frc.team3946.robot.Robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.ColorMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;
import com.ni.vision.NIVision.MorphologyMethod;
import com.ni.vision.NIVision.Range;
import com.ni.vision.NIVision.StructuringElement;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.NIVisionException;

/**
 *
 */

public class ProcessImage extends Command {

    public ProcessImage() {
        // Use requires() here to declare subsystem dependencies
//          requires(Robot.camera);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Image sourceImage = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
//    	Robot.camera.getImageFromCamera(sourceImage);
    
    	Image threshold = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);
    	Range red = new Range(0, 225);
    	Range green = new Range(200, 255);
    	Range blue = new Range(0, 253);
    	NIVision.imaqColorThreshold(threshold, sourceImage, 0, ColorMode.RGB, red, green, blue);
    	
    	
    	Image morphology = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);
    	NIVision.imaqMorphology(morphology, threshold, MorphologyMethod.PCLOSE, new StructuringElement(3,3,1));
    }
    
    /**
     * Creator of code lines 54-98
     * @author dtjones
     */
    	int imageHeight;
		int imageWidth;
		int center_mass_x;
		int center_mass_y;
		double center_mass_x_normalized;
		double center_mass_y_normalized;
		double particleArea; 
		int boundingRectLeft;
		int boundingRectTop;
		int boundingRectWidth;
		int boundingRectHeight;
		double particleToImagePercent;
		double particleQuality;
    
    	ProcessImage(BinaryImage image, int index) throws NIVisionException {
    		imageHeight = image.getHeight();
            imageWidth = image.getWidth();
            center_mass_x = (int) NIVision.imaqMeasureParticle(image.image, index, 0, NIVision.MeasurementType.MT_CENTER_OF_MASS_X);
            center_mass_y = (int) NIVision.imaqMeasureParticle(image.image, index, 0, NIVision.MeasurementType.MT_CENTER_OF_MASS_Y);
            center_mass_x_normalized = (2.0 * center_mass_x / imageWidth) - 1.0;
            center_mass_y_normalized = (2.0 * center_mass_y / imageHeight) - 1.0;
            particleArea = NIVision.imaqMeasureParticle(image.image, index, 0, NIVision.MeasurementType.MT_AREA);
            boundingRectLeft = (int) NIVision.imaqMeasureParticle(image.image, index, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_LEFT);
            boundingRectTop = (int) NIVision.imaqMeasureParticle(image.image, index, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_TOP);
            boundingRectWidth = (int) NIVision.imaqMeasureParticle(image.image, index, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_WIDTH);
            boundingRectHeight = (int) NIVision.imaqMeasureParticle(image.image, index, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_HEIGHT);
            particleToImagePercent = NIVision.imaqMeasureParticle(image.image, index, 0, NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA);
            particleQuality = NIVision.imaqMeasureParticle(image.image, index, 0, NIVision.MeasurementType.MT_AREA_BY_PARTICLE_AND_HOLES_AREA);
    }
    	 
    	 static double getParticleToImagePercent(BinaryImage image, int index) throws NIVisionException {
    	        return NIVision.imaqMeasureParticle(image.image, index, 0, NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA);
    	    }
    	 
    	 public String toString() {
    	        return "Particle Report: \n" +
    	               "    Image Height    : " + imageHeight + "\n" +
    	               "    Image Width     : " + imageWidth + "\n" +
    	               "    Center of mass  : ( " + center_mass_x + " , " + center_mass_y + " )\n" +
    	               "    normalized      : ( " + center_mass_x_normalized + " , " + center_mass_y_normalized + " )\n" +
    	               "    Area            : " + particleArea + "\n" +
    	               "    percent         : " + particleToImagePercent + "\n" +
    	               "    Bounding Rect   : ( " + boundingRectLeft + " , " + boundingRectTop + " ) " + boundingRectWidth + "*" + boundingRectHeight + "\n" +
    	               "    Quality         : " + particleQuality + "\n";
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
