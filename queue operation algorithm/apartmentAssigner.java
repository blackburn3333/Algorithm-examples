//Author Jayendra Matarage
import java.util.Scanner;

public class apartmentAssigner {
    static systemOperations sysOps = new systemOperations();
    static system_settings sysSettings = new system_settings();

    public static void main(String[] args) {
        Scanner insertChoice = new Scanner(System.in);
        sysSettings.screenClear();
        System.out.println("|---------------------------------------------------------|");
        System.out.println("|        London Met University Apartment Assigner         |");
        System.out.println("|---------------------------------------------------------|");
        System.out.println("| Insert Students                > 1                      |");
        System.out.println("| View Students                  > 2                      |");
        System.out.println("| Insert Apartments              > 3                      |");
        System.out.println("| View Apartments                > 4                      |");
        System.out.println("| Assign Apartments              > 5                      |");
        System.out.println("| View Assigned Apartments       > 6                      |");
        System.out.println("| Exit                           > 0                      |");
        System.out.println("|---------------------------------------------------------|");
        System.out.print("| Enter your choice         > ");
        String start = insertChoice.nextLine();
        while (true) {
            switch (start) {
                case "0":
                    sysSettings.system_exit();
                case "1":
                    sysSettings.screenClear();
                    sysOps.add_student();
                case "2":
                    sysSettings.screenClear();
                    sysOps.viewStudents();
                case "3":
                    sysSettings.screenClear();
                    sysOps.add_apartments();
                case "4":
                    sysSettings.screenClear();
                    sysOps.viewApartments();
                case "5":
                    sysSettings.screenClear();
                    sysOps.assign_apartments();
                case "6":
                    sysSettings.screenClear();
                    sysOps.view_assigned_apartments();
                default:
                    main(args);
            }
        }
    }
}


class systemOperations {

    private system_settings sysSettings = new system_settings();
    private student_operations stdOps = new student_operations();
    private apartment_operations apsOps = new apartment_operations();
    private assigned_aps_operations assignApsOps = new assigned_aps_operations();
    private apartmentAssigner main = new apartmentAssigner();

    void assign_apartments() {
        Scanner assignScanner = new Scanner(System.in);
        sysSettings.screenClear();
        System.out.println("|---------------------------------------------------------|");
        System.out.println("|        London Met University Apartment Assigner         |");
        System.out.println("|                   Assign Apartments                     |");
        System.out.println("|---------------------------------------------------------|");
        try {
            if (stdOps != null && apsOps != null) {
                student_operations stuOpsTemp = new student_operations();
                apartment_operations apsOpsTemp = new apartment_operations();

                while (stdOps.studentNode != null && apsOps.apartmentNode != null) {

                    if (apsOps.apartmentNode.apartment_quality >= stdOps.studentNode.student_required_quality) {

                        System.out.println("| Apartment ID -> " + apsOps.apartmentNode.apartment_id + " Apartment Name -> " + apsOps.apartmentNode.apartment_name);
                        System.out.println("| Student ID -> " + stdOps.studentNode.student_id + " Student Name -> " + stdOps.studentNode.student_name);
                        System.out.println("| Apartment Quality -> " + apsOps.apartmentNode.apartment_quality + " Student Requested Quality -> " + stdOps.studentNode.student_required_quality);

                        System.out.println("|---------------------------------------------------------|");
                        System.out.println("| Assign apartment " + apsOps.apartmentNode.apartment_name + " to " + stdOps.studentNode.student_name);
                        System.out.print("| (Y)es/(N)o ? ");
                        String assignChoice = assignScanner.next();
                        System.out.println("|---------------------------------------------------------|");
                        if (assignChoice.equals("Y") || assignChoice.equals("y")) {
                            assignApsOps.assign_apartments_enque(apsOps.apartmentNode.apartment_id, stdOps.studentNode.student_id, apsOps.apartmentNode.apartment_quality, stdOps.studentNode.student_required_quality, apsOps.apartmentNode.apartment_name, stdOps.studentNode.student_name);
                            stdOps.stu_deque(stdOps.studentNode);
                            apsOps.aps_deque(apsOps.apartmentNode);
                        } else {
                            stdOps.student_enque(stdOps.generate_std_id(), stdOps.studentNode.student_required_quality, stdOps.studentNode.student_name);
                            stdOps.stu_deque(stdOps.studentNode);
                            apsOpsTemp.apartment_enque(apsOps.apartmentNode.apartment_id, apsOps.apartmentNode.apartment_quality);
                        }
                    } else {
                        apsOpsTemp.apartment_enque(apsOps.apartmentNode.apartment_id, apsOps.apartmentNode.apartment_quality);
                        stuOpsTemp.student_enque(stdOps.studentNode.student_id, stdOps.studentNode.student_required_quality, stdOps.studentNode.student_name);
                    }
                    stdOps.studentNode = stdOps.studentNode.next;
                    apsOps.apartmentNode = apsOps.apartmentNode.next;
                }
                while (apsOpsTemp.apartmentNode != null) {
                    apsOps.apartment_enque(apsOpsTemp.apartmentNode.apartment_id, apsOpsTemp.apartmentNode.apartment_quality);
                    apsOpsTemp.apartmentNode = apsOpsTemp.apartmentNode.next;
                }
                while (stuOpsTemp.studentNode != null) {
                    stdOps.student_enque(stuOpsTemp.studentNode.student_id, stuOpsTemp.studentNode.student_required_quality, stuOpsTemp.studentNode.student_name);
                    stuOpsTemp.studentNode = stuOpsTemp.studentNode.next;
                }
            }
        } catch (Exception ex) {
            System.out.println("assign apartments exceptions -> " + ex);
        }
    }

