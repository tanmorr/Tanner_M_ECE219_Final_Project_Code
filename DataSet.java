import java.util.ArrayList;

/**
 * This class allows for the easy storage of all the values and variables associated with the
 * dataset for a particular disk.
 *
 * @author Tanner Morrison
 * @version 1.0
 */
public class DataSet {

  public double a = 0.0; // the diameter of the upper disk for this set
  public double b = 0.0; // the diameter of the lower disk for this set
  public double d = 0.0; // the distance between the disks for this set
  private ArrayList<Double> radius = new ArrayList<>();   // the radius data for this set
  private ArrayList<Double> density = new ArrayList<>();  // the corresponding density at each radius

  /**
   * This method creates a new Dataset object, filling the given arrays of radius and corresponding
   * density values into their respective ArrayList fields.
   *
   * @param radius the list of radius values for this disk
   * @param density the corresponding list of density values for this disk
   * @param a the diameter of the upper disk for a given project value set
   * @param b the diameter of the lower disk for a given project value set
   * @param d the distance between the two disks for a given project value set
   */
  public DataSet(double[] radius, double[] density, double a, double b, double d) {

    // fill the radius data list
    for (double val : radius) {
      this.radius.add(val);
    }

    // fill the density data list
    for (double val : density) {
      this.density.add(val);
    }

    // fill in other problem variables (in meters)
    this.a = a;
    this.b = b;
    this.d = d;
  }

  /**
   * This method allows access to the ArrayList of radius values.
   *
   * @return the ArrayList of radius values
   */
  public ArrayList<Double> getRadius() {
    return radius;
  }

  /**
   * This method allows access to the ArrayList of density values.
   *
   * @return the ArrayList of density values
   */
  public ArrayList<Double> getDensity() {
    return density;
  }
}
