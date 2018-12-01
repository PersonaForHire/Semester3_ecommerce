/**
 * Student number: 20079431
 * @author  Conor Askins
 * @version 1.0
 * @since   2018-11-11
 */
package application;

public class Product {
   private String name,desc,category;
   private double cost;
   private int id;


    public Product(String name, String desc, String category, double cost, int id) {
        this.name = name;
        this.desc = desc;
        this.category = category;
        this.cost = cost;
        this.id = id;
    }

    //Get and set accessors

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public void setCost(double cost) {
        this.cost = cost;
    }


    public void setId(int id){
        this.id=id;
    }

    //toString for use in displaying the information in the TextArea's
    public String toString() {
        return "id= " + this.id + "\n name= " + this.name + "\n description= " + this.desc  + "\n cost= " + this.cost + "\n";
    }

}
