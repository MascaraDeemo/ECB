/**
 * This class is used to add every phone book person into arraylist
 * and several methods to modify the arraylist
 * **/
package Assignment;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class PhoneBook {
	public static ArrayList<PhoneBookPerson> ecb = new ArrayList<PhoneBookPerson>();
	
	
	/**
	 * initialize two arraylist, ecb for whole arraylist, report for query file
	 */

	/**
	 * use the fileName give to load the data in file and add them into array list
	 * @param fileName
	 */
	public static void loadPhoneBook(String fileName) {
		
		String[] variable;
		String temp;
		File file = new File(fileName);
		try {
			Scanner scan = new Scanner(file);
				int i = 0;
				int numOfObjects = countNames(fileName);
				System.out.println(numOfObjects);
				while(scan.hasNextLine()) {
					temp = scan.nextLine();
					if(temp.equals("")){
						i++;					
					}
					else {
							variable = temp.split(" ", 2);
							if (variable[0].equalsIgnoreCase("address")) {
								String fullAddress = variable[1];
								String line = null;
								while(scan.hasNextLine()){
									line = scan.nextLine();
									if(line.contains("\t")){
										line = line.replace("\t", "");
										fullAddress += line;
									}
									else
										break;
								}
								ecb.get(i).SetAddress(fullAddress);
								if(!"".equals(line)&&scan.hasNextLine()){
								variable = line.split(" ", 2);
								if (variable[0].equalsIgnoreCase("birthday")) {
									String regex = "^[0-3]?[0-9]-[0-3]?[0-9]-(?:[0-9]{2})?[0-9]{2}$";
									if(variable[1].matches(regex)&&timeValidator(variable[1])){
									SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
									Date date1 = new Date(1999-1-1);
									date1 =	date.parse(variable[1]);
									ecb.get(i).SetBirthday(date1);}
									else
										System.out.println("Wrong birthday format for " + ecb.get(i).getName());
								}
								else if (variable[0].equalsIgnoreCase("phone")) {
									long phone;
									String regex = "\\d+";;
									if(variable[1].matches(regex)){
									phone = Long.parseLong(variable[1]);
									ecb.get(i).SetPhone(phone);
									}
									else
										System.out.println("Wrong phone format for " +ecb.get(i).getName());
								}
								else if (variable[0].equalsIgnoreCase("name")) {
									String nm = variable[1];
									String regex = "^[a-zA-Z\\s]+";
									if(nm.matches(regex)){
										ecb.get(i).SetName(nm);
									}
									else
										System.out.println("Wrong name format at "+ i + "location");
								}
								else if (variable[0].equalsIgnoreCase("email")) {
									String email = variable[1];
						            String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
						            Pattern pattern = Pattern.compile(regex);
						            Matcher matcher = pattern.matcher(email);
						            if (!matcher.matches()) {
						            		System.out.println("Email Invalid for " + ecb.get(i).getName());
						            }
						            else {
						            		ecb.get(i).SetEmail(email);
						            }

								}
								}
								else
									i++;
								
							}
							else if(variable[0].equalsIgnoreCase("name")){
								String nm = variable[1];
								String regex = "^[a-zA-Z\\s]+";
								if(nm.matches(regex)){
									ecb.get(i).SetName(nm);
								}
								else
									System.out.println("Wrong name format at "+ (i+1) + " position");
							}
							else if (variable[0].equalsIgnoreCase("birthday")) {
								String regex = "^[0-3]?[0-9]-[0-3]?[0-9]-(?:[0-9]{2})?[0-9]{2}$";
								if(variable[1].matches(regex)&&timeValidator(variable[1])){
								SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
								Date date1 = new Date(1999-1-1);
								date1 =	date.parse(variable[1]);
								ecb.get(i).SetBirthday(date1);}
								else
									System.out.println("Wrong birthday format for " + ecb.get(i).getName());
							}
							else if (variable[0].equalsIgnoreCase("phone")) {
								long phone;
								String regex = "\\d+";;
								if(variable[1].matches(regex)){
								phone = Long.parseLong(variable[1]);
								ecb.get(i).SetPhone(phone);
								}
								else
									System.out.println("Wrong phone format for " +ecb.get(i).getName());
							}

							else if (variable[0].equalsIgnoreCase("email")) {
								String email = variable[1];
					            String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
					            Pattern pattern = Pattern.compile(regex);
					            Matcher matcher = pattern.matcher(email);
					            if (!matcher.matches()) {
					            		System.out.println("Email Invalid for " + ecb.get(i).getName());
					            }
					            else {
					            		ecb.get(i).SetEmail(email);
					            }

							}

					}	
					}
				for(int j = 0; j< ecb.size(); j++){
					if("".equals(ecb.get(j).getName())){
						ecb.remove(j);
					}
				}
						
			
			scan.close();
		}
		
		catch(Exception e) {
			System.out.println("No File found " + e);
		}

		}

	
	/**
	 * use name to search in arraylist
	 * @param name
	 */
	public static void Query(String name){
		PrintWriter pw = null;
		FileOutputStream fos = null;
		try{
			fos  = new FileOutputStream("record.txt", true);
			pw = new PrintWriter(fos);
		}	
			catch (Exception e) {
				System.out.println(e);
			}
		pw.println("============Query "+ name +"============");
		for(PhoneBookPerson pbp:ecb) {
			if(pbp.getName().equals(name)) {
				pw.println("Name: " + pbp.getName());
				pw.println("Birthday: " + new SimpleDateFormat("dd-MM-yyyy").format(pbp.getBirthday()));
				if (!pbp.getAddress().equals("")){
					pw.println("Address: " + pbp.getAddress());
				}
				if (!(pbp.getPhone() == -1)) {
					pw.println("Phone: " + pbp.getPhone());
				}
				if (!(pbp.getEmail().equals(""))) {
					pw.println("Email: " + pbp.getEmail());
					
				}
			}
		}
		pw.println("============Query "+ name + " finish============");
		pw.println();
		pw.close();
	}
	public static void Query(Date birth){
		PrintWriter pw = null;
		FileOutputStream fos = null;
		try{
			fos  = new FileOutputStream("record.txt", true);
			pw = new PrintWriter(fos);
		}	
			catch (Exception e) {
				System.out.println(e);
			}
		String birth1 = new SimpleDateFormat("dd-MM-yyyy").format(birth);
		pw.println("============Query "+ birth1 +"============");
		for(PhoneBookPerson pbp:ecb) {
			if(pbp.getBirthday().equals(birth)) {
				pw.println("Name: " + pbp.getName());
				pw.println("Birthday: " + new SimpleDateFormat("dd-MM-yyyy").format(pbp.getBirthday()));
				if (!pbp.getAddress().equals("")){
					pw.println("Address: " + pbp.getAddress());
				}
				if (!(pbp.getPhone() == -1)) {
					pw.println("Phone: " + pbp.getPhone());
				}
				if (!(pbp.getEmail().equals(""))) {
					pw.println("Email: " + pbp.getEmail());	
				}
			}
		}
		pw.println("============Query "+ birth1 + " finish============");
		pw.println();
		pw.close();
	}
	public static void Query(long phone){
		PrintWriter pw = null;
		FileOutputStream fos = null;
		try{
			fos  = new FileOutputStream("record.txt", true);
			pw = new PrintWriter(fos);
		}	
			catch (Exception e) {
				System.out.println(e);
			}
		pw.println("============Query "+ phone +"============");
		for(PhoneBookPerson pbp:ecb) {
			if(pbp.getPhone() == phone) {
				pw.println("Name: " + pbp.getName());
				pw.println("Birthday: " + new SimpleDateFormat("dd-MM-yyyy").format(pbp.getBirthday()));
				if (!pbp.getAddress().equals("")){
					pw.println("Address: " + pbp.getAddress());
				}
				if (!(pbp.getPhone() == -1)) {
					pw.println("Phone: " + pbp.getPhone());
				}
				if (!(pbp.getEmail().equals(""))) {
					pw.println("Email: " + pbp.getEmail());	
				}
			}
		}
		pw.println("============Query "+ phone + " finish============");
		pw.println();
		pw.close();
	}


	
	/**
	 * use name to remove data match the name
	 * @param name
	 */
	public static void removePhoneBookPerson(String name) {
		for(PhoneBookPerson pbp:ecb)
			if(pbp.getName().equalsIgnoreCase(name))
				ecb.remove(pbp);
	}
	/**
	 * remove data match both name and birthday
	 * @param name
	 * @param birth
	 */
	public static void removePhoneBookPerson(String name, Date birth) {
		for(int i = 0; i < ecb.size(); i++){
			if(ecb.get(i).getName().equals(name) && ecb.get(i).getBirthday().equals(birth)){
				ecb.remove(i);
			}
		}
		
	}
	/**
	 * save and output the file in format
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	public static void savePhoneBook(String fileName){
		PrintWriter pw = null;
		FileOutputStream fos = null;
		try{
			fos  = new FileOutputStream(fileName);
			pw = new PrintWriter(fos);
			for(int i = 0;i<ecb.size();i++){
				if (ecb.get(i).getName().equals("")){
					ecb.remove(i);
					i--;
				}
			}
			for(int k =0; k<ecb.size();k++){
				if(ecb.get(k).getBirthday() == null){
					ecb.remove(k);
					k--;
				}
			}
			for (PhoneBookPerson pbp: ecb) {				
				pw.println("Name: " + pbp.getName());
				pw.println("Birthday: " + new SimpleDateFormat("dd-MM-yyyy").format(pbp.getBirthday()));
				
				if (!pbp.getAddress().equals("")){
					pw.println("Address: " + pbp.getAddress());
				}
				if (!(pbp.getPhone() == -1)) {
					pw.println("Phone: " + pbp.getPhone());
				}
				if (!(pbp.getEmail().equals(""))) {
					pw.println("Email: " + pbp.getEmail());					
				}	
				pw.println();
			}
			pw.close();
		
		}	
		catch (Exception e) {
			System.out.println(e + " save phone book");
		}
	}
		
	
	public static int countNames(String fileName) {
		int counting = 0;
		String countName;
		String[] spliT;
		try {
			File countFile  = new File(fileName);
			Scanner count = new Scanner(countFile);

			while (count.hasNextLine()) {
				countName = count.nextLine();
				if(countName.equals("")){
					ecb.add(new PhoneBookPerson());
			}
			}
			ecb.add(new PhoneBookPerson());
			count.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.print("Error counting names");
		}
		
		return counting;
	}

	public static void determineInstruction(String fileName) {
		File file = new File(fileName);
		try {
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()) {
				String instructions = scan.nextLine();
				String[] insAndInfor;
				insAndInfor = instructions.split(" ", 2);
				if (insAndInfor[0].equalsIgnoreCase("add")) {
					addInPhoneBook(insAndInfor[1]);
				}
				else if(insAndInfor[0].equalsIgnoreCase("delete")) {					
					
					int numOfSemis= 0;
					for(int i= 0; i< insAndInfor[1].length(); i++) {
						if(insAndInfor[1].charAt(i) == ';') {
							numOfSemis++;
						}

					}
					if (numOfSemis > 1) {
						System.out.println("Incorrect Removal Input");
					}
					else if (numOfSemis == 1){
						String[] splitThem;
						String name; Date birthday = new Date(1900-1-1);
						splitThem = insAndInfor[1].split("; ", 2);
							name = splitThem[0];
							SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
							Date date1 = new Date(1999-1-1);
							try {
								date1 =	date.parse(splitThem[1]);
								} catch (ParseException e) {
									System.out.println("Parse Failed");
									e.printStackTrace();
									}
							birthday = date1;

						
						removePhoneBookPerson(name, birthday);
					}
					else if (numOfSemis == 0) {
						String[] num0Split;
						String name = "s";
						num0Split = insAndInfor[1].split(" ", 2);
						if   (num0Split[0].equalsIgnoreCase("name")) {
							 name = num0Split[1];
						}
						else {
							System.out.println("invalid specifier");
						}
						removePhoneBookPerson(name);
					}
				}
				else if (insAndInfor[0].equalsIgnoreCase("query")) {
					String[] querySplit;
					querySplit = insAndInfor[1].split(" ",2);
					if (querySplit[0].equalsIgnoreCase("name")) {
						Query(querySplit[1]);
					}
					else if (querySplit[0].equalsIgnoreCase("birthday")) {
						SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
						Date date1 = new Date(1999-1-1);
						try {
							date1 =	date.parse(querySplit[1]);
							} catch (ParseException e) {
								System.out.println("Parse Failed");
								e.printStackTrace();
								}
						Query(date1);
					}
					else if(querySplit[0].equalsIgnoreCase("phone")) {
						long ph = -1;
						String regex = "\\d+";;
						if(querySplit[1].matches(regex)){
						ph = Long.parseLong(querySplit[1]);
						}
						else
							System.out.println("Wrong phone format for Querying");
					
						Query(ph);
					}
				}
				else if(insAndInfor[0].equalsIgnoreCase("save")) {
					savePhoneBook("results.txt");
				}
				
			}
			
			scan.close();
			}
		catch(Exception e) {
			System.out.print(e + " error determine");
		}
		

		
	}

	public static void addInPhoneBook(String information) {
		int numOfSemi = 0;
		for(int i = 0; i< information.length(); i++) {
			if(information.charAt(i) == ';') {
				numOfSemi++;
			}
		}
		String[] splitInformation;
		splitInformation = information.split("; ", numOfSemi+1);
		ecb.add(new PhoneBookPerson());
		int whichToGet = ecb.size()-1;
		for (int k = 0; k< splitInformation.length; k++) {
			String[] secondSplit;
			secondSplit = splitInformation[k].split(" ", 2);
			if   (secondSplit[0].equalsIgnoreCase("name")) {
				String nm = secondSplit[1];
				String regex = "^[a-zA-Z\\s]+";
				if(nm.matches(regex)){
					ecb.get(whichToGet).SetName(nm);
				}
				else
					System.out.println("Wrong name format when adding name " + nm);
			}
			else if (secondSplit[0].equalsIgnoreCase("birthday")) {
				String regex = "^[0-3]?[0-9]-[0-3]?[0-9]-(?:[0-9]{2})?[0-9]{2}$";
				if(secondSplit[1].matches(regex)&&timeValidator(secondSplit[1])){
				SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
				Date date1 = new Date(1999-1-1);
				
				try {
					
					date1 =	date.parse(secondSplit[1]);
					System.out.println(date1);
				} catch (ParseException e) {
					System.out.println(e);
				}
				ecb.get(whichToGet).SetBirthday(date1);
				}
				else
					System.out.println("Wrong birthday format when adding birth " + secondSplit[1]);
				}
			else if (secondSplit[0].equalsIgnoreCase("phone")) {
				long phone;
				String regex = "\\d+";;
				if(secondSplit[1].matches(regex)){
				phone = Long.parseLong(secondSplit[1]);
				ecb.get(whichToGet).SetPhone(phone);
				}
				else
					System.out.println("Wrong phone format for adding " +ecb.get(whichToGet).getName());
			
			}
			else if (secondSplit[0].equalsIgnoreCase("address")) {
				ecb.get(whichToGet).SetAddress(secondSplit[1]);
				
			}
			else if (secondSplit[0].equalsIgnoreCase("email")) {
				String email = secondSplit[1];
	            String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
	            Pattern pattern = Pattern.compile(regex);
	            Matcher matcher = pattern.matcher(email);
	            if (!matcher.matches()) {
	            		System.out.println("Email Invalid");
	            }
	            else {
	            		ecb.get(whichToGet).SetEmail(email);
	            }

			}
			
		}
		updateExisted();
		
	}
	public static void updateExisted() {
		for(int i = 0;i<ecb.size();i++){
			if (ecb.get(i).getName().equals("")){
				ecb.remove(i);
				i--;
			}
		}
		for(int k =0; k<ecb.size();k++){
			if(ecb.get(k).getBirthday() == null){
				ecb.remove(k);
				k--;
			}
		}
		for(int i = 0; i< ecb.size()-1;i++) {
			if ((ecb.get(i).getName().equals(ecb.get(ecb.size()-1).getName())) && (ecb.get(i).getBirthday().equals(ecb.get(ecb.size()-1).getBirthday()))) {
				if(!ecb.get(ecb.size()-1).getAddress().equals("")) {
					ecb.get(i).SetAddress(ecb.get(ecb.size()-1).getAddress());
				}
				if(!ecb.get(ecb.size()-1).getEmail().equals("")) {
					ecb.get(i).SetEmail(ecb.get(ecb.size()-1).getEmail());
				}
				if(!(ecb.get(ecb.size()-1).getPhone() == -1)) {
					ecb.get(i).SetPhone(ecb.get(ecb.size()-1).getPhone());
				}
				ecb.remove(ecb.size()-1);
			}
		}
	}
	public static boolean timeValidator(String time){
		boolean ifValid = true;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try{
			sdf.setLenient(false);
			Date timeCom = sdf.parse(time);
			Date timeNow = sdf.parse("06-06-2018");
			if(timeCom.compareTo(timeNow)>0){
				ifValid = false;
			}
		}catch(ParseException e){
			ifValid = false;
		}
		
		return ifValid;
	}

	
}

