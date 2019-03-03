import configuration.Configuration;
import crossover.Crossover;
import mutation.Mutation;
import selection.Selection;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Scanner;

public class CommandLine {

    private static Crossover crossover;
    private Selection selection;
    private static Mutation mutation;
    private static Configuration config;
    private Type crossoverType;


        public static void main(String []args){

            String command = "";
            String cm = "0";
            float cr = 0;
            String mm = "0";
            float mr = 0;
            String s = "0";
            int n = 0;
            int check;
            boolean correkt = false;
            String[] expression = {"OnePX", "TwoPX", "AX", "HX", "IX", "KPX", "SX", "UNX", "DM", "EM", "INSM", "INVM", "SM", "BS", "RS", "RWS", "TS"};

            System.out.println("cm[#]cr<#>mm[#]mr<#>s[#]n<#>");
            Scanner scanner = new Scanner(new InputStreamReader(System.in));
            command = scanner.nextLine();
            scanner.close();

//select cm
            check = command.indexOf("cm");
            if(check > -1){
                check = check + 2;
                if(command.charAt(check) == (char)91) check++;
                cm = command.substring(check, command.indexOf("]", check));
                for(int i = 0; i<8;i++) if(cm.equals(expression[i])) correkt = true;
                if(correkt == false) System.err.println("Error: Wrong expression cm! Please use: 1PX, 2PX, AX, HX, IX, KPX, SX, UNX");
            }
            else System.err.println("Error: cm not found! Please use correkt expression: cm[#]cr<#>mm[#]mr<#>s[#]n<#>");
            correkt = false;

//select cr
            check = command.indexOf("cr");
            if(check > -1){
                check = check + 2;
                if(command.charAt(check) == (char)60) check++;
                try {cr = Float.parseFloat(command.substring(check, command.indexOf(">", check)));}
                catch(Exception e) {
                    System.err.println("Error: No value cr! Please use correkt expression: cr[#]cr<#>mm[#]mr<#>s[#]n<#>");
                    correkt = true;
                }
                if((cr < 0.5 || cr > 0.8) && !correkt) System.err.println("Error: Value out of range. 0.5 <= cr <= 0.8");
            }
            else System.err.println("Error: cr not found! Please use correkt expression: cr[#]cr<#>mm[#]mr<#>s[#]n<#>");
            correkt = false;

//select mm
            check = command.indexOf("mm");
            if(check > -1){
                check = check + 2;
                if(command.charAt(check) == (char)91) check++;
                mm = command.substring(check, command.indexOf("]", check));
                for(int i = 8; i<13; i++) if(mm.equals(expression[i])) correkt = true;
                if(correkt == false) System.err.println("Error: Wrong expression mm! Please use: DM, EM, INSM, INVM, SM");
            }
            else System.err.println("Error: mm not found! Please use correkt expression: mm[#]cr<#>mm[#]mr<#>s[#]n<#>");
            correkt = false;

//select mr
            check = command.indexOf("mr");
            if(check > -1){
                check = check + 2;
                if(command.charAt(check) == (char)60) check++;
                try {mr = Float.parseFloat(command.substring(check, command.indexOf(">", check)));}
                catch(Exception e) {
                    System.err.println("Error: No value mr! Please use correkt expression: mr[#]cr<#>mm[#]mr<#>s[#]n<#>");
                    correkt = true;
                }
                if((mr < 0.001 || mr > 0.005) && !correkt) System.err.println("Error: Value out of range. 0.001 <= mr <= 0.005");
            }
            else System.err.println("Error: mr not found! Please use correkt expression: mr[#]cr<#>mm[#]mr<#>s[#]n<#>");
            correkt = false;

//select s
            check = command.indexOf("s");
            if(check > -1){
                check = check + 2;
                if(command.charAt(check) == (char)91) check++;
                s = command.substring(check, command.indexOf("]", check));
                for(int i = 13; i<17; i++) if(s.equals(expression[i])) correkt = true;
                if(correkt == false) System.err.println("Error: Wrong expression s! Please use: BS, RS, RWS, TS");
            }
            else System.err.println("Error: s not found! Please use correkt expression: s[#]cr<#>mm[#]mr<#>s[#]n<#>");
            correkt = false;

//select n
            check = command.indexOf("n");
            if(check > -1){
                check = check + 2;
                if(command.charAt(check) == (char)60) check++;
                try {n = Integer.parseInt(command.substring(check, command.indexOf(">", check)));}
                catch(Exception e) {
                    System.err.println("Error: No value n! Please use correkt expression: n[#]cr<#>mm[#]mr<#>s[#]n<#>");
                }
            }
            else System.err.println("Error: n not found! Please use correkt expression: n[#]cr<#>mm[#]mr<#>s[#]n<#>");

            switch(cm) {
                case "PMX":
                    config.instance.crossoverType = Crossover.OnePX;

                case "IRX" :
                    config.instance.crossoverType = Crossover.TwoPX;

                case "AX":
                    config.instance.crossoverType = Crossover.AX;

                case "HX":
                    config.instance.crossoverType = Crossover.HX;

                case "IX":
                    config.instance.crossoverType = Crossover.IX;

                case "KPX":
                    config.instance.crossoverType = Crossover.KPX;

                case "SX":
                    config.instance.crossoverType = Crossover.SX;

                case "UNX":
                    config.instance.crossoverType = Crossover.UNX;
            }

            switch(mm) {
                case "DM":
                     mutation = Mutation.DM;

                case"EM":
                     mutation = Mutation.EM;
            }

        }
    }

