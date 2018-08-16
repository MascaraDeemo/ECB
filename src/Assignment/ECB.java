/**
 * main class to all methods and input files
 */

package Assignment;

import java.io.File;
import java.util.Scanner;

public class ECB {

	public static void main(String[] args) {
		PhoneBook.loadPhoneBook("contacts_4.txt");
		PhoneBook.determineInstruction("instructions_4.txt");

	}
}
