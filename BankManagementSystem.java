import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
interface IsBank
{
    void GetAccountNO();
    void CreateUser();
    void Login();
    void CheckBalance();
    void DepositeBalance() throws NegativeBalanceException;
    void WithdrawBalance() throws LowBalanceException;    
}

class NegativeBalanceException extends Exception
{
    public NegativeBalanceException(String sms)
    {
        super(sms);
    }
}
class LowBalanceException extends Exception
{
    public LowBalanceException(String sms)
    {
        super(sms);
    }
}
class PasswordMissmatchException extends Exception
{
    public PasswordMissmatchException(String sms)
    {
        super(sms);
    }
}

class User
{
    String name;
    int accountNo;
    private double balance;
    int phoneNo;
    String panNo;
    String password;

    public User() 
    {

    }

    static ArrayList<User> myUser=new ArrayList<>();
    
    public User(String name,int accountNo,double balance,int phoneNo, String panNo,String password)
    {
        this.name=name;
        this.accountNo=accountNo;
        this.balance=balance;
        this.phoneNo=phoneNo;
        this.panNo=panNo;
        this.password=password;
    }

    public String getPassword()
    {
        return password;
    }
    public String getName()
    {
        return name;
    }

    public int getAccountNo()
    {
        return accountNo;
    }
    public double getBalance()
    {
        return balance;
    }
    public int getPhoneNo()
    {
        return phoneNo;
    }
    public String getPanNo()
    {
        return panNo;
    }

    public void setPassword(String isPassword)
    {
        this.password=isPassword;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public void setBalance(double balance)
    {
        this.balance=balance;
    }
    public void setPhoneNo(int phoneNo)
    {
        this.phoneNo=phoneNo;
    }
    public void setPanNo(String panNo)
    {
        this.panNo=panNo;
    }
    public void setDeposite(Double amount)
    {
        this.balance+=amount;
        
    }
    
    public void setWithdraw(Double amount)
    {
        this.balance-=amount;
    }

    public String toString()
    {
        return name+" "+balance +" "+accountNo+" "+phoneNo+" "+panNo;
    }


    static HashMap<Integer,ArrayList<String>> myAccountStatement=new HashMap<>();

    public void AddStatement(HashMap<Integer,ArrayList<String>> myAccountStatement,int key,String value)
    {
        if(!myAccountStatement.containsKey(key))
        {
            myAccountStatement.put(key,new ArrayList<>());
        }
        myAccountStatement.get(key).add(value);
    }
}

class Management extends User
{
    Scanner sc=new Scanner(System.in);

    public Management()
    {

    }
    User u;
    public Management(User u)
    {
        this.u=u;
    }
    
    int superNo;
    String password;

    public Management(int superNo,String password)
    {
        this.superNo=superNo;
        this.password=password;
    }
    public int getSuper()
    {
        return superNo;
    }
    public String getPasswordSuper()
    {
        return password;
    }


    HashSet<Management> mySuperUser=new HashSet<>();
        String reset = "\u001B[0m";
        String red= "\u001B[31m";
        String green= "\u001B[32m";
    public void CreateSuperUser()
    {
        
        boolean superUserCreate=true;
        while (superUserCreate) 
        {
            System.out.println(red+"==================================================================================================="+reset);
            System.out.println("\tNote :- Througth Program You Can Create Super User Only One Time");
            System.out.print("\n\t\tEnter the Super USer No    ::    ");
            int superusers=sc.nextInt();
            System.out.print("\t\tEnter the Super Password   ::    ");
            sc.nextLine();
            String password=sc.nextLine();
            System.out.print("\t\tRe-Enter Your Password     ::    ");
            String rePassword=sc.nextLine();
            if(password.equals(rePassword))
            {
                mySuperUser.add(new Management(superusers,password));
                superUserCreate=false;
                System.out.println(red+"\n===================================================================================================\n"+reset);
                System.out.println(green+"\tSuper User Created Successfully"+reset);
            }
            else
            {
                System.out.println("\n===================================================================================================\n");
                System.out.println(red+"\tPassword Should Not Match\n"+reset);
            }
            
            //System.out.println("\n===================================================================================================\n");
        }
            
    }

    String emp_name;
    int emp_id;
    int password_emp;

    static ArrayList<Management> myEmployeeList=new ArrayList<>();
    public Management( String emp_name, int emp_id,int password_emp)
    {
        this.emp_name=emp_name;
        this.emp_id=emp_id;
        this.password_emp=password_emp;
    }

    public String getEmployeeName()
    {
        return emp_name;
    }

    public int getEmployeeId()
    {
        return emp_id;
    }

    public int getEmployeePassword()
    {
        return password_emp;
    }
    
    public void setEmployeeName(String emp_name)
    {
        this.emp_name=emp_name;

    }

    public void setEmployeeId(int emp_id)
    {
        this.emp_id=emp_id;
    }

    public void setEmployeePassword(int password_emp)
    {
        this.password_emp=password_emp;
    }

    public String toString()
    {
       if(superNo==0)
       {
        return emp_name+" "+" "+emp_id +" "+password_emp;
       } 
       else
       {
        return superNo+" "+" "+password;
       }
    }

    public void SuperUserLogin()
    {
        
        System.out.println(red+"\n===================================================================================================\n"+reset);
        System.out.print("\t\tEnter the Super User No                  ::    ");
        int superUserNo=sc.nextInt();

        System.out.print("\t\tEnter the Super User Password            ::    ");
        sc.nextLine();
        String passwordSuperUser=sc.nextLine();

        boolean checkSuperUser=false;
        for(Management m : mySuperUser)
        {
                if(superUserNo==m.superNo && passwordSuperUser.equals(m.password))
                {
                   checkSuperUser=true; 
                }
        }

        if(checkSuperUser)
        {
            System.out.println(red+"\n===================================================================================================\n"+reset);
            System.out.println(green+"\tLogin Successfull"+reset);
            OperationSuperUser();
        }
        else
        {
            
            System.out.println(red+"\n===================================================================================================\n"+reset);
            System.out.println(red+"\tLogin failed"+reset);
        }
    }

