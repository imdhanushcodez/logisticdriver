package com.example.logisticsdriver;

public class data_class_auction {
    String from,to,name,number,product,betname,betamt;

    public data_class_auction(String from, String to, String name, String number, String product, String betname, String betamt) {
        this.from = from;
        this.to = to;
        this.name = name;
        this.number = number;
        this.product = product;
        this.betname = betname;
        this.betamt = betamt;
    }

    public data_class_auction() {

    }


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }



    public String getBetname() {
        return betname;
    }

    public void setBetname(String betname) {
        this.betname = betname;
    }

    public String getBetamt() {
        return betamt;
    }

    public void setBetamt(String betamt) {
        this.betamt = betamt;
    }
}
