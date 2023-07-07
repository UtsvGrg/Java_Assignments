import java.util.*;

public class Portal {
    PlacementCell pCell;
    LinkedList<Company> allCompanies = new LinkedList<Company>();
    LinkedList<Student> allStudents = new LinkedList<Student>();
    Portal(){
        this.pCell = new PlacementCell(); //Composition
    }

    public void studentMode(PlacementCell pCell){
        int tempInt;
        String tempname;
        String temproll;
        String tempbranch;
        double tempcgpa;

        System.out.println("__________________");
        System.out.println("Choose the student query to perform");
        System.out.println("1) Enter as a student (Give Student Name, Roll No.)");
        System.out.println("2) Add Student, one at a time");
        System.out.println("3) Back");
        tempInt = scan.nextInt();
        switch(tempInt){
            case 1: 
                Student tempStudent;
                System.out.print("Enter student name: ");
                tempname = scanName.nextLine();
                System.out.print("Enter roll number: ");
                temproll = scanName.nextLine();
                for(Student student: this.allStudents){
                    // System.out.println(student.rollNo);
                    if (Integer.parseInt(student.rollNo) == Integer.parseInt(temproll)){
                        tempStudent = student;
                        tempStudent.chooseStudent(pCell);
                        break;
                    }
                }
                this.studentMode(pCell);
            case 2:
                System.out.println("Enter the following data:");
                System.out.print("Name of the student: ");
                tempname = scanName.nextLine();
                System.out.print("Roll number of the student: ");
                temproll = scanName.nextLine();
                System.out.print("CGPA of the student: ");
                tempcgpa = scan.nextDouble();
                System.out.print("Branch of the student: ");
                tempbranch = scan.next();
                Student student = new Student(tempname,temproll,tempcgpa,tempbranch);
                this.allStudents.push(student);
                this.studentMode(pCell);
                break;
            case 3:
                this.chooseMode();
                break;

        }
    }
    public void companyMode(PlacementCell pCell){
        int tempInt;
        String tempname;
        String temprole;
        double tempctc;
        double tempcgpa;

        System.out.println("__________________");
        System.out.println("Choose the company query to perform");
        System.out.println("1) Add Company and Details");
        System.out.println("2) Choose Company");
        System.out.println("3) Get Available Companies");
        System.out.println("4) Back");
        tempInt = scan.nextInt();
        switch(tempInt){
            case 1: 
                System.out.println("Enter the following data:");
                System.out.print("Name of the company: ");
                tempname = scanName.nextLine();
                System.out.print("Role requirement of the company: ");
                temprole = scanName.nextLine();
                System.out.print("CTC offered by the company: ");
                tempctc = scan.nextDouble();
                System.out.print("Minimum CGPA requirement: ");
                tempcgpa = scan.nextDouble();
                Company company = new Company(tempname,temprole,tempctc,tempcgpa);
                this.allCompanies.push(company);
                this.companyMode(pCell);
                break;

            case 2:
                System.out.println("Choose to enter into mode of Available Companies");
                int Index = 0;
                for(Company tempCompany: this.allCompanies){
                    System.out.println((++Index)+") "+tempCompany.name);
                }
                System.out.print("Type in the required company number: ");
                Index = scan.nextInt();
                Company tempCompany = this.allCompanies.get(Index-1);
                System.out.println(tempCompany.name);
                tempCompany.chooseCompany(pCell);
                this.companyMode(pCell);
                break;

            case 3: 
                System.out.println("Following are the Available Companies");
                Index = 0;
                for(Company temCompany: this.allCompanies){
                    System.out.println((++Index)+") Name:"+temCompany.name+" | Role:"+temCompany.roleOffered+" | CTC:"+temCompany.packageOffered);
                }
                this.companyMode(pCell);
                break;

            case 4:
                this.chooseMode();
                break;
        }
    }
    public void placementCellMode(){
        int tempInt;
        // String tempString;
        System.out.println("_______________________________\n");
        System.out.println("Welcome to IIITD Placement Cell");
        System.out.println("1)  Open Student Registration");
        System.out.println("2)  Open Company Registration");
        System.out.println("3)  Get Number of Student Registrations");
        System.out.println("4)  Get Number of Company Registrations");
        System.out.println("5)  Get Number of Offered/Unoffered/Blocked Students");
        System.out.println("6)  Get Student Details");
        System.out.println("7)  Get Company Details");
        System.out.println("8)  Get Average Package");
        System.out.println("9)  Get Company Results");
        System.out.println("10) Back\n");
        tempInt = scan.nextInt();
        switch(tempInt){
            case 1:
                this.pCell.studentRegistration();
                placementCellMode();
                break;

            case 2:
                this.pCell.companyRegistration();
                placementCellMode();
                break;

            case 3:
                this.pCell.numberStudentRegistration();
                placementCellMode();
                break;
                    
            case 4:
                this.pCell.numberCompanyRegistration();
                placementCellMode();
                break;

            case 5:
                this.pCell.studentStatus();
                placementCellMode();
                break;

            case 6:
                Student tempStudent;
                String tempname;
                String temproll;
                System.out.print("Enter student name: ");
                tempname = scanName.nextLine();
                System.out.print("Enter roll number: ");
                temproll = scanName.nextLine();
                for(Student student: this.allStudents){
                    // System.out.println(student.rollNo);
                    if (Integer.parseInt(student.rollNo) == Integer.parseInt(temproll)){
                        tempStudent = student;
                        this.pCell.getStudentDetails(tempStudent);
                        break;
                    }
                }
                placementCellMode();
                break;
            

            case 7:
                Company tempCompany;
                System.out.print("Enter company name: ");
                tempname = scanName.nextLine();
                for(Company company: this.allCompanies){
                    // System.out.println(student.rollNo);
                    if (company.name.equals(tempname)){
                        tempCompany = company;
                        this.pCell.getCompanyDetails(tempCompany);
                        placementCellMode();
                        break;
                    }
                }
            
            case 8:
                this.pCell.getAveragePackage();
                placementCellMode();
                break;

            case 9:
                // Result Declaration Start
                System.out.print("Enter company name: ");
                tempname = scanName.nextLine();
                for(Company company: this.allCompanies){
                    if (company.name.equals(tempname)){
                        company.studentSelection(); // Main linking function
                        this.pCell.offeredResult(company);
                        // this.pCell.companyResult(company);
                        placementCellMode();
                        break;
                    }
                }
            
            case 10:
                this.chooseMode();
                break;
        }
    }

