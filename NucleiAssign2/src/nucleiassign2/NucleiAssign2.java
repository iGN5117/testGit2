/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nucleiassign2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.TreeSet;
import org.apache.commons.lang3.StringUtils;

;

/**
 *
 * @author Nilesh Gajwani
 */
public class NucleiAssign2 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        TreeSet<User> users = new TreeSet<>();
        try {
            FileInputStream fin = new FileInputStream("Users");
            ObjectInputStream in = new ObjectInputStream(fin);
            users = (TreeSet) in.readObject();
            in.close();
            fin.close();
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {

        }

        boolean updated = false, exitAfterSaving = false;
        int choice;
        System.out.println("------------------------------------------------------------------");
        System.out.println("                    Welcome to the user database                  ");
        System.out.println("------------------------------------------------------------------");
        System.out.println("1. Add User Details");
        System.out.println("2. Display User Details");
        System.out.println("3. Delete User Details");
        System.out.println("4. Save User Details");
        System.out.println("5. Exit");
        while (1 > 0) {
            try {
                choice = Integer.parseInt(br.readLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid option");
                continue;
            }
            if (choice == 1) {
                String name, address;
                int rollNumber, age;
                ArrayList<Course> courses = new ArrayList<>();
                name = getName();
                rollNumber = getrollNumber();
                age = getAge();
                address = getAddress();
                courses = getCourses();
                User newUser = new User(name, address, age, rollNumber, courses);
                users.add(newUser);
                updated = true;
            } else if (choice == 2) {
                TreeSet<User> toDisplay = null;
                System.out.println("Select the field by which to do ordering:");
                System.out.println("1. Name");
                System.out.println("2. Age");
                System.out.println("3. Address");
                System.out.println("4. Roll Number");
                int fieldChoice = Integer.parseInt(br.readLine().trim());
                System.out.println("1. Ascending");
                System.out.println("2. Descending");
                int orderChoice = Integer.parseInt(br.readLine().trim());
                switch (fieldChoice) {
                    case 1:
                        toDisplay = users;
                        break;
                    case 2:
                        toDisplay = new TreeSet<User>(new ageCompare());
                        toDisplay.addAll(users);
                        break;
                    case 3:
                        toDisplay = new TreeSet<User>(new addressCompare());
                        toDisplay.addAll(users);
                        break;
                    case 4:
                        toDisplay = new TreeSet<User>(new rollCompare());
                        toDisplay.addAll(users);
                        break;
                    case 5:
                        System.out.println("Invalid choice entered");
                        continue;

                }
                if (orderChoice == 2) {
                    System.out.println("descending..........");
                    toDisplay = (TreeSet<User>) toDisplay.descendingSet();
                }
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("Name     Roll     Number     Age     Address     Courses");
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
                for (User u : toDisplay) {
                    System.out.println(u.toString());
                }
//                System.out.println("You can choose the following filters: ");
//                System.out.println("1. Filter ");
            } else if (choice == 3) {
                int roll;
                System.out.println("Enter the roll number of the user you wish to delete: ");
                roll = getrollNumber();
                boolean found = false;
                for (User u : users) {
                    if (u.rollNumber == roll) {
                        System.out.println("Deleting user: " + u.toString());
                        users.remove(u);
                        updated = true;
                        found = true;

                    }
                }
                if (!found) {
                    System.out.println("No user found with roll number: " + roll);
                }

            } else if (choice == 4) {
                System.out.println("Saving to disk");
                FileOutputStream fs = new FileOutputStream("Users");
                ObjectOutputStream out = new ObjectOutputStream(fs);
                out.writeObject(users);
                out.close();
                fs.close();
                if (exitAfterSaving == true) {
                    System.out.println("Exiting....");
                    break;
                }
            } else if (choice == 5) {
                char response;
                if (updated == true) {
                    System.out.println("Are you sure you want to exit without saving changes? (Y/N)");
                    response = br.readLine().trim().charAt(0);
                    if (response == 'Y') {
                        break;
                    } else {
                        exitAfterSaving = true;
                        choice = 4;
                        continue;
                    }
                }
                break;
            } else {
                System.out.println("Please enter a valid option");
            }
        }

    }

    public static String getName() throws IOException {
        String name = "";
        String response;

        while (name.equals("")) {
            System.out.println("Enter name of user: ");
            response = br.readLine();
            if (!(StringUtils.isAlpha(response))) {
                System.out.println("Invalid Name entered... Please enter a valid name");
                continue;
            } else {
                name = response;
            }

        }
        return name;
    }

    public static String getAddress() throws IOException {
        String address = "";
        String response;
        System.out.println("Enter address of user: ");
        while (address.isEmpty()) {
            response = br.readLine();

            address = response;

        }
        return address;
    }

    public static int getrollNumber() throws IOException {
        int rollNumber = -1;
        while (rollNumber < 0) {
            System.out.println("Enter roll number of student: ");
            try {
                rollNumber = Integer.parseInt(br.readLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid rollnumber");
                continue;
            }
        }
        return rollNumber;

    }

    public static int getAge() throws IOException {
        int age = -1;
        while (age < 0 || age > 110) {
            System.out.println("Enter age of user");
            try {

                age = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid age");
                continue;
            }
            if (age < 0 || age > 110) {
                System.out.println("Please enter a valid age");
                continue;
            }

        }
        return age;
    }

    public static ArrayList<Course> getCourses() throws IOException {
        ArrayList<Course> courses = new ArrayList<>();
        String courseInput;
        String[] courseSplit;
        int count = 4;
        while (count > 0) {
            System.out.println("Please enter atleast " + (count) + "courses seperated by a space");
            courseInput = br.readLine();
            courseSplit = courseInput.split(" ");
            for (String s : courseSplit) {
                try {
                    courses.add(Course.valueOf(s));
                    count--;
                } catch (IllegalArgumentException e) {

                }
            }
        }

        return courses;
    }
}
