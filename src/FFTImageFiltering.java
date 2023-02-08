public class FFTImageFiltering {

    public static int N = 256 ;

    public static void main(String [] args) throws Exception {

        double[][] X = new double[N][N];
        ReadPGM.read(X, "C:\\Users\\dangr\\OneDrive\\Desktop\\Coding\\InteliJ - Java\\ScientificComputing\\src\\wolf.pgm", N);

        DisplayDensity display =
                new DisplayDensity(X, N, "Original Image");

        // create array for in-place FFT, and copy original data to it
        double[][] CRe = new double[N][N], CIm = new double[N][N];
        for (int k = 0; k < N; k++) {
            for (int l = 0; l < N; l++) {
                CRe[k][l] = X[k][l];
            }
        }

        fft2d(CRe, CIm, 1);  // Fourier transform

        Display2dFT display2 =
                new Display2dFT(CRe, CIm, N, "Discrete FT");

        // create array for in-place inverse FFT, and copy FT to it
        double[][] reconRe = new double[N][N],
                reconIm = new double[N][N];
        for (int k = 0; k < N; k++) {
            for (int l = 0; l < N; l++) {
                reconRe[k][l] = CRe[k][l];
                reconIm[k][l] = CIm[k][l];
            }
        }

        fft2d(reconRe, reconIm, -1);  // Inverse Fourier transform

        DisplayDensity display3 = new DisplayDensity(reconRe, N, "Reconstructed Image");
    }

    // in place matrix transpose
    public static void transpose(double[][] A) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                double temp = A[i][j];
                A[i][j] = A[j][i];
                A[j][i] = temp;
            }
        }
    }

    public static void fft2d(double[][] re, double[][] im, int isgn) {
        for (int i = 0; i < N; i++) {
            FFT.fft1d(re[i], im[i], isgn);
        }
        transpose(re);
        transpose(im);
        for (int i = 0; i < N; i++) {
            FFT.fft1d(re[i], im[i], isgn);
        }
        transpose(re);
        transpose(im);
    }

}
//low pass FFT filter