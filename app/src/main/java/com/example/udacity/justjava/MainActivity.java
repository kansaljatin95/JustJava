package com.example.udacity.justjava;
import java.text.NumberFormat;
/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
int quantity=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText text=(EditText) findViewById(R.id.name_field);
        String name=text.getText().toString();
        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream=whippedCreamCheckbox.isChecked();
        CheckBox chocolateCreamCheckbox = (CheckBox) findViewById(R.id.chocolate_cream_checkbox);
        boolean hasChocolateCream = chocolateCreamCheckbox.isChecked();
        int price=calculatePrice(hasWhippedCream,hasChocolateCream);
        String printmessage=createOrderSummary(name,price,hasWhippedCream,hasChocolateCream);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT,"Just Java Order Summary for"+name);
        intent.putExtra(Intent.EXTRA_TEXT,printmessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
    public void IncrementMethod(View view)
    {
        if(quantity==100)
        {
            Toast.makeText(this,"You cannot have more than 100 Coffees",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity+1;
        display(quantity);

    }
    public void DecrementMethod(View view)
    {
        if(quantity==1)
        {
            Toast.makeText(this,"You cannot have less than 1 Coffees",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity-1;
        display(quantity);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    private String createOrderSummary(String name,int price,boolean addWhippedCream,boolean addChocolateCream){
        String printmessage = getString(R.string.order_summary_name,name);
        printmessage+="\nAdd Whipped Cream= "+addWhippedCream;
        printmessage+="\nAdd Chocolate Cream= "+addChocolateCream;
        printmessage=printmessage + "\nQuantity:"+quantity;
        printmessage=printmessage +"\nTotal: $" + price;
        printmessage=printmessage+"\n"+getString(R.string.thank_you);
        return (printmessage);

    }
    private int calculatePrice(boolean addWhippedCream,boolean addChocolateCream){
        int baseprice=5;
        if(addWhippedCream)
        {
            baseprice=baseprice+1;
        }
        if(addChocolateCream)
        {
            baseprice+=2;
        }
        return baseprice*quantity;
    }


}