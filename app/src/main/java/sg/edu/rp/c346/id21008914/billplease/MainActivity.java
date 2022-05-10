package sg.edu.rp.c346.id21008914.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText amount;
    EditText NoPax;
    ToggleButton svs;
    ToggleButton gst;
    TextView total;
    TextView each;
    Button Split;
    Button Reset;
    EditText Discount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amount = findViewById(R.id.editTextNumber);
        NoPax = findViewById(R.id.editTextNumberPax);
        total = findViewById(R.id.totalBill);
        each = findViewById(R.id.eachPay);
        svs = findViewById(R.id.toggleButtonSvs);
        gst = findViewById(R.id.toggleButtonGst);
        Split = findViewById(R.id.buttonSplit);
        Reset = findViewById(R.id.buttonClear);
        Discount = findViewById(R.id.editTextNumberDiscount);


        Split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount.getText().toString().trim().length()!=0 && NoPax.getText().toString().trim().length()!=0) {
                    double newAmt = 0.0;
                    if (!svs.isChecked() && !gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString());
                    } else if (svs.isChecked() && !gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.1;
                    } else if (!svs.isChecked() && gst.isChecked()) {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.07;
                    } else {
                        newAmt = Double.parseDouble(amount.getText().toString()) * 1.17;
                    }
//Discount
                    if (Discount.getText().toString().trim().length() != 0) {
                        newAmt *= 1 - Double.parseDouble(Discount.getText().toString()) / 100;
                    }
                    total.setText("Total Bill: $" + String.format("%.2f", newAmt));
                    int numPerson = Integer.parseInt(NoPax.getText().toString());
                    if (numPerson != 1)
                        each.setText("Each Pays: $" + String.format("%.2f", newAmt / numPerson));
                    else
                        each.setText("Each Pays: $" + newAmt);
                }
            }
        });
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                NoPax.setText("");
                svs.setChecked(false);
                gst.setChecked(false);
                Discount.setText("");
            }
        });
    }
}