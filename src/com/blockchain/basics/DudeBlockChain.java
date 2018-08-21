package com.blockchain.basics;

import com.google.gson.GsonBuilder;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class DudeBlockChain {

    private static List<Block> blockChain = new ArrayList<>();
    private static final int DIFFICULTY=5;

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        blockChain.add(new Block("Adarsh", "0"));
        blockChain.get(0).mineBlock(DIFFICULTY);
        blockChain.add(new Block("Prakhar", blockChain.get(blockChain.size() - 1).getHashCode()));
        blockChain.get(1).mineBlock(DIFFICULTY);
        blockChain.add(new Block("Akanksha", blockChain.get(blockChain.size() - 1).getHashCode()));
        blockChain.get(2).mineBlock(DIFFICULTY);

        String jsonBlockChain = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
        System.out.println(jsonBlockChain);

        System.out.println("Testing the integrity of the Blockchain");
        System.out.println("com.blockchain.basics.Block Chain is Valid:"+isChainValid(blockChain));
    }

    /**
     * A chain is valid when all the blocks in the chain have the previousHasCode value congruent with the hashcode value of previous com.blockchain.basics.Block
     * Except for the genisis com.blockchain.basics.Block where previousHascode value will be 0;
     * This will check for integrity of the blockchain
     **/
    public static boolean isChainValid(List<Block> blockChain) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        for (int i = 0; i < blockChain.size(); i++) {
            if (i > 0) {
                Block previousBlock = blockChain.get(i - 1);
                ;
                Block currentBlock = blockChain.get(i);
                if (currentBlock.getPreviousHasCode() != previousBlock.getHashCode()) {
                    return false;
                }
                //This will take care of the integrity when any changes in the block data is done but other data including hashcode is untouched
                if (!currentBlock.getHashCode().equals(StringUtil.generateHashCode(currentBlock))) {
                    return false;
                }

                //check if hash is solved
                if(!currentBlock.getHashCode().substring( 0, DIFFICULTY).equals(StringUtil.getTargetPrefixHash(DIFFICULTY))) {
                    System.out.println("This block hasn't been mined");
                    return false;
                }
            } else {
                if (!blockChain.get(i).getHashCode().equals("0")) {//genesi block's prevHasCode is not equal to 0 then integrity is lost
                    return false;
                }
            }
        }
        return true;
    }
}


