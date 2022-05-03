import java.util.ArrayList;

/**
 * This class has methods to load the datasets given for the final project in ECE219 and to
 * perform the necessary computations on this data.
 *
 * @author Tanner Morrison
 * @version 1.0
 */
public class ECE219FinalProject {

  private DataSet exampleUpper; // data for upper disk of example dataset
  private DataSet exampleLower; // data for lower disk of example dataset
  private DataSet projectUpper; // data for upper disk of project 2
  private DataSet projectLower; // data for lower disk of project 2

  /**
   * This method initializes the Dataset fields with their appropriate radius and density values.
   */
  public void initialize() {

    // Load the upper disk data from the example
    double[] radius =
        {0.00E+00, 1.00E-05, 2.00E-05, 3.00E-05, 4.00E-05, 5.00E-05, 6.00E-05, 7.00E-05, 8.00E-05,
            9.00E-05, 1.00E-04};

    double[] density =
        {1.075E-07, 1.085E-07, 1.105E-07, 1.135E-07, 1.175E-07, 1.235E-07, 1.336E-07, 1.486E-07,
            1.767E-07, 2.420E-07, 1.085E-06};

    exampleUpper = new DataSet(radius, density, 0.004, 0.0002, 0.0004);

    // Load the lower disk data from the example
    radius = new double[] {0.00E+00, 1.00E-05, 2.00E-05, 3.00E-05, 4.00E-05, 5.00E-05, 6.00E-05,
        7.00E-05, 8.00E-05, 9.00E-05, 1.00E-04, 2.00E-04, 3.00E-04, 4.00E-04, 5.00E-04, 6.00E-04,
        7.00E-04, 8.00E-04, 9.00E-04, 1.00E-03, 1.10E-03, 1.20E-03, 1.30E-03, 1.40E-03, 1.50E-03,
        1.60E-03, 1.70E-03, 1.80E-03, 1.90E-03, 2.00E-03};

    density = new double[] {-7.760E-09, -7.740E-09, -7.710E-09, -7.680E-09, -7.660E-09, -7.630E-09,
        -7.600E-09, -7.450E-09, -7.340E-09, -7.230E-09, -7.120E-09, -5.740E-09, -4.280E-09,
        -3.050E-09, -2.140E-09, -1.560E-09, -1.100E-09, -8.380E-10, -6.680E-10, -5.190E-10,
        -4.220E-10, -3.450E-10, -2.560E-10, -2.310E-10, -2.060E-10, -1.820E-10, -1.670E-10,
        -1.520E-10, -1.420E-10, -2.200E-10};

    exampleLower = new DataSet(radius, density, 0.004, 0.0002, 0.0004);

    // Load the upper disk data from project variant 2
    radius = new double[] {0.00E+00, 1.00E-06, 2.00E-06, 3.00E-06, 4.00E-06, 5.00E-06, 6.00E-06,
        7.00E-06, 8.00E-06, 9.00E-06, 1.00E-05};

    density =
        new double[] {1.500E-06, 1.510E-06, 1.520E-06, 1.560E-06, 1.580E-06, 1.710E-06, 1.830E-06,
            1.990E-06, 2.340E-06, 3.120E-06, 7.820E-06};

    projectUpper = new DataSet(radius, density, 0.0004, 0.00002, 0.00004);

    // Load the lower disk data from the project variant 2
    radius = new double[] {0.00E+00, 1.00E-06, 2.00E-06, 3.00E-06, 4.00E-06, 5.00E-06, 6.00E-06,
        7.00E-06, 8.00E-06, 9.00E-06, 1.00E-05, 2.00E-05, 3.00E-05, 4.00E-05, 5.00E-05, 6.00E-05,
        7.00E-05, 8.00E-05, 9.00E-05, 1.00E-04, 1.10E-04, 1.20E-04, 1.30E-04, 1.40E-04, 1.50E-04,
        1.60E-04, 1.70E-04, 1.80E-04, 1.90E-04, 2.00E-04};

    density = new double[] {-8.220E-08, -8.220E-08, -8.230E-08, -8.220E-08, -8.190E-08, -8.170E-08,
          -8.140E-08, -7.860E-08, -7.760E-08, -7.660E-08, -7.560E-08, -6.190E-08, -4.570E-08,
          -3.370E-08, -2.260E-08, -1.730E-08, -1.210E-08, -9.430E-09, -7.310E-09, -5.390E-09,
          -4.570E-09, -3.890E-09, -3.210E-09, -2.550E-09, -2.300E-09, -2.060E-09, -1.820E-09,
          -1.580E-09, -1.340E-09, -1.090E-09};

    projectLower = new DataSet(radius, density, 0.0004, 0.00002, 0.00004);
  }

