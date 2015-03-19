package org.usfirst.frc.team3946.robot.subsystems;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Camera extends Subsystem implements Runnable {
	int session;
	Image frame;
	NIVision.RawData colorTable;

	public Camera() {
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

		// the camera name (ex "cam0") can be found through the roborio web
		// interface
		session = NIVision.IMAQdxOpenCamera("cam0",
				NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		NIVision.IMAQdxConfigureGrab(session);

		colorTable = new NIVision.RawData();

		NIVision.imaqSetImageSize(frame, 320, 240);
		NIVision.IMAQdxStartAcquisition(session);
	}

	public void initDefaultCommand() {
		// setDefaultCommand();
	}

	public void grabImage() {
		NIVision.IMAQdxGrab(session, frame, 1);
		CameraServer.getInstance().setImage(frame);
		NIVision.imaqWriteJPEGFile(frame, "/home/lvuser/frame.jpg", 95,
				colorTable);
	}

	public void stopAcquisition() {
		NIVision.IMAQdxStopAcquisition(session);
	}

	public void run() {
		while (true) {
			grabImage();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}