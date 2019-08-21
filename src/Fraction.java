public class Fraction {
    private int numerator, denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("the denominator cannot be 0");
        } else if (denominator < 0) {
            denominator *= -1;
            numerator *= -1;
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction(int number) {
        this(number, 1);
    }

    public Fraction() {
        this(0, 1);
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public String toString() {
        String str = numerator + "/" + denominator;
        return str;
    }

    public double toDouble() {
        double dbl = (double)(numerator / denominator);
        return dbl;
    }

    public Fraction add(Fraction other) {
        int newDenominator = this.denominator * other.denominator;
        int thisNumerator = this.numerator * other.denominator;
        int otherNumerator = other.numerator * this.denominator;
        int newNumerator = thisNumerator + otherNumerator;
        return new Fraction(newNumerator, newDenominator);
    }

    public Fraction subtract(Fraction other) {
        int newDenominator = this.denominator * other.denominator;
        int thisNumerator = this.numerator * other.denominator;
        int otherNumerator = other.numerator * this.denominator;
        int newNumerator = thisNumerator - otherNumerator;
        return new Fraction(newNumerator, newDenominator);
    }

    public Fraction multiply(Fraction other) {
        int newNumerator = this.numerator * other.numerator;
        int newDenominator = this.denominator * other.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    public Fraction divide(Fraction other) {
        if (other.numerator == 0) {
            throw new IllegalArgumentException("Cannot divide by 0.");
        }
        int newNumerator = this.numerator * other.denominator;
        int newDenominator = this.denominator * other.numerator;
        return new Fraction(newNumerator, newDenominator);
    }

    public void toLowestTerms() {
        int gcd = gcd(this.numerator, this.denominator);
        this.numerator /= gcd;
        this.denominator /= gcd;
    }

    public static int gcd(int numerator, int denominator) {
        while (numerator != 0 && denominator != 0) {
            int remainder = numerator % denominator;
            numerator = denominator;
            denominator = remainder;
        }
        return numerator;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Fraction)) {
            throw new IllegalArgumentException("Argument must be an object of type 'Fraction'");
        }
        Fraction otherFraction = (Fraction)other;
        int thisGCD = gcd(this.numerator, this.denominator);
        int otherGCD = gcd(otherFraction.numerator, otherFraction.denominator);
        int thisNumerator = this.numerator / thisGCD;
        int thisDenominator = this.denominator / thisGCD;
        int otherNumerator = otherFraction.numerator / otherGCD;
        int otherDenominator = otherFraction.denominator / otherGCD;
        if (thisNumerator == otherNumerator && thisDenominator == otherDenominator) {
            return true;
        }
        return false;
    }
}
