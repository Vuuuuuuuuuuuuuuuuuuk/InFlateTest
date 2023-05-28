package com.evv.java.inflatetest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment2 extends Fragment implements View.OnClickListener{
  EditText text;
  Button toActivity, toFragment2;

  MyListenerToActivityText listenerToActivityText;
  MyListenerToChangeFragment listenerToChangeFragment;

  public Fragment2(MyListenerToActivityText listenerToActivityText, MyListenerToChangeFragment listenerToChangeFragment) {
    this.listenerToActivityText = listenerToActivityText;
    this.listenerToChangeFragment = listenerToChangeFragment;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    return inflater.inflate(R.layout.fragment2_layout, container, false);
  }

  @Override
  public void onStart() {
    super.onStart();

    text = getActivity().findViewById(R.id.editText_Frag2);
    toActivity  = getActivity().findViewById(R.id.button_Frag2_toActivity);
    toFragment2 = getActivity().findViewById(R.id.button_Frag2_toFrag1);

    toActivity.setOnClickListener(this);
    toFragment2.setOnClickListener(this);
  }


  @Override
  public void onClick(View view) {
    switch(view.getId()){
      case R.id.button_Frag2_toActivity:
        listenerToActivityText.sendText(text.getText().toString());
        break;
      case R.id.button_Frag2_toFrag1:
        listenerToChangeFragment.changeFragment();
        break;
    }
  }
}