    //view Assigned apartments
    void view_assigned_apartments() {
        sysSettings.screenClear();
        String viewAgain = "N";
        Scanner assedApsScanner = new Scanner(System.in);
        while (viewAgain.equals("N") || viewAgain.equals("n")) {
            sysSettings.screenClear();
            System.out.println("|---------------------------------------------------------|");
            System.out.println("|        London Met University Apartment Assigner         |");
            System.out.println("|              Assigned apartments in system              |");
            System.out.println("|---------------------------------------------------------|");
            assignApsOps.view_assigned_apps();
            System.out.print("| Go to home? (Y)es,(N)o > ");
            viewAgain = assedApsScanner.next();
        }
        String[] args = new String[0];
        main.main(args);
    }

    void add_apartments() {
        String nextApartment = "y";
        Scanner apsScanner = new Scanner(System.in);
        while (nextApartment.equals("Y") || nextApartment.equals("y")) {
            sysSettings.screenClear();
            System.out.println("|---------------------------------------------------------|");
            System.out.println("|        London Met University Apartment Assigner         |");
            System.out.println("|                     Enter Apartments                    |");
            System.out.println("|---------------------------------------------------------|");

            int apsID = apsOps.generate_aps_id();
            System.out.println("| Apartment ID > " + apsID);
            System.out.print("| Enter apartment quality > ");
            int apsQuality = apsScanner.nextInt();
            while (apsQuality < 1 || apsQuality > 100) {
                System.out.print("| Invalid quality, acceptable quality range 1-100 > ");
                apsQuality = apsScanner.nextInt();
            }

            System.out.println("|---------------------------------------------------------|");
            System.out.println("|              Apartment successfully added               |");
            System.out.println("|---------------------------------------------------------|");
            apsOps.apartment_enque(apsID, apsQuality);
            System.out.print("| Enter new apartment? (Y)es,(N)o > ");
            nextApartment = apsScanner.next();
        }
        String[] args = new String[0];
        main.main(args);
    }

    void viewApartments() {
        String viewAgain = "N";
        Scanner apsScanner = new Scanner(System.in);
        while (viewAgain.equals("N") || viewAgain.equals("n")) {
            sysSettings.screenClear();
            System.out.println("|---------------------------------------------------------|");
            System.out.println("|        London Met University Apartment Assigner         |");
            System.out.println("|                    Apartments in system                 |");
            System.out.println("|---------------------------------------------------------|");
            apsOps.apartment_display();
            System.out.print("| Go to home? (Y)es,(N)o > ");
            viewAgain = apsScanner.next();
        }
        String[] args = new String[0];
        main.main(args);
    }

    void add_student() {
        String nextStudent = "Y";
        Scanner stuScanner = new Scanner(System.in);

        while (nextStudent.equals("Y") || nextStudent.equals("y")) {
            sysSettings.screenClear();
            System.out.println("|---------------------------------------------------------|");
            System.out.println("|        London Met University Apartment Assigner         |");
            System.out.println("|                     Enter Students                      |");
            System.out.println("|---------------------------------------------------------|");

            int stuID = stdOps.generate_std_id();
            System.out.println("| Student ID > " + stuID);
            System.out.print("| Enter student name > ");
            String stdName = stuScanner.next();
            System.out.print("| Enter requested quality > ");
            int reqQuality = stuScanner.nextInt();
            while (reqQuality < 1 || reqQuality > 100) {
                System.out.print("| Invalid requested quality, acceptable quality range 1-100 > ");
                reqQuality = stuScanner.nextInt();
            }

            System.out.println("|---------------------------------------------------------|");
            System.out.println("|            Student registration successful!             |");
            System.out.println("|---------------------------------------------------------|");
            stdOps.student_enque(stuID, reqQuality, stdName);
            System.out.print("| Enter new student? (Y)es,(N)o > ");
            nextStudent = stuScanner.next();
        }

        String[] args = new String[0];
        main.main(args);
    }