    public void OperationSuperUser()
    {
        
        boolean superUserWork=true;

        while (superUserWork) 
        { 
            System.out.println(red+"\n============================================"+reset+"  Super Suer Menu  "+red+"====================================\n"+reset);
            System.out.println("\tLogin As: Super User");
            LocalDate date=LocalDate.now();
            System.out.println("\tDate    : "+date);
            System.out.println("\n\t\t1.Add Employee\n\t\t2.Remove Employee\n\t\t3.View Information\n\t\t4.Logout Super User");
            System.out.println(red+"\n===================================================================================================\n"+reset);
            System.out.print("\tEnter the Response::");
            int operationSuperUser=sc.nextInt();
            switch (operationSuperUser) 
            {
                case 1:
                    AddEmployee();
                    break;
                
                case 2:
                    RemoveEmployee();
                   break;
                case 3:
                    ViewInfromation();
                    break;
                case 4:
                    superUserWork=false;
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    break;
                default:
                    break;
            }
        }
    }


    public void AddEmployee()
    {
        System.out.println(red+"\n===================================================================================================\n"+reset);
        System.out.print("\t\t\tEnter Employee Name     ::    ");
        sc.nextLine();
        String emp_name=sc.nextLine();
        System.out.print("\t\t\tEnter the id            ::    ");
        int id=sc.nextInt();
        System.out.print("\t\t\tEnter the password      ::    ");
        int employee_password=sc.nextInt();

        boolean employeecheck=true;
        for(Management m:myEmployeeList)
        {
            if(m.emp_id==id)
            {
                employeecheck=false;
            }
        }

        if(employeecheck)
        {
            myEmployeeList.add(new Management(emp_name,id,employee_password));
            System.out.println(red+"\n===================================================================================================\n"+reset);
            System.out.println(green+"\tEmployee Added Successfully..."+reset);
        }
        else
        {
            System.out.println(red+"\n===================================================================================================\n"+reset);
            System.out.println(red+"Entered Id are Alerady Present In System"+red);
            System.out.println(red+"\n===================================================================================================\n"+reset);
        }
        
        
    }

