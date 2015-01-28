package libraries;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Xbox 360 USB controller wrapper for the Joystick class.
 */
public class XboxController extends Joystick {
    
    /**
     * Buttons
     */
    public static final int A = 1;
    public static final int B = 2;
    public static final int X = 3;
    public static final int Y = 4;
    public static final int LeftBumper = 5;
    public static final int RightBumper = 6;
    public static final int Back = 7;
    public static final int Start = 8;
    public static final int LeftStick = 9;
    public static final int RightStick = 10;
    
    /**
     * Axis
     */
    public static final int LeftX = 0;
    public static final int LeftY = 1;
    public static final int LeftTrigger = 2;
    public static final int RightTrigger = 3;
    public static final int RightX = 4;
    public static final int RightY = 5;

    public XboxController(int port) {
        super(port);
    }
   /**
     * Read the value of the left joystick's X axis.
     * @return the value of the left joystick's X axis.
     */
    public double getLeftStickX() {
        return getRawAxis(LeftX);
    }

    /**
     * Read the value of the left joystick's Y axis.
     * @return the value of the left joystick's Y axis.
     */
    public double getLeftStickY() {
        return getRawAxis(LeftY);
    }
    
    /**
     * Read the value of the right joystick's X axis.
     * @return the value of the right joystick's X axis.
     */
    public double getRightStickX() {
        return getRawAxis(RightX);
    }

    /**
     * Read the value of the right joystick's Y axis.
     * @return the value of the right joystick's Y axis.
     */
    public double getRightStickY() {
        return getRawAxis(RightY);
    }

    /**
     * Read the value of the left trigger.
     * @return the value of the left trigger.
     */
    public double getLeftTrigger() {
    	return getRawAxis(LeftTrigger);
    }
    
    /**
     * Read the value of the right trigger.
     * @return the value of the right trigger.
     */
    public double getRightTrigger() {
    	return getRawAxis(RightTrigger);
    }
    
    /**
     * Read the value of the d-pad.
     * @return the value of the d-pad.
     */
    public int getDPad() {
    	return getPOV();
    }
    
    /**
     * Read the state of the A button.
     * @return the state of the A button.
     */
    public boolean getAButton() {
        return getRawButton(A);
    }

    /**
     * Read the state of the B button.
     * @return the state of the B button.
     */
    public boolean getBButton() {
        return getRawButton(B);
    }

    /**
     * Read the state of the X button.
     * @return the state of the X button.
     */
    public boolean getXButton() {
        return getRawButton(X);
    }

    /**
     * Read the state of the Y button.
     * @return the state of the Y button.
     */
    public boolean getYButton() {
        return getRawButton(Y);
    }

    /**
     * Read the state of the Back button.
     * @return the state of the Back button.
     */
    public boolean getBackButton() {
        return getRawButton(Back);
    }

    /**
     * Read the state of the Start button.
     * @return the state of the Start button.
     */
    public boolean getStartButton() {
        return getRawButton(Start);
    }

    /**
     * Read the state of the right bumper.
     * @return the state of the right bumper.
     */
    public boolean getRightBumper() {
        return getRawButton(RightBumper);
    }

    /**
     * Read the state of the left bumper.
     * @return the state of the left bumper.
     */
    public boolean getLeftBumper() {
        return getRawButton(LeftBumper);
    }

    /**
     * Read the state of the left stick button.
     * @return the state of the left stick button.
     */
    public boolean getLeftStickButton() {
        return getRawButton(LeftStick);
    }

    /**
     * Read the state of the right stick button.
     * @return the state of the right stick button.
     */
    public boolean getRightStickButton() {
        return getRawButton(RightStick);
    } 
}