    void viewStudents() {
        String viewAgain = "N";
        Scanner stuScanner = new Scanner(System.in);
        while (viewAgain.equals("n") || viewAgain.equals("N")) {
            sysSettings.screenClear();
            System.out.println("|---------------------------------------------------------|");
            System.out.println("|        London Met University Apartment Assigner         |");
            System.out.println("|                   Students in system!                   |");
            System.out.println("|---------------------------------------------------------|");
            stdOps.student_display();
            System.out.print("| Go to home? (Y)es,(N)o > ");
            viewAgain = stuScanner.next();
        }
        String[] args = new String[0];
        main.main(args);
    }
}


class student_operations {
    student_node studentNode;

    //add students to node
    void student_enque(int std_id, int req_quality, String name) {
        student_node stuTemp;
        stuTemp = studentNode;
        student_node newStudentNode = new student_node(std_id, req_quality, name);
        if (studentNode == null) {
            studentNode = newStudentNode;
        } else {
            while (stuTemp.next != null) {
                stuTemp = stuTemp.next;
            }
            stuTemp.next = newStudentNode;
            stuTemp.next.next = null;
        }
    }

    //view students in node
    void student_display() {
        student_node stuTemp = studentNode;
        if (stuTemp == null) {
            System.out.println("|                 No students in system!                  |");
            System.out.println("|---------------------------------------------------------|");
        } else {
            while (stuTemp != null) {
                System.out.println("| Student ID > " + stuTemp.student_id);
                System.out.println("| Student Name > " + stuTemp.student_name);
                System.out.println("| Student Requested Quality > " + stuTemp.student_required_quality);
                stuTemp = stuTemp.next;
                System.out.println("|---------------------------------------------------------|");
            }
        }
    }

    void stu_deque() {
        student_node stuNodeTemp = studentNode;
        if (studentNode != null) {
            studentNode = stuNodeTemp.next;
        }
    }

    void stu_deque(student_node deque_item) {
        student_node stuNodeTemp = studentNode;
        while (stuNodeTemp != deque_item) {
            stuNodeTemp = stuNodeTemp.next;
        }
        deque_item = deque_item.next;
    }

    //generate student ID
    public int generate_std_id() {
        student_node stuTemp = studentNode;
        if (studentNode == null) {
            return 1;
        } else {
            while (stuTemp.next != null) {
                stuTemp = stuTemp.next;
            }
            return stuTemp.student_id + 1;
        }
    }
}


class apartment_operations {
    apartment_node apartmentNode;

    //add apartment to node
    void apartment_enque(int aps_id, int aps_quality) {
        apartment_node apsTemp;
        apsTemp = apartmentNode;
        apartment_node newApartmentNode = new apartment_node(aps_id, aps_quality);
        if (apartmentNode == null) {
            apartmentNode = newApartmentNode;
        } else {
            while (apsTemp.next != null) {
                apsTemp = apsTemp.next;
            }
            apsTemp.next = newApartmentNode;
            apsTemp.next.next = null;
        }
    }

    //view apartments in node
    void apartment_display() {
        apartment_node apsTemp = apartmentNode;
        if (apsTemp == null) {
            System.out.println("|                No apartments in system!                 |");
            System.out.println("|---------------------------------------------------------|");
        } else {
            while (apsTemp != null) {
                System.out.println("| Apartment ID > " + apsTemp.apartment_id);
                System.out.println("| Apartment Name > " + apsTemp.apartment_name);
                System.out.println("| Apartment Quality > " + apsTemp.apartment_quality);
                apsTemp = apsTemp.next;
                System.out.println("|---------------------------------------------------------|");
            }
        }
    }

    //remove first element from apartment queue
    void aps_deque() {
        apartment_node apsNodeTemp;
        apsNodeTemp = apartmentNode;
        if (apartmentNode != null) {
            apartmentNode = apartmentNode.next;
        }
    }

