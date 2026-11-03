
import javax.swing.JOptionPane;

public class SPECIAL_PIZZA extends PIZZA {

    private static final double SPECIAL_CHARGE = 50.00;

    public SPECIAL_PIZZA() {
        super("Special Pizza");
    }

    public double getSpecialChargePerPizza() {
        return SPECIAL_CHARGE;
    }

    public void showMessage() {
        JOptionPane.showMessageDialog(null,
                "Special Pizza selected!\n"
                + "Additional charge: PHP 50.00 per pizza.");
    }

    @Override
    public double calculatePrice() {
        double price = super.calculatePrice();

        price = price + (SPECIAL_CHARGE * getHowMany());

        setPrice(price);

        return price;
    }
}

