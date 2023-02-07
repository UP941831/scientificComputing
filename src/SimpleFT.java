public class SimpleFT {

    public static int N = 256 ;

    static final double π = Math.PI ;

    public static void main(String [] args) throws Exception {

        double [] [] X = new double [N] [N] ;
        ReadPGM.read(X, "C:\\Users\\up941831\\IdeaProjects\\scientific labs ig\\src\\wolf.pgm", N) ;

        DisplayDensity display = new DisplayDensity(X, N, "Original Image") ;

        double [] [] CRe = new double [N] [N], CIm = new double [N] [N] ;
        // Forward 2D DFT, based on slide 34
        for(int k = 0 ; k < N ; k++) {
            for(int l = 0 ; l < N ; l++) {
                double sumRe = 0, sumIm = 0 ;
                // Nested for loops performing sum over X elements
                // m = row
                // n = column
                for(int m = 0 ; m < N-1 ; m++) {
                    for(int n = 0 ; n < N-1 ; n++) {
                        double arg = -2*π*(m*k + n*l)/N ; //slide 34, compute sine and cosine?
                        double cos = Math.cos(arg) ;
                        double sin = Math.sin(arg) ;
                        sumRe += cos * X [m] [n] ;
                        sumIm += sin * X [m] [n] ;
                    }
                }
                CRe [k] [l] = sumRe ;
                CIm [k] [l] = sumIm ;
            }
            System.out.println("Completed FT line " + k + " out of " + N) ;
        }

        Display2dFT display2 =
                new Display2dFT(CRe, CIm, N, "Discrete FT") ;
    }


}

//take screenshot of what you have implemented
//explain solution
//display input and output
//explain what this input shows
//i applied high pass filter/low and this is the image etc