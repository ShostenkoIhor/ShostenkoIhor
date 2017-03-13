
package iptv.com.ihor.shostenkoihor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExchangeRate {

    @SerializedName("Currency")
    @Expose
    private String currency;
    @SerializedName("CurrencyName")
    @Expose
    private String currencyName;
    @SerializedName("Buy")
    @Expose
    private Double buy;
    @SerializedName("Sale")
    @Expose
    private Double sale;
    @SerializedName("BuyDelta")
    @Expose
    private Double buyDelta;
    @SerializedName("SaleDelta")
    @Expose
    private Double saleDelta;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Double getBuy() {
        return buy;
    }

    public void setBuy(Double buy) {
        this.buy = buy;
    }

    public Double getSale() {
        return sale;
    }

    public void setSale(Double sale) {
        this.sale = sale;
    }

    public Double getBuyDelta() {
        return buyDelta;
    }

    public void setBuyDelta(Double buyDelta) {
        this.buyDelta = buyDelta;
    }

    public Double getSaleDelta() {
        return saleDelta;
    }

    public void setSaleDelta(Double saleDelta) {
        this.saleDelta = saleDelta;
    }

}