    public void RemoveEmployee()
    {
        System.out.println(red+"\n===================================================================================================\n"+reset);
        System.out.print("\t\t\tEnter Id Of Employee   ::    ");
        int removeId=sc.nextInt();
                   
        boolean flag=false;
        Iterator<Management> I=myEmployeeList.iterator();
        while (I.hasNext()) 
        {
            Management removeRecord=I.next();
            if(removeRecord.emp_id==removeId)
            {
                I.remove();
                flag=true;
            }
        }

        if(!flag)
        {
            System.out.println(red+"\n===================================================================================================\n"+reset);
            System.out.println(red+"Entered id not contain..."+reset);
        }
        else
        {
            System.out.println(red+"\n===================================================================================================\n"+reset);
            System.out.println(green+"Account Deleted Successfully..."+reset);
        }
        
    }
    public void ViewInfromation()
    {
        
        boolean viewEmployee=true;
        while (viewEmployee) 
        {
            System.out.println(red+"\n======================================"+reset+"  View Information Menu  "+red+"====================================\n"+reset);
            System.out.print("\t\t1.UserList\n\t\t2.UserInformation\n\t\t3.View Employee List\n\t\t4.Exit");
            System.out.println(red+"\n===================================================================================================\n"+reset);
            System.out.print("\tEnter your Choice::");
            int ch=sc.nextInt();
            System.out.println(red+"\n===================================================================================================\n"+reset);
        
            switch (ch) 
            {
                case 1:
                    UserList();
                    break;
                case 2:
                    UserInformation();
                    break;
                case 3:
                    EmployeeList();
                    break;
                case 4:
                    viewEmployee=false;
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\tExit From View Information...");
                    break;
                default:
                    System.out.println("\tEnvalid Choice...");
                    break;
            }
        }
    }
    private void UserList()
    {
        for(User u:myUser)
        {
            System.out.print("\t\t\t"+u.name);
            System.out.println();
        }
    }
    private void UserInformation()
    {
        int maxlength=0;
        for(User u:myUser)
        {
            if(maxlength<u.getName().length());
            {
                maxlength=u.getName().length();
            }
        }

        maxlength=maxlength*4+26;
        System.out.print("\t");
        for(int i=0;i<maxlength;i++)
        {
            if(i==1 || i==maxlength-2)
            {
                System.out.print("+");
            }
            else
            {
                System.out.print("-");
            }
        }
        System.out.println();
        System.out.println("\t |   Account    Name     Pan      PhoneNo   |  ");
        System.out.print("\t");
        for(int i=0;i<maxlength;i++)
        {
            if(i==1 || i==maxlength-2)
            {
                System.out.print("+");
            }
            else
            {
                System.out.print("-");
            }
        }
        System.out.println();
        for(User u:myUser)
        {
            int j;
            System.out.print("\t");
            for(j=0;j<8;j++)
            {
                if(j==1)
                {
                    System.out.print("|");
                }
                else
                {
                    System.out.print(" ");
                }
            }
            System.out.print(""+u.getAccountNo()+"    "+u.getName()+"    "+u.getPanNo()+"    "+u.getPhoneNo());
            for(j=0;j<8;j++)
            {
                if(j==3)
                {
                    System.out.print("|");
                }
                else
                {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.print("\t");
        for(int i=0;i<maxlength;i++)
        {
            if(i==1 || i==maxlength-2)
            {
                System.out.print("+");
            }
            else
            {
                System.out.print("-");
            }
        }
        
    }
    private void EmployeeList()
        
    {
        for(Management m:myEmployeeList)
        {
            System.out.print("\t\t\t"+m.getEmployeeId()+" "+m.getEmployeeName());
            System.out.println();
        }
        
    }
}
class Employee extends Management
{
    Scanner sc=new Scanner(System.in);
    UserOperation u;

    public Employee(UserOperation u)
    {
        this.u=u;
    }
    
    public void AddCustomer()
    {
        u.getAccountNo();
        u.CreateUser();
    }
    public void DeleteAccount()
    {
        System.out.print("\t\t\tEnter the Account Number    ::    ");
        int deleteAccountNo=sc.nextInt();

        boolean flag=false;
        Iterator<User> I=myUser.iterator();
        while (I.hasNext()) 
        {
            User removeAccount=I.next();
            if(removeAccount.getAccountNo()==deleteAccountNo)
            {
                I.remove();
                flag=true;
            }
        }

        if(!flag)
        {
            System.out.println(red+"\n===================================================================================================\n"+reset);
            System.out.println(red+"\tEntered id not contain..."+reset);
        }
        else
        {
            System.out.println(red+"\n===================================================================================================\n"+reset);
            System.out.println(green+"\tAccount Deleted Successfully..."+reset);
        }
    }
    public void UpdateInformation()
    {
       boolean updateInformation=true;
       while(updateInformation) 
       {
         System.out.println(red+"\n===================================="+reset+"  Update Menu  "+red+"================================================\n"+reset);
         System.out.println("\t\t\t1.Name & Mobile No\n\t\t\t2.Add Balance\n\t\t\t3.Delete Balance\n\t\t\t4.Exit");
         System.out.println(red+"\n===================================================================================================\n"+reset);
         System.out.print("\tEnter your Response::");
         int isChoice=sc.nextInt();
         System.out.println(red+"\n===================================================================================================\n"+reset);
         switch (isChoice) 
         {
            case 1:
                System.out.print("\t\t\tEnter Account No               ::    ");
                int updateAccount=sc.nextInt();
                System.out.print("\t\t\tYou want change mobile No (1/0)::    ");
                int changeMobile=sc.nextInt();
                int changeMobileNo=0;
                if(changeMobile==1)
                {
                    System.out.print("\t\t\tEnter New Mobile Number        ::   ");
                    changeMobileNo=sc.nextInt();
                }
                
                boolean ifUserExist=false;

                for(User u:myUser)
                {
                    if(updateAccount==u.getAccountNo())
                    {
                        ifUserExist=true;
                        System.out.print("\t\t\tEnter the New Name           ::    ");
                        sc.nextLine();
                        String updateName=sc.nextLine();
                        if(changeMobile==1)
                        {
                            u.setPhoneNo(changeMobileNo);
                        }
                        u.setName(updateName);
                    }
                }
                if(ifUserExist)
                {
                    System.out.println(red+"\n===================================================================================================\n"+reset);
                    System.out.println(green+"\tName And MObile No Change Successfully"+reset);
                }
                else
                {
                    System.out.println(red+"\n===================================================================================================\n"+reset);
                    System.out.println(red+"\tAccount No Not Match"+reset);
                }
                break;
            case 2:
                System.out.print("\t\t\tEnter Account No    ::    ");
                int DepositeAccount=sc.nextInt();
                
                boolean depositeAccount=false;
                for(User u:myUser)
                {
                    if(DepositeAccount==u.getAccountNo())
                    {
                        depositeAccount=true;
                        System.out.print("\t\t\tEnter the Deposite Balance    ::    ");
                        double newBalance=sc.nextDouble();
                        u.setDeposite(newBalance);
                    }
                }
                if(depositeAccount)
                {
                    System.out.println(red+"\n===================================================================================================\n"+reset);
                    System.out.println(green+"\tAmount Successfully Deposited"+reset);
                }
                else
                {
                    System.out.println(red+"\n===================================================================================================\n"+reset);
                    System.out.println("\tAccount does not contain");
                }
                break;
            case 3:
                System.out.print("\t\t\tEnter Account No    ::    ");
                int WithdrawAccount=sc.nextInt();
                
                boolean withdrawAccount=false;
                for(User u:myUser)
                {
                    if(WithdrawAccount==u.getAccountNo())
                    {
                        withdrawAccount=true;
                        System.out.print("\t\t\tEnter the Withdraw Balance    ::    ");
                        double newBalance=sc.nextDouble();
                        u.setWithdraw(newBalance);
                    }
                }
                if(withdrawAccount)
                {
                    System.out.println(red+"\n===================================================================================================\n"+reset);
                    System.out.println(green+"\tAmount Successfully Withdraw"+reset);
                }
                else
                {
                    System.out.println(red+"\n===================================================================================================\n"+reset);
                    System.out.println("\tAccount does not contain");
                }
                break;
            case 4:
                updateInformation=false;
                System.out.print("\033[H\033[2J");
                System.out.flush();
                break;
            default:
                System.out.println("Envalid Choice...");
                break;
         }
       } 
    }
    public void GetAccountStatement()
    {
        System.out.println(red+"\n===================================================================================================\n"+reset);
        System.out.print("\t\tEnter Account Number::");
        int statementAccountNumber=sc.nextInt();
        boolean statementStatus=false;

        for(User u:myUser)
        {
            if(statementAccountNumber==u.getAccountNo())
            {
                statementStatus=true;
            }
        }
        if(statementStatus)
        {
            String red= "\u001B[31m";
            String green= "\u001B[32m";
            String reset = "\u001B[0m";
            Iterator i=myAccountStatement.get(statementAccountNumber).iterator();
            System.out.print("\t");
            for(int k=0;k<46;k++)
            {
                if(k==1 || k==44)
                {
                    System.out.print("+");
                }
                else
                {
                    System.out.print("-");
                }
            }
            System.out.println();
            System.out.println("\t |\t   Date        Time      Amount     |");
            System.out.print("\t");
            for(int k=0;k<46;k++)
            {
                if(k==1 || k==44)
                {
                    System.out.print("+");
                }
                else
                {
                    System.out.print("-");
                }
            }
            System.out.println();
    
            while (i.hasNext()) 
            {
                String str=i.next().toString();
                String idetifier=Character.toString(str.charAt(24));
                System.out.print("\t");                
                for(int k=0;k<6;k++)
                {
                    if(k==1)
                    {
                        System.out.print("|");
                    }
                    else
                    {
                        System.out.print(" ");
                    }
                }
                if(idetifier.equals("+"))
                {
                    System.out.print("\t"+green+str+""+reset); 
                }
                else
                {
                    System.out.print("\t"+red+str+""+reset);
                }
                for(int k=0;k<7;k++)
                {
                    if(k==6)
                    {
                        System.out.print("|");
                    }
                    else
                    {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
            System.out.print("\t");
            for(int k=0;k<46;k++)
            {
                if(k==1 || k==44)
                {
                    System.out.print("+");
                }
                else
                {
                    System.out.print("-");
                }
            }
            System.out.println();
        }
        else
        {
            System.out.println(red+"\n===================================================================================================\n"+reset);
            System.out.println("\tAccount Does not exist.");
        }
    }
    int employeeId;
    public void EmployeeLogin()
    {
        System.out.println(red+"\n===================================================================================================\n"+reset);
        System.out.print("\t\t\tEnter Employee Login Id    ::    ");
        employeeId=sc.nextInt();
        System.out.print("\t\t\tEnter Account Password     ::    ");
        int employeePassword=sc.nextInt();

        boolean employeeLoginStatus=false;
        for(Management m:myEmployeeList)
        {
            if(employeeId==m.getEmployeeId())
            {
                if(employeePassword==m.getEmployeePassword())
                {
                    employeeLoginStatus=true;
                    System.out.println(red+"\n===================================================================================================\n"+reset);
                    System.out.println(green+"\tEmployee Login Successfully..."+reset); 
                }
            }
        }

        if(employeeLoginStatus)
        {
          
           boolean employeeWork=true;
           while (employeeWork) 
           {
                System.out.println(red+"\n===================================================================================================\n"+reset);
                System.out.println("\tLogin As      : Employee");
                for(Management m:myEmployeeList)
                {
                    if(employeeId==m.getEmployeeId())
                    {
                        System.out.println("\tEmployee Name : "+m.getEmployeeName());
                    }
                }
                System.out.println("\tEmployee Id   : "+employeeId);
                System.out.println();

                System.out.println("\t\t  1.Add Customer\n\t\t  2.Delete Customer\n\t\t  3.Update Information\n\t\t  4.Transfer Money\n\t\t  5.Get Account Statement\n\t\t  6.Exit");
                System.out.println(red+"\n===================================================================================================\n"+reset);
                System.out.print("\tEnter Your Choice::");
                int choice=sc.nextInt();

                switch (choice) 
                {
                    case 1:
                        AddCustomer();
                        break;
                    case 2:
                        DeleteAccount();
                        break;
                    case 3:
                        UpdateInformation();
                        break;
                    case 4:
                        System.out.println(red+"\n===================================================================================================\n"+reset);
                        System.out.print("\t\t\tEnter Sender Account No    ::    ");
                        int senderAccountNo=sc.nextInt();
                        System.out.print("\t\t\tEnter Receiver Account No  ::    ");
                        int receiverAccountNo=sc.nextInt();

                        boolean senderStatus=false;
                        boolean receiverStatus=false;

                        for(User u:myUser)
                        {
                            if(u.getAccountNo()==senderAccountNo)
                            {
                                senderStatus=true;
                            }
                            if(u.getAccountNo()==receiverAccountNo)
                            {
                                receiverStatus=true;
                            }
                        }

                        if(senderStatus && receiverStatus)
                        {
                            System.out.print("\t\t\tEnter amount to transfer   ::    ");
                            double transferMoney=sc.nextDouble();
                            boolean transferStatus=false;
                            for(User u:myUser)
                            {
                                if(u.getAccountNo()==receiverAccountNo)
                                {
                                        u.setDeposite(transferMoney);
                                        transferStatus=true;
                                }
                                if(u.getAccountNo()==senderAccountNo)
                                {
                                    u.setWithdraw(transferMoney);
                                    System.out.println(red+"\n===================================================================================================\n"+reset);
                                    System.out.println(green+"\tTransfer Successful..."+reset);
                                }
                            }
                        }
                        else
                        {
                            System.out.println(red+"\n===================================================================================================\n"+reset);
                            System.out.println("\tSomething Event Wrong Like Account Does not Contail Or Not Minimum Balance.....");
                        }
                        break;
                    case 5:
                          GetAccountStatement();
                          break;
                    case 6:
                        employeeWork=false;
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        break;
                    default:
                        break;
                }
           }
        }
        else
        {
            System.out.println(red+"\n===================================================================================================\n"+reset);
            System.out.println("\tLogin Failed");
        }
    }
}
class UserLoginInformation
{
    ArrayList<UserLoginInformation> myLoginDetails=new ArrayList<>();
    String createDate;
    String loginTime;
    int accno;
    
    public UserLoginInformation()
    {

    }
    public UserLoginInformation(int accno,String createDate,String loginTime)
    {
        this.accno=accno;
        this.createDate=createDate;
        this.loginTime=loginTime;
    }

    public int getAccNo()
    {
        return accno;
    }
    public String getcreatedDate()
    {
        return createDate;
    }
    public String getLoginTime()
    {
        return loginTime;
    }

    public void setAccNo(int accno)
    {
        this.accno=accno;
    } 

    public void setCreatedDate(String date)
    {
        this.createDate=date;
    }
    public void setLoginTime(String time)
    {
        this.loginTime=time;
    }

    // public void getTime()
    // {
    //     LocalTime time=LocalTime.now();
    //     this.loginTime=time.toString();
    // }
    // public void getDate()
    // {
    //     LocalDate date=LocalDate.now();
    //     this.createDate=date.toString();
    // }

    public String toString()
    {
        return accno+" "+loginTime+" "+createDate;
    }
}
class UserOperation extends User implements IsBank
{
    UserLoginInformation usr;
    User u;

    public UserOperation()
    {

    }
    public UserOperation(UserLoginInformation usr)
    {
        this.usr=usr;
    }

    Scanner sc=new Scanner(System.in);
    boolean getAccountRequest=false;
    int lastBankAccountNumber;
    int accountNoBank;
    int final_LoginAccountNo;
    boolean checkCurrentLogin=false;
    double transferBalance=0;
    int transderAccount=0;
    int createUserCheck=0;
   
        String reset = "\u001B[0m";
        String red= "\u001B[31m";
        String green= "\u001B[32m";
    public void GetAccountNO()
    {
        for(User u:myUser)
        {
            lastBankAccountNumber=u.accountNo;
        }
        if(lastBankAccountNumber==0)
        {
            lastBankAccountNumber=100;
        }
        accountNoBank=lastBankAccountNumber+1;
        System.out.println("\t\t\tYour Account No Is       ::    "+accountNoBank);
    }
    public void CreateUser()
    {
        System.out.println(red+"\n===================================================================================================\n"+reset);
        GetAccountNO();
        
        System.out.print("\t\t\tEnter Acc Holder Name    ::    ");
        if(createUserCheck!=0)            
        sc.nextLine();
        createUserCheck=1;
        String accHolderName=sc.nextLine();

        System.out.print("\t\t\tEnter Account NUmber     ::    ");
        int finalAccNo=sc.nextInt();
        System.out.print("\t\t\tEnter Account Password   ::    ");
        sc.nextLine();
        String userPassword=sc.nextLine();
        System.out.print("\t\t\tRe-Enter  Password       ::    ");
        String reUserPassword=sc.nextLine();
        System.out.print("\t\t\tEnter Account Balance    ::    ");
        double userBalance=sc.nextDouble();
        System.out.print("\t\t\tEnter Phone No           ::    ");
        int userPhoneNo=sc.nextInt();
        System.out.print("\t\t\tEnter Holder PanNo       ::    ");
        sc.nextLine();
        String userPanNo=sc.nextLine();

        boolean checkExistingAccount=true;
        for(User u:myUser)
        {
            if((u.panNo).equals(userPanNo))
            {
                checkExistingAccount=false;
            }
        }
        
        if(checkExistingAccount)
        {
            if(finalAccNo==accountNoBank && userPassword.equals(reUserPassword))
            {
                
                myUser.add(new User(accHolderName, finalAccNo, userBalance, userPhoneNo, userPanNo,userPassword));
                System.out.println(red+"\n===================================================================================================\n"+reset);
                System.out.println(green+"\tAccount Successfully Created"+reset);

                LocalDate date=LocalDate.now();
                String finalDate=date.toString();
                LocalTime time=LocalTime.now();
                DateTimeFormatter d=DateTimeFormatter.ofPattern("HH:mm:ss");
                String finalTime=time.format(d);

                usr.myLoginDetails.add(new UserLoginInformation(finalAccNo, finalDate, finalTime));
            }
            else
            {
                System.out.println(red+"\n===================================================================================================\n"+reset);
                System.out.println("\tAccount NO Does Not Match Or Password Not Match");
            }
        }
        else
        {
            System.out.println(red+"\n===================================================================================================\n"+reset);
            System.out.println("\tAccount Holder Already Exist");
        }
    }
    public void Login()
    {
        System.out.println(red+"\n===================================================================================================\n"+reset);
        System.out.print("\t\t\tEnter the Account NO   ::    ");
        int loginAccountNo=sc.nextInt();
        System.out.print("\t\t\tEnter The Password     ::    ");
        sc.nextLine();
        String userLoginPassword=sc.nextLine();
        boolean loginStatus=false;
        for(User u:myUser)
        {
            if(loginAccountNo==u.getAccountNo() && userLoginPassword.equals(u.getPassword()))
            {
               loginStatus=true; 
               final_LoginAccountNo=loginAccountNo;
               
               for(UserLoginInformation obj :usr.myLoginDetails)
               {
                    if(loginAccountNo==obj.accno)
                    {
                        LocalTime time=LocalTime.now();
                        DateTimeFormatter d=DateTimeFormatter.ofPattern("HH:mm:ss");
                        String finalTime=time.format(d);   
                        obj.loginTime=finalTime; 
                    }
               }
               checkCurrentLogin=true;
               System.out.println(red+"\n===================================================================================================\n"+reset);
               System.out.println(green+"\tLogin Successfull..."+reset);
               break;
            }
            else
            {
                System.out.println(red+"\n===================================================================================================\n"+reset);
                System.out.println("\tAccount Does Not Contain Or Password Not Match");
            }
        }

        if(loginStatus)
        {
            boolean loginIterate=true;
            while (loginIterate) 
            {
                System.out.println(red+"\n===================================================================================================\n"+reset);
                System.out.println("\tLogin As    : Customer");
                for(User u:myUser)
                {
                    if(u.getAccountNo()==final_LoginAccountNo)
                    {
                        System.out.println("\tUser Name   : "+u.getName());
                    }
                }
                System.out.println("\tAccount No  : "+final_LoginAccountNo);
                System.out.print("\t\t   ");
            for(int i=1;i<40;i++)
            if(i==2 || i==38)
            {
                System.out.print("+");
            }
            else
            {
                System.out.print("-");
            }
            System.out.println();
            System.out.print("\t\t    |   Select One Option From Bellow   |");
            System.out.println("");
            System.out.print("\t\t   ");
            for(int i=1;i<40;i++)
            if(i==2 || i==38)
            {
                System.out.print("+");
            }
            else
            {
                System.out.print("-");
            }
            System.out.println();
            System.out.println("\t\t    |     1.Check Balance               |\n\t\t    |     2.Deposite Balance            |\n\t\t    |     3.Withdraw Balance            |\n\t\t    |     4.Transfer Balance            |\n\t\t    |     5.Last Login Time             |\n\t\t    |     6.Account Information         |\n\t\t    |     7.View Account Statement      |\n\t\t    |     8.Logout                      |");
            
            System.out.print("\t\t   ");
            for(int i=1;i<40;i++)
            if(i==2 || i==38)
            {
                System.out.print("+");
            }
            else
            {
                System.out.print("-");
            }
                System.out.println();          
                System.out.println(red+"\n===================================================================================================\n"+reset);
                System.out.print("\tEnter your Choice       ::    ");
                int ch=sc.nextInt();
                System.out.println(red+"\n===================================================================================================\n"+reset);
                switch (ch) 
                {
                    case 1:
                        CheckBalance();
                        break;
                    
                    case 2:
                        try 
                        {
                           DepositeBalance(); 
                        } 
                        catch (Exception e) 
                        {
                            System.out.println(e);
                        }
                    
                        break;
                    case 3:
                        try 
                        {
                            WithdrawBalance();
                        } 
                        catch (Exception e) 
                        {
                            System.out.println(e);
                        }
                        break;

                    case 4:
                        try 
                        {
                            Transfer();
                        } 
                        catch (Exception e) 
                        {
                            System.out.println(e);
                        }
                        break;
                    case 8:
                        loginIterate=false;
                        System.out.println("\tLogout From Customer...");
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        break;
                    case 5:
                            for(UserLoginInformation obj:usr.myLoginDetails)
                            {
                                if(final_LoginAccountNo==obj.accno)
                                {
                                    System.out.println("\tLogin Time Is          ::"+obj.loginTime);
                                }
                            }
                            break;
                    case 6:
                        AccountInformation();
                        break;
                    case 7:
                            getStatement();
                            break;
                    default:
                        System.out.println("\tInvalid Choice");
                        break;
                }
            }
        }
    }
    public void AccountInformation()
    {
        
        System.out.print("\t");
        for(int i=1;i<60;i++)
        if(i==2 || i==58)
        {
            System.out.print("+");
        }
        else
        {
            System.out.print("-");
        }
        System.out.println();
        System.out.print("         |                     Account Information               |");
        System.out.println("");
        System.out.print("\t");
        for(int i=1;i<60;i++)
        if(i==2 || i==58)
        {
            System.out.print("+");
        }
        else
        {
            System.out.print("-");
        }
        System.out.println();
        for(User u:myUser)
        {
            if(final_LoginAccountNo==u.accountNo)
            {
                System.out.println("\t |    Account Number          ::    "+u.getAccountNo()+"                  |");
                System.out.println("\t |    Account Holder Name     ::    "+u.getName()+"                |");
                System.out.println("\t |    Account Holder Phone No ::    "+u.getPhoneNo()+"               |");
                System.out.println("\t |    Account Holder Pan No   ::    "+u.getPanNo()+"              |");
            }
        }
        for(UserLoginInformation us:usr.myLoginDetails)
        {
            if(final_LoginAccountNo==us.accno)
            {
                System.out.println("\t |    Account Create Date     ::    "+us.getcreatedDate()+"           |");
            }
        }
        System.out.print("\t");
        for(int i=1;i<60;i++)
        if(i==2 || i==58)
        {
            System.out.print("+");
        }
        else
        {
            System.out.print("-");
        }
        System.out.println();
        
    }
    public void CheckBalance()
    {
        for(User u:myUser)
        {
            if(u.getAccountNo()==final_LoginAccountNo)
            {
                System.out.println("\tBalance of Account     ::    "+u.getBalance());
            }
        }
    }
    public void DepositeBalance() throws NegativeBalanceException
    {
        System.out.print("\t\t\tEnter Amount Deposit   ::    ");
        double depositeAmount=sc.nextDouble();
        
        if(depositeAmount<0)
        {
            System.out.println(red+"\n===================================================================================================\n"+reset);
            throw new NegativeBalanceException("Balance should not be Negative");
        }
        else
        {
            for(User u:myUser)
            {
                if(u.getAccountNo()==final_LoginAccountNo)
                {
                    u.setDeposite(depositeAmount);
                    LocalDate statement_date=LocalDate.now();
                    String finalStatement_date=statement_date.toString();
                    String statementAmount=Double.toString(depositeAmount);
                    LocalTime statement_time=LocalTime.now();
                    DateTimeFormatter final_time=DateTimeFormatter.ofPattern("HH:mm.ss");
                    String finalStatement_time=statement_time.format(final_time);

                    String identifier="+";
                    String sepreater="   ";
                    String final_statement=finalStatement_date+sepreater+finalStatement_time+sepreater+identifier+statementAmount;
                    AddStatement(myAccountStatement, u.getAccountNo(), final_statement);
                }
            }
        }
    }

    public void WithdrawBalance() throws LowBalanceException
    {
        double withdrawAmount=0;
        if(transderAccount==0)
        {
            System.out.print("\t\t\tEnter Amount Withdraw    ::    ");
            withdrawAmount=sc.nextDouble();
        }
        else
        {
            withdrawAmount=transferBalance;
        }
        
        for(User u:myUser)
        {
            if(u.getAccountNo()==final_LoginAccountNo)
            {
                if((u.getBalance())-withdrawAmount<500)
                {
                    System.out.println(red+"\n===================================================================================================\n"+reset);
                    throw new LowBalanceException("Account Contains Minimum 500 Balance");
                }
                else
                {
                    u.setWithdraw(withdrawAmount);
                    LocalDate statement_date=LocalDate.now();
                    String finalStatement_date=statement_date.toString();
                    String statementAmount=Double.toString(withdrawAmount);
                    LocalTime statement_time=LocalTime.now();
                    DateTimeFormatter final_time=DateTimeFormatter.ofPattern("HH:mm.ss");
                    String finalStatement_time=statement_time.format(final_time);

                    String identifier="-";
                    String sepreater="   ";
                    String final_statement=finalStatement_date+sepreater+finalStatement_time+sepreater+identifier+statementAmount;
                    AddStatement(myAccountStatement, u.getAccountNo(), final_statement);
                    
                }
            }
        }
    }

    public void Transfer() throws NegativeBalanceException
    {
        System.out.print("\t\t\tEnter The Transfer Account No    ::    ");
        transderAccount=sc.nextInt();

        System.out.print("\t\t\tEnter The Amount To Be Transfer  ::    ");
        transferBalance=sc.nextDouble();
        boolean transferStatus=false;

        if(transferBalance<0)
        {
            System.out.println(red+"\n===================================================================================================\n"+reset);
            throw new NegativeBalanceException("Balance Should Not Negative");
        }
        else
        {
            for(User u:myUser)
            {
                if(u.getAccountNo()==transderAccount)
                {
                    try 
                    {
                      WithdrawBalance();  
                    } 
                    catch (Exception e) 
                    {
                        System.out.println(e);
                    }
                    u.setDeposite(transferBalance);
                    transferStatus=true;
                    break;
                }
            }
        }

        if(transferStatus)
        {
            System.out.println(red+"\n===================================================================================================\n"+reset);
            System.out.println(green+"\tTransfer Successfully"+reset);
        }
        else
        {
            System.out.println(red+"\n===================================================================================================\n"+reset);
            System.out.println("\tTransfer Account Does not Present In System");
        }
    }
    public void getStatement()
    {
        String red= "\u001B[31m";
        String green= "\u001B[32m";
        String reset = "\u001B[0m";
        Iterator i=myAccountStatement.get(final_LoginAccountNo).iterator();
        System.out.print("\t");
        for(int k=0;k<46;k++)
        {
            if(k==1 || k==44)
            {
                System.out.print("+");
            }
            else
            {
                System.out.print("-");
            }
        }
        System.out.println();
        System.out.println("\t |\t   Date        Time      Amount     |");
        System.out.print("\t");
        for(int k=0;k<46;k++)
        {
            if(k==1 || k==44)
            {
                System.out.print("+");
            }
            else
            {
                System.out.print("-");
            }
        }
        System.out.println();

        while (i.hasNext()) 
        {
            String str=i.next().toString();
            String idetifier=Character.toString(str.charAt(24));
            System.out.print("\t");                
            for(int k=0;k<6;k++)
            {
                if(k==1)
                {
                    System.out.print("|");
                }
                else
                {
                    System.out.print(" ");
                }
            }
            if(idetifier.equals("+"))
            {
                System.out.print("\t"+green+str+""+reset); 
            }
            else
            {
                System.out.print("\t"+red+str+""+reset);
            }
            for(int k=0;k<7;k++)
            {
                if(k==6)
                {
                    System.out.print("|");
                }
                else
                {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.print("\t");
        for(int k=0;k<46;k++)
        {
            if(k==1 || k==44)
            {
                System.out.print("+");
            }
            else
            {
                System.out.print("-");
            }
        }
        System.out.println();
    }

    public void GetDetails()
    {

    }
}
class Visitors extends Thread
{
    String reset = "\u001B[0m";
    String red= "\u001B[31m";
    String green= "\u001B[32m";
    String yellow= "\u001B[33m";

    Scanner sc=new Scanner(System.in);
    public void run()
    {
        boolean visitorInformation=true;
        while (visitorInformation) 
        {
            System.out.println(red+"========================================"+reset+"  Visitor Menu  "+red+"==========================================\n"+reset);
            System.out.println(red+"\tLogin As:-Visitor");
            System.out.println(green+"\t\t\t1.Account Open\n\t\t\t2.Loan Information\n\t\t\t3.Exit\n");
            System.out.println(red+"==================================================================================================\n"+reset);
            System.out.print("\tEnter Your Response::");
            int visitorResponse=sc.nextInt();
            System.out.println();
            switch (visitorResponse) 
            {
                case 1:
                    AccountOpenInfromation();
                    break;
                case 2:
                    LoanInformation();
                    break;
                case 3:
                    visitorInformation=false;
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    break;
                default:
                    break;
            }
            
        }
    }
    public void AccountOpenInfromation()
    {
        System.out.println(red+"========================================"+reset+"Account Open Information"+red+"===================================\n"+reset);
        System.out.println("-> Required Documents:-\n");
        System.out.println(green+"\t1.AdharCard Zerox\n\t2.PanCard Zerox\n\t3.Two Passport Size Photo"+reset);
        System.out.println();
        setTime();
        System.out.println("-> Terms And Condition:-\n");
        System.out.println(green+"\t1.Required Two person sign that are already customer of bank for opening account\n\t2.At the opening account minimum deposite balance is 500\n\t3.Yearly service charge Rs 350 are debited from user account at the end of march"+reset);
        System.out.println();
        setTime();
        System.out.println("-> Provide Facelity:-\n"+reset);
        System.out.println(green+"\t1.Online Network\n\t2.Offline Service\n\t3.Flexible Customer Soupport"+reset);
        System.out.println();
        setTime();
        System.out.println("-> Bank Time:-\n");
        System.out.println(green+"\t mon :\t9.00 am to 4.00pm");
        System.out.println("\t tue :\t9.00 am to 4.00pm");
        System.out.println("\t wed :\t9.00 am to 4.00pm");
        System.out.println("\t thu :\t9.00 am to 4.00pm");
        System.out.println("\t fri :\t9.00 am to 4.00pm");
        System.out.println("\t sat :\t9.00 am to 4.00pm\n"+reset);

        System.out.println(red+"Note:- Third and Forth saturday bank are closed\n"+reset);
        System.out.println(red+"==================================================================================================\n"+reset);
    }
    public void LoanInformation()
    {

        System.out.println(red+"==========================================="+reset+"Loan Information"+red+"=======================================\n"+reset);
        System.out.println("-> Available Loan:-\n");
        System.out.println(green+"\t1.Personal loan\n\t2.Home Loan\n\t3.Gold Loan\n\t4.Student Loan\n\t5.Business Loan"+reset);
        System.out.println();
        setTime();
        System.out.println("-> Requird Documents:-\n");
        System.out.println(green+"\t1.AdharCard Zerox\n\t2.PanCard Zerox\n\t3.Two Passport Size Photo\n\t4.Salary Slip\n\t5.Last 6 Months bank account statement"+reset);
        System.out.println();
        setTime();
        System.out.println("->Terms And Condition:-\n");
        System.out.println(green+"\t1.Customer Pay loan on time\n\t2.Required two person that are User of this bank");
        System.out.println();
        System.out.println(red+"Note:-If Customer not pay loan on time then sutaible action take by bank"+reset);
        System.out.println();
        System.out.println(red+"===================================================================================================\n"+reset);
    }
    public void setTime()
    {
        try
        {
            Thread.sleep(700);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
class WelcomeMassage extends Thread
{
    public void DisplayPattern() throws InterruptedException
    {
        String reset = "\u001B[0m";
        String red= "\u001B[31m";
        String yellow= "\u001B[33m";


        System.out.println(red+"\n======================================= "+reset+"Wel -COME MESSAGE"+red+" =========================================\n"+reset);
        String str="-->   Well-Come To Bank Management System   <--";
        int strLength=str.length()+12;

        System.out.print("\t\t");
        for(int i=0;i<strLength;i++)
        {
            if(i==1 || i==strLength-2)
            {
                System.out.print(red+"+");
                Thread.sleep(60);
            }
            else
            {
                System.out.print("-");
                Thread.sleep(60);
            }
        }
        System.out.print(reset);
        System.out.print("\n");
        System.out.print("\t\t");
        for(int j=0;j<strLength;j++)
        {
            if(j==1 || j==strLength-2)
            {
                System.out.print(red+"|");
                Thread.sleep(60);
            }
            else
            {
                System.out.print(" ");
            }
        }
        System.out.print(reset);
        System.out.println();
        System.out.print("\t\t");
        for(int i=0;i<6;i++)
        {
            if(i==1)
            {
                System.out.print(red+"|"+reset);
                Thread.sleep(60);
            }
            else
            {
                System.out.print(" ");
            }
        }

        for(int i=0;i<str.length();i++)
        {
            System.out.print(str.charAt(i));
            Thread.sleep(60);
        }
        for(int i=0;i<5;i++)
        {
            if(i==4)
            {
                System.out.print(red+"|");
                Thread.sleep(60);
            }
            else
            {
                System.out.print(" ");
                Thread.sleep(60);
            }
        }
        System.out.print("\n");
        System.out.print("\t\t");
        for(int j=0;j<strLength;j++)
        {
            if(j==1 || j==strLength-2)
            {
                System.out.print("|");
                Thread.sleep(60);
            }
            else
            {
                System.out.print(" ");
            }
        }
        System.out.print("\n");
        System.out.print("\t\t"+red);
        for(int i=0;i<strLength;i++)
        {
            if(i==1 || i==strLength-2)
            {
                System.out.print("+");
                Thread.sleep(60);
            }
            else
            {
                System.out.print("-");
                Thread.sleep(60);
            }
        }
        System.out.print(reset);
        System.out.println(red+"\n\n===================================================================================================\n"+reset);
    }
    public void run()
    {
        try
        {
            DisplayPattern();
        }
        catch (Exception e)
        {

        }
        
    }
}
class BankManagementSystem
{
    public static void main(String[] args) throws InterruptedException
    {
        WelcomeMassage w=new WelcomeMassage();
        w.start();
        w.join();

        if(!w.isAlive())
        {
            UserLoginInformation usr=new UserLoginInformation();
            UserOperation obj=new UserOperation(usr);
            Visitors v=new Visitors();
            //    while(true)
            //    {
            //     obj.GetAccountNO();
            //    obj.CreateUser();
            //    //obj.DepositeBalance();
            //    obj.Login();
            //    }
            User u=new User();
            Scanner sc=new Scanner(System.in);
            Employee emp=new Employee(obj);
            Management m=new Management(u);

            String reset = "\u001B[0m";
            String red= "\u001B[31m";
            String green= "\u001B[32m";
            m.CreateSuperUser();
            boolean res=true;
            while (res)
            {
                    System.out.println(red+"\n=========================================="+reset+"  Bank Menu  "+red+"============================================\n"+reset);
                    System.out.println("\t\t1.Login Account \n\t\t2.Create Account\n\t\t3.Visitors\n\t\t4.Exit...");
                    System.out.println(red+"\n===================================================================================================\n"+reset);
                    
                    System.out.print("\tEnter your response::");
                    int response=sc.nextInt();
                    switch (response) 
                    {
                        case 1:
                            boolean loginLoop=true;
                            while (loginLoop) 
                            {
                                System.out.println(red+"\n=========================================="+reset+"  Login Menu  "+red+"===========================================\n"+reset);
                                System.out.println("\t\t1.Login As Customeer\n\t\t2.Login As SuperUser\n\t\t3.Login As Employee\n\t\t4.Exit...");
                                System.out.println(red+"\n===================================================================================================\n"+reset);
                                System.out.print("\tEnter your response::");
                                int loginResponse=sc.nextInt();
                                switch (loginResponse) 
                                {
                                    case 1:
                                        obj.Login();
                                        break;
                                    
                                    case 2:
                                        m.SuperUserLogin();
                                        break;

                                    case 3:
                                        emp.EmployeeLogin();
                                        break;

                                    case 4:
                                        System.out.println(red+"\n===================================================================================================\n"+reset);
                                        System.out.println("\tExit From Login Menu...");
                                        loginLoop=false;
                                        System.out.print("\033[H\033[2J");
                                        System.out.flush();
                                        break;
                                    default:
                                        System.out.println("Invalid Choice.....");
                                        break;
                                }
                            }
                            break;
                        
                        case 2:
                            obj.CreateUser();
                            break;

                        case 3:
                            v.start();
                            v.join();
                            break;

                        case 4:
                            System.out.println(red+"\n===================================================================================================\n"+reset);
                            System.out.print("\t");
                            String massage="Thank you Visit Again....";

                            for(int i=0;i<massage.length();i++)
                            {
                                System.out.print(massage.charAt(i));
                                Thread.sleep(100);
                            }
                            System.out.println(red+"\n\n===================================================================================================\n"+reset);
                            res=false;
                            System.out.print("\033[H\033[2J");
                            System.out.flush();
                            break;

                        default:
                            System.out.println(red+"\n===================================================================================================\n"+reset);
                            System.out.println("\tEnvalid choice...");
                            break;
                    }
            }
        } 
    }
} 