package com.bbjski.aoc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Day04 {

    private static Pattern pattern = Pattern.compile("^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$");

    private static ArrayList<String> colors = new ArrayList<>();

    static {
        colors.add("amb");
        colors.add("blu");
        colors.add("brn");
        colors.add("gry");
        colors.add("grn");
        colors.add("hzl");
        colors.add("oth");
    }

    private static String[] mandatoryKeys = new String[] {
      "byr",
      "iyr",
      "eyr",
      "hgt",
      "hcl",
      "ecl",
      "pid"
    };

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

        int validPassports = 0;
        int validPassports2 = 0;
        for (HashMap<String,String> passport : passports) {
            boolean valid = true;
            boolean valid2 = true;
            for (String key : mandatoryKeys) {
                boolean containsKey =  passport.containsKey(key);
                valid = valid && containsKey;
                valid2 = valid2 && valid && validateValue(key, passport.get(key));
            }
            validPassports = valid ? validPassports + 1 : validPassports;
            validPassports2 = valid2 ? validPassports2 + 1 : validPassports2;
        }

        System.out.println(">>> [part 1] valid passport: " + validPassports);
        System.out.println(">>> [part 2] valid passport: " + validPassports2);
    }

    private static boolean validateValue(String key, String value) {

        switch (key) {
            case "byr":
                return validateNumber(value, 1920, 2002);
            case "iyr":
                return validateNumber(value, 2010, 2020);
            case "eyr":
                return validateNumber(value, 2020, 2030);
            case "hgt":
                {
                    String unit = value.substring(value.length()-2);

                    if(!unit.equalsIgnoreCase("cm") && !unit.equalsIgnoreCase("in")) {
                        return false;
                    }

                    try {
                        int iValue = Integer.valueOf(value.substring(0, value.length() - 2));
                        if (unit.equalsIgnoreCase("cm") && iValue >= 150 && iValue <= 193) {
                            return true;
                        } else if (unit.equalsIgnoreCase("in") && iValue >= 59 && iValue <= 76) {
                            return true;
                        } else {
                            return false;
                        }
                    } catch (NumberFormatException ex) {
                        return false;
                    }
                }
            case "hcl":
                return pattern.matcher(value).matches();
            case "ecl":
                return colors.contains(value);
            case "pid":
                return value.length() == 9;
        }

        return false;
    }

    private static boolean validateNumber(String value, int min, int max) {
        try {
            int iValue = Integer.valueOf(value);
            if (value.length() == iValue >= min && iValue <= max) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
