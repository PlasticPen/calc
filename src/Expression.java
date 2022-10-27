

class Expression {

    private char sign;

    private char[] numSystem;

    private int [] numbers;
    Expression(){
        numbers = new int[]{0,0};
        numSystem = new char[]{'-', '-'};
    }


    public char getSign() {
        return sign;
    }

    public char [] getNumSystem () { return numSystem; }

    public int[] getNumbers() {
        return numbers;
    }

    public void parser(String input) throws FormatInputException{
        int counter = 0;
        String str = input.trim();
        ///CHECK THAT THERE IS ONLY 1 OPERATION
        for (int i = 0; i < str.length(); i++){
            if (str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*' || str.charAt(i) == '/'){
                counter++;
                this.sign = str.charAt(i);
            }
        }
        String[] expr;
        if (counter != 1)
            throw new FormatInputException("Exception: Wrong Input");
        ///SPLIT INPUT TO LEFT AND RIGHT OPERAND BY SIGN
        else
            expr = str.split("[" + (this.sign) + "]");

        expr[0] = expr[0].trim();
        expr[1] = expr[1].trim();

        for (int i = 0; i < 2; i++)
            checkingInput(expr[i], i);
        if (this.numSystem[0] == '-' || this.numSystem[1] == '-' || this.numSystem[0] != this.numSystem[1])
            throw new FormatInputException("Exception: Wrong input");
        if (this.numbers[0] > 10 || this.numbers[0] < 1 || this.numbers[1] > 10 || this.numbers[1] < 1)
            throw new FormatInputException("Exception: Input numbers can't be less than 1 and more than 10");
    }

    //CHECK THE NUMERAL SYSTEM AND PARSE STRING TO INT
    void checkingInput(String str, int ordinal){
            try{
                this.numbers[ordinal] = Integer.parseInt(str);
                this.numSystem[ordinal] = 'A';
            }catch (NumberFormatException nfe){
                for (RomanNum num : RomanNum.values()) {
                    if (num.toString().equals(str)) {
                        this.numSystem[ordinal] = 'R';
                        this.numbers[ordinal] = Integer.parseInt(num.arabicValue);
                    }
                }
            }
    }

    //ENCODE RESULT OF THE EXPRESSION BACK TO ROMAN NUMERALS
    public String fromArabicToRoman(int arabicResult) throws FormatInputException{

        int number = arabicResult;
        if (number <= 0)
            throw new FormatInputException("Roman Numerals result can't be less or equal to 0");

        StringBuilder romanResult = new StringBuilder();
        final RomanNum [] nums = RomanNum.values();
        for (int i = nums.length - 1; i >= 0; i--){
            while (number >= Integer.parseInt(nums[i].arabicValue)){
                romanResult.append(nums[i]);
                number -= Integer.parseInt(nums[i].arabicValue);
            }
        }
        return romanResult.toString();
    }
}