  /**
   * This method determines the voltage difference between the disks by computing the line
   * integral of the electric field along the path between the disks where r = 0.
   *
   * To compute the integral, this method uses the Trapezoidal Rule described in class.
   *
   * @param z list of the z values at which the electric field is determined
   * @param electField corresponding values of the electric field's z component along the r = 0 path
   * @return the magnitude of the voltage difference between the two disks
   */
  public static double voltageLineIntegral(double[] z, double[] electField) {

    double voltageDiff = 0.0; // voltage difference between the disks

    // For each pair of adjacent z values, compute the area of the "trapezoid" and add to sum
    for (int i = 0; i < z.length - 1; i++) {
      voltageDiff += trapezoidArea(z[i + 1] - z[i], electField[i + 1], electField[i]);
    }

    // return the magnitude of the potential difference
    return Math.abs(voltageDiff);
  }

  /**
   * This method computes the electric field at a given point (0, 0, z) between the two disks.
   * To compute the integral necessary to find electric field, this method uses the Trapezoidal
   * Rule described in class.
   *
   * @param upperData radius and density data for upper disk
   * @param lowerData radius and density data for lower disk
   * @param z the z coordinate of the point to find the electric field at
   * @return the magnitude of the electric field in the positive z direction at the given point
   */
  public static double electricFieldIntegral(DataSet upperData, DataSet lowerData, double z) {

    // Calculate Ez(z) for upper disk
    ArrayList<Double> radius = upperData.getRadius();
    ArrayList<Double> density = upperData.getDensity();
    double fieldUpper = 0.0;           // the total Ez from the upper disk
    double vertDist = z - upperData.d; // the vertical distance between the disk and (0, 0, z)

    // For each pair of adjacent radii, compute the area of the "trapezoid" and add to sum
    for (int i = 0; i < radius.size() - 1; i++) {

      // distance between the radii
      double height = radius.get(i + 1) - radius.get(i);

      // value of function being integrated at greater radius
      double firstBase = radius.get(i + 1) * density.get(i + 1);
      firstBase /= Math.pow(Math.sqrt(Math.pow(radius.get(i + 1), 2) + Math.pow(vertDist, 2)), 3);

      // value of function being integrated at smaller radius
      double secondBase = radius.get(i) * density.get(i);
      secondBase /= Math.pow(Math.sqrt(Math.pow(radius.get(i), 2) + Math.pow(vertDist, 2)), 3);

      fieldUpper += trapezoidArea(height, firstBase, secondBase);
    }

    // Multiply result of integral by the distance divided by twice the permittivity of free space
    fieldUpper *= (vertDist) / (2 * 8.85E-12);

    // Calculate Ez(z) for lower disk
    radius = lowerData.getRadius();
    density = lowerData.getDensity();
    double fieldLower = 0.0; // the total Ez from the lower disk
    vertDist = z;            // the vertical distance between the disk and (0, 0, z)

    // For each pair of adjacent radii, compute the area of the "trapezoid" and add to sum
    for (int i = 0; i < radius.size() - 1; i++) {

      // distance between the radii
      double height = radius.get(i + 1) - radius.get(i);

      // value of function being integrated at greater radius
      double firstBase = radius.get(i + 1) * density.get(i + 1);
      firstBase /= Math.pow(Math.sqrt(Math.pow(radius.get(i + 1), 2) + Math.pow(vertDist, 2)), 3);

      // value of function being integrated at smaller radius
      double secondBase = radius.get(i) * density.get(i);
      secondBase /= Math.pow(Math.sqrt(Math.pow(radius.get(i), 2) + Math.pow(vertDist, 2)), 3);

      fieldLower += trapezoidArea(height, firstBase, secondBase);
    }

    // Multiply the result of the integral by the distance in the z direction divided by
    // twice the permittivity of free space
    fieldLower *= vertDist / (2 * 8.85E-12);

    return fieldUpper + fieldLower;
  }

