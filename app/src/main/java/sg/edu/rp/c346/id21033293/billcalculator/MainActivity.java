package sg.edu.rp.c346.id21033293.billcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {
    EditText amount ;
    EditText numPax ;
    ToggleButton svs ;
    ToggleButton gst ;
    TextView totalBill ;
    TextView single_pays ;
    Button split ;
    Button reset ;
    EditText discount ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amount = findViewById(R.id.editInputAmount);
        numPax = findViewById(R.id.editPaxNum) ;
        totalBill = findViewById(R.id.tvTotalBill) ;
        single_pays = findViewById(R.id.tvSingle_pays) ;
        svs = findViewById(R.id.tbtnSvs) ;
        gst = findViewById(R.id.tbtnGst) ;
        split = findViewById(R.id.split) ;
        reset = findViewById(R.id.reset) ;
        discount = findViewById(R.id.discount_amt) ;


        split.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (amount.getText().toString().length() != 0 && numPax.getText().toString().length() != 0 ){
                    double updatedAmt = 0.0 ;
                    if (!svs.isChecked() && !gst.isChecked()) {
                        updatedAmt = Double.parseDouble(amount.getText().toString());
                    } else if (svs.isChecked() && !gst.isChecked()) {
                        updatedAmt = Double.parseDouble(amount.getText().toString()) * 1.1 ;
                    } else if (!svs.isChecked() && gst.isChecked()) {
                        updatedAmt = Double.parseDouble(amount.getText().toString()) * 1.07 ;

                    } else {
                        updatedAmt = Double.parseDouble(amount.getText().toString()) * 1.17 ;
                    }

                    if (discount.getText().toString().length() !=0) {
                        updatedAmt = 1 - Double.parseDouble(discount.getText().toString()) / 100 ;
                    }

                    totalBill.setText("Total Bill : $" + String.format("%.2f" , updatedAmt));
                    int numPerson = Integer.parseInt(numPax.getText().toString()) ;
                    if (numPerson != 1)
                        single_pays.setText("Each Pays : $" + String.format("%.2f" , updatedAmt / numPerson));
                    else
                        single_pays.setText("Each Pays : $" + updatedAmt);
                }

            }

        });}

}
