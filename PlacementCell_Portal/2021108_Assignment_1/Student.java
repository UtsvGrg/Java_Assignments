import java.util.*;

public class Student{
    LinkedList<Company> appliedCompanies = new LinkedList<Company>();
    LinkedList<Company> offeredCompanies = new LinkedList<Company>();
    static Scanner scan = new Scanner(System.in);
    static Scanner scanName = new Scanner(System.in);
    String name;
    String rollNo;
    double CGPA;
    String branch;
    String status;
    double salary;
    Company finalCompany;
    DateTime registerDateTime;

    Student(String name,String rollNo, double CGPA, String branch){
        this.name = name;
        this.rollNo = rollNo;
        this.CGPA = CGPA;
        this.branch = branch;
        this.status = "Not-Applied";
        this.salary = 0;
    }

    public void getAvailableCompanies(PlacementCell pCell){
        for(Company company: pCell.registeredCompanies){
            System.out.print(company.name+" | "+company.roleOffered+" | "+company.packageOffered+" | "+company.requiredCGPA);
            if (this.CGPA >= company.requiredCGPA){
                System.out.println("-AVAILABLE");
            }
            else{
                System.out.println("-NOT AVAILABLE");
            }
        }
    }

    public void registerSelf(PlacementCell pCell){
        pCell.registeredStudents.push(this);
        System.out.println("Student Registered at the placement drive, exiting to operations page");
        this.chooseStudent(pCell);
    }

    public void getStatus(){
        System.out.println("Your Current Status: "+this.status);
    }

    public void requestGradeChange(PlacementCell pCell){
        double newCGPA;
        System.out.print("Type in the new CGPA for request: ");
        newCGPA = scan.nextDouble();
        pCell.updateCGPA(this,newCGPA);
        System.out.println("The CGPA of the student has been successfully updated, exiting to operations");
    }

    public void offerResponse(PlacementCell pCell, int response){
        int Idx=0;
        if(response==1){
            if (this.status == "Offered"){
                for(Company company: this.offeredCompanies){
                    System.out.println((++Idx)+") "+company.name);
                }
                System.out.print("Type the company number:");
                Idx = scan.nextInt();
                this.finalCompany = this.offeredCompanies.get(Idx-1);
                System.out.println("Congratulation!!, You have accepted the offer by "+this.finalCompany.name);
                finalCompany.companySelectedStudents.push(this);
                this.salary = finalCompany.packageOffered;
                this.status = "Placed";
                System.out.println("Exiting to operations page");
            }
            else{
                System.out.println("You have not been offered from any company, exiting to the operations page");
            }
        }
        else{
            if (this.offeredCompanies.size() == 0){
                System.out.println("You have not been offered from any company, exiting to the operations page");
                this.chooseStudent(pCell);
            }
            else if (this.offeredCompanies.size() == 1){
                System.out.println("1) "+this.offeredCompanies.get(0).name);
                System.out.print("Do you wish to reject the offer, press 1: ");
                Idx = scan.nextInt();
                this.offeredCompanies.remove(Idx-1);
                System.out.println("You have rejected the offer");
                System.out.println("As you have rejected all the available offers, you have been blocked by the Placement Cell");
                System.out.println("Exiting to operations page");
                this.status = "Blocked";
                this.chooseStudent(pCell);
            }
            else{
                Idx = 0;
                for(Company company: this.offeredCompanies){
                    System.out.println((++Idx)+") "+company.name);
                }
                System.out.print("Type the company number:");
                Idx = scan.nextInt();
                this.offeredCompanies.remove(Idx-1);
                System.out.println("You have rejected the offer");
                System.out.println("Exiting to operations page");
            }
        }
    }

    public void chooseStudent(PlacementCell pCell){
        int Index;
        String tempString;
        // double tempDouble;
        System.out.println("__________________________");
        System.out.println("Welcome "+ this.name);
        System.out.println("1) Register for Placement Drive");
        System.out.println("2) Register for Company");
        System.out.println("3) Get all available companies");
        System.out.println("4) Get current status");
        System.out.println("5) Update CGPA");
        System.out.println("6) Accept Offer");
        System.out.println("7) Reject Offer");
        System.out.println("8) Back");
        System.out.print("Choose the required operation number: ");
        Index = scan.nextInt();
        switch(Index){
            case 1: 
                String tempDate, tempTime;
                System.out.println("Enter registration date in format DD/MM/YY");
                tempDate= scanName.nextLine();
                System.out.println("Enter registration start time in format HH:MM");
                tempTime= scanName.nextLine();
                this.registerDateTime = new DateTime(Integer.parseInt(tempDate.substring(0,2)),Integer.parseInt(tempDate.substring(3,5)),Integer.parseInt(tempDate.substring(6,8)),Integer.parseInt(tempTime.substring(0,2)), Integer.parseInt(tempTime.substring(3,5)));
                if(this.registerDateTime.dateCheck(this.registerDateTime,pCell.startStudent) ==1 && this.registerDateTime.dateCheck(this.registerDateTime,pCell.endStudent) == 2){
                    registerSelf(pCell);
                    System.out.println("Student Registered, exiting to operations page");
                }
                else{
                    System.out.println("Sorry the registration could not be completed");
                }
                break; 

            case 2:
                System.out.print("Type in the name of company you wish to apply: ");
                tempString = scan.next();
                if (pCell.registeredCompanies.size() == 0){
                    System.out.println("No companies registered at the moment");
                }
                else{
                    for(Company company: pCell.registeredCompanies){
                        if (tempString.equals(company.name) && (double)(company.requiredCGPA) <= (double)(this.CGPA) && company.packageOffered >= this.salary){
                            this.appliedCompanies.push(company);
                            company.companyRegisteredStudents.push(this);
                            System.out.println("Successfully registered at "+company.name+" for "+company.roleOffered+" Role");
                            this.status = "Not-Offered";
                            this.chooseStudent(pCell);
                            break;
                        }
                    }
                    System.out.println("Cannot register");
                }
                this.chooseStudent(pCell);
                break;


            case 3:
                this.getAvailableCompanies(pCell);
                this.chooseStudent(pCell);
                break;

            case 4:
                this.getStatus();
                this.chooseStudent(pCell);
                break;

            case 5:
                this.requestGradeChange(pCell);
                this.chooseStudent(pCell);
                break;

            case 6:
                this.offerResponse(pCell,1);
                this.chooseStudent(pCell);
                break;

            case 7:
                this.offerResponse(pCell,0);
                this.chooseStudent(pCell);
                break;
                
            case 8:
                // this.chooseStudent(pCell);
                break;      
            }
    }
}