    public void chooseMode(){ //Check for Placement Cell pCell
        int tempInt;
        System.out.println("\nChoose mode to further continue\n");
        System.out.println("1) Enter as Student Mode");
        System.out.println("2) Enter as Company Mode");
        System.out.println("3) Enter as Placement Cell Mode");
        System.out.println("4) Return to Main Application\n");
        System.out.print("Type in command: ");
        tempInt = scan.nextInt();
        switch(tempInt){
            case 1:
                System.out.println("Entering as student");
                this.studentMode(pCell);
                break;
            case 2:
                System.out.println("Entering as company");
                this.companyMode(pCell);
                break;
            case 3:
                System.out.println("Entering as placement cell");
                this.placementCellMode();
                break;
            case 4:
                System.out.println("Exiting to Application Page");
                this.applicationEnter();
                break;
        }
    }

    public void applicationEnter(){
        int applicationOption = 0;
        // int tempInt;
        System.out.println("\n____________________________________\n");
        System.out.println("Welcome to FutureBuild Portal\n");
        System.out.println("1) Enter the Application");
        System.out.println("2) Exit the Application\n");
        System.out.print("Type in command: ");
        
        applicationOption = scan.nextInt();
        if(applicationOption==1){
            this.chooseMode();
        }
        else{
            System.out.println("Exiting the Portal");
            System.out.println("Thanks for using FutureBuilder!");
            System.exit(0);
        }
    }

    static Scanner scan = new Scanner(System.in);
    static Scanner scanName = new Scanner(System.in);

    public static void main(String[] args) {
        Portal portal = new Portal();
        portal.applicationEnter();
    }
}
