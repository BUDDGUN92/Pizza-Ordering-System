class  Pizza
{
   private  int id;
   private  String name, description ;
   private  int  cost;
   

    public  Pizza(int tid, String tn, String  des,  int  cs)
    {
       id = tid;
       name = tn;
       description = des;
       cost =  cs;
       
     }

    public  void  displayPizza()
    {
       System.out.println(id+"    "+name);
    }


    public  String  getName()
    {
       return  name;
     }

    public  int  getCost()
    {
       return  cost;
    }  

 

    public  void  displayDetails()
    {
       System.out.println("Pizza ID     "+id);
       System.out.println("Pizza Name   "+name);
       System.out.println("Description "+description);
       System.out.println("Cost        "+cost);
    }    
}



