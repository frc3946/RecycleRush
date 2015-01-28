package libraries;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Gyro;

public class BetterGyro extends Gyro {

    public double resetOffset = 0.0;

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
     * Reset the robots sensors to the zero states.
     */
    public void reset() {
        resetOffset = super.getAngle();
    }

}
