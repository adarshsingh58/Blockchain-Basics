package com.blockchain.basics;

import com.blockchain.basics.Block;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Block  genesisBlock=new Block("Adarsh","0");
        System.out.println("Hash for block 1: "+genesisBlock.getHashCode());

        Block secondBlock = new Block("Yo im the second block",genesisBlock.getHashCode());
        System.out.println("Hash for block 2 : " + secondBlock.getHashCode());

        Block thirdBlock = new Block("Hey im the third block",secondBlock.getHashCode());
        System.out.println("Hash for block 3 : " + thirdBlock.getHashCode());

    }
}
