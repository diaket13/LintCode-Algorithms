package thread.baseProcess;

import java.io.IOException;
import java.util.Scanner;

public class BaseProcess {
	public static void main(String[] args) throws IOException {
		ProcessBuilder pb = new ProcessBuilder("cmd","/c","ipconfig/all");
		Process process = pb.start();
		Scanner scanner = new Scanner(process.getInputStream());
		while(scanner.hasNextLine()) {
			System.out.println(scanner.nextLine());
		}
		scanner.close();
		
		String cmd = "cmd "+"/c "+"ipconfig/all";
		Process porcessByexec = Runtime.getRuntime().exec(cmd);
		scanner = new Scanner(porcessByexec.getInputStream());
		while(scanner.hasNextLine()) {
			System.out.println(scanner.nextLine());
		}
		scanner.close();
	}
}
