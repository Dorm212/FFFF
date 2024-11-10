package packAll;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Abstract class to represent a generic Exam
abstract class AbstractExam {
    protected String department;
    protected String section;
    protected String courseName;
    protected String courseCode;
    protected String examiner;
    protected String examDate;
    protected int startTimeHour;
    protected int startTimeMinute;
    protected int endTimeHour;
    protected int endTimeMinute;
    protected String block;
    protected String roomNumber;

    public AbstractExam(String department, String section, String courseName, String courseCode, String examiner, 
                        String examDate, int startTimeHour, int startTimeMinute, 
                        int endTimeHour, int endTimeMinute,  String block, String roomNumber) {
        this.department = department;
        this.section = section;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.examiner = examiner;
        this.examDate = examDate;
        this.startTimeHour = startTimeHour;
        this.startTimeMinute = startTimeMinute;
        this.endTimeHour = endTimeHour;
        this.endTimeMinute = endTimeMinute;
        this.block = block;
        this.roomNumber = roomNumber;
    }

    public abstract void displayExam(); // Abstract method for displaying exam details
}

// Concrete class representing a specific Exam
class Exam extends AbstractExam {
    public Exam(String department, String section, String courseName, String courseCode, String examiner, 
                String examDate, int startTimeHour, int startTimeMinute, 
                int endTimeHour, int endTimeMinute, String block, String roomNumber) {
        super(department, section, courseName, courseCode, examiner, examDate, startTimeHour, startTimeMinute, 
               endTimeHour, endTimeMinute, block, roomNumber);
    }

    @Override
    public void displayExam() {
        System.out.println("----------------------------------------------");
        System.out.println("Department: " + department);
        System.out.println("Section: " + section);
        System.out.println("Course Name: " + courseName);
        System.out.println("Course Code: " + courseCode);
        System.out.println("Examiner: " + examiner);
        System.out.println("Exam Date: " + examDate);
        System.out.println("Exam Time: " + startTimeHour + ":" + startTimeMinute + " - " +
                endTimeHour + ":" + endTimeMinute + " LT");
        System.out.println("Block: " + block);
        System.out.println("Room Number: " + roomNumber);
        System.out.println("----------------------------------------------");
    }
}

// Class to handle adding exams
class AddClass {

    static Scanner scanner = new Scanner(System.in);