    void aps_deque(apartment_node dequeItem) {
        apartment_node apsNodeTemp = apartmentNode;
        while (apsNodeTemp != dequeItem) {
            apsNodeTemp = apsNodeTemp.next;
        }
        dequeItem = dequeItem.next;
    }

    //generate apartment ID
    public int generate_aps_id() {
        apartment_node apsTemp = apartmentNode;
        if (apartmentNode == null) {
            return 1;
        } else {
            while (apsTemp.next != null) {
                apsTemp = apsTemp.next;
            }
            return apsTemp.apartment_id + 1;
        }
    }
}

class assigned_aps_operations {

    assigned_aps_node assignedApsNode;

    void assign_apartments_enque(int aps_id, int stu_id, int aps_quality, int stu_quality, String aps_name, String stu_name) {
        assigned_aps_node newAssignNode = new assigned_aps_node(stu_id, aps_id, stu_name, aps_name, stu_quality, aps_quality);
        if (assignedApsNode == null) {
            assignedApsNode = newAssignNode;
            assignedApsNode.next = null;
        } else {
            assigned_aps_node assignApsNodeTemp = assignedApsNode;
            while (assignApsNodeTemp.next != null) {
                assignApsNodeTemp = assignApsNodeTemp.next;
            }
            assignApsNodeTemp.next = newAssignNode;
            newAssignNode.next = null;
        }
    }

    void view_assigned_apps() {
        assigned_aps_node assignApsNodeTemp = assignedApsNode;
        if (assignApsNodeTemp == null) {
            System.out.println("|       There is no assigned apartments in system!        |");
            System.out.println("|---------------------------------------------------------|");
        } else {
            while (assignApsNodeTemp != null) {
                System.out.println("| Student Name > " + assignApsNodeTemp.stu_name);
                System.out.println("| Apartment Name > " + assignApsNodeTemp.aps_name);
                System.out.println("| Student Requested Quality > " + assignApsNodeTemp.req_quality);
                System.out.println("| Apartment Quality > " + assignApsNodeTemp.aps_quality);
                assignApsNodeTemp = assignApsNodeTemp.next;
                System.out.println("|---------------------------------------------------------|");
            }
        }
    }
}

//student node
class student_node {
    int student_id;
    int student_required_quality;
    String student_name;
    student_node next;

    student_node(int id, int req_quality, String name) {
        this.student_id = id;
        this.student_required_quality = req_quality;
        this.student_name = name;
    }
}

//apartment node
class apartment_node {
    int apartment_id;
    int apartment_quality;
    String apartment_name;
    apartment_node next;

    apartment_node(int id, int aps_qulaity) {
        this.apartment_id = id;
        this.apartment_quality = aps_qulaity;
        this.apartment_name = "Apartment No " + id;
    }
}

//apartment_assigned_node
class assigned_aps_node {


    int stu_id;
    String stu_name;
    int req_quality;

    int aps_id;
    String aps_name;
    int aps_quality;

    assigned_aps_node next;

    assigned_aps_node(int stu_id, int aps_id, String stu_name, String aps_name, int req_quality, int aps_quality) {
        this.aps_id = aps_id;
        this.stu_id = stu_id;
        this.aps_name = aps_name;
        this.stu_name = stu_name;
        this.req_quality = req_quality;
        this.aps_quality = aps_quality;
    }
}

//settings of system
class system_settings {
    private apartmentAssigner main = new apartmentAssigner();

    public void screenClear() {
        String osPlatform = System.getProperty("os.name").toLowerCase();
        try {
            if (osPlatform.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else if (osPlatform.contains("osx") || osPlatform.contains("mac")) {
                System.out.print("\033[H\033[2J");
            } else if (osPlatform.contains("nix") || osPlatform.contains("aix") || osPlatform.contains("nux")) {
                System.out.print("\033[H\033[2J");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (Exception ex) {
            System.out.println("screen clear error -> " + ex);
        }

    }

    public void system_exit() {
        try {
            Scanner insertChoice = new Scanner(System.in);
            while (true) {
                System.out.print("| Are you sure(Y)es, (N)o ? > ");
                String exit_choice = insertChoice.nextLine();
                if (exit_choice.equals("Y") || exit_choice.equals("y")) {
                    System.exit(0);
                } else if (exit_choice.equals("N") || exit_choice.equals("n")) {
                    String[] args = new String[0];
                    main.main(args);
                }
            }
        } catch (Exception ex) {
            System.out.println("Exceptions on exit -> " + ex);
        }
    }
}