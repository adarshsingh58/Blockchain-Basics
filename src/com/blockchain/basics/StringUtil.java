package com.blockchain.basics;

import com.blockchain.basics.Block;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {

    public static String generateHashCode(Block block) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String data = block.getData() + block.getPreviousHasCode() + block.getTimestamp() + block.getNonce();
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(data.getBytes("UTF-8"));
        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }

    public static String getTargetPrefixHash(int difficulty) {
        char[] charDifficulty = new char[difficulty];
        return new String(charDifficulty).replace("\0","0");
    }

}
