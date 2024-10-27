package org.example.ncbaloopwebapi.api.utils;

import java.util.Random;

public class StringUtils {
    private static final String bankCode = "LOOP";
    private static final String countryCode = "KE";
    private static final String numberCharacters = "0123456789";
    private static final int branchLength = 4;
    private static final int signatureLength = 2;
    private static final int accountNumberLength = 10;
    private static final int ibanLength = 24;

    private static String SignatureGenerator(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder(signatureLength);
        for (int i = 0; i < signatureLength; i++)
            sb.append(numberCharacters.charAt(random.nextInt(numberCharacters.length())));
        return sb.toString();
    }
    private static String BranchGenerator(){
        Random random = new Random();

        StringBuilder sb = new StringBuilder(branchLength);

        for (int i = 0; i < branchLength; i++)
            sb.append(numberCharacters.charAt(random.nextInt(numberCharacters.length())));

        return sb.toString();
    }
    public static String AccountNumberGenerator(){
        Random random = new Random();

        StringBuilder sb = new StringBuilder(accountNumberLength);

        for (int i = 0; i < accountNumberLength; i++)
            sb.append(numberCharacters.charAt(random.nextInt(numberCharacters.length())));

        return sb.toString();
    }
    public static String BicSwiftGenerator(){
        StringBuilder sb = new StringBuilder(ibanLength);

        sb.append(countryCode);
        sb.append(SignatureGenerator());
        sb.append(" ");
        sb.append(BranchGenerator());
        sb.append(" ");

        return sb.toString();
    }

}
