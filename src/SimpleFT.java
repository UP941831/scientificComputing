public class SimpleFT {

    public static int N = 256;

    static final double π = Math.PI;

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        double[][] X = new double[N][N];
        ReadPGM.read(X, "C:\\Users\\dangr\\OneDrive\\Desktop\\Coding\\InteliJ - Java\\ScientificComputing\\src\\wolf.pgm", N);

        DisplayDensity display = new DisplayDensity(X, N, "Original Image");

        double[][] CRe = new double[N][N], CIm = new double[N][N];
        // Forward 2D DFT
        for (int k = 0; k < N; k++) {
            for (int l = 0; l < N; l++) {
                double sumRe = 0, sumIm = 0;
                // Nested for loops performing sum over X elements
                for (int m = 0; m < N - 1; m++) {
                    for (int n = 0; n < N - 1; n++) {
                        double arg = -2 * π * (m*k + n*l) / N;
                        double cos = Math.cos(arg);
                        double sin = Math.sin(arg);
                        sumRe += cos * X[m][n];
                        sumIm += sin * X[m][n];
                    }
                }
                CRe[k][l] = sumRe;
                CIm[k][l] = sumIm;
            }
            System.out.println("Completed FT line " + k + " out of " + N);
        }

        Display2dFT display2 =
                new Display2dFT(CRe, CIm, N, "Discrete FT");



        //High pass filter


        // Inverse Fourier Transform on 2D DFT
        double [] [] reconstructed = new double[N][N];
        for(int m = 0; m<N; m++){
            for(int n = 0; n < N; n++){
                double sum = 0;
                //nested for loops to perform sum over elements of CRe and CIm
                for(int k = 0; k<N; k++){
                    for(int l = 0; l<N; l++){
                        double arg = 2*π*(k*m + l*n)/N;
                        double cos = Math.cos(arg);
                        double sin = Math.sin(arg);
                        sum += cos*CRe[k][l] - sin*CIm[k][l];
                    }
                }
                reconstructed[m][n] = sum;
            }
            System.out.println("Completed inverse FT line " + m + " out of " + N);
        }
        DisplayDensity display3 = new DisplayDensity(reconstructed, N, "Reconstructed Image");

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(totalTime+" ms");

    }
}

//take screenshot of what you have implemented
//explain solution
//display input and output
//explain what this input shows
//i applied high pass filter/low and this is the image etc