    static void addExam() {
        try {
            System.out.println("\nAdding New Exam:");
            System.out.print("Enter Department: ");
            String department = ExceptionClass.getValidDepartmentInput();

            System.out.print("Enter Section: ");
            String section =scanner.nextLine();

            System.out.print("Enter Course Name: ");
            String courseName = ExceptionClass.getValidCourseNameInput();

            System.out.print("Enter Course Code: ");
            String courseCode = scanner.nextLine();

            System.out.print("Enter Examiner: ");
            String examiner = ExceptionClass.getValidExaminerInput();

            System.out.print("Enter Exam Date (YYYY-MM-DD): ");
            String examDate = ExceptionClass.getValidDateInput();

            System.out.print("Enter Exam Start Time (HH:MM): ");
            String startTime = ExceptionClass.getValidTimeInput();
            int startTimeHour = Integer.parseInt(startTime.substring(0, 2));
            int startTimeMinute = Integer.parseInt(startTime.substring(3, 5));
            


            System.out.print("Enter Exam End Time (HH:MM): ");
            String endTime = ExceptionClass.getValidTimeInput();
            int endTimeHour = Integer.parseInt(endTime.substring(0, 2));
            int endTimeMinute = Integer.parseInt(endTime.substring(3, 5));


            System.out.print("Enter Block: ");
            String block = scanner.nextLine();

            System.out.print("Enter Room Number: ");
            String roomNumber = scanner.nextLine();

            Exam newExam = new Exam(department, section, courseName, courseCode, examiner, examDate,
                    startTimeHour, startTimeMinute, endTimeHour, endTimeMinute, block, roomNumber);
            Democlass.exams.add(newExam);


            System.out.println("\nExam added successfully!");

        } catch (InputMismatchException e) {
            System.out.println("Invalid input for time or minutes. Please enter numbers.");
            scanner.nextLine();
        } catch (NumberFormatException e) {
            System.out.println("Invalid time format. Please enter in HH:MM format (e.g., 10:30).");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}

// Class to handle displaying and editing the timetable
class displayAndEditClass {
   static Scanner input = new Scanner(System.in);

    static void displayExam() {
        if (Democlass.exams.isEmpty()) {
            System.out.println("\nNo exams added yet.");
        } else {
            System.out.println("\nExam Timetable:");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-5s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n",
                    "#", "Department", "Section", "Course Name", "Course Code", "Examiner", "Exam Date", "Start Time", "End Time", "Block", "Room No");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (int i = 0; i < Democlass.exams.size(); i++) {
                Exam exam = Democlass.exams.get(i);
                System.out.printf("%-5d %-15s %-15s %-15s %-15s %-15s %-15s  %-15s %-15s %-15s %-15s\n",
                        (i + 1), exam.department, exam.section, exam.courseName, exam.courseCode, exam.examiner,
                        exam.examDate, exam.startTimeHour + ":" + exam.startTimeMinute , 
                        exam.endTimeHour + ":" + exam.endTimeMinute , exam.block,  exam.roomNumber );
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------");
        }
    }

    static void editExam() {
       
        if (Democlass.exams.isEmpty()) {
            System.out.println("\nNo exams to edit.");
            return;
        }

        displayExam();

        System.out.print("Enter the number of the exam you want to edit: ");
        int examIndex = input.nextInt() - 1;
        input.nextLine();

        if (examIndex < 0 || examIndex >= Democlass.exams.size()) {
            System.out.println("Invalid exam number.");
            return;
        }

        Exam examToEdit = Democlass.exams.get(examIndex);

        System.out.println("\nWhat do you want to edit?");
        System.out.println("1. Department");
        System.out.println("2. Section");
        System.out.println("3. Course Name");
        System.out.println("4. Course Code");
        System.out.println("5. Examiner");
        System.out.println("6. Exam Date");
        System.out.println("7. Start Time");
        System.out.println("8. End Time");
        System.out.println("9. Block");
        System.out.println("10. Room Number");
        System.out.print("Enter your choice: ");

        int editChoice = input.nextInt();
        input.nextLine();

        try {
            switch (editChoice) {
                case 1:
                    System.out.print("Enter new Department: ");
                    examToEdit.department = ExceptionClass.getValidDepartmentInput();
                    break;
                case 2:
                    System.out.print("Enter new Section: ");
                    examToEdit.section = input.nextLine();
                    break;
                case 3:
                    System.out.print("Enter new Course Name: ");
                    examToEdit.courseName = ExceptionClass.getValidCourseNameInput();
                    break;
                case 4:
                    System.out.print("Enter new Course Code: ");
                    examToEdit.courseCode = input.nextLine();
                    break;
                case 5:
                    System.out.print("Enter new Examiner: ");
                    examToEdit.examiner = ExceptionClass.getValidExaminerInput();
                    break;
                case 6:
                    System.out.print("Enter new Exam Date (YYYY-MM-DD): ");
                    examToEdit.examDate = ExceptionClass.getValidDateInput();
                    break;
                case 7:
                    System.out.print("Enter new Start Time (HH:MM): ");
                    String newStartTime = ExceptionClass.getValidTimeInput();
                    examToEdit.startTimeHour = Integer.parseInt(newStartTime.substring(0, 2));
                    examToEdit.startTimeMinute = Integer.parseInt(newStartTime.substring(3, 5));
                    break;
                case 8:
                    System.out.print("Enter new End Time (HH:MM): ");
                    String newEndTime = ExceptionClass.getValidTimeInput();
                    examToEdit.endTimeHour = Integer.parseInt(newEndTime.substring(0, 2));
                    examToEdit.endTimeMinute = Integer.parseInt(newEndTime.substring(3, 5));
                    break;
                case 9:
                    System.out.print("Enter new Block: ");
                    examToEdit.block = input.nextLine();
                    break;
                case 10:
                    System.out.print("Enter new Room Number: ");
                    examToEdit.roomNumber = input.nextLine();
                    break;
                default:
                    throw new Exception("Invalid edit choice.");
            }

            System.out.println("Exam edited successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            input.nextLine();
        } catch (NumberFormatException e) {
            System.out.println("Invalid time format. Please enter in HH:MM format (e.g., 10:30).");
            input.nextLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

// Class to handle input validation exceptions
class ExceptionClass {
         static Scanner scanner = new Scanner(System.in);

    public static String getValidTimeInput() {
        String input;
        while (true) {
            try {
                input = scanner.nextLine();
                if (input.length() == 5 && input.charAt(2) == ':') {
                    String hourPart = input.substring(0, 2);
                    String minutePart = input.substring(3, 5);

                    // Check if hour and minute are numeric
                    if (isNumeric(hourPart) && isNumeric(minutePart)) {
                        int hour = Integer.parseInt(hourPart);
                        int minute = Integer.parseInt(minutePart);

                        if (hour >= 1 && hour <= 12 && minute >= 0 && minute <= 59) {
                            return input;
                        } else {
                            System.out.println("Invalid time range. Hour must be between 1-12, and minute must be between 00-59.");
                        }
                    } else {
                        System.out.println("Invalid time format. Please enter in HH:MM format (e.g., 10:30).");
                    }
                } else {
                    System.out.println("Invalid time format. Please enter in HH:MM format (e.g., 10:30).");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter time in HH:MM format (e.g., 10:30).");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    public static String getValidCourseNameInput() {
        String input;
        while (true) {
            try {
                input = scanner.nextLine();
                if (isAlphaSpace(input)) {
                    return input;
                } else {
                    System.out.println("Invalid input. Course name must contain only letters and spaces.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid course name.");
                scanner.nextLine();
            }
        }
    }

    public static String getValidExaminerInput() {
        String input;
        while (true) {
            try {
                input = scanner.nextLine();
                if (isAlphaSpace(input)) {
                    return input;
                } else {
                    System.out.println("Invalid input. Examiner name must contain only letters and spaces.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid examiner name.");
                scanner.nextLine();
            }
        }
    }

    public static String getValidDepartmentInput() {
        String input;
        while (true) {
            try {
                input = scanner.nextLine();
                if (isAlphaSpace(input)) {
                    return input;
                } else {
                    System.out.println("Invalid input. Department name must contain only letters and spaces.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid department name.");
                scanner.nextLine();
            }
        }
    }

    public static String getValidDateInput() {
        String input;
        while (true) {
            try {
                input = scanner.nextLine();
                if (input.length() == 10 && input.charAt(4) == '-' && input.charAt(7) == '-') {
                    String yearPart = input.substring(0, 4);
                    String monthPart = input.substring(5, 7);
                    String dayPart = input.substring(8, 10);

                    // Check if year, month, and day are numeric
                    if (isNumeric(yearPart) && isNumeric(monthPart) && isNumeric(dayPart)) {
                        int year = Integer.parseInt(yearPart);
                        int month = Integer.parseInt(monthPart);
                        int day = Integer.parseInt(dayPart);

                        if (year >= 1950 && year <= 2050 && month >= 1 && month <= 12 && day >= 1 && day <= 31) {
                            return input;
                        } else {
                            System.out.println("Invalid date. Please enter a valid date in YYYY-MM-DD format.");
                        }
                    } else {
                        System.out.println("Invalid date format. Please enter in YYYY-MM-DD format.");
                    }
                } else {
                    System.out.println("Invalid date format. Please enter in YYYY-MM-DD format.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter date in YYYY-MM-DD format.");
                scanner.nextLine();
            }
        }
    }

    private static boolean isAlphaSpace(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}


// Main class for the exam management system
public class Democlass {
  static ArrayList<Exam> exams = new ArrayList<>();

  public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      int choice = 0;

      do {
          try {
              displayMenu();
              choice = scanner.nextInt();
              scanner.nextLine();

              switch (choice) {
                  case 1:
                      AddClass.addExam();
                      break;
                  case 2:
                  displayAndEditClass.displayExam();
                      break;
                  case 3:
                  displayAndEditClass.editExam();
                      break;
                  case 4:
                      System.out.println("Exiting...");
                      break;
                  default:
                      throw new Exception("Invalid menu choice. Please enter a number between 1 and 4.");
              }
          } catch (InputMismatchException e) {
              System.out.println("Invalid input. Please enter a number.");
              scanner.nextLine();
          } catch (Exception e) {
              System.out.println(e.getMessage());
          }
      } while (choice != 4);
      scanner.close();
  }

  static void displayMenu() {
      System.out.print("*********************************");
      System.out.println("\nExam Timetable Management System");
      System.out.println("1. Add Exam");
      System.out.println("2. Display Timetable");
      System.out.println("3. Edit Exam");
      System.out.println("4. Exit");
      System.out.println("*********************************");
      System.out.print("Enter your choice: ");

      System.out.println("2. Display Timetable");
      System.out.println("3. Edit Exam");
      System.out.println("4. Exit");
      System.out.println("*********************************");
      System.out.print("Enter your choice: ");
  }
}












     
     
    


     