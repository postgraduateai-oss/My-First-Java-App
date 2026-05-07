import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SlotMachineGUI {

    private static int coins = 100;

    public static void main(String[] args) {
        // Frame ဖန်တီးခြင်း
        JFrame frame = new JFrame("Java Slot Machine");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));

        // Components များ ထည့်သွင်းခြင်း
        JLabel coinsLabel = new JLabel("လက်ကျန် Coins: " + coins);
        coinsLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel betLabel = new JLabel("လောင်းကြေး (Bet) ထည့်ပါ -");
        JTextField betField = new JTextField(15);

        // Reel များ (Slot ပုံဖော်ရန်)
        JLabel reel1 = new JLabel("?");
        JLabel reel2 = new JLabel("?");
        JLabel reel3 = new JLabel("?");

        // Reel များ၏ Font နှင့် အရွယ်အစား
        Font reelFont = new Font("Arial", Font.BOLD, 30);
        reel1.setFont(reelFont);
        reel2.setFont(reelFont);
        reel3.setFont(reelFont);

        // Panel တစ်ခုအတွင်းသို့ Reel 3 ခု ထည့်ခြင်း
        JPanel reelPanel = new JPanel();
        reelPanel.setPreferredSize(new Dimension(300, 80));
        reelPanel.setBackground(Color.LIGHT_GRAY);
        reelPanel.add(reel1);
        reelPanel.add(Box.createHorizontalStrut(20));
        reelPanel.add(reel2);
        reelPanel.add(Box.createHorizontalStrut(20));
        reelPanel.add(reel3);

        JButton spinButton = new JButton("Spin 🚀");
        spinButton.setFont(new Font("Arial", Font.BOLD, 14));
        
        JLabel resultLabel = new JLabel("ဂိမ်းကစားရန် လောင်းကြေးထည့်ပြီး စတင်လိုက်ပါ");

        // Spin Button နှိပ်လိုက်သောအခါ လုပ်ဆောင်မည့် အပိုင်း
        spinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (coins <= 0) {
                    JOptionPane.showMessageDialog(frame, "သင့်တွင် Coins ကုန်သွားပါပြီ။ ဂိမ်းပြီးဆုံးပါပြီ။", "Game Over", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String betText = betField.getText().trim();
                int bet;
                try {
                    bet = Integer.parseInt(betText);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "ကျေးဇူးပြု၍ ဂဏန်းအမှန် ထည့်ပေးပါ။", "အမှား", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (bet <= 0 || bet > coins) {
                    JOptionPane.showMessageDialog(frame, "လောင်းကြေးသည် 0 ထက်ကြီးရမည်ဖြစ်ပြီး သင့်လက်ကျန်ငွေထက် မကျော်ရပါ။", "အမှား", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // လောင်းကြေး နှုတ်ခြင်း
                coins -= bet;

                // Random ရလဒ် ထုတ်ခြင်း (1 မှ 3 ထိ)
                Random random = new Random();
                int r1 = random.nextInt(3) + 1;
                int r2 = random.nextInt(3) + 1;
                int r3 = random.nextInt(3) + 1;

                // UI တွင် ရလဒ်ပြောင်းလဲခြင်း
                reel1.setText(String.valueOf(r1));
                reel2.setText(String.valueOf(r2));
                reel3.setText(String.valueOf(r3));

                // အနိုင်/အရှုံး စစ်ဆေးခြင်း
                if (r1 == r2 && r2 == r3) {
                    int win = bet * 5;
                    coins += win;
                    resultLabel.setText("ဂုဏ်ယူပါတယ်! " + win + " Coins ထပ်ရရှိပါသည်။");
                } else if (r1 == r2 || r2 == r3 || r1 == r3) {
                    int win = bet * 2;
                    coins += win;
                    resultLabel.setText("ကံကောင်းပါတယ်! " + win + " Coins ထပ်ရရှိပါသည်။");
                } else {
                    resultLabel.setText("စိတ်မကောင်းပါဘူး၊ ဒီအလှည့်မှာ ရှုံးနိမ့်သွားပါတယ်။");
                }

                // Coins လက်ကျန်ကို Update လုပ်ပေးခြင်း
                coinsLabel.setText("လက်ကျန် Coins: " + coins);
            }
        });

        // Frame ပေါ်သို့ Components များ ပေါင်းထည့်ခြင်း
        frame.add(coinsLabel);
        frame.add(betLabel);
        frame.add(betField);
        frame.add(reelPanel);
        frame.add(spinButton);
        frame.add(resultLabel);

        // Frame ပေါ်လာစေရန်
        frame.setVisible(true);
    }
}
