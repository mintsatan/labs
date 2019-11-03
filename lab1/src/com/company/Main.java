package com.company;

public class Main {

    public static float getRandFloat(float min, float max) {

        return (float) Math.random() * (max - min) + min;
    }

    public static void main(String[] args) {
        long[] d = new long[16];
	    float[] x = new float[11];
	    float[] [] c = new float[16] [11];
	    for (int i = 0; i < 16; i++) {
	        d[i] = 5 + i;
        }
	    for (int i = 0; i < 11; i++) {
	        x[i] = getRandFloat(-14.0f, 14.0f);
        }
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 11; j++) {
                switch((int) d[i]) {
                    case 15:
                        c[i][j] = (float) Math.pow(Math.pow(2 * (Math.pow(x[j], x[j]*(0.25f + x[j]))), 3) / 2, 3);
                        break;
                    case 5:
                    case 6:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 16:
                    case 19:
                        c[i][j] = (float) Math.pow(Math.E,(Math.tan(Math.pow(x[j], x[j]))));
                        break;
                    default:
                        c[i][j] = (float) Math.tan(Math.sin(Math.pow(Math.cos(x[j])/((Math.atan(x[j] / 28) - 0.25)), 2)));
                        break;
                }

            }
        }
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.printf("%.2f ", c[i][j]);
            }
            System.out.print("\n");
        }
    }
}
