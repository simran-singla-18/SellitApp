package app.reatailx.sellitapp.Models;

import java.io.Serializable;

/**
 * Created by IBRANDOX-4 on 08-08-2017.
 */

public class WalletMainPaymentModel implements Serializable {
    private String amount;
    private String payment_type;
    private String transactiontype;
    private String created_at;

    public String getAmount() {
        return amount;
    }

    public WalletMainPaymentModel setAmount(String amount) {
        this.amount = amount;
        return this;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public WalletMainPaymentModel setPayment_type(String payment_type) {
        this.payment_type = payment_type;
        return this;
    }

    public String getTransactiontype() {
        return transactiontype;
    }

    public WalletMainPaymentModel setTransactiontype(String transactiontype) {
        this.transactiontype = transactiontype;
        return this;
    }

    public String getCreated_at() {
        return created_at;
    }

    public WalletMainPaymentModel setCreated_at(String created_at) {
        this.created_at = created_at;
        return this;
    }
}
