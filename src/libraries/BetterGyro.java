package libraries;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Gyro;

public class BetterGyro extends Gyro {
	
    public double resetOffset = 0.0;
    double angle;

    public BetterGyro(int channel) {
        super(channel);        
    }

    public BetterGyro(AnalogInput channel) {
        super(channel);
    }

    /**
     * @return current angle
     */
    public double getAngle() {
        return super.getAngle() - resetOffset;
    }
    
    /**
     * @return current angle wrapped to [-180..180]
     */
    public double getYaw() {
    	angle = getAngle();
        if (angle < -180) angle += 360;
        if (angle > 180) angle -= 360;
        return angle;
    }
    
    /**
     * Reset the robots sensors to the zero states.
     */
    public void reset() {
        resetOffset = super.getAngle();
    }

}
