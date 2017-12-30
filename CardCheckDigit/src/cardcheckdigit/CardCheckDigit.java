/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardcheckdigit;

/*
 *
 * @author stane
 */
import java.util.Scanner;


public class CardCheckDigit {

    private static final int cardNoLength = 15;
    
    
    // checks if the input is a number
    // if its a number return true, else false;
    private static boolean isNumeric(String s){
        try{
            double d = Double.parseDouble(s);
        }
        catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }

    private static int addInteger(int num){
        int sum = 0;
        while (num > 0) {
            sum = sum + num % 10;
            num = num / 10;
        }
        return sum;
    }

    private static String calculateCheckDigit(String cardNo){
                int multiplied;
                int sumNumber = 0;
                for(int i=0; i<CardCheckDigit.cardNoLength; i++){
                    //To calculate the check digit, multiply every even-position digit 
                    //(when counted from the right) in the number by two. If the result is a two digit number, 
                    // then add these digits together to make a single digit (this is called the digital root).

                    multiplied = ( i % 2 == 0 )?Character.getNumericValue(cardNo.charAt(i))*2:Character.getNumericValue(cardNo.charAt(i));
//                    If the result is a two digit number, 
//                    then add these digits together to make a single digit (this is called the digital root)
                    if(String.valueOf(multiplied).length()>1){
                         multiplied = CardCheckDigit.addInteger(multiplied);
                    }

//                    To this total, we then add every odd-position digit.
//                    This will result in a total
                    sumNumber += multiplied;
                }
//                 The check-digit is what number needs to be added to this total to make the next multiple of 10.

                int modulusTen = sumNumber%10;
                int checkDigit = 10-modulusTen;
                return "Your Card Number is "+cardNo+""+checkDigit;
    }
        

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String cardNo; //524550000514110
        boolean checkNumeric;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter you debit/credit card number");
        cardNo = input.nextLine();

        checkNumeric = CardCheckDigit.isNumeric(cardNo);

        if(checkNumeric){
            //checks if the length is 15 digits
            if(cardNo.length() == cardNoLength)
                System.out.println(CardCheckDigit.calculateCheckDigit(cardNo));
            else
                System.out.println("kindly enter the "+ cardNoLength +" digits of your card number ");
        }
        else
            System.out.println("Kindly enter your credit/cards "+ cardNoLength +" digits. They should be numbers only");
    }
    
}
