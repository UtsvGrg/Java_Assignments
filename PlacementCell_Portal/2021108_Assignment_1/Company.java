import java.util.*;

public class Company{
    static Scanner scan = new Scanner(System.in);
    static Scanner scanName = new Scanner(System.in);
    DateTime registerDateTime;
    String name;
    String roleOffered;
    double packageOffered;
    double requiredCGPA;
    LinkedList<Student> companyRegisteredStudents = new LinkedList<Student>();
    LinkedList<Student> companyOfferedStudents = new LinkedList<Student>();
    LinkedList<Student> companySelectedStudents = new LinkedList<Student>();

    
    Company(String name, String roleOffered, double packageOffered, double requiredCGPA){
        this.name = name;
        this.roleOffered = roleOffered;
        this.packageOffered = packageOffered;
        this.requiredCGPA = requiredCGPA;
    }

    public void studentSelection(){
        for(Student student: companyRegisteredStudents){
            // add random function if time
            if (student.offeredCompanies.contains(this)){
                continue;
            }
            else{
            student.status = "Offered";
            student.offeredCompanies.push(this);
            this.companyOfferedStudents.push(student);
            }
        }

    }

    public void updateRole(String newRole){
        this.roleOffered = newRole;
    }

    public void updateCGPA(double newCGPA){
        this.requiredCGPA = newCGPA;
    }

    public void updatePackage(double newPackage){
        this.packageOffered = newPackage;
    }

    public void chooseCompany(PlacementCell pCell){
        int Index;
        String tempString;
        double tempDouble;
        System.out.println("Welcome "+this.name);
        System.out.println("1) Update Role");
        System.out.println("2) Update Package");
        System.out.println("3) Update CGPA Criteria");
        System.out.println("4) Register Company at Cell");
        System.out.println("5) Back");
        System.out.print("Choose the required operation number: ");
        Index = scan.nextInt();
        switch(Index){
            case 1: 
                System.out.print("Type in the New Role: ");
                tempString = scanName.nextLine();
                this.updateRole(tempString);
                System.out.println("Updation Complete, exiting to operations page");
                this.chooseCompany(pCell);
                break;
            case 2:
                System.out.print("Type in the New Package: ");
                tempDouble= scan.nextDouble();
                this.updatePackage(tempDouble);
                System.out.println("Updation Complete, exiting to operations page");
                this.chooseCompany(pCell);
                break;
            case 3:
                System.out.print("Type in the Updated CGPA: ");
                tempDouble= scan.nextDouble();
                this.updateCGPA(tempDouble);
                System.out.println("Updation Complete, exiting to operations page");
                this.chooseCompany(pCell);
                break;
            case 4:
                String tempDate, tempTime;
                System.out.println("Enter registration date in format DD/MM/YY");
                tempDate= scanName.nextLine();
                System.out.println("Enter registration start time in format HH:MM");
                tempTime= scanName.nextLine();
                this.registerDateTime = new DateTime(Integer.parseInt(tempDate.substring(0,2)),Integer.parseInt(tempDate.substring(3,5)),Integer.parseInt(tempDate.substring(6,8)),Integer.parseInt(tempTime.substring(0,2)), Integer.parseInt(tempTime.substring(3,5)));
                if(this.registerDateTime.dateCheck(this.registerDateTime,pCell.startCompany) ==1 && this.registerDateTime.dateCheck(this.registerDateTime,pCell.endCompany) == 2){
                    pCell.registeredCompanies.push(this);
                    System.out.println("Company Registered, exiting to operations page");
                }
                else{
                    System.out.println("Sorry the registration could not be completed");
                    System.out.println("Exiting to operations page");
                    System.out.println("____________________________");

                }
                this.chooseCompany(pCell);
                break; 
            case 5:
                break;
        }
    }

}