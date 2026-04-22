	package app;
	
	import javax.swing.*;
	import java.awt.*;
	import model.Result;
	
	public class ResultScreen extends JPanel {
	
	    public ResultScreen(Measurement frame, Result result) {
	        setLayout(new BorderLayout());
	
	        JLabel label = new JLabel(result.toString(), SwingConstants.CENTER);
	        JButton saveBtn = new JButton("Save to File");
	        JButton backBtn = new JButton("Back to Menu");
	
	        saveBtn.addActionListener(e -> result.saveToFile("results.txt"));
	        backBtn.addActionListener(e -> frame.showMainMenu());
	
	        add(label, BorderLayout.CENTER);
	
	        JPanel bottom = new JPanel();
	        bottom.add(saveBtn);
	        bottom.add(backBtn);
	
	        add(bottom, BorderLayout.SOUTH);
	    }
	}
