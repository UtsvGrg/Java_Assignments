import java.util.*;

class DateTime{
    int day;
    int month;
    int year;
    int hour;
    int minute;

    DateTime(int day, int month, int year, int hour, int minute){
        this.day = day;
        this.month = month;
        this.year = year;
        this.minute = minute;
        this.hour = hour;
    }
    public int dateCheck(DateTime dt1, DateTime dt2){
        int latest=1;
        if ((int)dt1.year > (int)dt2.year){
            latest = 1;
        }
        else if ((int)dt1.year < (int)dt2.year){
            latest = 2;
        }
        else{
            if ((int)dt1.month > (int)dt2.month){
                latest = 1;
            }
            else if ((int)dt1.month < (int)dt2.month){
                latest = 2;
            }
            else{
                if ((int)dt1.day > (int)dt2.day){
                    latest = 1;
                }
                else if ((int)dt1.day < (int)dt2.day){
                    latest = 2;
                }
                else{
                    if ((int)dt1.hour > (int)dt2.hour){
                        latest = 1;
                    }
                    else if ((int)dt1.hour < (int)dt2.hour){
                        latest = 2;
                    }
                    else{
                        if ((int)dt1.minute > (int)dt2.minute){
                            latest = 1;
                        }
                        else if ((int)dt1.minute < (int)dt2.minute){
                            latest = 2;
                        }
                        else{
                            latest = 0;
                        }
                    }
                }
            }
        }
        return latest;
    }
}


public class PlacementCell{

    static Scanner scan = new Scanner(System.in);
    DateTime startCompany;
    DateTime endCompany; // Add these to pCell attributes
    DateTime startStudent;
    DateTime endStudent; // Add these to pCell attributes
    LinkedList<Student> registeredStudents = new LinkedList<Student>();
    LinkedList<Company> registeredCompanies = new LinkedList<Company>();
    LinkedList<Student> placedStudents = new LinkedList<Student>();
    LinkedList<Student> unplacedStudents = new LinkedList<Student>();
    LinkedList<Student> blockedStudents = new LinkedList<Student>();

    String tempDate;
    String tempTime;

    public void companyRegistration(){
        System.out.println("The institute is open for company registrations");
        System.out.println("Enter registration start date in format DD/MM/YY");
        tempDate= scan.next();
        System.out.println("Enter registration start time in format HH:MM");
        tempTime= scan.next();
        this.startCompany = new DateTime(Integer.parseInt(tempDate.substring(0,2)),Integer.parseInt(tempDate.substring(3,5)),Integer.parseInt(tempDate.substring(6,8)),Integer.parseInt(tempTime.substring(0,2)), Integer.parseInt(tempTime.substring(3,5)));
        System.out.println("Enter registration end date in format DD/MM/YY");
        tempDate= scan.next();
        System.out.println("Enter registration end time in format HH:MM");
        tempTime= scan.next();
        this.endCompany = new DateTime(Integer.parseInt(tempDate.substring(0,2)),Integer.parseInt(tempDate.substring(3,5)),Integer.parseInt(tempDate.substring(6,8)),Integer.parseInt(tempTime.substring(0,2)), Integer.parseInt(tempTime.substring(3,5)));        
    }

    public void studentRegistration(){
        System.out.println("The institute is open for student registrations");
        System.out.println("Enter registration start date in format DD/MM/YY");
        tempDate= scan.next();
        System.out.println("Enter registration start time in format HH:MM");
        tempTime= scan.next();
        this.startStudent = new DateTime(Integer.parseInt(tempDate.substring(0,2)),Integer.parseInt(tempDate.substring(3,5)),Integer.parseInt(tempDate.substring(6,8)),Integer.parseInt(tempTime.substring(0,2)), Integer.parseInt(tempDate.substring(3,5)));
        System.out.println("Enter registration end date in format DD/MM/YY");
        tempDate= scan.next();
        System.out.println("Enter registration end time in format HH:MM");
        tempTime= scan.next();
        this.endStudent = new DateTime(Integer.parseInt(tempDate.substring(0,2)),Integer.parseInt(tempDate.substring(3,5)),Integer.parseInt(tempDate.substring(6,8)),Integer.parseInt(tempTime.substring(0,2)), Integer.parseInt(tempDate.substring(3,5)));        
    }

    public void numberStudentRegistration(){
        System.out.print("The number of students registered this year: ");
        System.out.println(this.registeredStudents.size());
    }

    public void numberCompanyRegistration(){
        System.out.print("The number of companies registered this year: ");
        System.out.println(this.registeredCompanies.size());
    }

    public void updateCGPA(Student student, double newCGPA){
        student.CGPA = newCGPA;
    }

    public void studentStatus(){ // Number 5 need to add list to different status and in end print size.
        for(Student student: this.registeredStudents){
            System.out.println("Name: "+student.name+" | Status: "+student.status);
            if (student.status.equals("Not-Placed")){
                this.unplacedStudents.push(student);
            }
            if (student.status.equals("Placed")){
                if(this.placedStudents.contains(student)){
                    continue;
                }
                else{
                this.placedStudents.push(student);
                }
            }
            if (student.status.equals("Blocked")){
                this.blockedStudents.push(student);
            }
        }
        System.out.println("Number of unplaced students: "+ unplacedStudents.size());
        System.out.println("Number of placed students: "  + placedStudents.size());
        System.out.println("Number of blocked students: " + blockedStudents.size());
    }

    public void getStudentDetails(Student student){
        for(Company company: this.registeredCompanies){
            System.out.print(company.name);
            if (student.appliedCompanies.contains(company)){
                System.out.println(" - Applied");
            }
            else{
                System.out.println(" - Not Applied");
            }
        }
        System.out.println("________________________________________");
        System.out.println("Following are the companies that offered "+student.name);
        for(Company company: student.offeredCompanies){
            System.out.println(company.name);
        }
        System.out.println("Operation completed, exiting to the menu");
    }

    public void getCompanyDetails(Company company){
        System.out.println("The choosen company is "+company.name);
        System.out.println("Package Offered: "+company.packageOffered+" | Role Offered: "+company.roleOffered+" | Required CGPA"+company.requiredCGPA);
        System.out.println("The following students have been offered from this company:-");
        for(Student student: company.companyOfferedStudents){
            System.out.println("Student Name: "+student.name+" | Rollno: "+student.rollNo);
        }
        System.out.println("Operation completed, exiting to the menu");

    }
    
    public void getAveragePackage(){
        if(this.placedStudents.size()==0){
            System.out.println("No one placed, hence avg package =0");
            return;
        }
        int sum = 0;
        for(Student student: this.placedStudents){
            sum += student.salary;
        }
        System.out.println("The average package is: "+sum/placedStudents.size());
    }

    public void companyResult(Company company){
        System.out.println("Following is the list of the students selected by "+ company.name);
        if(company.companySelectedStudents.size() == 0){
            System.out.println("No selected student as of now");
        }
        else{
            for(Student student: company.companySelectedStudents){
                System.out.println(student.name);
            }   
        }
    }
    public void offeredResult(Company company){ 
        if(company.companyOfferedStudents.size() == 0){
            System.out.println("No selected student as of now");
        }
        System.out.println("Following is the list of the students offered by "+ company.name);
        for(Student student: company.companyOfferedStudents){
            System.out.println(student.name);
        }   
    }
}