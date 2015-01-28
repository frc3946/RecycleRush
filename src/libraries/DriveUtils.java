package libraries;

import static java.lang.Math.*;

/**
 * This class contains some useful methods for driving.
 */
public final class DriveUtils {

    /**
     * Limits a value to the -1.0 to +1.0 range.
     *
     * @param num the number to limit
     * @return the limited number
     */
    public static double limit(double num) {
        if (num > 1.0) return 1.0;
        if (num < -1.0) return -1.0;
        return num;
    }

    /**
     * Rotate a vector in Cartesian space.
     *
     * @param x the x component of the vector
     * @param y the y component of the vector
     * @param angle the angle to rotate the vector
     * @return a 2 element array containing the rotated vector
     *
     */
    public static double[] rotateVector(double x, double y, double angle) {
        double cosA = cos(angle * (PI / 180.0));
        double sinA = sin(angle * (PI / 180.0));
        double out[] = new double[2];
        out[0] = x * cosA - y * sinA;
        out[1] = x * sinA + y * cosA;
        return out;
    }

    /**
     * Normalize all wheel speeds if the magnitude of any wheel is greater than
     * 1.0.
     *
     * @param wheelSpeeds the array of wheel speeds to normalize
     */
    public static void normalize(double wheelSpeeds[]) {
        double maxMagnitude = abs(wheelSpeeds[0]);
        // Loops through each number to find the biggest absolute value.
        for (double i : wheelSpeeds) {
            double temp = abs(i);
            if (maxMagnitude < temp) {
                maxMagnitude = temp;
            }
        }
        // If the maximum is greater than 1.0, reduce all the values down 
        // proportionally so the maximum becomes 1.0.
        if (maxMagnitude > 1.0) {
            for (double i : wheelSpeeds) {
               i = i / maxMagnitude;
            }
        }
    }
}
