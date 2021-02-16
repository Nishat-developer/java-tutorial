// Compile and run this program on the net whenever a frame is opened press "ENTER" 
// If there is no net it gives an error saying
// "Error processing: http://magelang.com "

import java.io.*;
import java.awt.*;
import java.net.*;
import java.util.*; 
import javax.swing.*; 
import java.awt.event.*; 

public class Tester1
{
public static void main(String args[])
{ 
JFrame frame = new JFrame("Word Count URL");
frame.setDefaultCloseOperation(3);
Container contentPane = frame.getContentPane();

final JTextField textField = new JTextField("http://magelang.com"); 
contentPane.add(textField, BorderLayout.NORTH);

final JTextArea textArea = new JTextArea(); 
textArea.setEditable(false); 

JScrollPane scrollPane = new JScrollPane(textArea); 
contentPane.add(scrollPane, BorderLayout.CENTER); 

final WordCount wordCount = new WordCount();
ActionListener readURLAction = new ActionListener() {
	public void actionPerformed(ActionEvent actionEvent)
	{
		String urlString = textField.getText().trim();
		if (urlString.length() != 0) { 
		try
		{
			URL url = new URL(urlString); 
			wordCount.readURL(url);
			Map map = wordCount.getMap(); 
			String mapString = convertMap(map);
			textArea.setText(mapString);
		}
		catch (MalformedURLException mal) 
		{
			textArea.setText("Bad URL: " + urlString);
		} } }; 
	};

textField.addActionListener(readURLAction); 
JButton clearButton = new JButton("Clear");
contentPane.add(clearButton, BorderLayout.SOUTH);

ActionListener clearAction = new ActionListener() 
	{
	public void actionPerformed(ActionEvent actionEvent)
	{
		textArea.setText(""); 
		wordCount.clear(); 
	}
	};
	clearButton.addActionListener(clearAction); 
	frame.setSize(400, 250);
	frame.setVisible(true);
	}
	private static String convertMap(Map map)
	{
		StringWriter writer = new StringWriter();
		PrintWriter out = new PrintWriter(writer);
		
		// Create a case insensitive comparator 
		Comparator comparator = new CaseInsensitiveComparator(); 

		// Put the original map into a sorted map with the new comparator
		Map sortedMap = new TreeMap(comparator); 
		sortedMap.putAll(map); 
		// Print out one key per line
		Iterator iterator = sortedMap.entrySet().iterator();
		while (iterator.hasNext())
		{
			Map.Entry entry = (Map.Entry)(iterator.next());
			out.println(entry.getKey() + "=" +entry.getValue()); 
		}
		return writer.toString();
	}
}
