package org.usfirst.frc.team3946.robot.subsystems;

import org.usfirst.frc.team3946.robot.commands.misc.ProcessImage;

import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.USBCamera;

/**
 *
 */
public class Camera extends Subsystem {
    
	CameraServer server;
    USBCamera camera;
   
    
    public Camera() {
    	camera = new USBCamera("cam0");
		camera.openCamera();
		camera.setFPS(30);
		camera.setSize(640,480);
		server = CameraServer.getInstance();
	    server.setQuality(50);
	    server.setSize(0);
	    //the camera name (ex "cam0") can be found through the roborio web interface
	    //server.startAutomaticCapture("cam0");
	    
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new ProcessImage());
    }
    
    public void getImageFromCamera(Image image){
    	camera.getImage(image);
    }
}