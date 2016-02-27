/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tetiana Voitovych 
 * 2015
 */
public class GrayCode {

    GrayCode() {

    }

    String binaryTogray(String binary) {
        String gray = "" + binary.charAt(0);
        for (int i = 0; i < binary.length() - 1; i++) {
            if (binary.charAt(i) == binary.charAt(i + 1)) {
                gray = gray + "0";
            } else {
                gray = gray + "1";
            }
        }
        return gray;
    }

    String grayTobinary(String gray) {
        String binary = "" + gray.charAt(0);
        for (int i = 0; i < gray.length() - 1; i++) {
            if (binary.charAt(i) == gray.charAt(i + 1)) {
                binary = binary + "0";
            } else {
                binary = binary + "1";
            }
        }
        return binary;
    }

}