  /**
   * This method computes and returns total charge on the disk represented by the given Dataset.
   *
   * To compute the integral necessary to find total charge, this method uses the Trapezoidal Rule
   * described in class.
   *
   * @param data the Dataset containing the radius and density values for the disk
   * @return the total charge on the disk
   */
  public static double totChargeIntegral(DataSet data) {

    double totCharge = 0.0; // holds result

    // add the areas of all trapezoids with "f(x)" being (density * radius)
    for (int i = 0; i < data.getRadius().size() - 1; i++) {
      totCharge += trapezoidArea(data.getRadius().get(i + 1) - data.getRadius().get(i),
                                data.getDensity().get(i) * data.getRadius().get(i),
                             data.getDensity().get(i + 1) * data.getRadius().get(i + 1));
    }

    // multiply entire sum by 2pi
    return totCharge * 2 * Math.PI;
  }

  /**
   * This method computes the area of a trapezoid with a given height and bases.
   *
   * @param height the height of the trapezoid
   * @param firstBase the length of the first base of the trapezoid
   * @param secondBase the length of the other base of the trapezoid
   * @return the total area of the given trapezoid
   */
  public static double trapezoidArea(double height, double firstBase, double secondBase) {
    return height * ((firstBase + secondBase) / 2);
  }

  /**
   * This method calls the necessary functions to load and perform the proper calculations on the
   * data given for the final project of ECE219.
   *
   * @param args not used
   */
  public static void main(String[] args) {

    // PART 0 - Initialize data sets
    ECE219FinalProject driver = new ECE219FinalProject();
    driver.initialize();

    System.out.println("\nECE 219 Final Project Calculations:");

    // PART 1 - Upper plate charge
    System.out.println("\n\tPlate Charges:");
    double upperPlateQ = totChargeIntegral(driver.projectUpper);
    System.out.printf("\t\tThe total charge (upper plate) is %.5E C\n", upperPlateQ);

    // PART 1 - Lower plate charge
    double lowerPlateQ = totChargeIntegral(driver.projectLower);
    System.out.printf("\t\tThe total charge (lower plate) is %.5E C\n", lowerPlateQ);

    // PART 2 - Ez at 9 points between plates
    double[] z = new double[9];
    double[] eFieldZ = new double[z.length];

    System.out.println("\n\tElectric Field:");
    for (int i = 1; i <= z.length; i++) {
      z[i - 1] = driver.projectUpper.d * i / 10.0;
      eFieldZ[i - 1] = electricFieldIntegral(driver.projectUpper, driver.projectLower, z[i - 1]);

      System.out.printf("\t\tThe electric field at %dd/10 is %.2f V/m\n", i, eFieldZ[i - 1]);
    }

    // PART 3 - Voltage difference between plates
    System.out.println("\n\tVoltage Difference:");
    double voltageDiff = voltageLineIntegral(z, eFieldZ);
    System.out.printf("\t\tThe voltage difference between the plates is %.3f V\n", voltageDiff);

    // PART 4 - Capacitance of the model
    System.out.println("\n\tCapacitance:");
    System.out.printf("\t\tThe calculated capacitance for this system is %.3E F",
          upperPlateQ / voltageDiff);
  }
}
