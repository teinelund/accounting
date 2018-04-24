package org.teinelund.application.accounting.model;

import java.math.BigDecimal;
import java.util.Date;

public class Invoice {

    private String name;
    private Date payDate;
    private BigDecimal amount;
    private String giroName;
    private String gironumber;
    private String giroType;
    private String ocrnumber;

    public static class Builder {
        private String name;
        private Date payDate;
        private BigDecimal amount;
        private String giroName;
        private String gironumber;
        private String giroType;
        private String ocrnumber;

        public Builder() { }

        public Builder setName( String name ) {
            this.name = name;
            return this;
        }

        public Builder setPayDate( Date payDate ) {
            this.payDate = payDate;
            return this;
        }

        public Builder setAmount( BigDecimal amount ) {
            this.amount = amount;
            return this;
        }

        public Builder setGiroName( String giroName ) {
            this.giroName = giroName;
            return this;
        }

        public Builder setGiroNumber( String gironumber ) {
            this.gironumber = gironumber;
            return this;
        }

        public Builder setGiroType( String giroType ) {
            this.giroType = giroType;
            return this;
        }

        public Builder setOcrNumber( String ocrnumber ) {
            this.ocrnumber = ocrnumber;
            return this;
        }

        public Invoice build() {
            return new Invoice( this );
        }
    }

    Invoice(Builder builder) {
        this.name = builder.name;
        this.payDate = builder.payDate;
        this.amount = builder.amount;
        this.giroName = builder.giroName;
        this.gironumber = builder.gironumber;
        this.giroType = builder.giroType;
        this.ocrnumber = builder.ocrnumber;
    }

    public String getName() {
        return name;
    }

    public Date getPayDate() {
        return payDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getGiroName() {
        return giroName;
    }

    public String getGironumber() {
        return gironumber;
    }

    public String getGiroType() {
        return giroType;
    }

    public String getOcrnumber() {
        return ocrnumber;
    }
}
