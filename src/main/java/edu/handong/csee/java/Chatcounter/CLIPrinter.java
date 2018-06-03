package edu.handong.csee.java.Chatcounter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * This is public class, CLIPrinter.</br>
 * This class will implement through argument.
 * 
 * @author Administrator
 *
 */
public class CLIPrinter {

	String filePath;
	String filename;
	boolean help;
	boolean verbose;

	/**
	 * This is public method, run.</br>
	 * This method will use the options to implement a logic.
	 * 
	 * @param args
	 */
	public void run(String[] args) {
		Options options = createOptions();

		if (parseOptions(options, args)) {
			if (help) {
				printHelp(options);
				return;
			}

		}
	}

	/**
	 * This is public method, parseOptions.</br>
	 * This method will implement logic that parse options typed by users in CLI.
	 * 
	 * @param args
	 */
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {
			CommandLine cmd = parser.parse(options, args);

			filePath = cmd.getOptionValue("i");
			filename = cmd.getOptionValue("o");
			help = cmd.hasOption("h");
		}

		catch (Exception e) {
			printHelp(options);
			return false;
		}
		return true;
	}

	/**
	 * This is public method, createOptions.</br>
	 * This method will create an instance of Option class.
	 * 
	 * @param args
	 */
	private Options createOptions() {
		Options options = new Options();

		options.addOption(Option.builder("i").longOpt("filePath").desc("Set a path of a directory or a file to display")
				.hasArg().argName("Path name to display").required().build());

		options.addOption(Option.builder("o").longOpt("filename").desc("count the kakao chat").hasArg()
				.argName("ChatCounter").required().build());

		options.addOption(Option.builder("h").longOpt("help").desc("Help").build());

		return options;
	}

	/**
	 * This is public method, printHelp.</br>
	 * this method will display the help message.
	 * 
	 * @param options
	 */
	private void printHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		String header = "This is a Kakao ChatCounter!";
		String footer = "\nPlease report issues at https://github.com/Bigstargithub/ChatCounter";
		formatter.printHelp("CLIExample", header, options, footer, true);
	}

	/**
	 * This is public method, getfilepath.</br>
	 * This method will get the file path.
	 * 
	 * @return
	 */
	public String getfilepath() {
		return filePath;
	}

	/**
	 * This is public method, getfilepath.</br>
	 * This method will get the file name.
	 * 
	 * @return
	 */
	public String getfilename() {
		return filename;
	}

}
