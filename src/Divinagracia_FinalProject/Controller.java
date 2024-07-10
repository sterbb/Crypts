package Divinagracia_FinalProject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Controller {


    @FXML
    private TableView<Product> ic_table;
    @FXML
    private TableColumn<Product, String> name;
    @FXML
    private TableColumn<Product, String> position;
    @FXML
    private TableColumn<Product, Integer> qty;
    @FXML
    private TableColumn<Product, Double> amount;
    @FXML
    private TableColumn<Product, String> date;
    @FXML
    private TextField ic_date;
    @FXML
    private TextField ic_qty;
    @FXML
    private TextField ic_amount;
    @FXML
    private TextField ic_name;
    @FXML
    private TextField ic_search;
    @FXML
    private Text ic_investment;
    @FXML
    private Text ic_profit;
    @FXML
    private Text ic_p;
    @FXML
    private Text ic_income;
    @FXML
    private Text ic_profita;
    @FXML
    private Text cc_txtqty;
    @FXML
    private Text cc_txtamount;
    @FXML
    private TextField cc_camount;
    @FXML
    private TextField cc_amount;
    @FXML
    private TextField cc_qty;
    @FXML
    private TextField p_2;
    @FXML
    private TextField p_amount;
    @FXML
    private TextField p_3;
    @FXML
    private TextField p_4;
    @FXML
    private TextField p_5;
    @FXML
    private TextField p_1;
    @FXML
    private Text p_p;
    @FXML
    private Text p_t;
    @FXML
    private TextField pc_sell;
    @FXML
    private TextField pc_buy;
    @FXML
    private Text pc_profit;
    @FXML
    private TextField pc_investment;
    @FXML
    private Text pc_number;
    @FXML
    private Text pc_prcnt;

    //investment calculator
    public ArrayList<String> name_array = new ArrayList<>();
    public ArrayList<String> position_array = new ArrayList<>();
    public ArrayList<Integer> qty_array = new ArrayList<>();
    public ArrayList<Double> amount_array = new ArrayList<>();
    public ArrayList<String> date_array = new ArrayList<>();
    public double investment;
    public double income;
    public double totalincome;
    public double profit;
    public double totalprofit;
    public double percentage;
    public int quantity;
    //2D-array
    public static ArrayList<ArrayList<String>> buyarray =  new ArrayList<ArrayList<String>>();
    //initialize 2D-array
    @FXML
    public void initialize(){
        buyarray.add(new ArrayList<String>());
        buyarray.add(new ArrayList<String>());
        buyarray.add(new ArrayList<String>());
        System.out.println(buyarray.toString());
    }

    public DecimalFormat df2 = new DecimalFormat("0.00");
    public static ObservableList<Product> data = FXCollections.observableArrayList();

    @FXML
    void Buy(ActionEvent event) {
        //display
        name_array.add(ic_name.getText());
        position_array.add("Buy");
        qty_array.add(Integer.parseInt(ic_qty.getText()));
        amount_array.add(Double.parseDouble(ic_amount.getText()));
        date_array.add(ic_date.getText());
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        position.setCellValueFactory(new PropertyValueFactory<>("position"));
        qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        Product product = new Product(ic_name.getText(),"Buy",Integer.parseInt(ic_qty.getText()),Double.parseDouble(ic_amount.getText()),ic_date.getText());
        data.add(product);
        ic_table.setItems(data);

        System.out.println(buyarray.get(0).size());

        //adding to array list
        buyarray.get(0).add(ic_name.getText());
        buyarray.get(1).add(ic_qty.getText());
        buyarray.get(2).add(ic_amount.getText());

        System.out.println(buyarray.toString());


        investment += Double.parseDouble(ic_qty.getText())*Double.parseDouble(ic_amount.getText());
        ic_investment.setText(Double.toString(investment));
        System.out.println(name_array.toString());
        System.out.println(position_array.toString());
        System.out.println(qty_array.toString());
        System.out.println(amount_array.toString());
        System.out.println(date_array.toString());

    }

    @FXML
    void Sell(ActionEvent event) {
        //display
        name_array.add(ic_name.getText());
        position_array.add("Sell");
        qty_array.add(Integer.parseInt(ic_qty.getText()));
        amount_array.add(Double.parseDouble(ic_amount.getText()));
        date_array.add(ic_date.getText());
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        position.setCellValueFactory(new PropertyValueFactory<>("position"));
        qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        Product product = new Product(ic_name.getText(),"Sell",Integer.parseInt(ic_qty.getText()),Double.parseDouble(ic_amount.getText()),ic_date.getText());
        data.add(product);
        ic_table.setItems(data);

        System.out.println(buyarray.get(0).get(0));

        //loop array
        for (int i =0; i<buyarray.get(0).size();i++){
            if (buyarray.get(0).get(i)==ic_name.getText()){
                income = (Double.parseDouble(ic_amount.getText())/Double.parseDouble(buyarray.get(2).get(i)))*Integer.parseInt(ic_qty.getText());
                totalincome += income;
                profit = income - Integer.parseInt(buyarray.get(1).get(i))*Double.parseDouble(buyarray.get(2).get(i));
                totalprofit += profit;
                buyarray.get(1).set(i,Integer.toString(Integer.parseInt(buyarray.get(1).get(i)) - Integer.parseInt(ic_qty.getText())));
                System.out.println("hehhe"+income);
                System.out.println("hahah"+profit);
                if (Integer.parseInt(buyarray.get(1).get(i)) == 0 ) {
                    buyarray.get(0).remove(i);
                    buyarray.get(0).remove(i);
                    buyarray.get(0).remove(i);
                }else if (Integer.parseInt(buyarray.get(1).get(i))<0){
                    quantity = Math.abs(Integer.parseInt(buyarray.get(1).get(i)));
                    buyarray.get(0).remove(i);
                    buyarray.get(0).remove(i);
                    buyarray.get(0).remove(i);
                    while(quantity < 0){
                        for(int i2 = 0;i2<buyarray.get(0).size();i2++ ){
                            if (ic_name.getText() == buyarray.get(0).get(i2)){
                                income = (Double.parseDouble(ic_amount.getText())/Double.parseDouble(buyarray.get(2).get(i2)))*Integer.parseInt(ic_qty.getText());
                                totalincome += income;
                                profit = income - quantity*Double.parseDouble(buyarray.get(2).get(i2));
                                totalprofit += profit;
                                buyarray.get(1).set(i2,Integer.toString(Integer.parseInt(buyarray.get(1).get(i2)) - quantity));
                                quantity = Integer.parseInt(buyarray.get(1).get(i2));
                                if (Integer.parseInt(buyarray.get(1).get(i2))<=0) {
                                    quantity = Math.abs(Integer.parseInt(buyarray.get(1).get(i)));
                                    buyarray.get(0).remove(i2);
                                    buyarray.get(0).remove(i2);
                                    buyarray.get(0).remove(i2);
                                }
                                break;
                            }
                        }
                    }

                }

            }
        }



        ic_income.setText(Double.toString(totalincome));
        ic_profita.setText(Double.toString(totalprofit));
        System.out.println(name_array.toString());
        System.out.println(position_array.toString());
        System.out.println(qty_array.toString());
        System.out.println(amount_array.toString());
        System.out.println(date_array.toString());
    }

    @FXML
    void Convert(ActionEvent event) {
        double fromcryp = Double.parseDouble(cc_qty.getText())*Double.parseDouble(cc_amount.getText());
        double tocryp = Double.parseDouble(df2.format(fromcryp/Double.parseDouble(cc_camount.getText()))) ;

        cc_txtqty.setText((Double.toString(tocryp)));



    }

    @FXML
    void Predict(ActionEvent event) {
        double ave_closing;
        ave_closing = (Double.parseDouble(p_1.getText())+Double.parseDouble(p_2.getText())+Double.parseDouble(p_3.getText())+Double.parseDouble(p_4.getText())+Double.parseDouble(p_5.getText()))/5;

        if (Double.parseDouble(p_amount.getText())>=ave_closing){
            p_p.setText("Bullish Trend");
            p_t.setText("Buy!");
            p_p.setFill(Color.rgb(1,144,47));
            p_t.setFill(Color.rgb(1,144,47));

        }else{
            p_p.setText("Bearish Trend");
            p_t.setText("Sell!");
            p_p.setFill(Color.rgb(185,5,5));
            p_t.setFill(Color.rgb(185,5,5));
            System.out.println(ave_closing);

        }

    }

    @FXML
    void Print(ActionEvent event) {

    }

    @FXML
    void Search(ActionEvent event) {

    }



    @FXML
    void calculate_Profit(ActionEvent event) {

        String profit,number,prcnt;

        /*
        pc_profit.setText(Double.toString((((Double.parseDouble(pc_investment.getText())/Double.parseDouble(pc_buy.getText()))
                *Double.parseDouble(pc_sell.getText())))));
        pc_number.setText(Double.toString((((Double.parseDouble(pc_investment.getText())/Double.parseDouble(pc_buy.getText()))
                *Double.parseDouble(pc_sell.getText()))-Double.parseDouble(pc_investment.getText()))));
        pc_prcnt.setText(Double.toString(Double.parseDouble(pc_number.getText())/Double.parseDouble(pc_investment.getText())));
        */

        profit =  df2.format((Double.parseDouble(pc_investment.getText())/Double.parseDouble(pc_buy.getText())) * Double.parseDouble(pc_sell.getText()));
        number =  df2.format((((Double.parseDouble(pc_investment.getText())/Double.parseDouble(pc_buy.getText()))
                *Double.parseDouble(pc_sell.getText()))-Double.parseDouble(pc_investment.getText())));
        prcnt = df2.format((Double.parseDouble(number)/Double.parseDouble(pc_investment.getText()))*100);




        /*
        pc_profit.setText(Double.toString(Math.round((Double.parseDouble(profit)*100)/100)));
        pc_number.setText(Double.toString(Math.round((Double.parseDouble(number)*100)/100)));
        pc_prcnt.setText(Double.toString(Math.round((Double.parseDouble(prcnt)*100)/100)));
          */
        pc_profit.setText(profit);
        pc_number.setText(number);
        pc_prcnt.setText(prcnt+"%");

        if (Double.parseDouble(number)<0){
            pc_profit.setFill(Color.rgb(185,5,5));
            pc_number.setFill(Color.rgb(185,5,5));
            pc_prcnt.setFill(Color.rgb(185,5,5));
        }else{
            pc_profit.setFill(Color.rgb(1,144,47));
            pc_number.setFill(Color.rgb(1,144,47));
            pc_prcnt.setFill(Color.rgb(1,144,47));
        }


        System.out.println(profit+number+prcnt);
    }

}
