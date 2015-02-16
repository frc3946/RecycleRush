package org.usfirst.frc.team3946.robot.subsystems;

import org.usfirst.frc.team3946.robot.RobotMap;
import org.usfirst.frc.team3946.robot.commands.misc.ProcessImage;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Camera extends Subsystem {
    
	private Relay r1 = new Relay(RobotMap.camSpike1);
//	CameraServer server;
//    USBCamera camera;
    int session;
    Image frame;
    
    public Camera() {
    	
    	frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

        // the camera name (ex "cam0") can be found through the roborio web interface
        session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);
        
//    	camera = new USBCamera("cam0");
//		camera.openCamera();
//		camera.setFPS(30);
//		camera.setSize(640,480);
//		server = CameraServer.getInstance();
//	    server.setQuality(50);
//	    server.setSize(0);
	    //the camera name (ex "cam0") can be found through the roborio web interface
	    //server.startAutomaticCapture("cam0");
        NIVision.IMAQdxStartAcquisition(session);
    }
    
    public void initDefaultCommand() {
        setDefaultCommand(new ProcessImage());
    }
    
    public void getImageFromCamera(Image image){
	    NIVision.IMAQdxGrab(session, frame, 1);            
	    CameraServer.getInstance().setImage(frame);
	    image = frame;
//    	camera.getImage(image);
//    	server.setImage(image);
    }
    
    public void ToteTracking() {
    	r1.set(Relay.Value.kReverse); //Tote tracking
	}
    
    public void ToteAlignment() {
    	r1.set(Relay.Value.kForward); //Left right alignment
    }
    
	public void Off() {
		r1.set(Relay.Value.kOff);
	}
}