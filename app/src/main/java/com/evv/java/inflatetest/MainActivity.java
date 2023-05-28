package com.evv.java.inflatetest;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  private FragmentManager fragmentManager;
  private FragmentTransaction fragmentTransaction;
//
  private Fragment1 fragment1;
  private Fragment2 fragment2;
  private FragmentEmpty fragmentE;

  private int id_fragment = 0;

  TextView tvOut;
  Button btn_fragment1, btn_fragment2, btn_fragmentE;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    tvOut = findViewById(R.id.textViewOut);
    btn_fragment1 = findViewById(R.id.buttonFrag1);
    btn_fragment2 = findViewById(R.id.buttonFrag2);
    btn_fragmentE = findViewById(R.id.buttonClose);

    btn_fragment1.setOnClickListener(this);
    btn_fragment2.setOnClickListener(this);
    btn_fragmentE.setOnClickListener(this);

    MyListenerToChangeFragment myListenerToChangeFragment = new MyListenerToChangeFragment() {
      @Override
      public void changeFragment() {
        if(id_fragment == 1){
          fragmentTransaction = fragmentManager.beginTransaction();
          fragmentTransaction.replace(R.id.PlaceForFragment, fragment2);
          fragmentTransaction.commit();
          id_fragment = 2;
        }else{
          fragmentTransaction = fragmentManager.beginTransaction();
          fragmentTransaction.replace(R.id.PlaceForFragment, fragment1);
          fragmentTransaction.commit();
          id_fragment = 1;
        }
      }
    };

    MyListenerToActivityText myListenerToActivityText = new MyListenerToActivityText() {
      @Override
      public void sendText(String text) {
        tvOut.setText(text);
      }
    };

    fragment1 = new Fragment1(myListenerToActivityText, myListenerToChangeFragment);
    fragment2 = new Fragment2(myListenerToActivityText, myListenerToChangeFragment);
    fragmentE = new FragmentEmpty();

    fragmentManager = getSupportFragmentManager();

    fragmentTransaction = fragmentManager.beginTransaction();
    fragmentTransaction.add(R.id.PlaceForFragment, fragmentE);
    fragmentTransaction.commit();
  }

  @Override
  public void onClick(View view) {
    switch(view.getId()){
      case R.id.buttonFrag1:
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.PlaceForFragment, fragment1);
        fragmentTransaction.commit();
        id_fragment = 1;
        break;
      case R.id.buttonFrag2:
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.PlaceForFragment, fragment2);
        fragmentTransaction.commit();
        id_fragment = 2;
        break;
      case R.id.buttonClose:
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.PlaceForFragment, fragmentE);
        fragmentTransaction.commit();
        id_fragment = 0;
        break;
    }
  }
}