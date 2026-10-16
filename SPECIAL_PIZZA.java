public class SPECIAL_PIZZA extends PIZZA {

    private static final double SPECIAL_CHARGE = 50.00;

    public SPECIAL_PIZZA() {
        super("Special Pizza");
    }

    @Override
    public double calculatePrice() {
        double regularTotal = super.calculatePrice();

        double specialCharge = SPECIAL_CHARGE * getHowMany();

        double specialTotal = regularTotal + specialCharge;

        setPrice(specialTotal);

        return specialTotal;
    }
}
