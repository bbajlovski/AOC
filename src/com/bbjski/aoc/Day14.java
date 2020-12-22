package com.bbjski.aoc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Day14 extends Thread {

    public static void main(String[] args) throws Exception {

        System.out.println(">>> Enter instructions:");

        Scanner input = new Scanner(System.in);

        String mask = null;
        HashMap<String, String> memory1 = new HashMap<>();
        HashMap<String, String> memory2 = new HashMap<>();
        while (input.hasNextLine()){
            String line = input.nextLine();
            String op = line.split("=")[0].trim();
            String value = line.split("=")[1].trim();

            if (op.equalsIgnoreCase("mask")) {
                mask = value + "";
            } else {
                String address = op.substring(4, op.length() - 1);
                value = String.format("%36s", Long.toBinaryString(Integer.valueOf(value))).replace(" ", "0");
                String valueCopy = value + "";

                value = applyMask(value, mask);
                memory1.put(address, value);

                applyMask(memory2, address, valueCopy, mask);
            }

        }

        long sum = calculateSum(memory1);
        long sum2 = calculateSum(memory2);

        System.out.println(">>> [part 1] sum: " + sum);
        System.out.println(">>> [part 2] sum: " + sum2);
    }

    private static String applyMask(String value, String mask) {
        String changedValue = value + "";
        for (int index = 0; index < mask.length(); index++) {
            if (mask.charAt(index) != 'X') {
                char[] changedValueArray = changedValue.toCharArray();
                changedValueArray[index] = mask.charAt(index);
                changedValue =  new String(changedValueArray);
            }
        }

        return changedValue;
    }

    private static void applyMask(HashMap memory, String address, String value, String mask) {
        String addressMasked = String.format("%36s", Long.toBinaryString(Integer.valueOf(address))).replace(" ", "0");

        ArrayList<String> addresses = new ArrayList<>();

        for (int index = 0; index < mask.length(); index++) {
            if (mask.charAt(index) == '1') {
                char[] addressMaskArray = addressMasked.toCharArray();
                addressMaskArray[index] = '1';
                addressMasked =  new String(addressMaskArray);
            } else if (mask.charAt(index) == 'X') {
                char[] addressMaskArray = addressMasked.toCharArray();
                addressMaskArray[index] = 'X';
                addressMasked =  new String(addressMaskArray);
            }
        }

        addresses.add(addressMasked);
        boolean containsFloating = addressMasked.indexOf('X') >= 0;

        while (containsFloating) {
            ArrayList<String> newAddresses = new ArrayList<>();
            ArrayList<String> removeAddresses = new ArrayList<>();
            for (String addr : addresses) {
                int index = addr.indexOf('X');
                if ( index >= 0) {
                    char[] changedAddrArray = addr.toCharArray();

                    changedAddrArray[index] = '0';
                    newAddresses.add(new String(changedAddrArray));

                    changedAddrArray[index] = '1';
                    newAddresses.add(new String(changedAddrArray));

                    removeAddresses.add(addr);
                }
            }

            for (String addr : newAddresses) {
                addresses.add(addr);
            }

            for (String addr : removeAddresses) {
                addresses.remove(addr);
            }

            containsFloating = false;
            for (String addr : addresses) {
                containsFloating = containsFloating || addr.indexOf('X') >= 0;
            }
        }

        for (String addr : addresses) {
            memory.put(String.valueOf(Long.parseLong(addr, 2)), value);
        }


    }

    private static long calculateSum(HashMap<String, String> memory) {
        long sum = 0;

        String[] memDump = memory.values().toArray(new String[memory.size()]);

        for (String val : memDump){
            sum += Long.parseLong(val, 2);
        }

        return sum;
    }
}


