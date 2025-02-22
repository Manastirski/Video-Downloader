import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;

public class GUI {

    videoDownloader videoDownloader = new videoDownloader();
    public void createAndShowGUI() {
        JFrame frame = new JFrame("Youtube video downloader");

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JTextField textField = new JTextField(35);
        panel.add(textField);

        JButton downloadButton = new JButton("Download");
        downloadButton.setPreferredSize(new Dimension(100, 120));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);



        downloadButton.addActionListener(e -> {
            String inputText = textField.getText();
            System.out.println("Downloading Video");
            videoDownloader.download(inputText);

        });


        panel.add(downloadButton);
        frame.add(panel);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 1;
        gbc.gridy = 1;

        gbc.insets = new Insets(320, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        frame.add(downloadButton, gbc);

        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBackground(Color.LIGHT_GRAY);

        frame.setVisible(true);



    }
}
