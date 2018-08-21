package com.blockchain.basics;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Block {

    private String hashCode;
    private String previousHasCode;
    private String data;
    private Long timestamp;
    private int nonce=0;

    public Block(String data, String previousHasCode) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        this.previousHasCode = previousHasCode;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
        this.hashCode = StringUtil.generateHashCode(this);
    }

    public void mineBlock(int difficulty) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String targetPreZero = StringUtil.getTargetPrefixHash(difficulty);

        while(!this.hashCode.substring(0,difficulty).equals(targetPreZero)){
            nonce++;
            this.hashCode= StringUtil.generateHashCode(this);
        }

        System.out.println("com.blockchain.basics.Block Mined. Hash -> "+ this.getHashCode());
    }


    public String getHashCode() {
        return hashCode;
    }

    public String getPreviousHasCode() {
        return previousHasCode;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getData() {
        return data;
    }

    public int getNonce() {
        return nonce;
    }
}
