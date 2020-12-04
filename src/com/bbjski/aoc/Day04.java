package com.bbjski.aoc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day04 {

    public static void main(String[] args) {

        System.out.println(">>> Enter passports:");

        Scanner input = new Scanner(System.in);
        List<HashMap<String,String>> passports = new ArrayList<>();
        HashMap<String, String> newPassport = new HashMap<>();
        while (input.hasNextLine()){
            String line = input.nextLine();
            if (line == null || line.length() == 0) {
                if (!newPassport.isEmpty()) {
                    passports.add(newPassport);
                }
                newPassport = new HashMap<>();
            } else {
                String[] fields = line.split(" ");
                for (String field : fields) {
                    String[] elements = field.split(":");
                    String key = elements[0];
                    String value = elements[1];

                    newPassport.put(key, value);
                }
            }
        }
        if (!newPassport.isEmpty()) {
            passports.add(newPassport);
        }

        String[] mandatoryKeys = new String[] {
          "byr",
          "iyr",
          "eyr",
          "hgt",
          "hcl",
          "ecl",
          "pid"
        };

        int validPasswords = 0;
        int validPasswords2 = 0;
        for (HashMap<String,String> passport : passports) {
            boolean valid = true;
            boolean valid2 = true;
            for (String key : mandatoryKeys) {
                boolean containsKey =  passport.containsKey(key);
                valid = valid && containsKey;
                valid2 = valid2 && valid && validateValue(key, passport.get(key));
            }
            validPasswords = valid ? validPasswords + 1 : validPasswords;
            validPasswords2 = valid2 ? validPasswords2 + 1 : validPasswords2;
        }

        System.out.println(">>> [part 1] validPasswords: " + validPasswords);
        System.out.println(">>> [part 2] validPasswords2: " + validPasswords2);
    }

    private static boolean validateValue(String key, String value) {
        boolean valid = false;

        switch (key) {
            case "byr":
                {
                    int iValue = Integer.valueOf(value);
                    if (value.length() == 4 && iValue >= 1920 && iValue <= 2002) {
                        return true;
                    } else {
                        return false;
                    }
                }
            case "iyr":
                {
                    int iValue = Integer.valueOf(value);
                    if (value.length() == 4 && iValue >= 2010 && iValue <= 2020) {
                        return true;
                    } else {
                        return false;
                    }
                }
            case "eyr":
                {
                    int iValue = Integer.valueOf(value);
                    if (value.length() == 4 && iValue >= 2020 && iValue <= 2030) {
                        return true;
                    } else {
                        return false;
                    }
                }
            case "hgt":
                {
                    String unit = value.substring(value.length()-2);

                    if(!unit.equalsIgnoreCase("cm") && !unit.equalsIgnoreCase("in")) {
                        return false;
                    }

                    int iValue = Integer.valueOf(value.substring(0, value.length()-2));
                    if (unit.equalsIgnoreCase("cm") && iValue >= 150 && iValue <= 193) {
                        return true;
                    } else if (unit.equalsIgnoreCase("in") && iValue >= 59 && iValue <= 76) {
                        return true;
                    } else {
                        return false;
                    }
                }
            case "hcl":
                {
                    String hexColor = "^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$";
                    Pattern pattern = Pattern.compile(hexColor);
                    Matcher matcher = pattern.matcher(value);
                    return matcher.matches();
                }
            case "ecl":
                {
                    ArrayList<String> colors = new ArrayList<>();
                    colors.add("amb");
                    colors.add("blu");
                    colors.add("brn");
                    colors.add("gry");
                    colors.add("grn");
                    colors.add("hzl");
                    colors.add("oth");

                    return colors.contains(value);
                }
            case "pid":
                {
                    return value.length() == 9;
                }
        }

        return valid;
    }
}
